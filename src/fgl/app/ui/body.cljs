(ns fgl.app.ui.body
  (:require
   [lambdaisland.glogi :as log]
   [taoensso.encore :as enc]
   [re-frame.core :as rf]
   [fgl.app.ui.panel :as panel]
   [fgl.app.ui.guild-panel :as gpanel]
   [fgl.app.views.guild-route :as groute]
   [fgl.app.ui.toast :as toast]))

(defn- to-home [] (rf/dispatch [:navigate :route/home]))

(defn wrapper [children]
  [:div.cs2.ce3 {:style {:minWidth "64vw"}} children])

(defn ui [route-data children]
  (let [{:keys [panel-name] n :name} route-data

        guild? (-> n (or :_) name (.startsWith "guild"))

        guild-wrapper
        (if guild?
          #(vector gpanel/ui route-data %)
          identity)

        panel-wrapper
        (if panel-name
          #(vector panel/ui route-data panel-name 80 to-home %)
          identity)

        body [wrapper [panel-wrapper [guild-wrapper children]]]]
    [:main.grid-area-main.grid.justify-strech
     {:style {:gridTemplateColumns "1fr auto 1fr"
              :justifyContent      "strech"}}
     body
     [toast/ui]]))
