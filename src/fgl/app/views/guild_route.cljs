(ns fgl.app.views.guild-route
  (:require
   [taoensso.encore :as enc]
   [lambdaisland.glogi :as log]
   [fgl.app.routes :as routes]
   [re-frame.core :as rf]))

(defn route [text route-key]
  (let [to #(rf/dispatch [:navigate route-key])]
    (fn []
      (let [current-route @(rf/subscribe [::routes/current-route])
            {n :name}     (get current-route :data {})]
        [:li.my-6
         [:button.w-full.h-full
          {:className
           (str
            "py-1.5 "
            (if (= n route-key) "active" ""))
           :on-click to}
          text]]))))

(defn ui []
  [:ul.guild-menu.w-48.h-full.text-lg.guild-font-family.text-center.pt-12
   [route "Basic" :route/guild-basic]
   [route "Alter" :route/guild-alter]
   [route "Vote" :route/guild-vote]
   [route "Management" :route/guild-management]
   [route "Allocation" :route/guild-allocation]])
