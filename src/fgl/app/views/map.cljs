(ns fgl.app.views.map
  (:require
   [fgl.app.ui.map :as uimap]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main [_]
  [uimap/ui true])
