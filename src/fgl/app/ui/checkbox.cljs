(ns fgl.app.ui.checkbox
  (:require
   ["@radix-ui/react-checkbox" :as C]
   [taoensso.encore :as enc]))

(defn ui
  ([opt1] (ui opt1 {}))
  ([{:keys [width text]} {:keys [className] :as opt2}]
   [:> C/Root
    (enc/nested-merge
     opt2
     {:className (str "flex flex-row relative items-center" (or className ""))})
    [:div {:style {:backgroundColor "rgba(79, 126, 140, 0.231)"
                   :width           width
                   :height          width
                   :borderColor     "rgb(23, 47, 57)"
                   :borderRadius    "0.26rem"
                   :borderStyle     "solid"
                   :borderWidth     "0.05rem"}}]
    (and text text)

    [:> C/Indicator
     [:img.absolute.left-0
      {:style {:width  width
               :height width
               :top    "16%"}
       :src   "/images/check.svg"}]]]))
