(ns fgl.app.ui.gold-img)

(defn ui [width]
  [:img
   {:style {:width (str width "rem")
            :height (str width "rem")
            :transform "translateY(5%)"}
    :src "/images/gold-token.png"}])
