(ns xxx.wallet
  (:require
   [xxx.rf-util :as rfu]
   ["ethers" :as ethers]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(defonce initialized (atom false))

(rfu/simple-reg
 ::state
 {:on-v
  (fn [v _db]
    (when (= v ::installed)
      (rf/dispatch [::provider (ethers/providers.Web3Provider. js/ethereum)])))})

(rfu/simple-reg ::provider)
(rfu/simple-reg ::current-account)
(rfu/simple-reg ::current-chain)

(defn installed? []
  (and js/ethereum js/ethereum.isMetaMask))

(defn- connected
  ([accounts] (connected accounts identity))
  ([accounts cb]
   (when-let [account (first accounts)]
     (rf/dispatch [::state ::connected])
     (rf/dispatch [::current-account account])
     (cb))))

(defn- disconnected []
  (rf/dispatch [::state ::installed])
  (rf/dispatch [::current-account nil]))

(defn- accounts-changed [accounts]
  (try
    (if (first accounts)
      (connected accounts)
      (disconnected))
    (catch js/Error err
      (disconnected))))

(defn- setup-listeners []
  (js/ethereum.on "accountsChanged" accounts-changed)
  (js/ethereum.on "chainChanged" #(rf/dispatch [::current-chain (or % nil)])))

(defn sconnected? []
  (= @(rf/subscribe [::state]) ::connected))
(defn sprovider []
  @(rf/subscribe [::provider]))
(defn saccount []
  @(rf/subscribe [::current-account]))
(defn schain []
  @(rf/subscribe [::current-chain]))

(defn init!
  ([] (init! {}))
  ([{:keys [target-chain-id installed]}]
   (when (and (not @initialized) (installed?))
     (setup-listeners)
     (and (fn? installed) (installed @(rf/subscribe [::provider])))
     (rf/dispatch [::state :installed])
     (-> (js/ethereum.request #js {:method "eth_chainId"})
         (p/then #(rf/dispatch [::current-chain %]))
         (p/catch identity))
     (-> (js/ethereum.request #js {:method "eth_accounts"})
         (p/then #(when-not (-> % count zero?) (connected % identity)))
         (p/catch identity))
     (reset! initialized true))))

(defn connect [& {:keys [failed success]}]
  (-> (js/ethereum.request #js {:method "eth_requestAccounts"})
      (p/then #(do (connected % (if (fn? success) success identity))))
      (p/catch #(and (fn? failed) (failed %)))))
