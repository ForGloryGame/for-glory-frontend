(ns fgl.app.views.front.header
  (:require
   ;; ["@radix-ui/react-visually-hidden" :as Invisible]
   ["@radix-ui/react-navigation-menu" :as Nav]
   ;; [re-frame.core :as rf]
   ))

(defn nav-root [x]
  [:> Nav/Root {:className "absolute flex flex-col justify-center w-full z-10"} x])

(defn nav-item [x]
  [:> Nav/Item x])

(defn nav-link [{:keys [className] :as opts} children]
  [nav-item
   [:> Nav/Link (assoc opts :className (str (or className "") " " "block p-2"))
    children]])

(defn logo []
  [:img.mr-8 {:width "4.75rem" :style {:width "4.75rem" :minWidth "76px"} :alt "link to home page"
              :srcset "/images/logo-128.png 128w,
                       /images/logo-256.png 256w,
                       /images/logo-512.png 512w,
                       /images/logo-1024.png 1024w,"
              :sizes "(max-width: 1024px) 128px
                      (max-width: 2048px) 256px
                      (max-width: 2880px) 512px
                      (min-width: 2880px) 1024px"}])

(defn twitter []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/twitter.svg" :alt "link to twitter"}])

(defn open-sea []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/open-sea.svg" :alt "link to opensea"}])

(defn medium []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/medium.svg" :alt "link to medium"}])

(defn discord []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/discord.svg" :alt "link to discord"}])

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
  [:header.sticky.top-0.flex.justify-center
   [nav-root
    [:> Nav/List {:className "flex justify-between  py-4 mx-auto max-w-screen-2xl"}
     ^{:key 'left} [:> Nav/List {:className "flex justify-center items-center"}
                    ^{:key 'logo} [nav-link {:href "/"} [logo]]
                    ^{:key 'twitter} [nav-link {:href "https://twitter.com/GloryGameNFT"} [twitter]]
                    ^{:key 'open-sea} [nav-link {:href "https://open-sea.com"} [open-sea]]
                    ^{:key 'medium} [nav-link {:href "https://medium.com/glorygame"} [medium]]
                    ^{:key 'discord} [nav-link {:href "https://discord.com"} [discord]]]
     ^{:key 'right} [:> Nav/List {:className "flex justify-center items-center"}
                     ^{:key 'logo} [nav-link {:className "invisible" :disabled true} [logo]]
                     ^{:key 'about} [nav-item [about]]
                     ^{:key 'roadmap} [nav-item [roadmap]]]]]])
