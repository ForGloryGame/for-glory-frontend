(ns fgl.contracts.landeed
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.landeed.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :landeed :address conf/contract-addr-landeed :abi abi/data))

(defonce DeedType {0 :standard 1 :medium 2 :large 3 :mega})

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(rf/reg-sub
 ::balance
 (fn [db [_ addr]]
   (.toString (get-in db [addr ::balance] 0))))

(rf/reg-sub
 ::token-ids
 (fn [db [_ addr]]
   (get-in db [addr ::token-ids])))

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [balance (r :balanceOf addr)
                 token-ids (r :tokensOfOwner addr)
                 deedsInfo (p/all (map #(r :deedsInfo %) (keys DeedType)))]
           (map-indexed
            (fn  [idx [price cap minted]]
              (rf/dispatch [::set price ::info idx :price])
              (rf/dispatch [::set cap ::info idx :cap])
              (rf/dispatch [::set minted ::info idx :minted]))
            deedsInfo)
           (rf/dispatch [::set token-ids addr ::token-ids])
           (rf/dispatch [::set balance addr ::balance])))))

   {}))

(ctc/reg-send c ::send)
