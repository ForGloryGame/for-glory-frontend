(ns fgl.contracts
  (:require-macros [fgl.contracts :refer [with-provider with-provider-call]])
  (:require
   [lambdaisland.glogi :as log]
   ["ethers" :refer [Contract]]
   [promesa.core :as p]
   [oops.core :refer [oapply+ ocall]]))

(defonce cache (atom nil))

(defn re-init! [& {:keys [address abi]}]
  (Contract. (or address "0x1111111111111111111111111111111111111111") (clj->js abi)))

(defn init [& {:keys [id address abi reinit!]}]
  (assert abi "Inavlid contract abi")
  (cond (or reinit! (and id (not (get @cache id))))
        (let [c (re-init! :address address :abi abi)]
          (when id (swap! cache #(assoc % id c)))
          c)
        (and id (get @cache id))
        (get @cache id)
        :else
        (re-init! :address address :abi abi)))

(defn connect [c provider]
  (ocall c "connect" (ocall provider "getSigner")))

(defn get-request-fn [contract]
  (assert contract "Invalid contract instance")
  (fn [method-name & args]
    ;; (log/debug :contract-call [(name method-name) args])
    (p/let [rst (oapply+ contract (name method-name) args)]
      rst)))

(defn get-call-fn [contract]
  (assert contract "Invalid contract instance")
  (fn [method-name & args]
    (p/let [rst (oapply+ contract (str "callStatic." (name method-name)) args)]
      rst)))
