(ns fgl.contracts.bfproxy
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   [lambdaisland.glogi :as log]
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

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [unrevealed-commits (r :unstakeCommits addr)
                 height (first unrevealed-commits)]
           (when-not (-> height .toString (= "0"))
             (rf/dispatch [::set height addr ::to-reveal]))))))

   {::w/raddrnet ::init-raw}))

;; join
;; commitUnstake
;; revealUnstake
(ctc/reg-send c ::send)
