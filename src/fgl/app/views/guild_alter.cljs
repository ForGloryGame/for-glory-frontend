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
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.glory-img :as gloryimg]
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
  [:div.grid.fb
   {:style {:gridTemplateColumns "1fr 18rem"}}

   [:div.relative.px-10
    [:div.w-full.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db
     "Amount " [goldimg/ui "2.5rem"] "sacrificed:"]
    [:div.w-full.text-4xl.bg-C0d293180.py-3_5.text-center
     {:style
      {:boxShadow "inset 1px 1px #111, 1px 1px rgba(116, 191, 206, 0.2)"}}
     (balance/ui "12000000000000000000" {:keep-end true})]
    [:div.w-full.text-right.mt-1.ffd.text-Cffffff80 "MAX:22.00"]
    [:div.w-full.mt-6.mb-2.ffd.text-C6bc9db "Locked weeks:"]
    [:div.w-full.grid.grid-cols-4.gap-5.fb
     [:button.rounded.py-1.bg-Cffffff26 "4 WEEKS"]
     [:button.rounded.py-1.bg-C71edf59c "8 WEEKS"]
     [:button.rounded.py-1.bg-Cffffff26 "16 WEEKS"]
     [:button.rounded.py-1.bg-Cffffff26 "32 WEEKS"]]
    ;; [:div.border-t-2.border-solid.border-white.w-full.my-12]
    [separator/ui {:className "my-12"}]
    [:div.w-full.mb-2.ffd.text-C6bc9db "Your sGOLD in return will be:"]
    [:div.flexr.w-full.bg-C81c6dd1a.py-3.text-4xl [gloryimg/ui "2.5rem"] "18"]
    [btn/ui
     {:className "absolute bottom-12 transform left-1/2 -translate-x-1/2 bg-Ced8e28 border-Cffd386 w-14rem text-xl" :c :orange}
     "SACRIFICED"]]

   [:div.relative.h-full.text-center.bg-gradient-to-b.from-transparent.to-C50929e3b.bg-no-repeat
    {:style {:backgroundImage    "url(/images/for-glory-bg.svg)"
             :backgroundSize     "140%"
             :backgroundPosition "50% 2%"}}
    [:p.mt-12.text-base.px-16.ffd "Your $GOLD eligible to redeem"]
    [:p.guild-font-family.text-4xl.mt-6.flexr [goldimg/ui "2.5rem"] (balance/ui "88880000000000000000")]
    [btn/ui
     {:className "absolute bottom-12 left-1/2 transform -translate-x-1/2 text-xl"
      :style     {:width "10rem"}}
     "REDEEM"]]])
