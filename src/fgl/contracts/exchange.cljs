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
   (let [{::w/keys [provider]} db]
     (rf/dispatch [::set 100 ::ratio])
     ;; (ctc/with-provider c provider
     ;;   (p/then (r :getReserves)
     ;;           (fn [[gold glory]] (rf/dispatch [::set (.div gold glory) ::reserves]))))
     )
   {}))

(ctc/reg-send c ::send)
