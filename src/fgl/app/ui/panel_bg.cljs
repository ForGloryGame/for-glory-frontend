(ns fgl.app.ui.panel-bg)

;; width 107.375 rem
;; height 51.5835 rem
(defn ui [width & children]
  (into
   [:div.bg-no-repeat
    {:style {:backgroundImage "url(/images/panel-bg-small.png)"
             :backgroundSize  "100%"
             :width        (str width "rem")
             :height       (str (/ width 2.08157647) "rem")}}]
   children))
