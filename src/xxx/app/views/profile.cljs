(ns xxx.app.views.profile
  (:require
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]))

(defn controllers []
  [{:start #(info "start" "profile controller")
    :stop  #(info "stop" "profile controller")}])

(defn main [{:keys [path]}]
  (info "profile route path: " path)
  [:section "Profile page"
   [:p>button {:on-click #(rf/dispatch [:navigate :route/front])} "> Front"]])
