(ns fgl.contracts.battlefield
  (:require
   [fgl.config :as conf]
   [fgl.contracts.battlefield.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :battlefield :address conf/contract-addr-battlefield :abi abi/data))
