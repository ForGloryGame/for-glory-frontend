(ns xxx.app.views.front
  (:require
   ;; [re-frame.core :as rf]
   ;; [taoensso.timbre :as log]
   [xxx.app.views.front.header :as header]
   [xxx.app.views.front.body :as body]
   [xxx.app.views.front.footer :as footer]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main [_]
  [:div.main
   [header/ui]
   [body/ui]
   [:link {:rel "stylesheet" :href "/fonts/family.css"}]
   [footer/ui]])
