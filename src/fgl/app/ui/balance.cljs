(ns fgl.app.ui.balance
  (:require
   ["ethers" :as ethers]
   [fgl.utils :refer [->display-token]]
   [lambdaisland.glogi :as log]))

(defn ui [amount]
  (let [amount (or amount (ethers/BigNumber.from 0))]
    [:data {:value (.toString amount)} (->display-token amount)]))
