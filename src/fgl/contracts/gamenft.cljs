(ns fgl.contracts.gamenft
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
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
   (assoc-in db paths v)))

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
 ::get-token-traits
 (fn [{:keys [db]} [_ token-id]]
   (when-not (get-in db [::traits token-id])
     (let [{::w/keys [provider]} db]
       (ctc/with-provider c provider
         (p/let [trait (r :getTokenTraits token-id)
                 [is-lord peerage] trait
                 token-id-str (.toString token-id)]
           (rf/dispatch [::set is-lord ::traits token-id-str :is-lord])
           (rf/dispatch [::set peerage ::traits token-id-str :peerage])))))
   {}))

(reg-event-pfx
 ::get
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [balance (r :balanceOf addr)
                 token-ids (r :tokensOfOwner addr)]
           (rf/dispatch [::set token-ids addr ::token-ids])
           (rf/dispatch [::set balance addr ::balance])
           (doseq [id token-ids]
             (rf/dispatch [::get-token-traits id]))))))

   {}))

(rf/reg-event-fx
 ::send
 (fn [{:keys [db]} [_ method & params]]
   (let [{::w/keys [provider]} db]
     (ctc/with-provider c provider
       (apply r method params)))
   {}))
