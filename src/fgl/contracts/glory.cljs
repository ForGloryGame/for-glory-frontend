(ns fgl.contracts.glory
  (:require
   [fgl.config :as conf]
   [fgl.contracts.erc20.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :glory :address conf/contract-addr-glory :abi abi/data))
