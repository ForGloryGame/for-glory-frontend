(ns fgl.app.ui.merchant-panel
  (:require
   [fgl.app.ui.panel-route :as proute]
   [lambdaisland.glogi :as log]))

(defn ui [{:keys [css]} body]
  [:div.w-full.h-full.grid.grid-rows-1
   {:style {:gridTemplateColumns "12rem 1fr"
            :height              "90.8%"}}
   (and css [:link {:rel "stylesheet" :href css :type "text/css" :media "screen"}])
   [proute/ui
    ["Mint" :route/merchant-mint]
    ["Land Deeds" :route/merchant-landeeds]
    ["Burn Land Deeds" :route/merchant-burn]
    ["$Virtue for $Glory" :route/merchant-swap]]
   body])
