(ns xxx.app.views.front
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]))

(defn controllers []
  [{:start #(info "start" "front controller")
    :stop  #(info "stop" "front controller")}])

(defn main [_]
  [:section "Front page"
   [:p>button {:on-click #(rf/dispatch [:navigate :route/login])} "> Login"]
   [:p>button {:on-click #(rf/dispatch [:navigate :route/home])} "> Home"]
   [:p>button {:on-click #(rf/dispatch [:navigate :route/about])} "> About"]
   [:p>button {:on-click #(rf/dispatch [:navigate :route/profile {:id :cz}])} "> Profile"]])
