(ns fgl.app.views.guild-vote
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
  [:div "vote"])
