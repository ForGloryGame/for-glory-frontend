(ns fgl.contracts.gamepass
  (:require
   [fgl.config :as conf]
   [fgl.contracts.gamepass.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :gamepass :address conf/contract-addr-gamepass :abi abi/data))
