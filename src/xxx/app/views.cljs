(ns xxx.app.views
  (:require
   ;; [girouette.core :refer [css]]
   [re-frame.core :as rf]
   [breaking-point.core :as bp]
   [xxx.app.routes :as routes]
   [xxx.app.events :as events]
   [xxx.app.subs :as subs]
   [xxx.config :as config]))

(defn main-panel []
  (let [current-route                      @(rf/subscribe [::routes/current-route])
        {:keys [name view] :as route-data} (get current-route :data {})]
    (case name
      :route/front [view route-data]
      [:div
       [:h1
        "Current route " [:code name] ". Site Version " config/version]
       [:div
        [:h3 (str "screen-width: " @(rf/subscribe [::bp/screen-width]))]
        [:h3 (str "screen: " @(rf/subscribe [::bp/screen]))]]
       [:div
        (if current-route
          [view route-data]
          [:p "Loading..."])]])))
