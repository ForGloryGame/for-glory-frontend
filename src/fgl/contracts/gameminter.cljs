(ns fgl.contracts.gameminter
  (:require
   [fgl.config :as conf]
   [fgl.contracts.gameminter.abi :as abi]
   [fgl.contracts :as ctc]))

(def c (ctc/init :id :gameminter :address conf/contract-addr-minter :abi abi/data))
