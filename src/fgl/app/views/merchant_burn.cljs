(ns fgl.app.views.merchant-burn
  (:require
   ["ethers" :as ethers]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.checkbox :as checkbox]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.eth-img :as ethimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.separator :as separator]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.landeed :as landeed]
   [fgl.re-frame]
   [fgl.utils :refer [sbn]]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       ;; get staked info
       (rf/dispatch [::landeed/init]))
    :stop identity}])

(defn style []
  [:style "
      .guild-burn-land-deeds button>figure {
        transition: all 0.2s ease;
      }

      .guild-burn-land-deeds button:nth-child(n + 2) {
        margin-left: 0.8rem;
      }

      .guild-burn-land-deeds button>figure:hover {
        transform: scale(1.05);
      }

      .guild-burn-land-deeds button>figure .selected {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        border: 0.25rem solid #fff;
        border-radius: 4px;
      }
      .guild-burn-land-deeds button>figure .selected::after {
        content: \"\";
        position: absolute;
        width: 0;
        height: 0;
        border: 1rem solid transparent;
        border-right: 1rem solid #fff;
        transform: translate(calc(-50% - 1px), calc(-50% - 1px)) rotate(45deg);
      }

      .guild-burn-land-deeds figcaption {
        text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.4);
      }
      figure.dt2>img {
        filter: hue-rotate(230deg) saturate(.6);
      }
      figure.dt1>img {
        filter: hue-rotate(171deg) saturate(.6);
      }
      figure.dt0>img {
        filter: hue-rotate(57deg) saturate(.6);
      }"])

(rf/reg-event-db ::payment (fn [db [_ p]] (assoc db ::payment p)))
(rf/reg-event-db ::token-id (fn [db [_ p]] (assoc db ::token-id p)))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys       [addr]
          ::keys         [token-id]
          ::landeed/keys [rgold reth total]}
         db

         token-ids (get-in db [addr ::landeed/token-ids])

         ttype     (get-in db [::landeed/type token-id])
         locked-lp (get-in db [::landeed/locked (and token-id token-id)])

         eth (-> (sbn locked-lp)
                 (.mul (sbn reth))
                 (.mul 9)
                 (.div 10)
                 (.div (sbn total 1)))

         gold (-> (sbn locked-lp)
                  (.mul (sbn rgold))
                  (.mul 9)
                  (.div 10)
                  (.div (sbn total 1)))]

     {:token-ids token-ids
      :token-id  (or token-id ::token-id)
      :ttype     ttype
      :eth       eth
      :gold      gold})))

(defn token [id]
  (r/create-class
   {:component-did-mount
    (fn []
      (rf/dispatch [::landeed/type id])
      (rf/dispatch [::landeed/locked id]))
    :reagent-render
    (fn []
      (let [{:keys [ttype token-id]} @(rf/subscribe [::data])
            class                    (str "dt" (or ttype 0))]

        ^{:key id}
        [:button.text-left
         {:on-click #(rf/dispatch [::token-id id])}
         [:figure.relative.w-11_2rem
          {:className class}
          [:img {:src "/images/mega.png"}]
          (and (= id token-id) [:div.selected])]]))}))

(defn tokens []
  (let [{:keys [token-ids]} @(rf/subscribe [::data])]
    [:<>
     [:p.text-C6bc9db.mb-4.text-lg "My Land Deeds"]
     (into [:div.flex.bg-C81c6dd1a.py-3.pl-2.pr-6.rounded-sm.relative] (map #(vector token %) token-ids))]))

(defn returns []
  (let [{:keys [eth gold]} @(rf/subscribe [::data])]
    [:<>
     [:p.text-C6bc9db.mb-4.mt-6.text-lg
      "Your returned token will be"]
     [:ul.grid.grid-cols-2.gap-x-6.text-3xl
      [:li.bg-C81c6dd1a.leading-3rem.rounded-sm
       {:on-click #(rf/dispatch [::payment :eth])}
       [:div.flexr
        [ethimg/ui "2rem"]
        [balance/ui eth {:fixed 4}]]]
      [:li.bg-C81c6dd1a.leading-3rem.rounded-sm
       {:on-click #(rf/dispatch [::payment :gold])}
       [:div.flexr
        [goldimg/ui "2rem"]
        [balance/ui gold {:fixed 4}]]]]]))

(rf/reg-event-db
 ::burn-checked
 (fn [db [_ checked?]]
   (assoc db ::burn-checked checked?)))

(rf/reg-sub
 ::burn-checked
 (fn [db _]
   (get db ::burn-checked false)))

(defn burn-checkbox []
  (let [checked? @(rf/subscribe [::burn-checked])]
    [checkbox/ui
     {:width "1.5rem"
      :text  [:span.text-xl.ml-2.fb "Do not remind me next time"]}
     {:className       "flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center"
      :onCheckedChange #(rf/dispatch [::burn-checked %])
      :checked         checked?}]))

(defn burn []
  (let [{:keys [token-id eth gold]} @(rf/subscribe [::data])
        burn-checked?               @(rf/subscribe [::burn-checked])
        skip?                       #(js/localStorage.getItem "burn-checked")
        f1                          #(if burn-checked?
                                       (js/localStorage.setItem "burn-checked" 1)
                                       (js/localStorage.removeItem "burn-checked"))
        f2                          #(rf/dispatch
                                      [::landeed/send
                                       {:method :redeem
                                        :params [#js [token-id] eth gold]
                                        :on-success
                                        (fn []
                                          (rf/dispatch [::landeed/init-raw])
                                          (rf/dispatch [::landeed/pair-init-raw])
                                          (dialog/on-success))}])
        burn                        (fn []
                                      (if (skip?)
                                        (f2)
                                        (rf/dispatch
                                         [::dialog/set
                                          :open true
                                          :title ""
                                          :desc [:<>
                                                 [:p.ffd.text-base.dialog-body
                                                  "Notice: If you choose to Burn your Land Deed, you will receive 95% of the
token you used to mint the Land Deed. The rest 5% are being charged as
handling fee. Afterwards, you will lose corresponding utilities of this Land
Deed."]
                                                 [:br]
                                                 [burn-checkbox]]
                                          :actions [:<>
                                                    [btn/ui {:t :osm :on-click (comp f2 f1) :className "mr-8"} "Confirm"]
                                                    [btn/ui {:t :bsm :on-click #(rf/dispatch [::dialog/set :remove true])} "Cancel"]]])))]
    [:div.text-center
     [btn/ui
      {:t        :osm
       :on-click burn}
      "BURN"]]))

(defn main []
  [:<>
   [style]
   [:div.guild-burn-land-deeds.relative.px-10.py-4
    [tokens]
    [returns]
    [separator/ui {:className "mt-24 mb-8"}]
    [burn]]])
