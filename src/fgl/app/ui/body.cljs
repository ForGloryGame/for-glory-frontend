(ns fgl.app.ui.body)

(defn ui [& children]
  [:main.grid-area-main.grid..justify-between
   {:style {:gridTemplateColumns "1fr auto 1fr"}}
   (into
    [:div.cs2.ce3
     {:style {:width "64vw"}}]
    children)])
