(ns fgl.app.views.guild-vote
  (:require
   ["ethers" :as ethers]
   [clojure.string :as s]
   [fgl.app.snapshot :as snapshot]
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

(rf/reg-sub
 ::data
 (fn [db [_ proposal-id]]
   (let [proposal (get-in db [::snapshot/proposal proposal-id])
         votes    (get-in db [::snapshot/votes proposal-id])]
     {:proposal proposal
      :votes    votes})))

(defn main [{:keys [path-params]}]
  (let [{:keys [proposal-id]}    path-params
        {:keys [proposal votes]} @(rf/subscribe [::data proposal-id])]
    (r/create-class
     {:component-did-mount
      (fn []
        (if proposal-id
          (do (rf/dispatch [::snapshot/proposal proposal-id])
              (rf/dispatch [::snapshot/votes proposal-id])))
        (rf/dispatch [:navigate :route/guild-vote-proposal {:proposal-id "0x0eb23ea0b877666ad3ddcd0d7da0114acdfe5ae6390b5628b7509f4338022db5"}]))
      :reagent-render
      (fn []
        [:div "vote"
         [:pre (str proposal)]
         [:pre (str votes)]])})))
