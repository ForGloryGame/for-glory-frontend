(ns fgl.app.ui.merchant-panel
  (:require
   [lambdaisland.glogi :as log]
   [fgl.app.ui.panel-route :as proute]))

(defn ui [{:keys [css]} body]
  [:div.w-full.h-full.grid.grid-rows-1
   {:style {:gridTemplateColumns "12rem 1fr"
            :height              "90.8%"}}
   (and css [:link {:rel "stylesheet" :href css :type "text/css" :media "screen"}])
   [proute/ui
    ["Mint" :route/merchant-mint]
    ["Land Deeds" :route/merchant-landeeds]
    ["Burn Land Deeds" :route/merchant-burn]
    ["$GLORY for $GOLD" :route/merchant-swap]]
   body])
