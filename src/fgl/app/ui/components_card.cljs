(ns fgl.app.ui.components-card
  (:require
   [reagent.core :as reagent]
   [nubank.workspaces.core :as ws]
   [nubank.workspaces.card-types.react :as ct.react]
   [fgl.app.ui.toast :as toast]
   [fgl.app.ui.nft-card :as nftc]
   [fgl.app.ui.connect-btn]
   [fgl.app.ui.checkbox :as checkbox]))

(ws/defcard ^:dev/before-load nft-card
  (ct.react/react-card
   (reagent/as-element
    [:div [nftc/ui {:id 1 :name "Load#1128" :image "/images/lord-example.png" :gold 1e18 :width "21.7rem" :earned? 1}]])))

(ws/defcard ^:dev/before-load nft-card-staked
  (ct.react/react-card
   (reagent/as-element
    [:div [nftc/ui {:id 1 :name "Lord#1128" :image "/images/lord-example.png" :gold 1e18 :width "21.7rem" :earned? 1 :staked true}]])))

(ws/defcard ^:dev/before-load nft-card-gold-and-glory
  (ct.react/react-card
   (reagent/as-element
    [:div [nftc/ui {:id 2 :name "Lord#1128" :image "/images/lord-example.png" :gold 1e18 :glory 1e18 :width "21.7rem"}]])))

(ws/defcard ^:dev/before-load connect-btn
  (ct.react/react-card
   (reagent/as-element
    [fgl.app.ui.connect-btn/ui])))

(ws/defcard ^:dev/before-load toast
  (ct.react/react-card
   (reagent/as-element
    [:div [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]])))

(ws/defcard ^:dev/before-load checkbox
  (ct.react/react-card
   (reagent/as-element
    [:div [checkbox/ui {:width "2.4rem"}]])))
