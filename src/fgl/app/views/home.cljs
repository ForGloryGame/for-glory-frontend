(ns fgl.app.views.home
  (:require
   [fgl.app.ui.header :as header]
   [re-frame.core :as rf]
   [taoensso.timbre :refer [info]]))

(defn controllers []
  [{:start #(info "start" "home controller")
    :stop  #(info "stop" "home controller")}])

(defn main [_]
  [:div.grid
   {:style {:gridTemplate "\"header\" \"main\""}}
   [header/ui]
   [:main.grid-area-main.grid
    [:section "Home page"
     [:p>button {:on-click #(rf/dispatch [:navigate :route/front])} "> Front"]]]])
