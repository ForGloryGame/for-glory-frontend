(ns fgl.contracts
  (:require-macros [fgl.contracts :refer [with-provider with-provider-call]])
  (:require
   [fgl.utils :refer [scan-tx-url]]
   [fgl.app.ui.dialog :as dialog]
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
   (fn [{:keys [db]} [_ {:keys [method params dialog title submitting on-success on-submitted on-failure err-handler]}]]
     (let [{::w/keys [provider]} db
           params                (or params [])

           title (or title "")

           [submitting
            on-success
            on-submitted
            on-failure]
           (if (false? dialog)
             [submitting
              on-success
              on-submitted
              on-failure]
             [(or submitting true)
              (or on-success true)
              (or on-submitted true)
              (or on-failure true)])

           submitting (cond (true? submitting) dialog/submitting
                            (fn? submitting)   submitting
                            :else              identity)

           on-success
           (cond (fn? on-success)   on-success
                 (true? on-success) dialog/on-success
                 :else              identity)
           on-submitted
           (cond (fn? on-submitted)   on-submitted
                 (true? on-submitted) dialog/on-submitted
                 :else                identity)
           on-failure
           (cond (fn? on-failure)   on-failure
                 (true? on-failure) dialog/failed
                 :else              identity)
           on-failure (if (fn? err-handler) (comp on-failure err-handler) on-failure)]
       (with-provider c provider
         (-> (apply r method params)
             (p/then #(do
                        (if (and (.-hash %) (.-wait %))
                          (do
                            (on-submitted (.-hash %))
                            (p/then
                             (.wait % 1)
                             (fn [receipt]
                               (on-success
                                {:title "TX Confirmed"
                                 :desc  [:a
                                         {:href   (scan-tx-url (.-transactionHash receipt))
                                          :rel    "noopener noreferrer"
                                          :target "_blank"}
                                         "View On Blockchain Explorer"]}))))

                          (on-success {:title "TX Succeeded"}))))

             (p/catch #(if (instance? js/Error %)
                         (do
                           (js/console.error %)
                           (on-failure {:title "TX Failed"
                                        :desc  (or (oget % "?.error.message") (oget % "?.reason") (oget % "message"))}))
                         (on-failure {:title "TX Failed"}))))
         (rf/dispatch [::dialog/set :title title])
         (submitting)))
     {})))
