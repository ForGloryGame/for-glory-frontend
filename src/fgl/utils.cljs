(ns fgl.utils
  (:require
   [lambdaisland.glogi :as log]
   [fgl.config :as conf]
   ["ethers" :as ethers]))

(defn calc [w x]
  (str "calc(" w " * " x ")"))

(defn keep-fixed [x n]
  (cond (and (> n 1) (.endsWith x ".0"))
        (.join (clj->js (into [x] (repeat (dec n) "0"))) "")
        (.test #"\." x)
        (let [a              (.lastIndexOf x ".")
              b              (count x)
              decimal-length (- b (inc a))
              n              (- n decimal-length)]
          (.join (clj->js (into [x] (repeat n "0"))) ""))
        :else
        (.join (clj->js (into [x "."] (repeat n "0"))) "")))

(defn strip-ends-zero
  ([n] (strip-ends-zero n {}))
  ([n {:keys [keep-end fixed]}]
   (cond (and (not keep-end) (string? n) (.test #"\.0+$" n))
         (.replace n #"\.0+" "")
         keep-end
         (keep-fixed n fixed)
         :else n)))

(comment
  (strip-ends-zero "0.01" {:keep-end true :fixed 4})
  (strip-ends-zero "0" {:keep-end true :fixed 4})
  (strip-ends-zero "1" {:keep-end true :fixed 4}))

(defn ->display-token
  ([bignumberish] (->display-token bignumberish {}))
  ([bignumberish {:keys [fixed keep-end]}]
   (let [fixed        (or fixed 2)
         bignumberish (ethers/BigNumber.from bignumberish)
         mod          (.pow (ethers/BigNumber.from 10) (- 18 fixed))
         sub          (.mod bignumberish mod)
         rst
         (-> bignumberish
             (.sub sub)
             (ethers/utils.formatUnits 18)
             ethers/utils.commify
             (strip-ends-zero {:keep-end keep-end :fixed fixed}))]
     rst)))

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

(defn shorten-addr [addr]
  (str (.substring addr 0 6) "...." (.substring addr 34)))
