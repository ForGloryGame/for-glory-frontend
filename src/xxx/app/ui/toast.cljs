(ns xxx.app.ui.toast
  (:require
   ["@radix-ui/react-toast" :as Toast]))

(defn ui
  ([] (ui nil))
  ([children]
   [:> Toast/Provider
    (when (seq children) children)
    [:> Toast/Root
     [:> Toast/Title
      [:h1 "Title"]]
     [:> Toast/Description
      [:p "Descriptions"]]
     [:> Toast/Action {:altText "altText"} "OK"]
     [:> Toast/Close "x"]]
    [:> Toast/Viewport {:asChild true}
     [:div.fixed.bottom-0.right-0.w-20.flex.flex-col.gap-10px.p-3.max-w-100vw.m-0.z-2147483647.list-none]]]))
