(ns fgl.contracts.battlefield
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   ["ethers" :as ethers]
   [fgl.contracts.gamenft :as nft]
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
        (get-in db [addr ::reward (.toString token-id) type] (ethers/BigNumber.from 0)))))

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [token-ids (r :depositsOf addr)
                 rewards (p/all (map #(r :pendingReward %) token-ids))]
           (loop [ids token-ids
                  rs  rewards]
             (when-let [id (first ids)]
               (rf/dispatch [::nft/get-token-traits id])
               (rf/dispatch [::set (first (first rs)) addr ::reward (-> id .toString) :gold])
               (rf/dispatch [::set (second (first rs)) addr ::reward (-> id .toString) :glory])
               (recur (rest ids) (rest rs))))
           (rf/dispatch [::set token-ids addr ::token-ids])))))

   {}))

;; join
;; commitUnstake
;; revealUnstake
(rf/reg-event-fx
 ::send
 (fn [{:keys [db]} [_ method & params]]
   (let [{::w/keys [provider]} db]
     (ctc/with-provider c provider
       (apply r method params)))
   {}))
