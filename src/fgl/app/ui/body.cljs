(ns fgl.app.ui.body)

(defn ui [& children]
  [:main.grid-area-main.grid..justify-between
   {:style {:gridTemplateColumns "1fr auto 1fr"}}
   (into
    [:div.col-start-2.col-end-3
     {:style {:width "64vw"}}]
    children)])
