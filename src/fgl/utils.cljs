(ns fgl.utils
  (:require
   ["ethers" :as ethers]))

(defn ->display-token [bignumberish]
  (-> bignumberish
      ethers/BigNumber.from
      (ethers/utils.formatUnits 18)
      ethers/utils.commify))

(defn ->token-ids [token-ids-in-str-or-int]
  (->> (map int token-ids-in-str-or-int)
       sort
       clj->js))
