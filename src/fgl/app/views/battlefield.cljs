(ns fgl.app.views.battlefield
  (:require
   [taoensso.encore :as enc]
   [fgl.app.ui.checkbox :as checkbox]
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
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.btn :as btn]
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
       {:db (assoc db ::selected (->> items (map :id) (into #{})))})
     {:db (assoc db ::selected #{})})))

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

         bf-approved (::nft/bf-approved addr-db)

         selected (::selected db)

         data (get
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

         all-selected (= (count selected) (count data))]

     [type
      data
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
          :defaultValue  "Staked"
          :value         type
          :onValueChange set-type}
         [:> S/Trigger
          {:className "cs1 ce2 rs1 re2 justify-self-start text-xl text-center pl-14 pr-10 py-0.5 rounded relative fb"
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
        image           (if is-lord "/images/lord-example.png" "/images/lord-example.png")]
    [nftc/ui
     {:id              id
      :name            name
      :image           image
      :gold            gold
      :selected        checked
      :staked          staked?
      :disabled        disabled
      :checked         checked
      :width           "16rem"
      :glory           glory
      :earned?         (and gold (not glory))
      :onCheckedChange onCheckedChange}
     {:className "mr-4"
      :style     {:flexShrink 0}}]))

(defn cards []
  (fn []
    (let [[type data] @(rf/subscribe [::data])
          staked?     (= type :staked)
          selected    @(rf/subscribe [::selected])]
      (into
       [:div.overflow-x-auto.flex.pb-8.min-h-21rem]
       (map (fn [{:keys [id is-lord gold glory]}]
              ^{:key id} [card id is-lord gold glory staked? (not (nil? (some #{id} selected))) false])
            data)))))

(defn select-all []
  (let [select-all #(rf/dispatch [::select-all %])]
    (fn []
      (let [[_ _ _ all-selected] @(rf/subscribe [::data])]
        [checkbox/ui
         {:width "1.5rem"
          :text  [:span.text-xl.ml-2.fb {:style {:color "rgb(213, 228, 232)"}} "SELECT ALL"]}
         {:className "flex flex-row cs1 ce2 rs5 re6 justify-self-start items-center"
          :onCheckedChange select-all
          :checked         all-selected}]))))

(defn btns []
  (let [approve
        #(rf/dispatch [::nft/send
                       {:method :setApprovalForAll
                        :params [conf/contract-addr-battlefield
                                 true]}])
        enter
        (fn [token-ids] #(rf/dispatch [::bfproxy/send {:method :join
                                                       :params [(->token-ids token-ids)]}]))
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
            no-selected?          (not (seq selected))
            className             (if staked? "grid-cols-3 gap-4" "grid-cols-1")]
        [:div.cs3.ce4.rs5.re6.justify-self-end
         {:className className}
         (and (not approved?)
              [btn/ui
               {:t         :bsm
                :disabled  no-selected?
                :className "mr-4"
                :on-click  approve}
               "APPROVE"])
         (and approved?
              [btn/ui
               {:t         :bsm
                :disabled  no-selected?
                :className "mr-4"
                :on-click  (enter selected)}
               "ENTER"])
         (and approved? staked?
              [btn/ui
               {:t         :bsm
                :disabled  no-selected?
                :className "mr-4"
                :on-click  (unstake selected)}
               "FLEE"])
         (and approved? staked?
              [btn/ui
               {:disabled no-selected?
                :on-click (claim selected)
                :t        :olg}
               "CLAIM"])]))))

(defn main [_]
  [:div.grid.gap-4
   {:style {:padding "2%" :width "98%"}}
   [select]
   [separator/ui {:className "cs1 ce4 rs2 re3 mt-2 mb-4"}]
   [:div.cs1.ce4.rs3.re4.justify-self-stretch.overflow-x-auto
    [cards]]
   [select-all]
   [btns]])
