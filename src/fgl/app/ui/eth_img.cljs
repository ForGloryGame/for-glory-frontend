(ns fgl.app.ui.eth-img
  (:require
   [taoensso.encore :as enc]))

(defn ui
  ([width] (ui width {}))
  ([width opts]
   (let [width (if string? width (str width "rem"))]
     [:img.mx-2
      (enc/nested-merge
       opts
       {:style {:width     (str "calc(" width " / 1.60902256)")
                :height    width
                :transform "translateY(5%)"}
        :src   "/images/eth-token.png"})])))
