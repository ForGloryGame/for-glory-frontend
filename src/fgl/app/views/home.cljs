(ns fgl.app.views.home
  (:require
   [re-frame.core :as rf]
   [lambdaisland.glogi :as log]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main [_]
  [:div
   [:p "Home"]
   [:button {:on-click #(rf/dispatch [:navigate :route/dashboard])} "-> dashboard"]
   [:br]
   [:button {:on-click #(rf/dispatch [:navigate :route/battlefield])} "-> battlefield"]
   [:br]
   [:button {:on-click #(rf/dispatch [:navigate :route/council])} "-> council"]
   [:br]
   [:button {:on-click #(rf/dispatch [:navigate :route/guild-basic])} "-> guild"]
   [:br]
   [:button {:on-click #(rf/dispatch [:navigate :route/merchant-mint])} "-> merchant"]
   [:br]
   [:button {:on-click #(rf/dispatch [:navigate :route/mint])} "-> mint"]])
