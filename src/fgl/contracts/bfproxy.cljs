(ns fgl.contracts.bfproxy
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.bfproxy.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :bfproxy :address conf/contract-addr-battlefield-proxy :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

;; join
;; commitUnstake
;; revealUnstake
(rf/reg-event-fx
 ::send
 (fn [{:keys [db]} [_ method & params]]
   (let [{::w/keys [provider]} db]
     ;; TODO: handle write contract result
     (ctc/with-provider c provider
       (apply r method params)))
   {}))
