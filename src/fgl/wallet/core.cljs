(ns fgl.wallet.core
  (:require
   [lambdaisland.glogi :as log]
   ["ethers" :as ethers]
   [oops.core :refer [ocall]]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(declare connect!)
(defonce provider (atom nil))

(defn request
  ([method] (request method []))
  ([method params] (request method params identity identity))
  ([method on-success on-failure] (request method [] on-success on-failure))
  ([method params on-success on-failure]
   (let [p      @provider
         params (or params [])
         promise
         (ocall p "send" method (clj->js params))]
     (if p (-> promise
               (p/then on-success)
               (p/catch on-failure))
         (on-failure (js/Error. "No provider found")))
     promise)))

(defn reinit-provider []
  (reset! provider (ethers/providers.Web3Provider. js/ethereum)))

(defn check-installation [_]
  (when (and js/window.ethereum js/ethereum.isMetaMask)
    (reinit-provider)
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
        (rf/dispatch [::connected (first addrs)])
        (rf/dispatch [::disconnected])))))

(defn check-chain [db]
  (when (= (::state db) :installed)
    (p/let [chainId (request "eth_chainId")]
      (rf/dispatch [::chain-changed chainId]))))

(rf/reg-event-db
 ::connected
 [rf/trim-v]
 (fn [db [addr]]
   (let [addr (if (string? addr) addr (first addr))]
     (assoc db ::state :connected ::addr addr))))

(rf/reg-event-db
 ::disconnected
 (fn [db _]
   (assoc db ::state :installed ::addr nil)))

(rf/reg-event-fx
 ::accounts-changed
 [rf/trim-v]
 (fn [_ [addrs]]
   (if (count-addrs addrs)
     {:fx [[:dispatch [::connected (first addrs)]]]}
     {:fx [[:dispatch [::disconnected (first addrs)]]]})))

(rf/reg-event-db
 ::chain-changed
 [rf/trim-v]
 (fn [db [chainId]]
   (reinit-provider)
   (let [target-chain (::target-chain db)]
     (assoc db ::current-chain chainId
            ::provider @provider
            ::wrong-network (and target-chain (not (= target-chain chainId)))))))

(rf/reg-event-db
 ::installed
 [(rf/after check-connection)
  (rf/after check-chain)]
 (fn [db _]
   (assoc db
          ::state :installed
          ::provider @provider)))

(rf/reg-event-db
 ::init!
 [rf/trim-v
  (rf/after check-installation)]
 (fn [db [target-chain-id]]
   (when-not (::state db)
     (assoc db
            ::state :uninstalled
            ::addr nil
            ::provider nil
            ::target-chain target-chain-id))))

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
 ::addr
 (fn [db _]
   (get db ::addr)))

(rf/reg-sub
 ::provider
 (fn [db _]
   (get db ::provider)))

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

(def accounts-changed-interceptor
  (rf/->interceptor
   :id :wallet/accounts-changed
   :before (fn [context]
             (let [new-db   (rf/get-effect context :db)
                   old-db   (rf/get-coeffect context :db)
                   acc-changed? (not (= (::addr new-db) (::addr old-db)))]
               (rf/assoc-coeffect context :wallet/accounts-changed true)))))

(rf/reg-global-interceptor accounts-changed-interceptor)
