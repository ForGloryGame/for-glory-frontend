(ns fgl.app.ui.landeed-img
  (:require
   [fgl.utils :refer [calc]]
   [taoensso.encore :as enc]))

(defn ui
  ([height] (ui height {}))
  ([height opts]
   (let [height (if string? height (str height "rem"))]
     [:img
      (enc/nested-merge
       opts
       {:style {:width     (calc height 0.81818182)
                :height    height
                :transform "translateY(5%)"}
        :src   "/images/landeed-token.png"})])))
