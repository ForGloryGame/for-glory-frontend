(ns fgl.utils
  (:require
   [fgl.config :as conf]
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

(defn scan-url [& args]
  (apply str conf/explorer-url args))

(defn scan-tx-url [hash]
  (scan-url "/tx/" hash))

(defn scan-addr-url [addr]
  (scan-url "/address/" addr))
