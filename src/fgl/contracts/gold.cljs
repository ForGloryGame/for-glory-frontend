(ns fgl.contracts.gold
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   ["ethers" :as ethers]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.erc20.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :gold :address conf/contract-addr-gold :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(rf/reg-sub
 ::balance
 (fn [db [_ addr]]
   (-> (or (get-in db [addr ::balance]) (ethers/BigNumber.from 0))
       (ethers/utils.formatUnits 18)
       ethers/utils.commify)))

(rf/reg-event-fx
 ::allowance
 (fn [{:keys [db]} [_ spender]]
   (let [{::w/keys [addr provider]} db]
     (when addr
       (ctc/with-provider c provider
         (p/then (r :allowance addr spender) #(rf/dispatch [::set % addr ::allowance spender])))))
   {}))

(reg-event-pfx
 ::init
 10000
 (fn [{:keys [db]} _]
   (let [{::w/keys [addr provider]} db]
     (when addr
       (ctc/with-provider c provider
         (p/then (r :balanceOf addr) #(rf/dispatch [::set % addr ::balance])))))
   {}))

(ctc/reg-send c ::send)
