(ns xxx.app.views.front.footer)

(defn ui []
  [:footer.flex.flex-row.justify-between.w-screen
   {:className "px-9.375rem py-4"}
   [:span "FORGLORY 2022."]
   [:div.flex.flex-row.justify-between
    [:a.block.mr-20px {:href "https://twitter.com"} [:img {:src "/images/twitter-2.svg"}]]
    [:a.block.mr-20px {:href "https://opensea.io"} [:img {:src "/images/open-sea-2.svg"}]]
    [:a.block.mr-20px {:href "https://medium.com"} [:img {:src "/images/medium-2.svg"}]]
    [:a {:href "https://discord.com"} [:img.h-24px.w-24px {:src "/images/discord-2.svg"}]]]])
