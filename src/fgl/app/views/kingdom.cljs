(ns fgl.app.views.kingdom
  (:require
   [fgl.app.ui.kingdom-map :as uikmap]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn main []
  [uikmap/ui true])
