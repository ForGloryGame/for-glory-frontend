(ns fgl.contracts.sgold
  (:require
   [fgl.config :as conf]
   [fgl.contracts.sgold.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :sgold :address conf/contract-addr-sgold :abi abi/data))
