(ns fgl.app.views.guild-alter
  (:require
   ["ethers" :as ethers]
   [clojure.string :as s]
   [taoensso.encore :as enc]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.sgold :as sgold]
   [fgl.contracts.gamenft :as nft]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.contracts.gold :as gold]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::gold/allowance (.-address sgold/c)])
       (rf/dispatch [::kingdom/init])
       (rf/dispatch [::sgold/init])
       (rf/dispatch [::gold/init]))
    :stop identity}])

(rf/reg-event-db
 ::input
 (fn [db [_ value]]
   (let [{::w/keys [addr]} db
         gold-balance     (get-in db [addr ::gold/balance])
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

       (and (.gte v 0) (.lte v gold-balance))
       (assoc db ::input v
              ::input-str value)

       :else
       db))))

(rf/reg-event-db
 ::weeks
 (fn [db [_ value]]
   (assoc db ::weeks value)))

(rf/reg-sub
 ::left-data
 (fn [db _]
   (let [{::keys [input-str input weeks] ::w/keys [addr]} db
         weeks                                            (or weeks 8)
         gold-allowance
         (get-in db [addr ::gold/allowance (.-address sgold/c)] (ethers/BigNumber.from "0"))
         gold-balance
         (get-in db [addr ::gold/balance] (ethers/BigNumber.from "0"))]
     {:weeks        weeks
      :approved?    (or (not input) (.gte gold-allowance input))
      :gold-balance gold-balance
      :input        input
      :input-str input-str
      :output       (-> (or input (ethers/BigNumber.from "0"))
                        (.mul weeks))})))
(rf/reg-sub
 ::right-data
 (fn [db _]
   (let [{::w/keys [addr]} db
         unlocked-sgold (get-in db [addr ::sgold/unlocked])]
     unlocked-sgold)))

(defn week-btn [n]
  (let [{:keys [weeks]} @(rf/subscribe [::left-data])]
    ^{:key n}
    [:button.rounded.py-1
     {:on-click  #(rf/dispatch [::weeks n])
      :className (if (= weeks n)
                   "bg-C71edf59c"
                   "bg-Cffffff26")}
     (str n " WEEKS")]))

(defn left []
  (let [{:keys [input input-str output weeks gold-balance approved?]} @(rf/subscribe [::left-data])]
    [:div.relative.px-10
     [:div.w-full.text-base.mt-4.mb-3.ffd.flexrs.text-C6bc9db
      "Amount " [goldimg/ui "2.5rem"] "sacrificed:"]
     [:input.w-full.text-4xl.bg-C0d293180.py-3_5.text-center
      {:on-change #(rf/dispatch [::input (-> % .-target .-value)])
       :value     input-str
       :style
       {:boxShadow "inset 1px 1px #111, 1px 1px rgba(116, 191, 206, 0.2)"}}]
     [:div.w-full.text-right.mt-1.ffd.text-Cffffff80 "MAX:" [balance/ui gold-balance {:className "ffd"}]]
     [:div.w-full.mt-6.mb-2.ffd.text-C6bc9db "Locked weeks:"]
     [:div.w-full.grid.grid-cols-4.gap-5.fb
      [week-btn 4]
      [week-btn 8]
      [week-btn 16]
      [week-btn 32]]
     [separator/ui {:className "my-12"}]
     [:div.w-full.mb-2.ffd.text-C6bc9db "Your sGOLD in return will be:"]
     [:div.flexr.w-full.bg-C81c6dd1a.py-3.text-4xl [gloryimg/ui "2.5rem"] [balance/ui output {:style {:maxWidth "40rem"}}]]
     [btn/ui
      {:className "absolute bottom-12 transform left-1/2 -translate-x-1/2 bg-Ced8e28 border-Cffd386 w-14rem text-xl"
       :t         :olg
       :on-click  #(rf/dispatch (if approved? [::sgold/send {:method :lock :params [input weeks]
                                                             :on-success
                                                             (fn []
                                                               (rf/dispatch [::input ""])
                                                               (rf/dispatch [::gold/init-raw])
                                                               (rf/dispatch [::gold/allowance (.-address sgold/c)])
                                                               (rf/dispatch [::sgold/init-raw]))}]
                                    [::gold/send
                                     {:method     :approve
                                      :params     [(.-address sgold/c)
                                                   "0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"]
                                      :on-success (fn [_]
                                                    (rf/dispatch
                                                     [::gold/allowance
                                                      (.-address sgold/c)]))}]))}
      (if approved? "SACRIFICED" "APPROVE")]]))

(comment
  (rf/dispatch [::gold/send
                {:method     :approve
                 :params     [(.-address sgold/c)
                              "0x0"]
                 :on-success (fn [_]
                               (log/debug :hhhhhhh :h)
                               (rf/dispatch
                                [::gold/allowance
                                 (.-address sgold/c)]))}]))

(defn right []
  (let [unlocked @(rf/subscribe [::right-data])]
    [:div.relative.h-full.text-center.bg-gradient-to-b.from-transparent.to-C50929e3b.bg-no-repeat
     {:style {:backgroundImage    "url(/images/for-glory-bg.svg)"
              :backgroundSize     "140%"
              :backgroundPosition "50% 2%"}}
     [:p.mt-12.text-base.px-16.ffd "Your $GOLD eligible to redeem"]
     [:p.guild-font-family.text-4xl.mt-6.flexr [goldimg/ui "2.5rem"] (balance/ui unlocked)]
     [btn/ui
      {:className "absolute bottom-12 left-1/2 transform -translate-x-1/2 text-xl"
       :t         :bsm
       :style     {:width "10rem"}}
      "REDEEM"]]))

(defn main []
  [:div.grid.fb
   {:style {:gridTemplateColumns "1fr 18rem"}}
   [left]
   [right]])
