(ns fgl.contracts.gameminter
  (:require
   [fgl.re-frame :refer [reg-event-pfx]]
   [lambdaisland.glogi :as log]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.gameminter.abi :as abi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :gameminter :address conf/contract-addr-minter :abi abi/data))

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
         (p/let [unrevealed-commits (r :mintCommits addr)]
           (when (and (js/Array.isArray unrevealed-commits) (number? (nth unrevealed-commits 2))
                      (pos? (nth unrevealed-commits 2)))
             (rf/dispatch [::set (nth unrevealed-commits 2) addr ::to-reveal]))))))

   {}))

(ctc/reg-send c ::send)
