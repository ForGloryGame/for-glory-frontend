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
  (let [p    @(rf/subscribe [::w/provider])
        addr @(rf/subscribe [::w/addr])]
    (rf/dispatch [::gold/get-balance p addr])
    (rf/dispatch [::glory/get-balance p addr])))

(defn nav-root [x]
  [:> Nav/Root
   {:className "bg-no-repeat bg-top"
    :style     {:backgroundImage "url(\"/images/header-bg.png\")"
                :backgroundSize  "100% 80%"}}
   x])

(defn ui []
  (init-balances)
  [:header.grid-area-header
   [nav-root
    [:> Nav/List
     {:className "grid auto-cols-max auto-rows-max justify-items-stretch justify-between items-center content-center pt-2"}

     ^{:key 'logo}
     [:> Nav/Item {:className "col-start-1 col-end-3 row-start-1 row-end-3"
                   :style     {}}
      [:> Nav/Link {:className "cursor-pointer block p-2"
                    :on-click  #(rf/dispatch [:navigate :route/front])}
       [logo/ui {:style {:minWidth "8.8125rem"}}]]]

     ^{:key 'glory}
     [:> Nav/Item {:className "col-start-7 col-end-9"}
      [:span (str "Glory: " @(rf/subscribe [::glory/balance]))]]

     ^{:key 'gold}
     [:> Nav/Item {:className "col-start-9 col-end-11"}
      [:span (str "Gold: " @(rf/subscribe [::gold/balance]))]]

     ^{:key 'ratio}
     [:> Nav/Item {:className "col-start-11 col-end-13"}
      [:span "ratio"]]

     ^{:key 'connect-btn}
     [:> Nav/Item {:className "col-start-13 col-end-15"}
      [fgl.app.ui.connect-btn/ui conf/target-chain-id]]]]])
