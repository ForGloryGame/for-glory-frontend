(ns fgl.app.views.merchant-burn
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.gold-img :as goldimg]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [fgl.contracts.landeed :as landeed]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       ;; get staked info
       (rf/dispatch [::landeed/init]))
    :stop identity}])

(rf/reg-event-db ::payment (fn [db [_ p]] (assoc db ::payment p)))
(rf/reg-event-db ::token-id (fn [db [_ p]] (assoc db ::token-id p)))

(rf/reg-sub
 ::data
 (fn [db _]
   {:payment  (get db ::payment :gold)
    :token-id (get db ::token-id)}))

(defn main []
  (let [{:keys [payment token-id]} @(rf/subscribe [::data])]
    [:div "my land deeds"]))
