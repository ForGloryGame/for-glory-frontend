(ns fgl.app.ui.panel-route
  (:require
   [lambdaisland.glogi :as log]
   [fgl.app.routes :as routes]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::hover
 (fn [db [_ route]]
   (assoc db ::hover route)))

(rf/reg-sub
 ::hover
 (fn [db _]
   (get db ::hover nil)))

(defn style []
  [:style
   "button.menu-active::after {
        content: \"\";
        position: absolute;
        width: 0;
        height: 0;
        border: 0.5rem solid transparent;
        border-left: 0.5rem solid #25606a;
        left: 100%;
        top: 50%;
        transform: translateY(-50%);
      }"])

(defn route [text route-key]
  (let [to #(rf/dispatch [:navigate route-key])]
    (fn []
      (let [current-route @(rf/subscribe [::routes/current-route])
            hovered       @(rf/subscribe [::hover])
            {n :name}     (get current-route :data {})
            active?       (.startsWith (name n) (name route-key))
            hover?        (= hovered route-key)]
        ^{:key text}
        [:li>button.relative.w-full.py-1_2rem
         {:onMouseEnter #(rf/dispatch [::hover route-key])
          :onMouseLeave #(rf/dispatch [::hover nil])
          :on-click     to
          :className    (if active? "menu-active" "")
          :style
          (if (or hover? active?)
            {:background "radial-gradient(farthest-corner ellipse at top center, #3c8895, #1f545d)"
             :color "white"}
            {})} text]))))

(defn ui [& routes]
  [into
   [:ul.text-lg.text-center.fb.ml-2
    {:style
     {:background "linear-gradient(to right, transparent, rgb(80, 146, 158, 0.231))"
      :color      "rgb(158, 169, 172)"
      :textShadow "1px 1px 1px rgba(0, 0, 0, 0.64)"}}
    [style]]
   (map (fn [[name route-name]]
          [route name route-name])
        routes)])
