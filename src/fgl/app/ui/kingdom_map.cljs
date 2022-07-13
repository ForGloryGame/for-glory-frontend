(ns fgl.app.ui.kingdom-map
  (:require
   [fgl.app.ui.animation :refer [anim! anim?]]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn t [text]
  [:div.absolute.w-full.bottom-20%.flexr
   {:style {:fontSize   "1.5rem"
            :background "linear-gradient(to left, transparent, #0000007a, transparent)"}}
   [:img {:src   "/images/square.svg"
          :style {:transform "scale(0.4)"}}]
   [:span.block {:style {:margin "0 5% 0 5%"}} text]
   [:img {:src   "/images/square.svg"
          :style {:transform "scale(0.4)"}}]])

(rf/reg-event-db
 ::img-loaded
 (fn [db _]
   (if (some? (::img-loaded db))
     (update db ::img-loaded inc)
     (assoc db ::img-loaded 1))))

(rf/reg-sub
 ::img-loaded
 (fn [db]
   (>= (get db ::img-loaded) 6)))

(defn ui
  ([] (ui false))
  ([fg?]
   (let [hover     (r/atom nil)
         fg-reset! (fn [v] (and fg? (reset! hover v)))]
     (r/create-class
      {:reagent-render
       (fn []
         (let [basic?         (= @hover :basic)
               management?    (= @hover :management)
               vote?          (= @hover :vote)
               allocation?    (= @hover :allocation)
               alter?         (= @hover :alter)
               back?          (= @hover :back)
               out?           @(anim? ::out)
               enter-kingdom? @(anim? :map->kingdom)
               img-loaded?    @(rf/subscribe [::img-loaded])]
           (when enter-kingdom? (anim! :map->kingdom false))
           [:div.fixed.top-50%.left-50%.overflow-clip.fi
            {:style {:minWidth  "100vw"
                     :minHeight "calc(100vw * 0.5625)"
                     :transform "translate(-50%,-50%) scale(1.12)"
                     :zIndex    (if fg? "unset" -10)}}
            [:img.absolute.top-0.left-0.overflow-auto
             {:src   "/images/kingdom-bg.png"
              :style {:width  "100vw"
                      :height "calc(100vw * 0.5625)"}}]
            [:img.absolute
             {:src   "/images/kingdom-map-btm-shadow.png"
              :style {:width "100vw"
                      :top   "calc(100vw * 0.5625 * 2275 / 2880)"}}]
            [:div.absolute.top-0.left-0.overflow-auto
             {:style {:background "radial-gradient(circle, rgba(255,255,255,0) 0%, rgba(0,0,0,0.2) 95%)"
                      :width      "100vw"
                      :height     "calc(100vw * 0.5625)"}}]

            ;; basic
            [:button.absolute
             {:className    (if img-loaded? "visible" "hidden")
              :on-click     #(rf/dispatch [:navigate :route/guild-basic])
              :onMouseEnter #(fg-reset! :basic)
              :onMouseLeave #(fg-reset! nil)
              :style        {:left "calc(100vw * 3016 / 5120)"
                             :top  "calc(100vw * 0.5625 * 20 / 2880)"}}
             [:img
              {:onLoad #(rf/dispatch [::img-loaded])
               :src    "/images/kingdom-basic.png"
               :style  {:width      "calc(100vw * 367 / 5120)"
                        :visibility (if basic? "hidden" "visible")}}]
             [:img.absolute
              {:src   "/images/kingdom-basic-hover.png"
               :style {:width      "calc(100vw * 471 / 5120)"
                       :top        "calc(100vw * 0.5625 * 120 / 2880)"
                       :visibility (if basic? "visible" "hidden")
                       :transform  "scale(1.3) translate(-1%,-1%)"}}]
             (and fg? [t "Basic"])]

            ;; management
            [:button.absolute
             {:className    (if img-loaded? "visible" "hidden")
              :on-click     #(rf/dispatch [:navigate :route/guild-management])
              :onMouseEnter #(fg-reset! :management)
              :onMouseLeave #(fg-reset! nil)
              :style        {:width "calc(100vw * 580 / 5120)"
                             :left  "calc(100vw * 2644 / 5120)"
                             :top   "calc(100vw * 0.5625 * 745 / 2880)"}}
             [:img
              {:onLoad #(rf/dispatch [::img-loaded])
               :src    "/images/kingdom-management.png"
               :style  {:width      "calc(100vw * 580 / 5120)"
                        :visibility (if management? "hidden" "visible")}}]
             [:img.absolute.top-0
              {:className "hover:opacity-100"
               :src       "/images/kingdom-management-hover.png"
               :style     {:width      "calc(100vw * 580 / 5120)"
                           :visibility (if management? "visible" "hidden")
                           :transform  "scale(1.2) translate(-1%,1%)"}}]
             (and fg? [t "Management"])]

            ;; vote
            [:button.absolute
             {:className    (if img-loaded? "visible" "hidden")
              :on-click     #(rf/dispatch [:navigate :route/guild-vote])
              :onMouseEnter #(fg-reset! :vote)
              :onMouseLeave #(fg-reset! nil)
              :style        {:left "calc(100vw * 1611 / 5120)"
                             :top  "calc(100vw * 0.5625 * 705 / 2880)"}}
             [:img
              {:onLoad #(rf/dispatch [::img-loaded])
               :src    "/images/kingdom-vote.png"
               :style  {:width      "calc(100vw * 794 / 5120)"
                        :visibility (if vote? "hidden" "visible")
                        :transform  "translate(2%,1%)"}}]
             [:img.absolute..top-0
              {:src   "/images/kingdom-vote-hover.png"
               :style {:width      "calc(100vw * 794 / 5120)"
                       :visibility (if vote? "visible" "hidden")
                       :transform  "scale(1.1)"}}]
             (and fg? [t "Vote"])]

            ;; allocation
            [:button.absolute
             {:className    (if img-loaded? "visible" "hidden")
              :on-click     #(rf/dispatch [:navigate :route/guild-allocation])
              :onMouseEnter #(fg-reset! :allocation)
              :onMouseLeave #(fg-reset! nil)
              :style        {:left "calc(100vw * 622 / 5120)"
                             :top  "calc(100vw * 0.5625 * 1775 / 2880)"}}
             [:img
              {:onLoad     #(rf/dispatch [::img-loaded])
               :src        "/images/kingdom-allocation.png"
               :style      {:width "calc(100vw * 1184 / 5120)"}
               :visibility (if allocation? "hidden" "visible")}]
             [:img.absolute.top-0
              {:src   "/images/kingdom-allocation-hover.png"
               :style {:width      "calc(100vw * 1184 / 5120)"
                       :visibility (if allocation? "visible" "hidden")
                       :transform  "scale(1.1) translate(-1%,-1%)"}}]
             (and fg? [t "Allocation"])]

            ;; alter
            [:button.absolute
             {:className    (if img-loaded? "visible" "hidden")
              :on-click     #(rf/dispatch [:navigate :route/guild-alter])
              :onMouseEnter #(fg-reset! :alter)
              :onMouseLeave #(fg-reset! nil)
              :style        {:width "calc(100vw * 1305 / 5120)"
                             :left  "calc(100vw * 3598 / 5120)"
                             :top   "calc(100vw * 0.5625 * 1294 / 2880)"}}
             [:img
              {:onLoad     #(rf/dispatch [::img-loaded])
               :src        "/images/kingdom-alter.png"
               :style      {:width "calc(100vw * 1305 / 5120)"}
               :visibility (if alter? "hidden" "visible")}]
             [:img.absolute..top-0
              {:src   "/images/kingdom-alter-hover.png"
               :style {:width      "calc(100vw * 1305 / 5120)"
                       :visibility (if alter? "visible" "hidden")
                       :transform  "scale(1.1) translate(-1%,-1%)"}}]
             (and fg? [t "Alter"])]

            (and fg?
                 ;; back
                 [:button.absolute
                  {:className    (if img-loaded? "visible" "hidden")
                   :on-click     (fn []
                                   (anim! ::out true)
                                   (js/setTimeout
                                    #(do (rf/dispatch [:navigate :route/map])
                                         (anim! ::out false))
                                    500))
                   :onMouseEnter #(fg-reset! :back)
                   :onMouseLeave #(fg-reset! nil)
                   :style        {:width "calc(100vw * 473 / 5120)"
                                  :left  "calc(100vw * 409 / 5120)"
                                  :top   "calc(100vw * 0.5625 * 2550 / 2880)"}}
                  [:img
                   {:onLoad #(rf/dispatch [::img-loaded])
                    :src    "/images/kingdom-back-btn.png"
                    :style  {:width     "calc(100vw * 473 / 5120)"
                             :transform (if back? "scale(1.1) translate(-1%,-1%)" "")}}]])
            [:div.absolute.top-0.left-0.overflow-auto
             {:style {:backgroundColor (cond enter-kingdom? "rgba(0,0,0,1)" out? "rgba(0,0,0,1)" :else "rgba(0,0,0,0)")
                      :width           "100vw"
                      :pointerEvents   "none"
                      :opacity         1
                      :transition      "all 0.5s linear"
                      :height          "calc(100vw * 0.5625)"}}]]))}))))
