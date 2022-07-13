(ns fgl.app.views.map
  (:require
   [fgl.app.ui.animation :refer [anim!]]
   [fgl.app.ui.map :as uimap]))

(defn controllers []
  [{:start #(do (anim! :map-in true)
                (anim! :map-out false))
    :stop  #(do (anim! :map-out true)
                (anim! :map-in false))}])

(defn main [_]
  [uimap/ui true])
