(ns fgl.contracts.exchange
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   ["ethers" :as ethers]
   [lambdaisland.glogi :as log]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.exchange.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :exchange :address conf/contract-addr-exchange :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(reg-event-pfx
 ::init
 10000
 (fn [{:keys [db]} _]
   (let [{::w/keys [addr provider]} db]
     (when addr
       (ctc/with-provider c provider
         (p/then (r :getReserves)
                 (fn [[gold glory]]
                   (rf/dispatch [::set gold ::rgold])
                   (rf/dispatch [::set glory ::rglory])
                   (rf/dispatch [::set (.div glory gold) ::ratio]))))))
   {::w/raddrnet ::init-raw}))

(ctc/reg-send c ::send)
