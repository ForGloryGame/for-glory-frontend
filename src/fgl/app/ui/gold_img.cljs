(ns fgl.app.ui.gold-img
  (:require
   [taoensso.encore :as enc]))

(defn ui
  ([width] (ui width {}))
  ([width opts]
   (let [width (if string? width (str width "rem"))]
     [:img
      (enc/nested-merge
       opts
       {:style {:width     width
                :height    width
                :transform "translateY(5%)"}
        :src   "/images/gold-token.png"})])))
