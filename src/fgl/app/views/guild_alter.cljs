(ns fgl.app.views.guild-alter
  (:require
   [taoensso.encore :as enc]
   [fgl.app.views.guild-route :as groute]
   [fgl.config :as conf]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.bfproxy :as bfproxy]
   [fgl.contracts.gamenft :as nft]
   [fgl.re-frame]
   [fgl.utils :refer [->display-token ->token-ids]]
   [fgl.app.ui.panel :as panel]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.rune-img :as runeimg]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       ;; get staked info
       (rf/dispatch [::kingdom/init])
       ;; get unstaked info
       (rf/dispatch [::nft/init]))
    :stop  identity}])

(defn main []
  [:div.grid
   {:style {:gridTemplateColumns "1fr 18rem"}}

   [:div.relative.px-10.text-center
    [:div.w-full.text-base.mt-12.mb-3.ffd.flexr
     {:className "text-#6bc9db"}
     "Amount " [goldimg/ui "2.5rem"] "sacrificed:"]
    [:div.guild-amount.w-full.text-4xl {:className "bg-#0d293180 py-3.5"} "12.00"]
    [:div.w-full.text-right.mt-1.ffd {:className "text-#ffffff80"} "MAX:22.00"]
    [:div.w-full.mt-6.mb-2.ffd {:className "text-#6bc9db"} "Locked weeks:"]
    [:div.w-full.grid.grid-cols-4.gap-5.ffd
     [:button.rounded.py-1 {:className "bg-#ffffff26"} "4 WEEKS"]
     [:button.rounded.py-1 {:className "bg-#71edf59c"} "8 WEEKS"]
     [:button.rounded.py-1 {:className "bg-#ffffff26"} "16 WEEKS"]
     [:button.rounded.py-1 {:className "bg-#ffffff26"} "32 WEEKS"]]
    [:div.border-t-2.border-solid.border-white.w-full.my-12]
    [:div.w-full.mb-2.ffd {:className "text-#6bc9db"} "Your sGOLD in return will be:"]
    [:div.flexr {:className "w-full bg-#81c6dd1a py-3 text-4xl"} [runeimg/ui "2.5rem"] "18"]
    [btn/ui {:className "absolute bottom-12 transform left-1/2 -translate-x-1/2"
             :style     {:width           "14rem"
                         :backgroundColor "rgb(237, 142, 40)"
                         :borderColor     "rgb(255, 211, 134)"}}
     "SACRIFICED"]]

   [:div.relative.h-full.text-center.bg-gradient-to-b.from-transparent
    {:className "to-#50929e3b bg-no-repeat"
     :style     {:backgroundImage    "url(/images/for-glory-bg.svg)"
                 :backgroundSize     "140%"
                 :backgroundPosition "50% 2%"}}
    [:p.mt-12.text-base.px-16.ffd "Your $GOLD eligible to redeem"]
    [:p.guild-font-family.text-4xl.mt-6.flexr [goldimg/ui "2.5rem"] "88.88"]
    [btn/ui
     {:className "absolute bottom-12 left-1/2 transform -translate-x-1/2"
      :style     {:width "10rem"}}
     "REDEEM"]]])
