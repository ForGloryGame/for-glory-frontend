(ns fgl.contracts.kingdoms
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
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

(rf/reg-sub
 ::kingdoms
 (fn [db _]
   (let [kingdoms (get db ::kingdom {})
         kingdoms
         (map
          (fn [[idx {:keys [elders senators member-count]}]]
            {:name         (nth kingdoms-name idx)
             :member-count member-count
             :elders       elders
             :senators     senators})
          kingdoms)]
     kingdoms)))

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [kingdom-id (r :getAccountInfo addr)
                 kingdom-id (first kingdom-id)
                 has-kingdom? (pos? kingdom-id)
                 elders (and has-kingdom? (r :getElders kingdom-id))
                 senators (and has-kingdom? (r :getSenators kingdom-id))

                 role (and has-kingdom? (cond (some #{addr} elders)   :elders
                                              (some #{addr} senators) :senators
                                              :else                   nil))]
           (when has-kingdom?
             (rf/dispatch [::set kingdom-id addr ::kingdom-id])
             (rf/dispatch [::set (js->clj elders) ::kingdom kingdom-id :elders])
             (rf/dispatch [::set (js->clj senators) ::kingdom kingdom-id ::senators])
             (rf/dispatch [::set role addr ::role]))))))

   {::w/raddrnet ::init-raw}))

(reg-event-pfx
 ::init-all
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [kingdoms (range 1 6)
                 kdoms (map #(r :kingdoms %) kingdoms)
                 elders (map #(r :getElders %) kingdoms)
                 senators (map #(r :getSenators %) kingdoms)
                 membersCount (map #(r :getMemberCount %) kingdoms)

                 promises
                 (->> (concat kdoms elders senators membersCount)
                      p/all)

                 [kdoms elders senators membersCount] (partition (count kingdoms) promises)]

           (doseq [kingdom-id kingdoms]
             (let [[treasury power nonce ;; name
                    ]
                   (nth kdoms (dec kingdom-id))]
               (rf/dispatch [::set treasury  ::kingdom kingdom-id :treasury])
               (rf/dispatch [::set power ::kingdom kingdom-id :power])
               (rf/dispatch [::set nonce ::kingdom kingdom-id :nonce])
               (rf/dispatch [::set (nth kingdoms-name kingdom-id) ::kingdom kingdom-id :name]))
             (rf/dispatch [::set (js->clj (nth elders (dec kingdom-id))) ::kingdom kingdom-id :elders])
             (rf/dispatch [::set (js->clj (nth senators (dec kingdom-id))) ::kingdom kingdom-id :senators])
             (rf/dispatch [::set (nth membersCount (dec kingdom-id)) ::kingdom kingdom-id :member-count]))))))

   {::w/raddrnet ::init-all-raw}))

(ctc/reg-send c ::send)
