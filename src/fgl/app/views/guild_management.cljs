(ns fgl.app.views.guild-management
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
   [re-frame.core :as rf]
   [reagent.core :as r]
   [taoensso.encore :as enc]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main []
  [:div.py-8.px-6
   [:button "Revocation"]
   [:div.p-4
    [:p.pl-4.mb-2.ffd "TITLE"]
    [:input.tracking-wide.uppercase.bg-C81c6dd1a.guild-font-family.text-4xl.py-2.px-4.w-full
     {:value "Change My Avatar", :type "text"}]
    [:p.pl-4.mt-4.mb-2.flex.justify-between.ffd
     [:span {:ffd "ffd"} "DESCRIPTIONS"]
     [:span.text-Cffffff80.tracking-wide "30/1,500"]]
    [:div.w-full.bg-C81c6dd1a.text-Cd6d6d6.p-1_5
     [:textarea.tracking-wide.bg-transparent.py-2.px-2_5.text-lg.w-full.h-44.ffd
      "descriptions: njahidbcascbjasbcasj scnbuascj asjcxnausjcjasncja,descriptions: njahidbcascbjasbcasj scnbuascj asjcxnausjcjasncjadescriptions: njahidbcascbjasbcasj scnbuascj asjcxnausjcjasncjadescriptions: njahidbcascbjasbcasj scnbuascj asjcxnausjcjasncjadescriptions: njahidbcascbjasbcasj scnbuascj asjcxnausjcjasncja"]
     [:p.w-full.text-right.ffd.p-2.border-t-1px.border-solid.border-C96a1ae
      "Add files by dragging and dropping selections and pasting"]]]])
