(ns fgl.app.ui.gold-img)

(defn ui [width]
  (let [width (if string? width (str width "rem"))]
    [:img
     {:style {:width width
              :height width
              :transform "translateY(5%)"}
      :src "/images/gold-token.png"}]))
