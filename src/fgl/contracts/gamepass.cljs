(ns fgl.contracts.gamepass
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   ["ethers" :as ethers]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.gamepass.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :gamepass :address conf/contract-addr-gamepass :abi abi/data))

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

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [;; balance (r :balanceOf addr)
                 token-ids (r :tokensOfOwner addr)]
           (rf/dispatch [::set token-ids addr ::token-ids])
           ;; (rf/dispatch [::set balance addr ::balance])
           ))))

   {}))

(ctc/reg-send c ::send)
