(ns fgl.app.views.merchant-landeeds
  (:require
   ;; ["@radix-ui/react-checkbox" :as C]
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.eth-img :as ethimg]
   [fgl.contracts.gold :as gold]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::gold/init]))
    :stop identity}])

(rf/reg-event-db ::payment (fn [db [_ p]] (assoc db ::payment p)))
(rf/reg-event-db ::type (fn [db [_ t]] (assoc db ::type t)))

(rf/reg-sub
 ::data
 (fn [db _]
   {:type    (get db ::type 3)
    :payment (get db ::payment :gold)}))

(defn payment-select []
  (let [{:keys [payment]} @(rf/subscribe [::data])
        gold?             (= payment :gold)]
    [:div.grid.grid-cols-2.gap-x-6.text-3xl
     [:button.bg-C81c6dd1a.leading-3rem.rounded-sm.flexr
      {:className (and (not gold?) "bg-C71edf599")
       :on-click  #(rf/dispatch [::payment :eth])}
      [ethimg/ui "2rem"]
      [balance/ui "1800000000000000000"]]
     [:button.bg-C81c6dd1a.leading-3rem.rounded-sm.flexr
      {:className (and gold? "bg-C71edf599")
       :on-click  #(rf/dispatch [::payment :gold])}
      [goldimg/ui "2rem"]
      [balance/ui "2330000000000000000"]]]))

(defn deed-type-select []
  (let [{:keys [type]} @(rf/subscribe [::data])]
    [:div.grid.grid-cols-4.gap-x-3.bg-C81c6dd1a.py-4.px-2.rounded-sm
     ^{:key 'mega}
     [:button.text-left
      {:on-click #(rf/dispatch [::type 3])}
      [:figure.relative
       [:img {:src "/images/mega.png"}]
       [:figcaption.absolute.w-full.pl-3.pb-4.bottom-0
        [:p.text-lg.tracking-widest.font-light.leading-5
         "MEGA"]
        [:p.text-xs "Land Deed"]]
       (and (= type 3) [:div.selected])]]
     ^{:key 'large}
     [:button.text-left
      {:on-click #(rf/dispatch [::type 2])}
      [:figure.relative
       [:img
        {:style {:filter "hue-rotate(230deg) saturate(.6)"}
         :src   "/images/mega.png"}]
       [:figcaption.absolute.w-full.pl-3.pb-4.bottom-0
        [:p.text-lg.tracking-widest.font-light.leading-5
         "LARGE"]
        [:p.text-xs "Land Deed"]]
       (and (= type 2) [:div.selected])]]
     ^{:key 'medium}
     [:button.text-left
      {:on-click #(rf/dispatch [::type 1])}
      [:figure.relative
       [:img
        {:style {:filter "hue-rotate(171deg) saturate(.6)"}
         :src   "/images/mega.png"}]
       [:figcaption.absolute.w-full.pl-3.pb-4.bottom-0
        [:p.text-lg.tracking-widest.font-light.leading-5
         "MEDIUM"]
        [:p.text-xs "Land Deed"]]
       (and (= type 1) [:div.selected])]]
     ^{:key 'standard}
     [:button.text-left
      {:on-click #(rf/dispatch [::type 0])}
      [:figure.relative
       [:img
        {:style {:filter "hue-rotate(57deg) saturate(.6)"}
         :src   "/images/mega.png"}]
       [:figcaption.absolute.w-full.pl-3.pb-4.bottom-0
        [:p.text-lg.tracking-widest.font-light.leading-5
         "STANDARD"]
        [:p.text-xs "Land Deed"]]
       (and (= type 0) [:div.selected])]]]))

(defn style []
  [:style
   "
      .guild-land-deeds figure {
        transition: all 0.2s ease;
      }

      .guild-land-deeds figure:hover {
        transform: scale(1.05);
      }

      .guild-land-deeds figure .selected,
      .guild-land-deeds figure .selected::after {
        visibility: visible;
      }

      .guild-land-deeds figure .selected {
        visibility: visible;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        border: 0.18rem solid #fff;
        border-radius: 4px;
      }
      .guild-land-deeds figure .selected::after {
        visibility: visible;
        content: \" \";
        position: absolute;
        width: 0;
        height: 0;
        border: 1rem solid transparent;
        border-right: 1rem solid #fff;
        transform: translate(calc(-50% - 1px), calc(-50% - 1px)) rotate(45deg);
      }

      .guild-land-deeds figcaption {
        text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.4);
      }"])

(defn main []
  (let [{:keys [type payment]} @(rf/subscribe [::data])]
    [:<>
     [style]
     [:div.guild-land-deeds.relative.px-10.py-4
      [:p.text-C6bc9db.mb-4.text-lg "Land Shop"]
      [deed-type-select]
      [:p.text-C6bc9db.mb-4.mt-6.text-lg "Cost"]
      [payment-select]
      [separator/ui {:className "mt-24 mb-8"}]
      [:div.text-center [btn/ui {:t :osm} "MINT"]]]]))
