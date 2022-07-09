(ns fgl.app.ui.header
  (:require
   ["@radix-ui/react-navigation-menu" :as Nav]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.connect-btn :as cbtn]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.config :as conf]
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
   {:style {;; :height "6.625rem"
            :backgroundColor "rgba(29, 51, 70, 0.17)"
            :boxShadow       "0px 3px 62px 0px rgba(0, 0, 0, 0.53)"}}
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
       {:className "flex items-stretch justify-between"}
       ^{:key 'logo}
       [:> Nav/Item
        [:> Nav/Link {:className "cursor-pointer block px-20 py-1"
                      :style     {:backgroundColor "rgba(52,73,81,0.6)"}
                      :on-click  #(rf/dispatch [:navigate :route/home])}
         [:img.w-20
          {:src "/images/header-logo.png"}]]]

       [:> Nav/List
        {:className "flex items-stretch justify-between h-full"}
        ^{:key 'virtue}
        [:> Nav/Item
         [:button.flex.justify-between.items-center.text-xl.cursor-pointer.h-full.px-16.py-1
          {:style    {:backgroundColor "rgba(0, 0, 0, 0.19111)"}
           :on-click #(w/request
                       "wallet_watchAsset"
                       {:type    :ERC20
                        :options {:address  (.-address glory/c)
                                  :symbol   "VIRTUE"
                                  :decimals 18
                                  :image    "https://cdn.jsdelivr.net/gh/ForGloryGame/for-glory-frontend@dev/resources/app/public/images/glory-token.png"}}
                       identity
                       js/console.log)}
          [gloryimg/ui "3rem" {:className "mr-4"}]
          [balance/ui glory-balance]]]

        ^{:key 'glory}
        [:> Nav/Item
         [:button.flex.justify-between.items-center.text-xl.cursor-pointer.h-full.px-16.py-1
          {:style    {:backgroundColor "rgba(0, 0, 0, 0.19111)"}
           :on-click #(w/request
                       "wallet_watchAsset"
                       {:type    :ERC20
                        :options {:address  (.-address gold/c)
                                  :symbol   "GLORY"
                                  :decimals 18
                                  :image    "https://cdn.jsdelivr.net/gh/ForGloryGame/for-glory-frontend@dev/resources/app/public/images/gold-token.png"}}
                       identity
                       js/console.log)}
          [goldimg/ui "3rem" {:className "mr-4"}]
          [balance/ui gold-balance]]]

        ^{:key 'ratio}
        [:> Nav/Item
         [:div.flex.justify-between.items-center.text-xl.h-full.px-12.py-1
          {:style {:backgroundColor "rgba(0, 0, 0, 0.19111)"}}
          [goldimg/ui "3rem"]
          [:span.mx-4 "1"]
          [:div.flex.flex-col.mx-4
           [:img.w-6 {:src "/images/ratio.svg"}]
           [:img.w-6 {:style {:transform "rotate(180deg)"} :src "/images/ratio.svg"}]]
          [:span.mx-4 ratio]
          [gloryimg/ui "3rem"]]]

        ^{:key 'connect-btn}
        [:> Nav/Item
         {:className "h-full"}
         [fgl.app.ui.connect-btn/ui conf/target-chain-id]]
        ^{:key 'placeholder}
        [:> Nav/Item {:className "cs14"}]]]]]))
