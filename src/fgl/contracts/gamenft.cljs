(ns fgl.contracts.gamenft
  (:require
   [fgl.config :as conf]
   [fgl.contracts.gamenft.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :gamenft :address conf/contract-addr-gamenft :abi abi/data))
