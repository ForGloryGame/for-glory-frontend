(ns fgl.app.home-views
  (:require
   [re-frame.core :as rf]
   ;; [breaking-point.core :as bp]
   [fgl.app.routes :as routes]
   [fgl.app.views.home :as home]
   [fgl.app.events :as events]
   [fgl.app.subs :as subs]
   [fgl.config :as config]))

(defn app-panel []
  (let [current-route                      @(rf/subscribe [::routes/current-route])
        {:keys [name view] :as route-data} (get current-route :data {})]
    (case name
      :route/front [view route-data]
      [:div
       (if current-route
         [view route-data]
         [home/main])])))
