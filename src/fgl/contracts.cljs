(ns fgl.contracts
  (:require-macros [fgl.contracts :refer [with-provider with-provider-call]])
  (:require
   [fgl.utils :refer [scan-tx-url]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [lambdaisland.glogi :as log]
   ["ethers" :refer [Contract]]
   [promesa.core :as p]
   [oops.core :refer [oget oapply+ ocall]]))

(defonce cache (atom nil))

(defn re-init! [& {:keys [address abi]}]
  (Contract. (or address "0x1111111111111111111111111111111111111111") (clj->js abi)))

(defn init [& {:keys [id address abi reinit!]}]
  (assert abi "Inavlid contract abi")
  (cond (or reinit! (and id (not (get @cache id))))
        (let [c (re-init! :address address :abi abi)]
          (when id (swap! cache #(assoc % id c)))
          c)
        (and id (get @cache id))
        (get @cache id)
        :else
        (re-init! :address address :abi abi)))

(defn connect [c provider]
  (ocall c "connect" (ocall provider "getSigner")))

(defn get-request-fn [contract]
  (assert contract "Invalid contract instance")
  (fn [method-name & args]
    ;; (log/debug :contract-call [(name method-name) args])
    (p/let [rst (oapply+ contract (name method-name) args)]
      rst)))

(defn get-call-fn [contract]
  (assert contract "Invalid contract instance")
  (fn [method-name & args]
    (p/let [rst (oapply+ contract (str "callStatic." (name method-name)) args)]
      rst)))

(defn reg-send [c id]
  (rf/reg-event-fx
   id
   (fn [{:keys [db]} [_ {:keys [method params on-success on-failure err-handler]}]]
     (let [{::w/keys [provider]} db
           params                (or params [])

           on-success
           (cond (fn? on-success)                               on-success
                 (or (nil? on-success) (= on-success :success)) #(rf/dispatch [:toast/success (log/spy %)])
                 (keyword? on-success)                          #(rf/dispatch [on-success %])
                 :else                                          identity)
           on-failure
           (cond (fn? on-failure)                               on-failure
                 (or (nil? on-failure) (= on-failure :failure)) #(rf/dispatch [:toast/failure %])
                 (keyword? on-failure)                          #(rf/dispatch [on-failure %])
                 :else                                          identity)
           on-failure (if (fn? err-handler) (comp on-failure err-handler) on-failure)]
       (with-provider c provider
         (-> (apply r method params)
             (p/then #(do
                        (if (and (.-hash %) (.-wait %))
                          (do
                            ;; (on-success
                            ;;  {:title "Tx Executed"
                            ;;   :desc  [:a {:href (scan-tx-url (.-hash %))}
                            ;;           "View On Blockchain Explorer"]})
                            (p/then
                             (.wait % 7)
                             (fn [receipt]
                               (on-success
                                {:title "Tx Confirmed"
                                 :desc  [:a
                                         (log/spy {:href   (scan-tx-url (.-transactionHash (log/spy receipt)))
                                                   :rel    "noopener noreferrer"
                                                   :target "_blank"})
                                         "View On Blockchain Explorer"]}))))

                          (on-success {:title "Tx Succeeded"}))))

             (p/catch #(if (instance? js/Error %)
                         (on-failure {:title "Tx Failed"
                                      :desc  (or (oget % "?.error.message") (oget % "?.reason") (oget % "message"))})
                         (on-failure {:title "Tx Failed"}))))))
     {})))
