(ns fgl.app.views.merchant-landeeds
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.contracts.gold :as gold]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::gold/init]))
    :stop identity}])

(rf/reg-event-db ::payment (fn [db [_ p]] (assoc db ::payment p)))
(rf/reg-event-db ::type (fn [db [_ t]] (assoc db ::type t)))

(rf/reg-sub
 ::data
 (fn [db _]
   {:type    (get db ::type 3)
    :payment (get db ::payment :gold)}))

(defn main []
  (let [{:keys [type payment]} @(rf/subscribe [::data])]
    [:div "land shop"]))
