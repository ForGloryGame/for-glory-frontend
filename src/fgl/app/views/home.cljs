(ns fgl.app.views.home
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]))

(defn controllers []
  [{:start #(info "start" "home controller")
    :stop  #(info "stop" "home controller")}])

(defn main [_]
  [:section "Home page"
   [:p>button {:on-click #(rf/dispatch [:navigate :route/front])} "> Front"]])
