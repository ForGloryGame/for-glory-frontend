(ns fgl.app.views.guild-basic
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
   [fgl.app.ui.sgold-img :as sgoldimg]
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
    :stop identity}])

(defn member-row [role addr locked]
  ^{:key addr}
  [:div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold
   {:style {:gridTemplateColumns "1fr 3fr 1fr"}}
   [:span role]
   [:span addr]
   [balance/ui locked]])

(defn kingdom-logo [src]
  [:img.w-36.mr-12 {:src src}])

(defn kingdom-info []
  [:div.flex.pt-9.pb-4.pl-11
   [kingdom-logo "/images/guild-avatar.png"]
   [:div.grid.grid-cols-3.text-3xl.gap-x-8.items-center
    [:span.text-4xl.col-span-full "Kingdom Name"]
    [:span
     [:span "20"]
     [:span.text-xl.text-C74bfcee6 "/ Members"]]
    [:span
     [:span "No.28"]
     [:span.text-xl.text-C74bfcee6 "/ Rank"]]
    [:span
     [:span "888"]
     [:span.text-xl.text-C74bfcee6 "/ Locked"]]
    [:span
     [:span "knight"]
     [:span.text-xl.text-C74bfcee6 "/ Your Role"]]]])

(defn kingdom-members []
  (let [member-data
        (map-indexed
         (fn [idx addr] ["Elder" (str addr idx) "10000000000000000000000"])
         (repeat 10 "0xA932480195801951510509DA0000000000000"))]
    [:div.px-6.py-4
     {:style {:height "calc(100% - 180px - 3.5rem)"}}
     [:div.text-xl.mb-2 "Member status"]
     [:div.rounded-sm.text-base.p-2.pb-0.grow
      {:style {:height     "calc(100% - 2.25rem)"
               :outline    "2px ridge #50929e33"
               :background "linear-gradient(
    rgba(163, 218, 226, 0.1),
    rgba(163, 218, 226, 0.05)
  )"}}
      [:div.rounded-sm.grid.justify-items-center.py-0_5.bg-C79c5da66.fb
       {:style {:gridTemplateColumns "1fr 3fr 1fr"}}
       [:span "ROLE"]
       [:span "ADDRESS"]
       [:span "RUNE LOCKED"]]
      [into
       [:div.overflow-auto
        {:style {:height "calc(100% - 1.625rem)"}}]
       (map #(vector apply member-row %) member-data)]]]))

(defn main []
  [:div
   [kingdom-info]
   [separator/ui]
   [kingdom-members]])
