(ns fgl.contracts.landeed
  (:require
   ["ethers" :as ethers]
   [fgl.re-frame :refer [reg-event-pfx]]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oapply+ ocall]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [fgl.config :as conf]
   [fgl.contracts.landeed.abi :as abi]
   [fgl.contracts.pair.abi :as pabi]
   [fgl.contracts :as ctc]
   [promesa.core :as p]))

(def c (ctc/init :id :landeed :address conf/contract-addr-landeed :abi abi/data))
(defonce pairc (atom nil))

(defonce DeedType {0 :standard 1 :medium 2 :large 3 :mega})

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(rf/reg-sub
 ::balance
 (fn [db [_ addr]]
   (.toString (get-in db [addr ::balance] 0))))

(rf/reg-sub
 ::token-ids
 (fn [db [_ addr]]
   (get-in db [addr ::token-ids])))

(reg-event-pfx
 ::pair-init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db
         paire-contract             @pairc]
     (when addr
       (ctc/with-provider paire-contract provider
         (p/then
          (r :totalSupply)
          #(rf/dispatch [::set % ::total]))
         (p/then
          (r :getReserves)
          (fn [[gold weth]]
            (rf/dispatch [::set gold ::rgold])
            (rf/dispatch [::set weth ::reth])
            (if (.eq weth 0)
              (rf/dispatch [::set (ethers/BigNumber.from 1) ::eth-gold-ratio])
              (rf/dispatch [::set (.div gold weth) ::eth-gold-ratio])))))))

   {}))

(reg-event-pfx
 ::init
 10000
 [rf/trim-v]
 (fn [{:keys [db]} _]
   (let [{::w/keys [provider addr]} db]
     (when addr
       (ctc/with-provider c provider
         (p/let [deedsInfo (p/all (map #(r :deedsInfo %) (keys DeedType)))]
           (loop [idx   0
                  items deedsInfo]
             (when-let [item (first items)]
               (rf/dispatch [::set (first item) ::info idx :price])
               (recur (inc idx) (rest items)))))

         (p/then (r :tokensOfOwner addr)
                 #(rf/dispatch [::set % addr ::token-ids]))
         (p/then (r :balanceOf addr)
                 #(rf/dispatch [::set % addr ::balance]))
         (p/then (r :pair)
                 #(do
                    (reset! pairc (ctc/init :id :eth-gold-pair :address % :abi pabi/data))
                    (rf/dispatch [::set % ::pair])
                    (rf/dispatch [::pair-init])))
         (p/then (r :minGoldAmount)
                 #(rf/dispatch [::set % ::minGoldAmount]))
         (p/then (r :maxGoldAmount)
                 #(rf/dispatch [::set % ::maxGoldAmount])))))

   {}))

(ctc/reg-send c ::send)
