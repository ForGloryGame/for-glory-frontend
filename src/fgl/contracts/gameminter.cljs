(ns fgl.contracts.gameminter
  (:require
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.gameminter.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :gameminter :address conf/contract-addr-minter :abi abi/data))

(rf/reg-event-fx
 ::send
 (fn [_ [_ provider method & params]]
   (ctc/with-provider c provider
     (apply r method params))
   {}))
