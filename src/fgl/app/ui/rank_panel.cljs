(ns fgl.app.ui.rank-panel
  (:require
   [fgl.app.ui.panel-route :as proute]
   [lambdaisland.glogi :as log]))

(defn ui [{:keys [css]} body]
  [:div.w-full.h-full.grid.grid-rows-1
   {:style {:gridTemplateColumns "12rem 1fr"
            :height              "90.8%"}}
   (and css [:link {:rel "stylesheet" :href css :type "text/css" :media "screen"}])
   [proute/ui
    ["Personal" :route/rank-personal]
    ["Kingdom" :route/rank-kingdom]]
   body])
