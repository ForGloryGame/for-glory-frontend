(ns fgl.app.ui.balance
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.utils :refer [->display-token]]
   [lambdaisland.glogi :as log]))

(defn ui [amount]
  (let [amount (enc/cond
                 (instance? ethers/BigNumber amount)
                 amount
                 amount (try (ethers/BigNumber.from amount)
                             (catch js/Error e
                               (ethers/BigNumber.from 0)))
                 :else (ethers/BigNumber.from 0))]
    [:data {:value (.toString amount)} (->display-token amount)]))
