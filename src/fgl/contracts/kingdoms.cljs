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
   (get-in db [addr ::kingdom-id])))

(rf/reg-event-fx
 ::get
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/let [kingdom-id (first (r :getAccountInfo addr))
                  _ (rf/dispatch [::set kingdom-id addr ::kingdom-id])
                  elders (r :getElders kingdom-id)
                  ;; TODO: check return value
                  _ (rf/dispatch [::set (log/spy elders) addr ::elders])
                  senators (r :getSenators kingdom-id)
                  _ (rf/dispatch [::set senators addr ::senators])
                  role (cond (some #{addr} elders)   :elders
                             (some #{addr} senators) :senators
                             :else                   nil)]
            (rf/dispatch [::set role addr ::role]))))

   {}))
