(ns fgl.app.ui.btn
  (:require
   [taoensso.encore :as enc]))

(defn ui [opt text]
  [:button.px-12.py-1.relative.border
   (enc/nested-merge
    {:style
     {:backgroundColor "rgb(37, 161, 183)"
      :textShadow "0.0341rem 0.0341rem 0.025rem rgba(0, 0, 0, 0.64)"
      :borderColor     "rgb(94, 203, 231)"
      :borderRadius    "1rem"}}
    opt)
   [:div.absolute
    {:style {:top                  "8%"
             :height               "30%"
             :left                 "50%"
             :transform            "translateX(-50%)"
             :width                "98%"
             :backgroundColor      "rgba(255, 255, 255, 0.149)"
             :borderTopLeftRadius  "1rem"
             :borderTopRightRadius "1rem"}}]
   text])
