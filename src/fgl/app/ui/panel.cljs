(ns fgl.app.ui.panel
  (:require
   [fgl.app.ui.panel-bg :as bg]))

(defn- w [width x]
  (str (/ width x) "rem"))

(defn ui [name width on-close children]
  (let [wf              (partial w width)
        close-btn-width (wf 54.87179487)]
    [bg/ui width
     [:div.flex.flex-row.justify-between.items-start
      {:style {:padding "0.5%"}}
      [:span.pl-2 {:style {:color "rgb(213, 228, 232)" :fontSize (wf 60)}} name]
      [:button
       {:on-click on-close}
       [:img {:src   "/images/panel-close.svg"
              :style {:width close-btn-width :height close-btn-width :paddingLeft (wf 685.89743588)}}]]]
     children]))
