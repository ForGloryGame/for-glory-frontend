(ns fgl.app.views.login
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]
   [fgl.config :as conf]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn main [_]
  [:p "login"])
