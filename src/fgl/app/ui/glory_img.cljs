(ns fgl.app.ui.glory-img
  (:require
   [taoensso.encore :as enc]))

(defn ui
  ([width] (ui width {}))
  ([width opts]
   (let [width (if (string? width) width (str width "rem"))]
     [:img
      (enc/nested-merge
       opts
       {:style {:width width
                :height width
                :transform "scale(1.1)"}
        :src "/images/glory-token.png"})])))
