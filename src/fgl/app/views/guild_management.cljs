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
  [:div "vote"])
