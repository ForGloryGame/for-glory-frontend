(ns fgl.app.views.guild-allocation
  (:require
   ["ethers" :as ethers]
   [clojure.string :as s]
   [taoensso.encore :as enc]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.sgold :as sgold]
   [fgl.contracts.gamenft :as nft]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.contracts.gold :as gold]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main []
  [:div.relative.px-10.py-8.text-center.fb
   [:div.flex.bg-C81c6dd1a.py-2.items-center
    [:div.grow.flex.flex-col.items-center
     [:span.text-C6bc9db "Weekly Glory amount"]
     [:span.guild-font-family.text-xl.mt-2.flexr [gloryimg/ui "2rem"] "360"]]
    [:div.h-10.border-l.border-solid.border-C79c5da87.mt-2]
    [:div.grow.flex.flex-col.items-center
     [:span.text-C6bc9db "Glory for you to allocate"]
     [:span.guild-font-family.text-xl.mt-2.flexr [gloryimg/ui "2rem"] "88"]]
    [:div.h-10.border-l.border-solid.border-C79c5da87.mt-2]
    [:div.grow.flex.flex-col.items-center
     [:span.text-C6bc9db "Next allocation"]
     [:span.guild-font-family.text-xl.mt-2 "01"
      [:span.text-base.ffd.text-C6bc9db.mr-3 " D"] "16"
      [:span.text-base.ffd.text-C6bc9db.mr-3 " H"] "20"
      [:span.text-base.ffd.text-C6bc9db " M"]]]]
   [:p.w-full.text-center.text-C6bc9db.mt-10.mb-3
    "Your reserved $GLORY percentage:"]
   [:p.w-full.text-center.guild-box-shadow.py-3.5.guild-font-family.text-4xl.bg-C0d293180
    "66.66%"]
   [:div.border-t-2.border-solid.border-white.w-full.mt-14.mb-4]
   [:div.grid.grid-cols-2.gap-x-2.rounded-sm.mb-20
    [:div.bg-C81c6dd1a.rounded-sm
     [:p.text-C6bc9db "Your reserved $GLORY amount:"]
     [:span.guild-font-family.text-4xl.inline-block.my-6.flexr [gloryimg/ui "2rem"] "18"]]
    [:div.bg-C81c6dd1a.rounded-sm
     [:p.text-C6bc9db "Everyone else will receive:"]
     [:span.guild-font-family.text-4xl.inline-block.my-6.flexr
      [gloryimg/ui "2rem"] "2.33"
      [:span.ffd.text-base.text-C6bc9db99.align-bottom]
      [:span.ffd.text-base.text-C6bc9db99.relative.top-0.5
       "person"]]]]
   [btn/ui {:t :olg} "CONFIRM"]])
