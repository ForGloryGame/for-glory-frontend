(ns fgl.app.ui.guild-panel
  (:require
   [lambdaisland.glogi :as log]
   [fgl.app.views.guild-route :as groute]))

(defn ui [{:keys [css]} body]
  [:div.w-full.h-full.grid.grid-rows-1
   {:style {:gridTemplateColumns "12rem 1fr"
            :height              "90.8%"}}
   (and css [:link {:rel "stylesheet" :href css :type "text/css" :media "screen"}])
   [groute/ui]
   body])
