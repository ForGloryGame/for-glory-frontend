(ns fgl.contracts.gold
  (:require
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
   (.toString (or (get-in db [addr ::balance]) 0))))

(rf/reg-event-fx
 ::get-balance
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/then (r :balanceOf addr) #(rf/dispatch [::set % addr ::balance]))))
   {}))
