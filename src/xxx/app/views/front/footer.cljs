(ns xxx.app.views.front.footer)

(defn ui []
  [:footer.flex.flex-row.justify-between.w-full
   {:className "px-9.375rem py-4"}
   [:span "FORGLORY 2022."]
   [:div.flex.flex-row.justify-between
    [:a.block.mr-20px {:href "https://twitter.com"} [:img.w-24px.h-24px {:src "/images/twitter-2.svg" :alt "link to twitter"}]]
    [:a.block.mr-20px {:href "https://opensea.io"} [:img.w-24px.h-24px {:src "/images/open-sea-2.svg" :alt "link to opensea"}]]
    [:a.block.mr-20px {:href "https://medium.com"} [:img.w-24px.h-24px {:src "/images/medium-2.svg" :alt "link to medium"}]]
    [:a {:href "https://discord.com"} [:img.w-24px.h-24px {:src "/images/discord-2.svg" :alt "link to discord"}]]]])
