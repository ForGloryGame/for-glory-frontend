(ns fgl.app.views
  (:require
   [fgl.app.ui.header :as header]
   [fgl.app.ui.body :as body]
   [re-frame.core :as rf]
   [fgl.app.routes :as routes]
   [fgl.app.events :as events]
   [fgl.app.subs :as subs]
   [fgl.config :as config]))

(defn main-panel []
  (let [current-route                 @(rf/subscribe [::routes/current-route])
        {:keys [view] :as route-data} (get current-route :data {})]
    [:div.grid
     {:style {:gridTemplate "\"header\" \"main\""}}
     [header/ui]
     [body/ui (if view [view route-data] [:div])]]))
