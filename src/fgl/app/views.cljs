(ns fgl.app.views
  (:require
   [lambdaisland.glogi :as log]
   [fgl.app.ui.header :as header]
   [fgl.app.ui.body :as body]
   [re-frame.core :as rf]
   [fgl.app.routes :as routes]
   [fgl.app.events :as events]
   [fgl.app.subs :as subs]
   [fgl.app.ui.map :as uimap]
   [fgl.config :as config]))

(defn main-panel []
  (let [current-route                 @(rf/subscribe [::routes/current-route])
        {:keys [view] :as route-data} (get current-route :data {})]
    [:div.grid.h-100vh.bg-cover.bg-no-repeat.bg-center
     {:style {:gridTemplate    "\"header\" min-content \"main\""
              ;; :backgroundImage "url(\"/images/bg@2x.png\""
              }}
     [header/ui]
     [uimap/ui]
     [body/ui route-data (if view [view route-data] [:div])]
     [:link {:rel "stylesheet" :href "/fonts/family.css"}]]))
