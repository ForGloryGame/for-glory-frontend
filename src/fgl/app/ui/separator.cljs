(ns fgl.app.ui.separator
  (:require
   [taoensso.encore :as enc]))

(defn ui
  ([] (ui {}))
  ([opt]
   [:div
    (enc/nested-merge
     {:style {:height          "0.1px"
              :boxShadow       "rgb(8 30 38) -1px -0.5px 0.5px 0.5px, rgb(0 182 255 / 12%) 1px 1px 0.5px 1px"
              :backgroundColor "rgba(100, 186, 214, 0)"}}
     opt)]))
