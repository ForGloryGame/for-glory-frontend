(ns fgl.app.ui.sgold-img
  (:require
   [taoensso.encore :as enc]))

(defn ui
  ([width] (ui width {}))
  ([width opts]
   (let [width (if string? width (str width "rem"))]
     [:img
      (enc/nested-merge
       opts
       {:style {:width  width
                :height width
                :transform "translateY(5%)"}
        :src   "/images/rune-token.png"})])))
