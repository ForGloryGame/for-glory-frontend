(ns fgl.app.ui.header
  (:require
   [fgl.config :as conf]
   [fgl.contracts :as c]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.glory :as glory]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.app.ui.connect-btn :as cbtn]
   ["@radix-ui/react-navigation-menu" :as Nav]
   [fgl.app.ui.logo :as logo]))

(defn init-balances []
  (rf/dispatch [::gold/get-balance])
  (rf/dispatch [::glory/get-balance]))

(defn nav-root [x]
  [:> Nav/Root
   {:className "bg-no-repeat bg-top"
    :style     {:backgroundImage "url(\"/images/header-bg.png\")"
                :backgroundSize  "100% 80%"}}
   x])

(defn ui []
  (init-balances)
  (let [addr @(rf/subscribe [::w/addr])]
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
        [:span (str "Glory: " @(rf/subscribe [::glory/balance addr]))]]

       ^{:key 'gold}
       [:> Nav/Item {:className "cs9 ce11"}
        [:span (str "Gold: " @(rf/subscribe [::gold/balance addr]))]]

       ^{:key 'ratio}
       [:> Nav/Item {:className "cs11 ce13"}
        [:span "ratio"]]

       ^{:key 'connect-btn}
       [:> Nav/Item {:className "cs13 ce15"}
        [fgl.app.ui.connect-btn/ui conf/target-chain-id]]]]]))
