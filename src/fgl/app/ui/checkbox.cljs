(ns fgl.app.ui.checkbox
  (:require
   [taoensso.encore :as enc]
   ["@radix-ui/react-checkbox" :as C]))

(defn ui
  ([] (ui {}))
  ([{:keys [width] :as opts}]
   [:> C/Root
    (enc/nested-merge {:style {:borderWidth     "0.05rem"
                               :width           width
                               :height          width
                               :borderStyle     "solid"
                               :borderRadius    "0.26rem"
                               :backgroundColor "rgba(79, 126, 140, 0.231)"
                               :borderColor     "rgb(23, 47, 57)"}}
                      opts)
    [:> C/Indicator
     [:img {:src "/images/check.svg"}]]]))
