(ns fgl.app.views.front.header
  (:require
   ;; ["@radix-ui/react-visually-hidden" :as Invisible]
   ["@radix-ui/react-navigation-menu" :as Nav]
   [fgl.app.ui.logo :as logo]
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

(defn twitter []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/twitter.svg" :alt "link to twitter"}])

(defn opensea []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/open-sea.svg" :alt "link to opensea"}])

(defn medium []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/medium.svg" :alt "link to medium"}])

(defn discord []
  [:img {:style {:width "1.5rem" :minWidth "24px"} :src "/images/discord.svg" :alt "link to discord"}])

(defn nav-btn [opts children]
  [:a.text-sm.mr-4.block.font-bold.text-center
   (merge {:className "px-2.575rem py-1.5"
           :style     {:borderRadius    "1.25rem"
                       :minWidth        "8.75rem"
                       :backgroundImage "linear-gradient(178deg, #E5CB6F 0%, #AC852C 100%)"}}
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
                    ^{:key 'logo}
                    [nav-link {:href "/"} [logo/ui]]
                    ^{:key 'twitter}
                    [nav-link {:href   "https://twitter.com/GloryGameNFT"
                               :rel    "noreferrer"
                               :target "_blank"} [twitter]]
                    ^{:key 'opensea}
                    [nav-link {:rel    "noreferrer"
                               :target "_blank"
                               :href   "https://opensea.io"} [opensea]]
                    ^{:key 'medium}
                    [nav-link {:rel    "noreferrer"
                               :target "_blank"
                               :href   "https://medium.com/glorygame"} [medium]]
                    ^{:key 'discord}
                    [nav-link {:rel    "noreferrer"
                               :target "_blank"
                               :href   "https://discord.com"} [discord]]]
     ^{:key 'right} [:> Nav/List {:className "flex justify-center items-center"}
                     ^{:key 'logo} [nav-link {:className "invisible" :disabled true} [logo/ui]]
                     ^{:key 'about} [nav-item [about]]
                     ^{:key 'roadmap} [nav-item [roadmap]]]]]])
