(ns fgl.app.views.dashboard
  (:require
   [re-frame.core :as rf]))

(defn main [_]
  [:div
   [:p "Home"]
   [:button {:on-click #(rf/dispatch [:navigate :route/mint])} "-> mint"]])
