(ns fgl.app.views.merchant-burn
  (:require
   ["ethers" :as ethers]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.eth-img :as ethimg]
   [lambdaisland.glogi :as log]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.btn :as btn]
   [re-frame.core :as rf]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.landeed :as landeed]))

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
      }"])

(rf/reg-event-db ::payment (fn [db [_ p]] (assoc db ::payment p)))
(rf/reg-event-db ::token-id (fn [db [_ p]] (assoc db ::token-id p)))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr] ::keys [type token-id] ::landeed/keys [info eth-gold-ratio]}
         db

         token-ids (get-in db [addr ::landeed/token-ids])

         type
         (or type 3)

         ethp
         (or (:price (get info type)) (ethers/BigNumber.from 0))

         goldp
         (.mul (or eth-gold-ratio (ethers/BigNumber.from 0)) ethp)]
     {:type      type
      :token-ids (or (seq token-ids) '(1 2 3 4 5))
      :ethp      ethp
      :goldp     goldp
      :token-id  (or token-id ::token-id)})))

(defn token [id]
  (let [{:keys [token-id]} @(rf/subscribe [::data])]
    ^{:key id}
    [:button.text-left
     {:on-click #(rf/dispatch [::token-id id])}
     [:figure.relative.w-11_2rem
      [:img {:src "/images/mega.png"}]
      (and (= id token-id) [:div.selected])]]))

(defn tokens []
  (let [{:keys [token-ids]} @(rf/subscribe [::data])]
    [:<>
     [:p.text-C6bc9db.mb-4.text-lg "My Land Deeds"]
     (into [:div.flex.bg-C81c6dd1a.py-3.pl-2.pr-6.rounded-sm.relative] (map #(vector token %) token-ids))]))

(defn returns []
  (let [{:keys [payment token-id token-ids]} @(rf/subscribe [::data])]
    [:<>
     [:p.text-C6bc9db.mb-4.mt-6.text-lg
      "Your returned token will be"]
     [:div.grid.grid-cols-2.gap-x-6.text-3xl
      [:button.bg-C81c6dd1a.leading-3rem.rounded-sm
       {:className (if (= payment :eth) "bg-C71edf599" "")
        :on-click  #(rf/dispatch [::payment :eth])}
       [:div.flexr
        [ethimg/ui "2rem"]
        [:span "18"]]]
      [:button.bg-C81c6dd1a.leading-3rem.rounded-sm
       {:className (if (= payment :gold) "bg-C71edf599" "")
        :on-click  #(rf/dispatch [::payment :gold])}
       [:div.flexr
        [goldimg/ui "2rem"]
        [:span "2.33"]]]]]))

(defn burn []
  (let [{:keys [token-id token-ids]} @(rf/subscribe [::data])]
    [:div.text-center [btn/ui {:t :osm} "BURN"]]))

(defn main []
  [:<>
   [style]
   [:div.guild-burn-land-deeds.relative.px-10.py-4
    [tokens]
    [returns]
    [separator/ui {:className "mt-24 mb-8"}]
    [burn]]])
