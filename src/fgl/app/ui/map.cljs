(ns fgl.app.ui.map
  (:require
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn t [text]
  [:div.absolute.w-full.bottom-20%.flexr
   {:style {:fontSize   "15rem"
            :background "linear-gradient(to left, transparent, #0000007a, transparent)"}}
   [:img {:src   "/images/square.svg"
          :style {:transform "scale(4)"}}]
   [:span.block {:style {:margin "0 5% 0 5%"}} text]
   [:img {:src   "/images/square.svg"
          :style {:transform "scale(4)"}}]])

(defn ui
  ([] (ui false))
  ([fg?]
   (let [hover     (r/atom nil)
         fg-reset! (fn [v] (and fg? (reset! hover v)))]
     (fn []
       (let [kingdom?     (= @hover :kingdom)
             council?     (= @hover :council)
             ruins?       (= @hover :ruins)
             merchant?    (= @hover :merchant)
             battlefield? (= @hover :battlefield)]
         [:div.fixed.top-50%.left-50%.overflow-clip.fi
          {:style {:minWidth  "1000vw"
                   :minHeight "calc(1000vw * 0.5625)"
                   :transform "translate(-50%,-50%) scale(0.11)"
                   :zIndex    (if fg? "unset" -10)}}
          [:img.absolute.top-0.left-0.overflow-auto
           {:src   "/images/map-division-bg.png"
            :style {:width  "1000vw"
                    :height "calc(1000vw * 0.5625)"}}]

          ;; kingdom
          [:button.absolute
           {:on-click     #(rf/dispatch [:navigate :route/guild-basic])
            :onMouseEnter #(fg-reset! :kingdom)
            :onMouseLeave #(fg-reset! nil)
            :style        {:width "calc(1000vw * 1377 / 5120)"
                           :left  "calc(1000vw * 2541 / 5120)"
                           :top   "calc(1000vw * 0.5625 * 358 / 2880)"}}
           [:img
            {:src   "/images/map-kingdom.png"
             :style {:width      "calc(1000vw * 1377 / 5120)"
                     :visibility (if kingdom? "hidden" "visible")}}]
           [:img.absolute.top-0
            {:src   "/images/map-kingdom-active.png"
             :style {:width      "calc(1000vw * 1377 / 5120)"
                     :visibility (if kingdom? "visible" "hidden")
                     :transform  "scale(1.1) translate(-1%,-1%)"}}]
           (and fg? [t "Kingdom"])]

          ;; council
          [:button.absolute
           {:on-click     #(rf/dispatch [:navigate :route/council])
            :onMouseEnter #(fg-reset! :council)
            :onMouseLeave #(fg-reset! nil)
            :style        {:left "calc(1000vw * 4341 / 5120)"
                           :top  "calc(1000vw * 0.5625 * 983 / 2880)"}}
           [:img
            {:src   "/images/map-council.png"
             :style {:width      "calc(1000vw * 466 / 5120)"
                     :visibility (if council? "hidden" "visible")}}]
           [:img.absolute.top-0
            {:className "hover:opacity-100"
             :src       "/images/map-council-active.png"
             :style     {:width      "calc(1000vw * 466 / 5120)"
                         :visibility (if council? "visible" "hidden")
                         :transform  "scale(1.2) translate(-1%,1%)"}}]
           (and fg? [t "Council"])]

          ;; ruins
          [:button.absolute
           {:on-click     #(rf/dispatch [:navigate :route/rank-personal])
            :onMouseEnter #(fg-reset! :ruins)
            :onMouseLeave #(fg-reset! nil)
            :style        {:left "calc(1000vw * 3810 / 5120)"
                           :top  "calc(1000vw * 0.5625 * 1824 / 2880)"}}
           [:img
            {:src   "/images/map-ruins.png"
             :style {:width      "calc(1000vw * 862 / 5120)"
                     :visibility (if ruins? "hidden" "visible")
                     :transform  "translate(2%,1%)"}}]
           [:img.absolute..top-0
            {:src   "/images/map-ruins-active.png"
             :style {:width      "calc(1000vw * 862 / 5120)"
                     :visibility (if ruins? "visible" "hidden")
                     :transform  "scale(1.1)"}}]
           (and fg? [t "Ruins"])]

          ;; merchant
          [:button.absolute
           {:on-click     #(rf/dispatch [:navigate :route/merchant-mint])
            :onMouseEnter #(fg-reset! :merchant)
            :onMouseLeave #(fg-reset! nil)
            :style        {:left "calc(1000vw * 2356 / 5120)"
                           :top  "calc(1000vw * 0.5625 * 1606 / 2880)"}}
           [:img
            {:src   "/images/map-merchant.png"
             :style {:width "calc(1000vw * 950 / 5120)"}}]
           :visibility (if merchant? "hidden" "visible")
           [:img.absolute.top-0
            {:src   "/images/map-merchant-active.png"
             :style {:width      "calc(1000vw * 950 / 5120)"
                     :visibility (if merchant? "visible" "hidden")
                     :transform  "scale(1.1) translate(-1%,-1%)"}}]
           (and fg? [t "Merchant"])]

          ;; battlefield
          [:button.absolute
           {:on-click     #(rf/dispatch [:navigate :route/battlefield])
            :onMouseEnter #(fg-reset! :battlefield)
            :onMouseLeave #(fg-reset! nil)
            :style        {:left "calc(1000vw * 523 / 5120)"
                           :top  "calc(1000vw * 0.5625 * 845 / 2880)"}}
           [:img
            {:src   "/images/map-battlefield.png"
             :style {:width "calc(1000vw * 899 / 5120)"}}]
           :visibility (if battlefield? "hidden" "visible")
           [:img.absolute..top-0
            {:src   "/images/map-battlefield-active.png"
             :style {:width      "calc(1000vw * 899 / 5120)"
                     :visibility (if battlefield? "visible" "hidden")
                     :transform  "scale(1.1) translate(-1%,-1%)"}}]
           (and fg? [t "Battlefield"])]])))))
