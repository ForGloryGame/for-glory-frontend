(ns fgl.app.ui.balance
  (:require
   ["ethers" :as ethers]
   [fgl.utils :refer [->display-token]]
   [lambdaisland.glogi :as log]
   [taoensso.encore :as enc]))

(defn ui
  ([amount] (ui amount {}))
  ([amount opts]
   (let [amount (enc/cond
                  (instance? ethers/BigNumber amount)
                  amount
                  amount (try (ethers/BigNumber.from amount)
                              (catch js/Error e
                                (ethers/BigNumber.from 0)))
                  :else (ethers/BigNumber.from 0))]
     [:data (enc/nested-merge {:value (.toString amount)
                               :style {:maxWidth "6rem"}
                               :className "fb"}
                              (-> opts (dissoc :fixed) (dissoc :keep-end)))
      (->display-token amount opts)])))
