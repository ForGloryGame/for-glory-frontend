(ns fgl.utils
  (:require
   [fgl.config :as conf]
   ["ethers" :as ethers]))

(defn calc [w x]
  (str "calc(" w " * " x ")"))

(defn ->display-token
  ([bignumberish] (->display-token bignumberish 2))
  ([bignumberish to-fixed-decimals]
   (let [bignumberish (ethers/BigNumber.from bignumberish)
         mod          (.pow (ethers/BigNumber.from 10) (- 18 to-fixed-decimals))
         sub          (.mod bignumberish mod)]
     (-> bignumberish
         (.sub sub)
         (ethers/utils.formatUnits 18)
         ethers/utils.commify))))

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
