(ns fgl.app.views.merchant-swap
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [clojure.string :as s]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.separator :as separator]
   [fgl.re-frame]
   [fgl.wallet.core :as w]
   [fgl.app.ui.btn :as btn]
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
       (rf/dispatch [::glory/allowance (.-address exchange/c)])
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
         ratio        (or ratio (ethers/BigNumber.from "1"))
         input        (or input (ethers/BigNumber.from "0"))

         receive (.div input ratio)

         glory-allowance
         (get-in db [addr ::glory/allowance (.-address exchange/c)] (ethers/BigNumber.from "0"))]
     {:input       input
      :approved?   (.lte input glory-allowance)
      :ratio       (/ 1 ratio)
      :input-str   input-str
      :receive     receive
      :balance-rst (.add gold-balance receive)})))

(defn main []
  (let [{:keys [input approved? input-str ratio receive balance-rst]} @(rf/subscribe [::data])]
    [:div.relative.px-10.fb
     [:div.w-full.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db
      "Exchange Rate:"]
     [:div.w-full.text-xl.py-3.bg-C81c6dd1a.px-40
      [:div.grid.grid-cols-5.items-center.m-auto.w-30%
       [gloryimg/ui "2rem"]
       [:span.mx-2 "1"]
       [:div.flex.flex-col
        [:img.w-6 {:src "/images/ratio.svg"}]
        [:img.w-6 {:style {:transform "rotate(180deg)"} :src "/images/ratio.svg"}]]
       ratio
       [goldimg/ui "2rem" {:className "ml-2"}]]]
     [:div.w-full.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db
      "Amount of $GLORY to exchange:"]
     [:input.w-full.text-4xl.bg-C0d293180.py-3_5.text-center
      {:on-change #(rf/dispatch [::input (-> % .-target .-value)])
       :value     input-str
       :style
       {:boxShadow "inset 1px 1px #111, 1px 1px rgba(116, 191, 206, 0.2)"}}]
     [separator/ui {:className "mt-14 mb-4"}]
     [:div.grid.grid-cols-2.gap-4
      [:div
       [:div.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db
        "You will receive:"]
       [:div.flexr.w-full.bg-C81c6dd1a.py-3.text-4xl
        [goldimg/ui "2.5rem"]
        [balance/ui
         receive
         {:fixed 4
          ;; :keep-end true
          :style {:maxWidth "20rem"}}]]]
      [:div
       [:div.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db
        "You $GOLD Balance will be:"]
       [:div.flexr.w-full.bg-C81c6dd1a.py-3.text-4xl
        [goldimg/ui "2.5rem"]
        [balance/ui
         balance-rst
         {:style {:maxWidth "20rem"}}]]]]
     [:div.w-full.flexr.mt-20
      [btn/ui
       {:on-click #(rf/dispatch
                    (if approved?
                      identity
                      [::glory/send
                       {:method :approve
                        :params [(.-address exchange/c) "0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"]
                        :on-success
                        (fn [_]
                          (rf/dispatch [::glory/allowance (.-address exchange/c)]))}]))
        :t        :osm}
       (if
        approved?
         "CONFIRM"
         "APPROVE")]]]))