(ns fgl.contracts.gamenft
  (:require
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.gamenft.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :gamenft :address conf/contract-addr-gamenft :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (let [old (get-in db paths)]
     (if (= old v)
       db
       (assoc-in db paths v)))))

(rf/reg-sub
 ::balance
 (fn [db [_ addr]]
   (.toString (get-in db [addr ::balance] 0))))

(defonce token-ids-cache (atom nil))

(rf/reg-sub
 ::token-ids
 (fn [db [_ addr]]
   (if @token-ids-cache @token-ids-cache
       (reset! token-ids-cache (get-in db [addr ::token-ids])))))

(rf/reg-event-fx
 ::get
 [rf/trim-v]
 (fn [_ [provider addr]]
   (and addr
        (ctc/with-provider c provider
          (p/let [balance (r :balanceOf addr)
                  token-ids (r :tokensOfOwner addr)]
            (rf/dispatch [::set token-ids addr ::token-ids])
            (rf/dispatch [::set balance addr ::balance]))))

   {}))

(rf/reg-event-fx
 ::send
 (fn [_ [_ provider method & params]]
   ;; TODO: handle write contract result
   (ctc/with-provider c provider
     (apply r method params))
   {}))
