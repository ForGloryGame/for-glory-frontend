(ns xxx.app.views.login
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]
   [xxx.wallet :as wallet]
   [xxx.config :as conf]))

(defn controllers []
  [{:start wallet/init!
    :stop  #(info "stop" "login controller")}])

(defn main [_]
  (let [installed (wallet/installed?)
        connected (wallet/sconnected?)
        txt       (cond connected "Connected"
                        installed "Connect Wallet"
                        :else     "Wallet not installed.")

        on-click
        (when-not connected
          #(do (.preventDefault %)
               (wallet/connect
                :failed (fn [] (js/alert "Failed to connect the wallet")))))]
    [:section
     [:form
      [:h1>label "Login"]
      [:button
       {:on-click on-click
        :disabled (or connected (not installed))}
       txt]]]))
