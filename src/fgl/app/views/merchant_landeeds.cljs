(ns fgl.app.views.merchant-landeeds
  (:require
   ["ethers" :as ethers]
   [fgl.utils :refer [sbn]]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.eth-img :as ethimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.separator :as separator]
   [fgl.wallet.core :as w]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.landeed :as landeed]
   [fgl.re-frame]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]))

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::landeed/init])
       (rf/dispatch [::gold/init])
       (rf/dispatch [::gold/allowance (.-address landeed/c)]))
    :stop identity}])

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

(rf/reg-event-db ::payment (fn [db [_ p]] (assoc db ::payment p)))
(rf/reg-event-db ::type (fn [db [_ t]] (assoc db ::type t)))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]
          ::keys   [type]
          ::landeed/keys
          [info
           eth-gold-ratio
           rgold
           reth
           total]} db

         gold-allowance (get-in db [addr ::gold/allowance (.-address landeed/c)])

         type (or type 3)

         eth-price  (sbn (:price (get info type)))
         gold-price (.mul (sbn eth-gold-ratio) eth-price)

         eth-lp  (.div eth-price (sbn reth 1))
         gold-lp (.div gold-price (sbn rgold 1))
         min-lp  (if (.gte eth-lp gold-lp) gold-lp eth-lp)
         min-lp
         (->
          min-lp
          (.div 10)
          (.mul 9)
          (.mul (sbn total))
          (.div "1000000000000000000"))]

     {:type       type
      :min-lp     min-lp
      :approved?  (.gte (sbn gold-allowance) gold-price)
      :eth-price  eth-price
      :gold-price gold-price})))

(defn cost []
  (let [{:keys [eth-price gold-price]} @(rf/subscribe [::data])]
    [:<>
     [:p.text-C6bc9db.mb-4.mt-6.text-lg "Cost"]
     [:ol.grid.grid-cols-2.gap-x-6.text-3xl
      [:li.bg-C81c6dd1a.leading-3rem.rounded-sm.flexr
       [ethimg/ui "2rem"]
       [balance/ui eth-price]]
      [:li.bg-C81c6dd1a.leading-3rem.rounded-sm.flexr
       [goldimg/ui "2rem"]
       [balance/ui gold-price]]]]))

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

(defn mint []
  (let  [{:keys [type eth-price min-lp approved?]} @(rf/subscribe [::data])]
    [:div.text-center
     [btn/ui
      {:t :osm
       :on-click
       #(rf/dispatch
         (if approved?
           [::landeed/send
            {:method   :purchase
             :params   [type min-lp]
             :override {:value (.toHexString eth-price)}
             :on-success
             (fn []
               (rf/dispatch [::gold/allowance (.-address landeed/c)])
               (dialog/on-success))}]
           [::gold/send
            {:method :approve
             :params [(.-address landeed/c)
                      "0xffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"]}]))}
      (if approved? "MINT" "APPROVE")]]))

(defn main []
  [:<>
   [style]
   [:div.guild-land-deeds.relative.px-10.py-4
    [:p.text-C6bc9db.mb-4.text-lg "Land Shop"]
    [deed-type-select]
    [cost]
    [separator/ui {:className "mt-24 mb-8"}]
    [mint]]])
