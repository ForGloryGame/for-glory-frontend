(ns fgl.app.views.home
  (:require
   [fgl.app.ui.header :as header]
   [re-frame.core :as rf]
   [lambdaisland.glogi :as log]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main [_]
  [:div "home"])
