(ns xxx.app.ui.connect-btn
  (:require
   [re-frame.core :as rf]
   [xxx.wallet.core :as w]))

(w/init!)

(defn ui []
  (let [state    @(rf/subscribe [::w/state])
        text     (case state
                   :installed "Connect"
                   :connected (first @(rf/subscribe [::w/addrs]))
                   "Wallet not installed")
        disabled (case state
                   :installed false
                   true)]
    [:button {:disabled disabled
              :name     "Connect wallet"
              :on-click w/connect!}
     text]))
