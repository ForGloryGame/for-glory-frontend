(ns xxx.app.views.front.header
  (:require
   ;; ["@radix-ui/react-visually-hidden" :as Invisible]
   ["@radix-ui/react-navigation-menu" :as Nav]
   ;; [re-frame.core :as rf]
   ))

(defn nav-root [x]
  [:> Nav/Root {:className "absolute flex justify-center w-screen z-10"} x])

(defn nav-item [x]
  [:> Nav/Item x])

(defn nav-link [{:keys [className] :as opts} children]
  [nav-item
   [:> Nav/Link (assoc opts :className (str (or className "") " " "block p-2"))
    children]])

(defn logo []
  [:img.mr-8.w-76px.h-51px {:src "/images/logo.png" :alt "link to home page"}])

(defn twitter []
  [:img.w-24px.h-24px {:src "/images/twitter.svg" :alt "link to twitter"}])

(defn open-sea []
  [:img.w-24px.h-24px {:src "/images/open-sea.svg" :alt "link to opensea"}])

(defn medium []
  [:img.w-24px.h-24px {:src "/images/medium.svg" :alt "link to medium"}])

(defn discord []
  [:img.w-24px.h-24px {:src "/images/discord.svg" :alt "link to discord"}])

(defn nav-btn [opts children]
  [:a.text-sm.py-1.bg-rgb-dc961a.mr-4.block
   (merge {:className "px-3.25rem" :style {:borderRadius "1.25rem"}}
          opts)
   children])

(defn about []
  [nav-btn {:href "#about"} "About"])

(defn roadmap []
  [nav-btn {:href "#roadmap"} "Roadmap"])

(defn ui []
  [:header.sticky.top-0
   [nav-root
    [:> Nav/List {:className "flex justify-between w-screen px-9.375rem py-4"}
     ^{:key 'left} [:> Nav/List {:className "flex justify-center items-center"}
                    ^{:key 'logo} [nav-link {:href "/"} [logo]]
                    ^{:key 'twitter} [nav-link {:href "https://twitter.com/"} [twitter]]
                    ^{:key 'open-sea} [nav-link {:href "https://open-sea.com"} [open-sea]]
                    ^{:key 'medium} [nav-link {:href "https://medium.com"} [medium]]
                    ^{:key 'discord} [nav-link {:href "https://discord.com"} [discord]]]
     ^{:key 'right} [:> Nav/List {:className "flex justify-center items-center"}
                     ^{:key 'logo} [nav-link {:className "invisible" :disabled true} [logo]]
                     ^{:key 'about} [nav-item [about]]
                     ^{:key 'roadmap} [nav-item [roadmap]]]]]])
