(ns fgl.contracts.kingdoms
  (:require
   [fgl.config :as conf]
   [fgl.contracts.kingdoms.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :kingdoms :address conf/contract-addr-kingdoms :abi abi/data))
