(ns fgl.app.ui.guild-panel
  (:require
   [lambdaisland.glogi :as log]
   [fgl.app.ui.panel-route :as proute]))

(defn ui [{:keys [css]} body]
  [:div.w-full.h-full.grid.grid-rows-1
   {:style {:gridTemplateColumns "12rem 1fr"
            :height              "90.8%"}}
   (and css [:link {:rel "stylesheet" :href css :type "text/css" :media "screen"}])
   [proute/ui
    ["Basic" :route/guild-basic]
    ["Alter" :route/guild-alter]
    ["Vote" :route/guild-vote]
    ["Management" :route/guild-management]
    ["Allocation" :route/guild-allocation]]
   body])
