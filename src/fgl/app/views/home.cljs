(ns fgl.app.views.home
  (:require
   [fgl.app.views.start :as start]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main [_]
  [start/main])

;; (defn main [_]
;;   [:div
;;    [:p "Home"]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/dashboard])} "-> dashboard"]
;;    [:br]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/battlefield])} "-> battlefield"]
;;    [:br]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/council])} "-> council"]
;;    [:br]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/guild-basic])} "-> guild"]
;;    [:br]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/merchant-mint])} "-> merchant"]
;;    [:br]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/the-map])} "-> map"]
;;    [:br]
;;    [:button {:on-click #(rf/dispatch [:navigate :route/mint])} "-> mint"]])
