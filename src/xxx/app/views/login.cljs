(ns xxx.app.views.login
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]
   [xxx.config :as conf]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn main [_]
  [:p "login"])
