(ns fgl.app.views
  (:require
   [fgl.app.events :as events]
   [fgl.app.routes :as routes]
   [fgl.app.subs :as subs]
   [fgl.app.ui.body :as body]
   [fgl.app.ui.header :as header]
   [fgl.app.ui.map :as uimap]
   [fgl.wallet.core :as w]
   [fgl.config :as config]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]))

(defn main-panel []
  (let [connected?                                (= @(rf/subscribe [::w/state]) :connected)
        wrong-network?                            @(rf/subscribe [::w/wrong-network])
        current-route                             @(rf/subscribe [::routes/current-route])
        {:keys [view] rname :name :as route-data} (get current-route :data {})]
    (cond (and (not connected?) rname (not (= (log/spy rname) :route/home)))
          (do
            (rf/dispatch [:navigate :route/home])
            (w/connect!)
            [:div])
          (and connected? wrong-network? rname (not (= (log/spy rname) :route/home)))
          (do
            (rf/dispatch [:navigate :route/home])
            (rf/dispatch [::w/switch-to-target-chain!])
            [:div])
          :else
          [:div.grid.h-100vh.bg-cover.bg-no-repeat.bg-center
           {:style {:gridTemplate "\"header\" min-content \"main\""
                    ;; :backgroundImage "url(\"/images/bg@2x.png\""
                    }}
           [header/ui]
           [uimap/ui]
           [body/ui route-data (if view [view route-data] [:div])]
           [:link {:rel "stylesheet" :href "/fonts/family.css"}]])))
