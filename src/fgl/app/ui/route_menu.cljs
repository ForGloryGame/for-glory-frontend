(ns fgl.app.ui.route-menu
  (:require
   [fgl.app.routes :as routes]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]))

(defn style []
  [:style
   ".guild-outer-menu {
        background-image: linear-gradient(
            to bottom,
            rgba(40, 84, 95),
            rgba(40, 84, 95, 0.5) 60%,
            transparent 80%
          ),
          linear-gradient(to bottom, #1a2a31, #1a2a31 60%, #1a2a3130);
      }

      .guild-outer-menu li>button {
        position: relative;
        width: 100%;
        margin-top: 2rem;
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .guild-outer-menu .guild-icon {
        position: relative;
        display: inline-block;
        width: 1.3rem;
        height: 1.3rem;
        background-position: center;
        background-repeat: no-repeat;
        background-size: contain;
        cursor: pointer;
        z-index: 1;
      }

      .guild-outer-menu .guild-bg-light {
        visibility: hidden;
        position: absolute;
        display: inline-block;
        width: 3rem;
        height: 3rem;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-image: radial-gradient(
          #55e6ff,
          #55e6ff90 20%,
          #55e6ff10 50%,
          transparent 60%,
          transparent
        );
        background-size: 100%;
        z-index: 0;
      }
      .guild-outer-menu li>button.active .guild-bg-light {
        visibility: visible;
      }"])

(rf/reg-event-db
 ::hover
 (fn [db [_ k]] (assoc db ::hover k)))

(rf/reg-sub
 ::data
 (fn [db _]
   (get db ::hover nil)))

(defn item [route-name route-str]
  (let [hover?        (= route-name @(rf/subscribe [::data]))
        current-route @(rf/subscribe [::routes/current-route])
        {n :name}     (get current-route :data {})
        active?       (and n (.startsWith (name n) route-str))]
    ^{:key route-str}
    [:li>button
     {:className      (if active? "active" "")
      :on-mouse-enter #(rf/dispatch [::hover route-name])
      :on-mouse-leave #(rf/dispatch [::hover])
      :on-click       #(rf/dispatch [:navigate route-name])}
     [:span.guild-icon
      {:style
       {:backgroundImage
        (if (or hover? active?)
          (str "url(/images/menu-tab-" route-str "-active.svg)")
          (str "url(/images/menu-tab-" route-str ".svg)"))}}]
     [:span.guild-bg-light]]))

(defn ui []
  [:<>
   [style]
   [:ul.guild-outer-menu.w-10.mr-3.pt-2.flex.flex-col.items-center
    [item :route/guild-basic "guild"]
    [item :route/battlefield "battlefield"]
    [item :route/council "council"]
    [item :route/merchant-mint "merchant"]
    [item :route/rank "rank"]]])
