(ns fgl.app.views.guild-allocation
  (:require
   ["ethers" :as ethers]
   [clojure.string :as s]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.sgold :as sgold]
   [fgl.re-frame]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oget]]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [taoensso.encore :as enc]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(rf/reg-event-db
 ::percent
 (fn [db [_ percent]]
   (let [{::keys [value display]} db
         percent                  (if (string? percent) percent "")
         percent                  (.replaceAll percent " " "")
         percent                  (.replaceAll percent "%" "")
         percent                  (if (.startsWith ".") (str "0" percent) percent)
         [v d]
         (cond
           (= "" percent)
           [nil ""]

           (false?
            (try
              (ethers/utils.parseUnits percent)
              (catch js/Error _ false)))
           [value display]

           (-> percent
               js/parseFloat
               (> 100))
           [value display]

           :else
           [(-> percent js/parseFloat) percent])]
     (assoc db ::value v
            ::display d))))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::keys [value display]} db]
     {:value value
      :display display})))

(defn input []
  (let [{:keys [display]} @(rf/subscribe [::data])]
    [:input.w-full.text-center.guild-box-shadow.py-3.5.guild-font-family.text-4xl.bg-C0d293180
     {:onChange #(rf/dispatch [::percent (oget % "target.value")])
      :value    (str display " %")}]))

(defn confirm []
  (let [{:keys [value]} @(rf/subscribe [::data])]
    [btn/ui
     {:t        :olg
      :disabled (not value)}
     "CONFIRM"]))

(defn main []
  [:div.relative.px-10.py-8.text-center.fb
   [:div.flex.bg-C81c6dd1a.py-2.items-center
    [:div.grow.flex.flex-col.items-center
     [:span.text-C6bc9db "Weekly Virtue amount"]
     [:span.guild-font-family.text-xl.mt-2.flexr [gloryimg/ui "2rem"] "360"]]
    [:div.h-10.border-l.border-solid.border-C79c5da87.mt-2]
    [:div.grow.flex.flex-col.items-center
     [:span.text-C6bc9db "Virtue for you to allocate"]
     [:span.guild-font-family.text-xl.mt-2.flexr [gloryimg/ui "2rem"] "88"]]
    [:div.h-10.border-l.border-solid.border-C79c5da87.mt-2]
    [:div.grow.flex.flex-col.items-center
     [:span.text-C6bc9db "Next allocation"]
     [:span.guild-font-family.text-xl.mt-2 "01"
      [:span.text-base.ffd.text-C6bc9db.mr-3 " D"] "16"
      [:span.text-base.ffd.text-C6bc9db.mr-3 " H"] "20"
      [:span.text-base.ffd.text-C6bc9db " M"]]]]
   [:p.w-full.text-center.text-C6bc9db.mt-10.mb-3
    "Your reserved $VIRTUE percentage:"]
   [input]
   [:div.border-t-2.border-solid.border-white.w-full.mt-14.mb-4]
   [:div.grid.grid-cols-2.gap-x-2.rounded-sm.mb-20
    [:div.bg-C81c6dd1a.rounded-sm
     [:p.text-C6bc9db "Your reserved $VIRTUE amount:"]
     [:span.guild-font-family.text-4xl.inline-block.my-6.flexr [gloryimg/ui "2rem"] "18"]]
    [:div.bg-C81c6dd1a.rounded-sm
     [:p.text-C6bc9db "Everyone else will receive:"]
     [:span.guild-font-family.text-4xl.inline-block.my-6.flexr
      [gloryimg/ui "2rem"] "2.33"
      [:span.ffd.text-base.text-C6bc9db99.align-bottom]
      [:span.ffd.text-base.text-C6bc9db99.relative.top-0.5
       "person"]]]]
   [confirm]])
