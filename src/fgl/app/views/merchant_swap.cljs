(ns fgl.app.views.merchant-swap
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [clojure.string :as s]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.re-frame]
   [fgl.wallet.core :as w]
   [fgl.app.ui.balance :as balance]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.glory :as glory]
   [fgl.contracts.exchange :as exchange]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::gold/init])
       (rf/dispatch [::glory/init])
       (rf/dispatch [::exchange/init]))
    :stop identity}])

(rf/reg-event-db
 ::input
 (fn [db [_ value]]
   (let [{::w/keys [addr]} db
         glory-balance     (get-in db [addr ::glory/balance])
         value             (-> value
                               (.replace  #"\.+" ".")
                               (.replace #"-" ""))
         v                 (try (ethers/utils.parseUnits value)
                                (catch js/Error _ :invalid))]
     (cond
       (= value "")
       (assoc db ::input (ethers/BigNumber.from "0")
              ::input-str "")

       (and (= v :invalid) (s/ends-with? value ".") (not (= (s/index-of value ".") (s/last-index-of value "."))))
       db

       (and (= v :invalid) (s/ends-with? value "."))
       (assoc db ::input-str value)

       (= v :invalid)
       db

       (and (.gte v 0) (.lte v glory-balance))
       (assoc db ::input v
              ::input-str value)

       :else
       db))))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::keys   [input input-str]
          ::w/keys [addr]
          ratio    ::exchange/ratio}
         db

         {gold-balance ::gold/balance}
         (get db addr)

         gold-balance (or gold-balance (ethers/BigNumber.from "0"))
         ratio (or ratio (ethers/BigNumber.from "1"))
         input (or input (ethers/BigNumber.from "0"))

         receive (.div input ratio)]
     {:input      input
      :input-str  input-str
      :receive     receive
      :balance-rst (.add gold-balance receive)})))

(defn main []
  (let [{:keys [input input-str receive balance-rst]} @(rf/subscribe [::data])]
    [:div "swap"]))
