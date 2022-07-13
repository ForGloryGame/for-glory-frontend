(ns fgl.app.ui.map
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
   (>= (get db ::img-loaded) 5)))

(defn ui
  ([] (ui false))
  ([fg?]
   (let [hover     (r/atom nil)
         fg-reset! (fn [v] (and fg? (reset! hover v)))]
     (r/create-class
      {:component-did-mount
       (fn []
         (anim! ::in true))
       :component-will-unmount
       (fn []
         (anim! ::in false)
         (anim! :header-out false))
       :reagent-render
       (fn []
         (let [kingdom?       (= @hover :kingdom)
               council?       (= @hover :council)
               ruins?         (= @hover :ruins)
               merchant?      (= @hover :merchant)
               battlefield?   (= @hover :battlefield)
               img-loaded?    @(rf/subscribe [::img-loaded])
               out?           @(anim? :header-out)
               in?            @(anim? ::in)
               enter-kingdom? @(anim? :map->kingdom)]
           [:div.fixed.top-50%.left-50%.overflow-clip.fi
            {:style {:minWidth   "100vw"
                     :minHeight  "calc(100vw * 0.5625)"
                     :transform  (if enter-kingdom?
                                   "translate(-60%, -20%) scale(1.8)"
                                   "translate(-50%,-50%) scale(1.12)")
                     :zIndex     (if fg? "unset" -10)
                     :transition "all 0.5s linear"}}
            [:img.absolute.top-0.left-0.overflow-auto
             {:src   "/images/start-bg.png"
              :style {:width  "100vw"
                      :height "calc(100vw * 0.5625)"}}]
            [:div.absolute.top-0.left-0.overflow-auto
             {:style {:background "radial-gradient(circle, rgba(255,255,255,0) 0%, rgba(0,0,0,0.2) 95%)"
                      :width      "100vw"
                      :opacity    (cond out? 0 in? 1 :else 0)
                      :transition "all 0.5s linear"
                      :height     "calc(100vw * 0.5625)"}}]
            [:div.relative
             {:style {:transition "all 0.5s linear"
                      :opacity    (cond out? 0 in? 1 :else 0)}}
             ;; kingdom
             [:button.absolute
              {:className    (if img-loaded? "visible" "hidden")
               :on-click     (fn []
                               (anim! :map->kingdom true)
                               (js/setTimeout
                                #(do (rf/dispatch [:navigate :route/kingdom-map]))
                                500))
               :onMouseEnter #(fg-reset! :kingdom)
               :onMouseLeave #(fg-reset! nil)
               :style        {:width "calc(100vw * 1377 / 5120)"
                              :left  "calc(100vw * 2541 / 5120)"
                              :top   "calc(100vw * 0.5625 * 358 / 2880)"}}
              [:img
               {:src    "/images/map-kingdom.png"
                :onLoad #(rf/dispatch [::img-loaded])
                :style  {:width      "calc(100vw * 1377 / 5120)"
                         :visibility (if kingdom? "hidden" "visible")
                         :transform  "scale(1.2) translate(-1%,-1%)"}}]
              [:img.absolute.top-0
               {:src   "/images/map-kingdom-active.png"
                :style {:width      "calc(100vw * 1377 / 5120)"
                        :visibility (if kingdom? "visible" "hidden")
                        :transform  "scale(1.3) translate(-1%,-1%)"}}]
              (and fg? [t "Kingdom"])]

             ;; council
             [:button.absolute
              {:className    (if img-loaded? "visible" "hidden")
               :on-click     #(rf/dispatch [:navigate :route/council])
               :onMouseEnter #(fg-reset! :council)
               :onMouseLeave #(fg-reset! nil)
               :style        {:left "calc(100vw * 4341 / 5120)"
                              :top  "calc(100vw * 0.5625 * 983 / 2880)"}}
              [:img
               {:onLoad #(rf/dispatch [::img-loaded])
                :src    "/images/map-council.png"
                :style  {:width      "calc(100vw * 466 / 5120)"
                         :visibility (if council? "hidden" "visible")}}]
              [:img.absolute.top-0
               {:className "hover:opacity-100"
                :src       "/images/map-council-active.png"
                :style     {:width      "calc(100vw * 466 / 5120)"
                            :visibility (if council? "visible" "hidden")
                            :transform  "scale(1.2) translate(-1%,1%)"}}]
              (and fg? [t "Council"])]

             ;; ruins
             [:button.absolute
              {:className    (if img-loaded? "visible" "hidden")
               :on-click     #(rf/dispatch [:navigate :route/rank-personal])
               :onMouseEnter #(fg-reset! :ruins)
               :onMouseLeave #(fg-reset! nil)
               :style        {:left "calc(100vw * 3810 / 5120)"
                              :top  "calc(100vw * 0.5625 * 1824 / 2880)"}}
              [:img
               {:onLoad #(rf/dispatch [::img-loaded])
                :src    "/images/map-ruins.png"
                :style  {:width      "calc(100vw * 862 / 5120)"
                         :visibility (if ruins? "hidden" "visible")
                         :transform  "translate(2%,1%)"}}]
              [:img.absolute..top-0
               {:src   "/images/map-ruins-active.png"
                :style {:width      "calc(100vw * 862 / 5120)"
                        :visibility (if ruins? "visible" "hidden")
                        :transform  "scale(1.1)"}}]
              (and fg? [t "Ruins"])]

             ;; merchant
             [:button.absolute
              {:className    (if img-loaded? "visible" "hidden")
               :on-click     #(rf/dispatch [:navigate :route/merchant-mint])
               :onMouseEnter #(fg-reset! :merchant)
               :onMouseLeave #(fg-reset! nil)
               :style        {:left "calc(100vw * 2356 / 5120)"
                              :top  "calc(100vw * 0.5625 * 1606 / 2880)"}}
              [:img
               {:onLoad #(rf/dispatch [::img-loaded])
                :src    "/images/map-merchant.png"
                :style  {:width      "calc(100vw * 950 / 5120)"
                         :visibility (if merchant? "hidden" "visible")}}]
              [:img.absolute.top-0
               {:src   "/images/map-merchant-active.png"
                :style {:width      "calc(100vw * 950 / 5120)"
                        :visibility (if merchant? "visible" "hidden")
                        :transform  "scale(1.1) translate(-1%,-1%)"}}]
              (and fg? [t "Merchant"])]

             ;; battlefield
             [:button.absolute
              {:className    (if img-loaded? "visible" "hidden")
               :on-click     #(rf/dispatch [:navigate :route/battlefield])
               :onMouseEnter #(fg-reset! :battlefield)
               :onMouseLeave #(fg-reset! nil)
               :style        {:left "calc(100vw * 523 / 5120)"
                              :top  "calc(100vw * 0.5625 * 845 / 2880)"}}
              [:img
               {:onLoad #(rf/dispatch [::img-loaded])
                :src    "/images/map-battlefield.png"
                :style  {:width      "calc(100vw * 899 / 5120)"
                         :visibility (if battlefield? "hidden" "visible")}}]
              [:img.absolute.top-0
               {:src   "/images/map-battlefield-active.png"
                :style {:width      "calc(100vw * 899 / 5120)"
                        :visibility (if battlefield? "visible" "hidden")
                        :transform  "scale(1.1) translate(-1%,-1%)"}}]
              (and fg? [t "Battlefield"])]]
            [:div.absolute.top-0.left-0.overflow-auto
             {:style {:backgroundColor (if enter-kingdom? "rgba(0,0,0,1)" "rgba(0,0,0,0)")
                      :width           "100vw"
                      :pointerEvents   "none"
                      :opacity         1
                      :transition      "all 0.5s linear"
                      :height          "calc(100vw * 0.5625)"}}]]))}))))
