(ns fgl.app.ui.panel-layout)

(defn ui [& children]
  [:div.mt-4.grid.auto-cols-max.grid-cols-3
   (into [:section.col-start-2.col-send-3] children)])
