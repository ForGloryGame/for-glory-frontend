(ns fgl.app.ui.header
  (:require
   ["@radix-ui/react-navigation-menu" :as Nav]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.connect-btn :as cbtn]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.header-tag :as header-tag]
   [fgl.app.ui.logo :as logo]
   [fgl.config :as conf]
   [fgl.contracts :as c]
   [fgl.contracts.exchange :as exchange]
   [fgl.contracts.glory :as glory]
   [fgl.contracts.gold :as gold]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]))

(defn init-balances []
  (rf/dispatch [::gold/init])
  (rf/dispatch [::glory/init])
  (rf/dispatch [::exchange/init]))

(defn nav-root [x]
  [:> Nav/Root
   {:className "bg-no-repeat"
    :style     {:backgroundImage
                "
url(\"/images/header-bg.svg\")
"

                :backgroundBlendMode "multiply"
                :backgroundPosition
                "
-10px 0.625rem"
                :backgroundSize
                "
100% 100%"}}
   x])

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db
         addr-db           (get db addr)
         ratio             (.toString (get db ::exchange/ratio 0))
         gold-balance      (::gold/balance addr-db)
         glory-balance     (::glory/balance addr-db)]
     [gold-balance  glory-balance ratio])))

(defn ui []
  (init-balances)
  (let [[gold-balance glory-balance ratio] @(rf/subscribe [::data])]
    [:header.w-100vw.grid-area-header.fixed.z-10
     [nav-root
      [:> Nav/List
       {:className "grid justify-items-stretch justify-between items-center content-center"}

       ^{:key 'logo}
       [:> Nav/Item {:className "cs1 ce3"}
        [:> Nav/Link {:className "cursor-pointer block p-2"
                      :on-click  #(rf/dispatch [:navigate :route/home])}
         [logo/ui {:style {:minWidth "8.8125rem"
                           :marginTop "-0.5rem"
                           :marginBottom "-0.5rem"}}]]]

       ^{:key 'glory}
       [:> Nav/Item {:className "cs6 ce8"}
        [header-tag/ui
         [:div.flex.flex-row.justify-between.items-center.text-xl.cursor-pointer
          {:on-click #(w/request
                       "wallet_watchAsset"
                       {:type    :ERC20
                        :options {:address  (.-address glory/c)
                                  :symbol   "GLORY"
                                  :decimals 18
                                  :image    "https://cdn.jsdelivr.net/gh/ForGloryGame/for-glory-frontend@dev/resources/app/public/images/glory-token.png"}}
                       identity
                       js/console.log)}
          [gloryimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
          [balance/ui glory-balance {:className "fi mr-1"}]]]]

       ^{:key 'gold}
       [:> Nav/Item {:className "cs8 ce10"}
        [header-tag/ui
         [:div.flex.flex-row.justify-between.items-center.text-xl.cursor-pointer
          {:on-click #(w/request
                       "wallet_watchAsset"
                       {:type    :ERC20
                        :options {:address  (.-address gold/c)
                                  :symbol   "GOLD"
                                  :decimals 18
                                  :image    "https://cdn.jsdelivr.net/gh/ForGloryGame/for-glory-frontend@dev/resources/app/public/images/gold-token.png"}}
                       identity
                       js/console.log)}
          [goldimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
          [balance/ui gold-balance {:className "fi mr-1"}]]]]

       ^{:key 'ratio}
       [:> Nav/Item {:className "cs10 ce12"}
        [header-tag/ui
         [:div.flex.flex-row.justify-between.items-center.text-xl.fi
          [goldimg/ui "2rem"]
          [:span.mx-2 "1"]
          [:div.flex.flex-col
           [:img.w-6 {:src "/images/ratio.svg"}]
           [:img.w-6 {:style {:transform "rotate(180deg)"} :src "/images/ratio.svg"}]]
          ratio
          [gloryimg/ui "2rem" {:className "mx-1"}]]]]

       ^{:key 'connect-btn}
       [:> Nav/Item {:className "cs12 ce14"}
        [fgl.app.ui.connect-btn/ui conf/target-chain-id]]
       ^{:key 'placeholder}
       [:> Nav/Item {:className "cs14"}]]]]))
