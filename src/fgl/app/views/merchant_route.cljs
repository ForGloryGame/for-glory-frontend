(ns fgl.app.views.merchant-route
  (:require
   [taoensso.encore :as enc]
   [lambdaisland.glogi :as log]
   [fgl.app.routes :as routes]
   [re-frame.core :as rf]))

(defn route [text route-key]
  (let [to #(rf/dispatch [:navigate route-key])]
    (fn []
      (let [current-route @(rf/subscribe [::routes/current-route])
            {n :name}     (get current-route :data {})
            active?       (= n route-key)]
        [:li.my-6
         [:button.w-full.h-full
          (enc/assoc-when
           {:className "py-1.5"
            :on-click  to}
           :style
           (and active?
                {:background
                 "
linear-gradient(
    to right,
    transparent 10%,
    rgba(116, 191, 206, 0.6) 60%,
    transparent 100%
  )"}))
          text]]))))

(defn ui []
  [:ul.text-lg.text-center.pt-12.fb
   {:style
    {:background "linear-gradient(to right, transparent, rgb(80, 146, 158, 0.231))"
     :color      "rgb(213, 228, 232)"
     :textShadow "1px 1px 1px rgba(0, 0, 0, 0.64)"}}
   [route "Mint" :route/merchant-mint]
   [route "Land Deeds" :route/merchant-landeeds]
   [route "Burn Land Deeds" :route/merchant-burn]
   [route "$GLORY for $GOLD" :route/merchant-swap]])
