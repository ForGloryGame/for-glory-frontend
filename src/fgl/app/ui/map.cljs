(ns fgl.app.ui.map)

(defn ui
  ([] (ui false))
  ([fore-ground?]
   [:div.fixed.top-50%.left-50%.overflow-clip
    {:style {:minWidth  "1000vw"
             :minHeight "calc(1000vw * 0.5625)"
             :transform "translate(-50%,-50%) scale(0.11)"
             :zIndex    (if fore-ground? "unset" -10)}}
    [:img.absolute.top-0.left-0.overflow-auto
     {:src   "/images/map-division-bg.png"
      :style {:width  "1000vw"
              :height "calc(1000vw * 0.5625)"}}]
    [:img.absolute
     {:className "hover:opacity-0"
      :src       "/images/map-kingdom.png"
      :style     {:width "calc(1000vw * 1377 / 5120)"
                  :left  "calc(1000vw * 2541 / 5120)"
                  :top   "calc(1000vw * 0.5625 * 358 / 2880)"}}]
    (and fore-ground?
         [:img.absolute.opacity-0
          {:className "hover:opacity-100"
           :src       "/images/map-kingdom-active.png"
           :style     {:width     "calc(1000vw * 1377 / 5120)"
                       :left      "calc(1000vw * 2541 / 5120)"
                       :top       "calc(1000vw * 0.5625 * 358 / 2880)"
                       :transform "scale(1.1) translate(-1%,-1%)"}}])
    [:img.absolute
     {:className "hover:opacity-0"
      :src       "/images/map-council.png"
      :style     {:width "calc(1000vw * 466 / 5120)"
                  :left  "calc(1000vw * 4341 / 5120)"
                  :top   "calc(1000vw * 0.5625 * 983 / 2880)"}}]
    (and fore-ground?
         [:img.absolute.opacity-0
          {:className "hover:opacity-100"
           :src       "/images/map-council-active.png"
           :style     {:width     "calc(1000vw * 466 / 5120)"
                       :left      "calc(1000vw * 4341 / 5120)"
                       :top       "calc(1000vw * 0.5625 * 983 / 2880)"
                       :transform "scale(1.2) translate(-1%,1%)"}}])
    [:img.absolute
     {:className "hover:opacity-0"
      :src       "/images/map-ruins.png"
      :style     {:width     "calc(1000vw * 862 / 5120)"
                  :left      "calc(1000vw * 3810 / 5120)"
                  :top       "calc(1000vw * 0.5625 * 1824 / 2880)"
                  :transform "translate(2%,1%)"}}]
    (and fore-ground?
         [:img.absolute.opacity-0
          {:className "hover:opacity-100"
           :src       "/images/map-ruins-active.png"
           :style     {:width     "calc(1000vw * 862 / 5120)"
                       :left      "calc(1000vw * 3810 / 5120)"
                       :top       "calc(1000vw * 0.5625 * 1824 / 2880)"
                       :transform "scale(1.1)"}}])
    [:img.absolute
     {:className "hover:opacity-0"
      :src       "/images/map-merchant.png"
      :style     {:width "calc(1000vw * 950 / 5120)"
                  :left  "calc(1000vw * 2356 / 5120)"
                  :top   "calc(1000vw * 0.5625 * 1606 / 2880)"}}]
    (and fore-ground?
         [:img.absolute.opacity-0
          {:className "hover:opacity-100"
           :src       "/images/map-merchant-active.png"
           :style     {:width     "calc(1000vw * 950 / 5120)"
                       :left      "calc(1000vw * 2356 / 5120)"
                       :top       "calc(1000vw * 0.5625 * 1606 / 2880)"
                       :transform "scale(1.1) translate(-1%,-1%)"}}])
    [:img.absolute
     {:className "hover:opacity-0"
      :src       "/images/map-battlefield.png"
      :style     {:width "calc(1000vw * 899 / 5120)"
                  :left  "calc(1000vw * 523 / 5120)"
                  :top   "calc(1000vw * 0.5625 * 845 / 2880)"}}]
    (and fore-ground?
         [:img.absolute.opacity-0
          {:className "hover:opacity-100"
           :src       "/images/map-battlefield-active.png"
           :style     {:width     "calc(1000vw * 899 / 5120)"
                       :left      "calc(1000vw * 523 / 5120)"
                       :top       "calc(1000vw * 0.5625 * 845 / 2880)"
                       :transform "scale(1.1) translate(-1%,-1%)"}}])]))
