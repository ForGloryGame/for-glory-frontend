(ns fgl.contracts.battlefield
  (:require
   ["ethers" :as ethers]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.battlefield.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :battlefield :address conf/contract-addr-battlefield :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (tap> (assoc-in db paths v))
   (assoc-in db paths v)))

(defonce token-ids-cache (atom nil))

(rf/reg-sub
 ::token-ids
 (fn [db [_ addr]]
   (if @token-ids-cache @token-ids-cache
       (reset! token-ids-cache (get-in db [addr ::token-ids])))))

(rf/reg-sub
 ::reward
 (fn [db [_ addr token-id type]]
   (and token-id
        (-> (get-in db [addr ::reward (.toString token-id) type] (ethers/BigNumber.from 0))
            (ethers/utils.formatUnits 18)
            ethers/utils.commify))))

(rf/reg-event-fx
 ::get
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/let [token-ids (r :depositsOf addr)
                  rewards (p/all (map #(r :pendingReward %) token-ids))]
            (loop [ids token-ids
                   rs  rewards]
              (when-let [id (first ids)]
                (rf/dispatch [::set (first (first rs)) addr ::reward (-> id .toString) :gold])
                (rf/dispatch [::set (second (first rs)) addr ::reward (-> id .toString) :glory])
                (recur (rest ids) (rest rs))))
            (rf/dispatch [::set token-ids addr ::token-ids]))))

   {}))

;; join
;; commitUnstake
;; revealUnstake
(rf/reg-event-fx
 ::send
 (fn [_ [_ provider method & params]]
   ;; TODO: handle write contract result
   (ctc/with-provider c provider
     (apply r method params))
   {}))
