(ns fgl.app.views.battlefield
  (:require
   [taoensso.encore :as enc]
   ["@radix-ui/react-checkbox" :as C]
   ["@radix-ui/react-select" :as S]
   [fgl.config :as conf]
   [fgl.contracts.battlefield :as battlefield]
   [fgl.contracts.bfproxy :as bfproxy]
   [fgl.contracts.gamenft :as nft]
   [fgl.re-frame]
   [fgl.utils :refer [->display-token ->token-ids]]
   [fgl.app.ui.panel :as panel]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [fgl.app.ui.nft-card :as nftc]
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

(rf/reg-event-fx
 ::select-all
 [(rf/inject-cofx :inject/sub [::data])]
 (fn [{::keys [data] :keys [db]} [_ select?]]
   (if select?
     (let [[_ items] data]
       {:db (assoc db ::selected (->> items (map :id) (into #{}))
                   ::all-selected true)})
     {:db (assoc db ::selected #{}
                 ::all-selected false)})))

(rf/reg-event-db
 ::select
 (fn [db [_ id]]
   (assoc db ::selected (conj (get db ::selected #{}) id))))

(rf/reg-event-db
 ::deselect
 (fn [db [_ id]]
   (assoc db ::selected (disj (get db ::selected #{}) id)
          ::all-selected false)))

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

         bf-approved (::nft/bf-approved addr-db)

         all-selected (::all-selected db)]

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
      bf-approved
      all-selected])))

(defn- to-home []
  (rf/dispatch [:navigate :route/home]))

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
          {:className "cs1 ce2 rs1 re2 justify-self-start text-xl text-center pl-14 pr-10 py-0.5 rounded"
           :style     {:backgroundColor "rgba(129, 198, 221, 0.2)"}}
          [:> S/Value (if (= type :staked) "Staked" "Unstaked")]
          [:> S/Icon [:img.inline-block.ml-6
                      {:style {:width "0.825rem"}
                       :src   "/images/select-down-arrow.svg"}]]]
         [:> S/Content
          {:className "text-xl text-center pl-12 pr-12 py-0.5 rounded"
           :style     {:backgroundColor "rgba(129, 198, 221, 0.2)"}}
          [:> S/Viewport
           ^{:key 'staked}
           [:> S/Item
            {:value :staked}
            [:> S/ItemText {:className "text-2xl"} "Staked"]
            [:> S/ItemIndicator (or (nil? type) (= type :staked))]]
           ^{:key 'unstaked}
           [:> S/Item
            {:value :unstaked}
            [:> S/ItemText {:className "text-2xl"} "Unstaked"]
            [:> S/ItemIndicator (= type :unstaked)]]]]]))))

(defn card [id is-lord gold glory staked? checked disabled]
  (let [onCheckedChange #(rf/dispatch [(if % ::select ::deselect) id])
        name            (str (if is-lord "Lord#" "Knight#") id)
        image (if is-lord "/images/lord-example.png" "/images/lord-example.png")]
    [nftc/ui
     {:id id
      :name name
      :image image
      :gold gold
      :staked staked?
      :disabled disabled
      :checked checked
      :width "16rem"
      :glory glory
      :earned? (not glory)
      :onCheckedChange onCheckedChange}
     {:className "mr-4"
      :style {:flexShrink 0}}]))

(defn cards []
  (fn []
    (let []
      (fn []
        (let [[type data] @(rf/subscribe [::data])
              staked?     (= type :staked)
              selected    @(rf/subscribe [::selected])]
          (into
           [:div.overflow-x-auto.flex.pb-8]
           (map (fn [{:keys [id is-lord gold glory]}]
                  ^{:key id} [card id is-lord gold glory staked? (not (nil? (some #{id} selected))) false])
                data)))))))

(defn select-all []
  (let [select-all #(rf/dispatch [::select-all %])]
    (fn []
      (let [[_ _ _ all-selected] @(rf/subscribe [::data])]
        [:> C/Root
         {:className       "cs1 ce2 rs4 re5 justify-self-start"
          :onCheckedChange select-all
          :checked         all-selected}
         "Select All"
         [:> C/Indicator [:span (str "checked: " all-selected)]]]))))

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
      (let [[type data approved?] @(rf/subscribe [::data])
            selected              @(rf/subscribe [::selected])
            staked?               (= type :staked)
            no-selected?          (not (seq selected))]
        [:div.cs2.ce3.rs4.re5.justify-self-end.grid.grid-cols-3
         (and (not approved?) [:button {:on-click approve} "APPROVE"])
         (and approved? (not staked?)  [:button {:on-click (enter selected) :disabled no-selected?} "ENTER"])
         (and approved? staked? [:button {:on-click (unstake selected) :disabled no-selected?} "FLEE"])
         (and approved? staked? [:button {:on-click (claim selected) :disabled no-selected?} "CLAIM"])]))))

(defn separator
  ([] (separator {}))
  ([opt]
   [:div
    (enc/nested-merge
     {:style {:height          0
              :boxShadow       "rgb(8 30 38) -1px -0.5px 0.5px 0.5px, rgb(0 182 255 / 12%) 1px 1px 0.5px 1px"
              :backgroundColor "rgba(100, 186, 214, 0)"}}
     opt)]))

(defn main [_]
  (let []
    (fn []
      (r/create-class
       {:reagent-render
        (fn []
          (let [[type] @(rf/subscribe [::data])
                ;; staked? (= type :staked)
                ]
            [panel/ui "Battlefield" 80 to-home
             [:div.grid.justify-center.gap-4
              {:style {:padding "2%"
                       :margin  "2%"}}
              [select]
              [separator {:className "cs1 ce4 rs2 re3"}]
              [:div.cs1.ce4.rs3.re4.justify-self-stretch.overflow-x-auto
               [cards]]
              [select-all]
              [btns]]]))}))))
