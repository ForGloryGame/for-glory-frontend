(ns fgl.app.ui.glory-img)

(defn ui [width]
  [:img
   {:style {:width (str width "rem")
            :height (str width "rem")
            :transform "scale(1.1)"}
    :src "/images/glory-token.png"}])
