(ns fgl.app.ui.nft-card
  (:require
   [fgl.utils :refer [calc]]
   [lambdaisland.glogi :as log]
   [taoensso.encore :as enc]
   [fgl.app.ui.balance :as uib]
   [fgl.app.ui.glory-img :as glory-logo]
   [fgl.app.ui.gold-img :as gold-logo]
   ["@radix-ui/react-checkbox" :as C]))

(defn ui
  ([data] (ui data {}))
  ([{:keys [id name image disabled selected onCheckedChange width staked gold glory earned?]} opts]
   (let [width (or width "21.7rem")
         calc  (partial calc width)]
     {:key id}
     [:> C/Root
      (enc/nested-merge
       {:disabled        disabled
        :name            name
        :value           name
        :onCheckedChange onCheckedChange
        :checked         selected
        :style           {:width              width
                          :position           "relative"
                          :height             (calc 1.29953917)
                          :backgroundPosition "top 53% left 51%"
                          :backgroundRepeat   "no-repeat"
                          :backgroundSize     "145% 131%"
                          :backgroundImage    "url(\"/images/card-bg.svg\")"}}
       opts)

      ;; body
      [:div.h-full.bg-transparent.w-full
       {:style {:borderRadius (calc 0.01497696)}}
       [:div.grid.justify-center.items-center.h-full.w-full
        {:style {:borderTop    "4px transparent"
                 :borderRadius (calc 0.01497696)
                 :padding      "1%"
                 :boxShadow    "black 2px 2px 3px"}}
        [:div.rs1.relative
         ;; nft image
         [:img.m-auto {:style {:width (calc 0.93894009)}
                       :src   image}]

         ;; shadow and name
         [:div.absolute.bottom-0
          {:style {:background   "linear-gradient(#ffffff00, #000000)"
                   :height       "20%"
                   :left         "50%"
                   :transform    "translateX(-50%)"
                   :width        (calc 0.93894009)
                   :borderRadius (calc 0.01497696)}}
          [:span.block.font-bold
           {:style {:transform "translateY(50%)"
                    :fontSize  (calc 0.10368664)}}
           name]]

         ;; staked tag
         (and staked
              [:div.absolute.right-0
               {:style {:backgroundImage "url(\"/images/staked-tag.png\")"
                        :backgroundSize  "100%"
                        :width           (calc 0.29032258)
                        :top             "5%"
                        :transform       (str "translateX(" (calc 0.01843318) ")")}}
               [:span.block
                {:style {:fontSize  (calc 0.06912442)
                         :color     "rgb(11, 57, 66)"
                         :transform "translateY(-5%)"}}
                "Staked"]])]

        ;; tokens
        [:div.rs2.flex.flex-row.justify-between.text-center.items-center
         {:style {;; :alignSelf "baseline"
                  :fontSize (calc 0.08640553)
                  :padding  (str "0 " (calc (if earned? 0.1843318 0.13824885)))}}
         (and
          (some? gold)
          [:div.flex.flex-row.items-center.font-bold [gold-logo/ui (calc 0.13824885)] [uib/ui gold {:style {:maxWidth (calc 0.2764977)}}]])
         (and
          (some? glory)
          [:div.flex.flex-row.items-center.font-bold [glory-logo/ui (calc 0.13824885)] [uib/ui glory {:style {:maxWidth (calc 0.2764977)}}]])
         (and earned? [:span "Earned"])]]]

      ;; selected indicator
      [:> C/Indicator
       {:className "block absolute top-0 left-0 h-full w-full"}
       [:div.border-4.border-white.absolute.w-full.h-full.p-px
        {:style {:borderRadius (calc 0.01497696)}}
        [:div.w-0.h-0.absolute.bottom-0.right-0
         {:style {:borderTop    (str (calc 0.06912442) " solid transparent")
                  :borderBottom (str (calc 0.06912442) " solid transparent")
                  :borderLeft   (str (calc 0.06912442) " solid white")
                  :transform    "translateY(33%) translateX(16%) rotate(45deg)"}}]]]])))
