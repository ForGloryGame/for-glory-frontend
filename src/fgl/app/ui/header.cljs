(ns fgl.app.ui.header
  (:require
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.header-tag :as header-tag]
   [fgl.config :as conf]
   [fgl.contracts :as c]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.glory :as glory]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.app.ui.connect-btn :as cbtn]
   ["@radix-ui/react-navigation-menu" :as Nav]
   [fgl.app.ui.logo :as logo]
   [fgl.app.ui.balance :as balance]))

(defn init-balances []
  (rf/dispatch [::gold/init])
  (rf/dispatch [::glory/init]))

(defn nav-root [x]
  [:> Nav/Root
   {:className "bg-no-repeat"
    :style     {:backgroundImage "
linear-gradient(to right, rgba(0,0,0,.302),rgba(0,0,0,.302)),
linear-gradient(to right, rgba(0,0,0,.302),rgba(0,0,0,.302)),
url(\"/images/header-bg.svg\")
"

                :backgroundBlendMode "multiply"
                :backgroundPosition  "100% 37%, -8% -16%, 63% -33%"
                :backgroundSize      "8.5% 44%, 100% 57%, 100% 77%"}}
   x])

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db
         addr-db           (get db addr)
         gold-balance      (::gold/balance addr-db)
         glory-balance     (::glory/balance addr-db)]
     [gold-balance glory-balance])))

(defn ui []
  (init-balances)
  (let [[gold-balance glory-balance] @(rf/subscribe [::data])]
    [:header.grid-area-header
     [nav-root
      [:> Nav/List
       {:className "grid auto-cols-max auto-rows-max justify-items-stretch justify-between items-center content-center pt-2"}

       ^{:key 'logo}
       [:> Nav/Item {:className "cs1 ce3 rs1 re3"}
        [:> Nav/Link {:className "cursor-pointer block p-2"
                      :on-click  #(rf/dispatch [:navigate :route/home])}
         [logo/ui {:style {:minWidth "8.8125rem"}}]]]

       ^{:key 'glory}
       [:> Nav/Item {:className "cs7 ce9"}
        [header-tag/ui
         [:div.flex.flex-row.justify-between.items-center.text-xl
          [gloryimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
          [balance/ui glory-balance {:className "fi mr-1"}]]]]

       ^{:key 'gold}
       [:> Nav/Item {:className "cs9 ce11"}
        [header-tag/ui
         [:div.flex.flex-row.justify-between.items-center.text-xl
          [goldimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
          [balance/ui gold-balance {:className "fi mr-1"}]]]]

       ^{:key 'ratio}
       [:> Nav/Item {:className "cs11 ce13"}
        [header-tag/ui
         [:div.flex.flex-row.justify-between.items-center.text-xl.fi
          [goldimg/ui "2rem"]
          [:span.mx-2 "1"]
          [:div.flex.flex-col
           [:img.w-6 {:src "/images/ratio.svg"}]
           [:img.w-6 {:style {:transform "rotate(180deg)"} :src "/images/ratio.svg"}]]
          "100"
          [gloryimg/ui "2rem" {:className "mx-1"}]]]]

       ^{:key 'connect-btn}
       [:> Nav/Item {:className "cs13 ce15"}
        [fgl.app.ui.connect-btn/ui conf/target-chain-id]]]]]))
