(ns fgl.app.views.front.footer)

(defn ui []
  [:footer.flex.flex-row.justify-between.w-full.mx-auto.p-4.max-w-screen-2xl
   [:span "FORGLORY 2022."]
   [:div.flex.flex-row.justify-between
    [:a.block.mr-20px
     {:rel    "noreferrer"
      :target "_blank"
      :href   "https://twitter.com/GloryGameNFT"}
     [:img {:style {:width "1.5rem" :minWidth "24px"}
            :src   "/images/twitter-2.svg" :alt "link to twitter"}]]

    [:a.block.mr-20px
     {:rel    "noreferrer"
      :target "_blank"
      :href   "https://opensea.io"}
     [:img {:style {:width "1.5rem" :minWidth "24px"}
            :src   "/images/open-sea-2.svg" :alt "link to opensea"}]]

    [:a.block.mr-20px
     {:rel    "noreferrer"
      :target "_blank"
      :href   "https://medium.com/glorygame"}
     [:img {:style {:width "1.5rem" :minWidth "24px"}
            :src   "/images/medium-2.svg" :alt "link to medium"}]]

    [:a {:rel    "noreferrer"
         :target "_blank"
         :href   "https://discord.com"}
     [:img {:style {:width "1.5rem" :minWidth "24px"}
            :src   "/images/discord-2.svg" :alt "link to discord"}]]]])
