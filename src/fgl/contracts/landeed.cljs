(ns fgl.contracts.landeed
  (:require
   [fgl.config :as conf]
   [fgl.contracts.landeed.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :landeed :address conf/contract-addr-landeed :abi abi/data))
