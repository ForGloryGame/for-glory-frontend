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
    :stop identity}])

(defn member-row [role addr locked]
  [:div.guild-table-row.grid.grid-1-3-1.justify-items-center.pb-1
   {:className "pt-1.125rem"}
   [:span role]
   [:span addr]
   [:span locked]])

(defn main []
  [:div
   [:div.flex.pt-9.pb-4.pl-11
    [:img.w-36.mr-12 {:src "/images/guild-avatar.png"}]
    [:div.grid.grid-cols-3.text-3xl.gap-x-8.items-center
     [:span.text-4xl.guild-font-family.col-span-full "Kingdom Name"]
     [:span
      [:span.guild-font-family "20"]
      [:span.text-xl.guild-color "/ Members"]]
     [:span
      [:span.guild-font-family "No.28"]
      [:span.text-xl.guild-color "/ Rank"]]
     [:span
      [:span.guild-font-family "888"]
      [:span.text-xl.guild-color "/ Locked"]]
     [:span
      [:span.guild-font-family "knight"]
      [:span.text-xl.guild-color "/ Your Role"]]]]
   [:div.border-t-2.border-solid.border-white.w-full]
   [:div.guild-members.px-6.py-4
    [:div.text-xl.guild-font-family.mb-2 "Member status"]
    [:div.guild-table.rounded-sm.text-base.p-2.pb-0.grow
     [:div.guild-table-header.rounded-sm.grid.grid-1-3-1.justify-items-center
      {:className "py-0.5"}
      [:span "ROLE"]
      [:span "ADDRESS"]
      [:span "RUNE LOCKED"]]
     (let [member-data (repeat 10 ["Elder" "0xA932480195801951510509DA00000000000000" "10000"])]
       [into
        [:div.guild-table-body.overflow-auto]
        (mapv #(vector apply member-row %) member-data)])]]])
