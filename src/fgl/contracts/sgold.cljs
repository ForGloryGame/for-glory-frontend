(ns fgl.contracts.sgold
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
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
 ::balance
 (fn [db [_ addr]]
   (.toString (get-in db [addr ::balance] 0))))
(rf/reg-sub
 ::locked
 (fn [db [_ addr]]
   (.toString (get-in db [addr ::locked] 0))))
(rf/reg-sub
 ::unlocked
 (fn [db [_ addr]]
   (.toString (get-in db [addr ::unlocked] 0))))

(defonce SECONDS_IN_DAY 86400)
(defonce CYCLE_TIMESTAMP_OFFSET 324000)
(defonce SECONDS_IN_WEEK (* SECONDS_IN_DAY 7))

(defn ->unlock-info [info]
  (map
   (fn [[amount week]]
     (let [date (-> week
                    .toNumber
                    (* SECONDS_IN_WEEK)
                    (+ CYCLE_TIMESTAMP_OFFSET)
                    (* 1000)
                    (js/Date.))]
       {:amount amount :date date}))
   info))

(rf/reg-sub
 ::info
 (fn [db [_ addr]]
   (let [unlockInfos (get-in db [addr ::info] [])]
     (->unlock-info unlockInfos))))

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (and addr
          (ctc/with-provider c provider
            (p/let [balance (r :balanceOf addr)
                    [locked unlocked user-gold-info] (r :getUserGoldInfo addr)]
              (rf/dispatch [::set balance addr ::balance])
              ;; TODO: week to date
              (rf/dispatch [::set locked addr ::locked])
              (rf/dispatch [::set unlocked addr ::unlocked])
              (rf/dispatch [::set user-gold-info addr ::info])))))

   {}))

(ctc/reg-send c ::send)
