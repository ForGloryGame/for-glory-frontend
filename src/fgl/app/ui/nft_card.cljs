(ns fgl.app.ui.nft-card
  (:require
   [lambdaisland.glogi :as log]
   [taoensso.encore :as enc]
   [fgl.app.ui.balance :as uib]
   [fgl.app.ui.glory-img :as glory-logo]
   [fgl.app.ui.gold-img :as gold-logo]
   ["@radix-ui/react-checkbox" :as C]))

;; TODO staked, nft name
(defn ui [{:keys [id name image selected width staked gold glory earned?] :as opts}]
  {:key id}
  [:> C/Root
   (enc/nested-merge
    {:className "relative"
     :style     {:width              width
                 :height             (str "calc(" width " * 1.29953917)")
                 :backgroundPosition "top 53% left 51%"
                 :backgroundRepeat   "no-repeat"
                 :backgroundSize     "145% 131%"
                 :backgroundImage    "url(\"/images/card-bg.svg\")"}}
    opts)

   ;; body
   [:div.h-full.bg-transparent
    {:style {:borderRadius "0.325rem"}}
    [:div.grid.justify-center.items-center.h-full
     {:style {:borderTop    "4px transparent"
              :borderRadius "0.325rem"
              :boxShadow    "black 2px 2px 3px"}}
     [:div.rs1.relative
      ;; nft image
      [:img {:style {:width "20.375rem"}
             :src   image}]

      ;; shadow
      [:div.absolute.left-0.bottom-0.w-full
       {:style {:background   "linear-gradient(#ffffff00, #000000)"
                :height       "20%"
                :borderRadius "0.325rem"}}
       [:span.block.text-white.font-bold.text-4xl {:style {:transform "translateY(50%)"}} name]]

      ;; staked tag
      (and staked
           [:div.absolute.right-0
            {:style {:backgroundImage "url(\"/images/staked-tag.png\")" :backgroundSize "100%" :width "6.3rem" :top "5%" :transform "translateX(0.4rem)"}}
            [:span.text-2xl.block {:style {:color "rgb(11, 57, 66)" :transform "translateY(-5%)"}} "Staked"]])]

     ;; tokens
     [:div.rs2.flex.flex-row.justify-between.text-white.text-3xl.text-center.items-center
      {:className (if earned? "px-16" "px-12")
       :style     {:alignSelf "baseline"}}
      (and (some? gold) [:div.flex.flex-row.items-center.font-bold [gold-logo/ui 3] [uib/ui gold]])
      (and (some? glory) [:div.flex.flex-row.items-center.font-bold [glory-logo/ui 3] [uib/ui glory]])
      (and earned? [:span "Earned"])]]]

   ;; selected indicator
   [:> C/Indicator
    {:className "block absolute top-0 left-0 h-full w-full"}
    [:div.border-4.border-white.absolute.w-full.h-full.p-px
     {:style {:borderRadius "0.325rem"}}
     [:div.w-0.h-0.absolute.bottom-0.right-0
      {:style {:borderTop    "1.5rem solid transparent"
               :borderBottom "1.5rem solid transparent"
               :borderLeft   "1.5rem solid white"
               :transform    "translateY(33%) translateX(16%) rotate(45deg)"}}]]]])
