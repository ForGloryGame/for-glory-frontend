(ns fgl.app.views.about
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]))

(defn controllers []
  [{:start #(info "start" "about controller")
    :stop  #(info "stop" "about controller")}])

(defn main [_]
  [:section "About page"
   [:p>button {:on-click #(rf/dispatch [:navigate :route/front])} "> Front"]])
