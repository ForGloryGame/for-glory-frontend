(ns fgl.app.ui.connect-btn
  (:require
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [fgl.wallet.core :as w]
   [oops.core :refer [ocall oget]]
   [reagent.core :as r]
   ;; [element-resize.core :as elr]
   ))

(w/init!)

(rf/reg-sub
 ::text
 :<- [::w/state]
 :<- [::w/wrong-network]
 :<- [::w/addr]
 (fn [[s wrong-network addr] _]
   (cond (= s :installed)
         ["Connect Wallet" "Connect Wallet" false w/connect!]
         wrong-network
         ["Wrong Network" "Wrong Network" false #(rf/dispatch [::w/switch-to-target-chain!])]
         :else
         [(or addr "") "Address" true identity])))

(defn ui [target-chain-id]
  (r/create-class
   {:component-did-mount
    (fn [_]
      (and target-chain-id (w/init! {:target-chain-id target-chain-id})))
    :reagent-render
    (fn []
      (let [[text name disabled on-click] @(rf/subscribe [::text])]
        [:button#connect-btn
         {:disabled disabled
          :name     name
          :on-click on-click}
         text]))}))
