(ns fgl.app.ui.header
  (:require
   [re-frame.core :as rf]
   [fgl.app.ui.connect-btn :as cbtn]
   ["@radix-ui/react-navigation-menu" :as Nav]
   [fgl.app.ui.logo :as logo]))

(defn nav-root [x]
  [:> Nav/Root
   {:style {:backgroundImage     "radial-gradient(circle at top left, rgba(0,0,0,1) 0%, rgba(255,255,255,1) 31%),url(\"/images/header-bg.png\")"
            :backgroundBlendMode "darken"
            :backgroundPosition  "top"
            :backgroundSize      "100% 100%"}} x])

(defn nav-item [x]
  [:> Nav/Item x])

(defn nav-link [{:keys [className] :as opts} children]
  [nav-item
   [:> Nav/Link (assoc opts :className (str (or className "") " " "block p-2"))
    children]])

(defn ui []
  [:header
   [nav-root
    [:> Nav/List

     ^{:key 'left}
     [:> Nav/List
      ^{:key 'logo}
      [nav-link {:className "cursor-pointer"
                 :on-click  #(rf/dispatch [:navigate :route/front])} [logo/ui]]]

     ^{:key 'right}
     [:> Nav/List
      [fgl.app.ui.connect-btn/ui]]]]])
