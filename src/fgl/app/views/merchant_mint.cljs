(ns fgl.app.views.merchant-mint
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.glory-img :as gloryimg]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn main []
  [:div "mint"])
