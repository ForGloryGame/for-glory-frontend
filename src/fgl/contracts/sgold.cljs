(ns fgl.contracts.sgold
  (:require
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.sgold.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :sgold :address conf/contract-addr-sgold :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(rf/reg-sub
 ::info
 (fn [db [_ addr]]
   (get-in db [addr ::info])))

(rf/reg-event-fx
 ::get
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/let [[_ _ user-gold-info] (r :getUserGoldInfo addr)]
            ;; TODO: week to date
            (rf/dispatch [::set user-gold-info addr ::info]))))

   {}))
