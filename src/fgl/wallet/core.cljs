(ns fgl.wallet.core
  (:require
   ["ethers" :as ethers]
   [oops.core :refer [ocall]]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(declare connect!)
(defonce provider (atom nil))

(defn request
  ([method] (request method []))
  ([method params]
   (ocall @provider "send" method (clj->js params))))

(defn check-installation [_]
  (when (and js/ethereum js/ethereum.isMetaMask)
    (reset! provider (ethers/providers.Web3Provider. js/ethereum))
    (rf/dispatch [::installed])
    (ocall js/ethereum "?.removeAllListeners")
    (js/ethereum.on "accountsChanged" #(rf/dispatch [::accounts-changed %]))
    (js/ethereum.on "chainChanged" #(rf/dispatch [::chain-changed %]))))

(defn- count-addrs [addrs]
  (if (pos? (count addrs)) addrs false))

(defn check-connection [db]
  (when (= (::state db) :installed)
    (p/let [addrs (request "eth_accounts")]
      (if (count-addrs addrs)
        (rf/dispatch [::connected addrs])
        (rf/dispatch [::disconnected])))))

(defn check-chain [db]
  (when (= (::state db) :installed)
    (p/let [chainId (request "eth_chainId")]
      (rf/dispatch [::chain-changed chainId]))))

(rf/reg-event-db
 ::connected
 [rf/trim-v]
 (fn [db [addrs]]
   (assoc db ::state :connected ::addrs addrs)))

(rf/reg-event-db
 ::disconnected
 (fn [db _]
   (assoc db ::state :installed :addrs [])))

(rf/reg-event-fx
 ::accounts-changed
 [rf/trim-v]
 (fn [_ [addrs]]
   (if (count-addrs addrs)
     {:fx [[:dispatch [::connected addrs]]]}
     {:fx [[:dispatch [::disconnected addrs]]]})))

(rf/reg-event-db
 ::chain-changed
 [rf/trim-v]
 (fn [db [chainId]]
   (let [target-chain (::target-chain db)]
     (assoc db ::current-chain chainId
            ::wrong-network (and target-chain (not (= target-chain chainId)))))))

(rf/reg-event-db
 ::installed
 [(rf/after check-connection)
  (rf/after check-chain)]
 (fn [db _]
   (assoc db ::state :installed)))

(rf/reg-event-db
 ::init!
 [rf/trim-v
  (rf/after check-installation)]
 (fn [db [target-chain-id]]
   (assoc db
          ::state :uninstalled
          ::addrs []
          ::provider nil
          ::target-chain target-chain-id)))

(rf/reg-sub
 ::path
 (fn [db [_ & paths]]
   (get-in db paths)))

(rf/reg-sub
 ::state
 (fn [db _]
   (get db ::state)))

(rf/reg-sub
 ::current-chain
 (fn [db _]
   (get db ::current-chain)))

(rf/reg-sub
 ::target-chain
 (fn [db _]
   (get db ::target-chain)))

(rf/reg-sub
 ::wrong-network
 (fn [db _]
   (get db ::wrong-network)))

(rf/reg-sub
 ::addrs
 (fn [db _]
   (get db ::addrs)))

(rf/reg-event-fx
 ::connect!
 (fn [_ _]
   (connect!) {}))

(rf/reg-event-fx
 ::switch-to-target-chain!
 (fn [{:keys [db]} _]
   (request "wallet_switchEthereumChain" [{:chainId (::target-chain db)}])
   {}))

(defn init!
  ([] (init! {}))
  ([{:keys [target-chain-id]}]
   (rf/dispatch-sync [::init! target-chain-id])))

(defn connect! []
  (p/then (request "eth_requestAccounts") #(rf/dispatch [::connected %])))
