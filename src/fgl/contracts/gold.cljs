(ns fgl.contracts.gold
  (:require
   [fgl.config :as conf]
   [fgl.contracts.erc20.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :gold :address conf/contract-addr-gold :abi abi/data))
