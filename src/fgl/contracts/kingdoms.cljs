(ns fgl.contracts.kingdoms
  (:require
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.kingdoms.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :kingdoms :address conf/contract-addr-kingdoms :abi abi/data))

(defonce kingdoms-name
  ["None"
   "Dragon"
   "Phoenix"
   "Lion"
   "Griffin"
   "Fenrir"])

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(rf/reg-sub
 ::role
 (fn [db [_ addr]]
   (get-in db [addr ::role])))

(rf/reg-sub
 ::kingdom-id
 (fn [db [_ addr]]
   (nth kingdoms-name (get-in db [addr ::kingdom-id] 0))))

;; avoid rerender
(def kingdoms-cache (atom []))

(rf/reg-sub
 ::kingdoms
 (fn [db _]
   (if @kingdoms-cache
     @kingdoms-cache
     (let [kingdoms (get db ::kingdom {})
           kingdoms
           (map
            (fn [[idx {:keys [elders senators member-count]}]]
              {:name         (nth kingdoms-name (inc idx))
               :member-count member-count
               :elders       elders
               :senators     senators})
            kingdoms)]
       (reset! kingdoms-cache kingdoms)))))

(rf/reg-event-fx
 ::get
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/let [kingdom-id (r :getAccountInfo addr)
                  kingdom-id (first kingdom-id)
                  _ (rf/dispatch [::set kingdom-id addr ::kingdom-id])

                  elders (r :getElders kingdom-id)
                  _ (rf/dispatch [::set elders ::kingdom kingdom-id :elders])

                  senators (r :getSenators kingdom-id)
                  _ (rf/dispatch [::set senators ::kingdom kingdom-id ::senators])

                  role (cond (some #{addr} elders)   :elders
                             (some #{addr} senators) :senators
                             :else                   nil)
                  _ (rf/dispatch [::set role addr ::role])])))

   {}))

(rf/reg-event-fx
 ::get-all
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/let [kingdoms (range 1 6)
                  elders (map #(r :getElders %) kingdoms)
                  senators (map #(r :getSenators %) kingdoms)
                  membersCount (map #(r :getMemberCount %) kingdoms)

                  [elders senators membersCount]
                  (->> (concat elders senators membersCount)
                       p/all
                       (partition (count kingdoms)))]

            (doseq [kingdom-id kingdoms]
              (rf/dispatch [::set (nth elders kingdom-id) ::kingdom kingdom-id :elders])
              (rf/dispatch [::set (nth senators kingdom-id) ::kingdom kingdom-id :senators])
              (rf/dispatch [::set (nth membersCount kingdom-id) ::kingdom kingdom-id :member-count])))))

   {}))

(rf/reg-event-fx
 ::send
 (fn [_ [_ provider method & params]]
   ;; TODO: handle write contract result
   (ctc/with-provider c provider
     (apply r method params))
   {}))
