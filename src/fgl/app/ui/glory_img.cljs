(ns fgl.app.ui.glory-img)

(defn ui [width]
  (let [width (if (string? width) width (str width "rem"))]
    [:img
     {:style {:width width
              :height width
              :transform "scale(1.1)"}
      :src "/images/glory-token.png"}]))
