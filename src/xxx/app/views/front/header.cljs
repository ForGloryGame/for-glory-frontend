(ns xxx.app.views.front.header
  (:require
   ["@radix-ui/react-visually-hidden" :as Invisible]
   ["@radix-ui/react-navigation-menu" :as Nav]
   [re-frame.core :as rf]))

(defn nav-root [x]
  [:> Nav/Root {:className "absolute flex justify-center w-screen z-10"} x])

(defn nav-item [x]
  [:> Nav/Item x])

(defn nav-link [{:keys [className] :as opts} children]
  [nav-item
   [:> Nav/Link (assoc opts :className (str (or className "") " " "block p-2"))
    children]])

(defn logo []
  [:img.mr-8 {:src "/images/logo.png"}])

(defn twitter []
  [:img {:src "/images/twitter.svg"}])

(defn boat []
  [:img {:src "/images/boat.svg"}])

(defn medium []
  [:img {:src "/images/medium.svg"}])

(defn discord []
  [:img {:src "/images/discord.svg"}])

(defn nav-btn [opts children]
  [:button.text-sm.py-1.bg-rgb-dc961a.mr-4
   (merge {:className "px-3.25rem" :style {:borderRadius "1.25rem"}}
          opts)
   children])

(defn about []
  [nav-btn {:on-click #(rf/dispatch [:navigate :route/about])} "About"])

(defn roadmap []
  [nav-btn {:on-click #(rf/dispatch [:navigate :route/about])} "Roadmap"])

(defn ui []
  [:header.sticky.top-0
   [nav-root
    [:> Nav/List {:className "flex justify-between w-screen px-9.375rem py-4"}
     ^{:key 'left} [:> Nav/List {:className "flex justify-center items-center"}
                    ^{:key 'logo} [nav-link {:href "/"} [logo]]
                    ^{:key 'twitter} [nav-link {:href "https://twitter.com/"} [twitter]]
                    ^{:key 'boat} [nav-link {:href "https://boat.com"} [boat]]
                    ^{:key 'medium} [nav-link {:href "https://medium.com"} [medium]]
                    ^{:key 'discord} [nav-link {:href "https://discord.com"} [discord]]]
     ^{:key 'right} [:> Nav/List {:className "flex justify-center items-center"}
                     ^{:key 'logo} [nav-link {:className "invisible" :disabled true} [logo]]
                     ^{:key 'about} [nav-item [about]]
                     ^{:key 'roadmap} [nav-item [roadmap]]]]]])
