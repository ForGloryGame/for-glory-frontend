(ns fgl.app.views.battlefield
  (:require
   ["@radix-ui/react-checkbox" :as C]
   ["@radix-ui/react-select" :as S]
   [fgl.config :as conf]
   [fgl.contracts.battlefield :as battlefield]
   [fgl.contracts.bfproxy :as bfproxy]
   [fgl.contracts.gamenft :as nft]
   [fgl.utils :refer [->display-token ->token-ids]]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       ;; get staked info
       (rf/dispatch [::battlefield/init])
       ;; get unstaked info
       (rf/dispatch [::nft/init]))
    :stop  identity}])

(rf/reg-event-db
 ::set-type
 (fn [db [_ type]]
   (assoc db ::type type
          ::selected #{})))

(rf/reg-event-db
 ::select
 (fn [db [_ id]]
   (assoc db ::selected (conj (get db ::selected #{}) id))))

(rf/reg-event-db
 ::deselect
 (fn [db [_ id]]
   (assoc db ::selected (disj (get db ::selected #{}) id))))

(rf/reg-sub
 ::selected
 (fn [db _]
   (get db ::selected #{})))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [type (or (::type db) :staked)

         {::w/keys [addr]} db
         addr-db           (get db addr)

         {::battlefield/keys [reward] staked-token-ids ::battlefield/token-ids}
         addr-db

         traits (::nft/traits db)

         unstaked-token-ids
         (::nft/token-ids addr-db)

         bf-approved (::nft/bf-approved addr-db)]

     [type
      (get
       {:staked   (map (fn [id]
                         (let [id (.toString id)]
                           (-> {:id id}
                               (merge (get traits id))
                               (merge (get reward id)))))
                       staked-token-ids)
        :unstaked (map (fn [id]
                         (let [id (.toString id)]
                           (-> {:id id}
                               (merge (get traits id)))))
                       unstaked-token-ids)}
       type)
      bf-approved])))

(defn close []
  (let [to-home #(rf/dispatch [:navigate :route/home])]
    (fn []
      [:button.cs4.ce5.rs1.re2 {:on-click to-home} "x"])))

(defn select []
  (let [set-type #(rf/dispatch [::set-type (keyword %)])]
    (fn []
      (let [[type] @(rf/subscribe [::data])]
        [:> S/Root
         {:name          "Token Type"
          :value         type
          :onValueChange set-type}
         [:> S/Trigger
          {:className "cs2 ce3 rs2 re3 justify-self-start"}
          [:> S/Value type]
          [:> S/Icon ">"]]
         [:> S/Content
          [:> S/Viewport
           ^{:key 'staked}
           [:> S/Item
            {:value :staked}
            [:> S/ItemText "Staked"]
            [:> S/ItemIndicator (or (nil? type) (= type :staked))]]
           ^{:key 'unstaked}
           [:> S/Item
            {:value :unstaked}
            [:> S/ItemText "Unstaked"]
            [:> S/ItemIndicator (= type :unstaked)]]]]]))))

(defn card [id is-lord gold glory staked? checked disabled]
  (let [onCheckedChange #(rf/dispatch [(if % ::select ::deselect) id])
        name            (str (if is-lord "Lord#" "Knight#") id)]
    [:> C/Root
     {:disabled        disabled
      :name            name
      :value           name
      :onCheckedChange onCheckedChange}
     [:ul.border
      [:li (str "Selected: " (if checked true false))]
      (and staked? [:li "staked"])
      [:li name]
      (and staked? [:li (str "Gold earned: " (->display-token gold))])
      (and staked? is-lord [:li (str "Glory earned: " (->display-token glory))])]
     [:> C/Indicator [:span checked]]]))

(defn cards []
  (fn []
    (let []
      (fn []
        (let [[type data] @(rf/subscribe [::data])
              staked?     (= type :staked)
              selected    @(rf/subscribe [::selected])]
          (into
           [:div.overflow-x-auto.flex]
           (map (fn [{:keys [id is-lord gold glory]}]
                  ^{:key id} [card id is-lord gold glory staked? (not (nil? (some #{id} selected))) false])
                data)))))))

(defn btns []
  (let [approve
        #(rf/dispatch [::nft/send
                       {:method :setApprovalForAll
                        :params [conf/contract-addr-battlefield
                                 true]}])
        enter
        (fn [token-ids] #(rf/dispatch [{::bfproxy/send {:method :join
                                                        :params [(->token-ids token-ids)]}}]))
        claim
        (fn [token-ids] #(rf/dispatch [::bfproxy/send
                                       {:method :claim
                                        :params [(->token-ids token-ids)]}]))
        unstake-lords
        (fn [token-ids] #(rf/dispatch [::bfproxy/send
                                       {:method :unstakeLords
                                        :params [(->token-ids token-ids)]}]))
        unstake
        (fn [token-ids] #(rf/dispatch [::bfproxy/send
                                       {:method :commitUnstake
                                        :params [(->token-ids token-ids)]}]))]
    (fn []
      (let [[type _ approved?] @(rf/subscribe [::data])
            selected           @(rf/subscribe [::selected])
            staked?            (= type :staked)
            no-selected?       (not (seq selected))]
        [:div.cs2.ce4.rs4.re5.justify-self-end.grid.grid-cols-3
         (and (not approved?) [:button {:on-click approve} "APPROVE"])
         (and approved? (not staked?)  [:button {:on-click (enter selected) :disabled no-selected?} "ENTER"])
         (and approved? staked? [:button {:on-click (unstake selected) :disabled no-selected?} "FLEE"])
         (and approved? staked? [:button {:on-click (claim selected) :disabled no-selected?} "CLAIM"])]))))

(defn main [_]
  (let []
    (fn []
      (r/create-class
       {:reagent-render
        (fn []
          (let [[type]  @(rf/subscribe [::data])
                ;; staked? (= type :staked)
                ]
            [:div.grid.p-1.border
             [:h1.cs1.ce3.rs1.re2 "Battlefield"]
             [close]
             [select]
             [:div.cs2.ce4.rs3.re4.justify-self-stretch.overflow-x-auto
              [cards]]
             [btns]]))}))))
