(ns fgl.app.ui.connect-btn
  (:require
   [fgl.app.ui.btn :as btn]
   [fgl.utils :refer [shorten-addr]]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(w/init!)

(rf/reg-sub
 ::text
 :<- [::w/state]
 :<- [::w/wrong-network]
 :<- [::w/addr]
 (fn [[s wrong-network addr] _]
   (cond
     (= s :uninstalled)
     ["Wallet not detected" "Wallet not detected" false #(js/open "https://metamask.io/")]
     (= s :installed)
     ["Connect Wallet" "Connect Wallet" false w/connect!]
     wrong-network
     ["Wrong Network" "Wrong Network" false #(rf/dispatch [::w/switch-to-target-chain!])]
     :else
     [(if addr (shorten-addr addr) "") "Address" false #(rf/dispatch [:navigate :route/dashboard])])))

(defn ui [target-chain-id]
  (r/create-class
   {:component-did-mount
    (fn [_]
      (and target-chain-id (w/init! {:target-chain-id target-chain-id})))
    :reagent-render
    (fn []
      (let [[text name disabled on-click] @(rf/subscribe [::text])]
        (cond (not disabled)
              [btn/ui {:id       "connect-btn"
                       :t        :blg
                       :name     name
                       :on-click on-click}
               [:span text]]
              disabled
              [:span text])))}))
