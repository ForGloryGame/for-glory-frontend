(ns fgl.contracts.bfproxy
  (:require
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

(rf/reg-sub
 ::token-ids
 (fn [db [_ addr]]
   (get-in db [addr ::token-ids] [])))

(rf/reg-sub
 ::rewards
 (fn [db [_ addr]]
   (get-in db [addr ::rewards])))

(rf/reg-event-fx
 ::get
 [rf/trim-v]
 (fn [_ [provider addr]]
   ;; (and addr
   ;;      (ctc/with-provider c provider
   ;;        (p/let [token-ids (r :depositsOf addr)
   ;;                rewards (p/all (map #(r :pendingReward %) token-ids))]
   ;;          (rf/dispatch [::set token-ids addr ::token-ids])
   ;;          (rf/dispatch [::set (log/spy rewards) addr ::rewards]))))

   {}))

;; join
;; commitUnstake
;; revealUnstake
(rf/reg-event-fx
 ::send
 (fn [_ [_ provider method & params]]
   ;; TODO: handle write contract result
   (ctc/with-provider c provider
     (apply r method params))
   {}))

(rf/reg-event-fx
 ::call
 (fn [_ [_ provider method & params]]
   ;; TODO: handle write contract result
   (ctc/with-provider-call c provider
     (apply r method params))
   {}))
