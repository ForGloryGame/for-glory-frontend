(ns fgl.contracts.gameminter
  (:require
   [fgl.config :as conf]
   [fgl.contracts :as ctc]
   [fgl.contracts.gameminter.abi :as abi]
   [fgl.re-frame :refer [reg-event-pfx]]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(def c (ctc/init :id :gameminter :address conf/contract-addr-minter :abi abi/data))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} [_ re-check?]]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (when re-check?
         (rf/dispatch [::set false addr ::to-reveal]))
       (ctc/with-provider c provider
         (p/let [unrevealed-commits (r :mintCommits addr)
                 height (-> unrevealed-commits first .toString)]
           (when-not (= height "0")
             (rf/dispatch [::set true addr ::to-reveal]))))))

   {::w/raddrnet ::init-raw}))

(ctc/reg-send c ::send)
