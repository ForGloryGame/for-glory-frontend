(ns fgl.app.views.battlefield
  (:require
   ["@radix-ui/react-select" :as S]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.checkbox :as checkbox]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.nft-card :as nftc]
   [fgl.app.ui.panel :as panel]
   [fgl.app.ui.separator :as separator]
   [fgl.config :as conf]
   [fgl.contracts.battlefield :as battlefield]
   [fgl.contracts.bfproxy :as bfproxy]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.glory :as glory]
   [fgl.contracts.gold :as gold]
   [fgl.re-frame]
   [fgl.utils :refer [->display-token ->token-ids]]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [taoensso.encore :as enc]))

(defn controllers []
  [{:start
    #(do
       ;; get staked info
       (rf/dispatch [::battlefield/init])
       (rf/dispatch [::bfproxy/init])
       ;; get unstaked info
       (rf/dispatch [::nft/init]))
    :stop identity}])

(rf/reg-event-db
 ::set-type
 (fn [db [_ type]]
   (assoc db ::type type
          ::selected #{})))

(rf/reg-event-fx
 ::select-all
 [(rf/inject-cofx :inject/sub (fn [[_ _ route-name]] [::data route-name]))]
 (fn [{::keys [data] :keys [db]} [_ select?]]
   (if select?
     (let [{items :data} data]
       {:db (assoc db ::selected (->> items (map :id) (into #{})))})
     {:db (assoc db ::selected #{})})))

(rf/reg-event-db
 ::select
 (fn [db [_ id]]
   (if (nil? id)
     (assoc db ::selected #{})
     (assoc db ::selected (conj (get db ::selected #{}) id)))))

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
 (fn [db [_ route-name]]
   (let [council? (= route-name :route/council)
         type     (or (::type db) :staked)

         {::w/keys [addr]} db
         addr-db           (get db addr)

         {::battlefield/keys [reward] staked-token-ids ::battlefield/token-ids}
         addr-db

         traits (::nft/traits db)

         unstaked-token-ids
         (::nft/token-ids addr-db)

         bf-approved (::nft/bf-approved addr-db)

         to-reveal (::bfproxy/to-reveal addr-db)

         selected (::selected db)

         filter-by (if council? :is-lord #(not (:is-lord %)))

         data (get
               {:staked   (->> staked-token-ids
                               (map #(.toString %))
                               distinct
                               (map (fn [id]
                                      (-> {:id id}
                                          (merge (get traits id))
                                          (merge (get reward id)))))
                               (filter filter-by))
                :unstaked (->> unstaked-token-ids
                               (map #(.toString %))
                               distinct
                               (map (fn [id]
                                      (-> {:id id}
                                          (merge (get traits id)))))
                               (filter filter-by))}
               type)

         all-selected (= (count selected) (count data))]

     {:type         type
      :addr         addr
      :to-reveal    to-reveal
      :data         data
      :bf-approved  bf-approved
      :all-selected all-selected})))

(defn- reveal [addr]
  (rf/dispatch
   [::bfproxy/send
    {:method :revealUnstake
     :params [addr]}]))

(defn maybe-show-reveal-dialog []
  (let [{:keys [addr to-reveal]} @(rf/subscribe [::data])]
    (when to-reveal
      (rf/dispatch
       [::dialog/set
        :open true
        :title "Reveal Unstake Commits"
        :desc [:<>
               [:p "Found pending commits"]
               [:br]
               [:p "Click the REVEAL button below to reveal them"]]
        :actions [btn/ui {:t :bsm :on-click (partial reveal addr)} "REVEAL"]]))))

(defn select []
  (let [set-type #(rf/dispatch [::set-type (keyword %)])]
    (fn []
      (let [{:keys [type]} @(rf/subscribe [::data])]
        [:> S/Root
         {:name          "Token Type"
          :defaultValue  "Staked"
          :value         type
          ;; :open true
          :onValueChange set-type}
         [:> S/Trigger
          {:className "text-xl text-center pl-14 pr-10 py-0.5 rounded fb"
           :style     {:backgroundColor "#355661"}}
          [:> S/Value (if (= type :staked) " Staked " "Unstaked")]
          [:> S/Icon [:img.inline-block.ml-6
                      {:style {:width "0.825rem"}
                       :src   "/images/select-down-arrow.svg"}]]]
         [:> S/Content
          {:className "text-xl text-center rounded fb pl-14 pr-10"
           :style     {:backgroundColor "#355661"}}
          [:> S/Viewport
           ^{:key 'staked}
           [:> S/Item
            {:value :staked}
            [:> S/ItemText " Staked "]
            [:> S/ItemIndicator (or (nil? type) (= type :staked))]
            [:img.inline-block.ml-6
             {:style {:width "0.825rem" :visibility (if (or (nil? type) (= type :staked)) "visible" "hidden")}
              :src   "/images/select-down-arrow.svg"}]]
           ^{:key 'unstaked}
           [:> S/Item
            {:value :unstaked}
            [:> S/ItemText "Unstaked"]
            [:img.inline-block.ml-6
             {:style {:width "0.825rem" :visibility (if (= type :unstaked) "visible" "hidden")}
              :src   "/images/select-down-arrow.svg"}]]]]]))))

(defn card [id is-lord gold glory staked? checked disabled]
  (let [onCheckedChange #(rf/dispatch [(if % ::select ::deselect) id])
        name            (str (if is-lord "Lord#" "Knight#") id)
        image           (if is-lord "/images/lord-example.png" "/images/knight-example.png")]
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
      :glory           (and is-lord glory)
      :earned?         (and gold (not is-lord))
      :onCheckedChange onCheckedChange}
     {:className "mr-4"
      :style     {:flexShrink 0}}]))

(defn cards [route-name]
  (let [!cards (r/atom nil)
        on-wheel
        (fn [e]
          (when-let [!cards-div @!cards]
            (.preventDefault e)
            (and (.-deltaY e)
                 (set!
                  (.-scrollLeft !cards-div)
                  (+ (.-scrollLeft !cards-div)
                     (.-deltaY e))))))]
    (r/create-class
     {:component-did-mount
      (fn []
        (rf/dispatch [::select])
        (when-let [!cards-div @!cards]
          (.addEventListener !cards-div "wheel" on-wheel #js {:passive false})))
      :component-will-unmount
      (fn []
        (when-let [!cards-div @!cards]
          (.removeEventListener !cards-div "wheel" on-wheel)))
      :reagent-render
      (fn []
        (let [{:keys [type data]} @(rf/subscribe [::data route-name])
              staked?             (= type :staked)
              selected            @(rf/subscribe [::selected])]
          (into
           [:div.overflow-x-auto.flex.pb-8.min-h-21rem
            {:ref #(reset! !cards %)}]
           (map (fn [{:keys [id is-lord gold glory]}]
                  ^{:key id} [card id is-lord gold glory staked? (not (nil? (some #{id} selected))) false])
                data))))})))

(defn select-all [route-name]
  (let [select-all #(rf/dispatch [::select-all % route-name])]
    (fn []
      (let [{:keys [all-selected]} @(rf/subscribe [::data route-name])]
        [checkbox/ui
         {:width "1.5rem"
          :text  [:span.text-xl.ml-2.fb {:style {:color "rgb(213, 228, 232)"}} "SELECT ALL"]}
         {:className "flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center"
          :onCheckedChange select-all
          :checked         all-selected}]))))

(rf/reg-event-db
 ::claim-checked
 (fn [db [_ checked?]]
   (assoc db ::claim-checked checked?)))

(rf/reg-sub
 ::claim-checked
 (fn [db _]
   (get db ::claim-checked false)))

(rf/reg-event-db
 ::flee-checked
 (fn [db [_ checked?]]
   (assoc db ::flee-checked checked?)))

(rf/reg-sub
 ::flee-checked
 (fn [db _]
   (get db ::flee-checked false)))

(defn claim-checkbox []
  (let [checked? @(rf/subscribe [::claim-checked])]
    [checkbox/ui
     {:width "1.5rem"
      :text  [:span.text-xl.ml-2.fb "Do not remind me next time"]}
     {:className       "flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center"
      :onCheckedChange #(rf/dispatch [::claim-checked %])
      :checked         checked?}]))

(defn flee-checkbox []
  (let [checked? @(rf/subscribe [::flee-checked])]
    [checkbox/ui
     {:width "1.5rem"
      :text  [:span.text-xl.ml-2.fb "Do not remind me next time"]}
     {:className       "flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center"
      :onCheckedChange #(rf/dispatch [::flee-checked %])
      :checked         checked?}]))

(defn btns [route-name]
  (let [battlefield? (= route-name :route/battlefield)
        approve
        #(rf/dispatch
          [::nft/send
           {:method :setApprovalForAll
            :params [conf/contract-addr-battlefield true]}
           :title "Approve All NFT"
           :on-success (fn []
                         (dialog/on-success)
                         (rf/dispatch [::bfproxy/init-raw]))])
        enter
        (fn [token-ids]
          #(rf/dispatch [::bfproxy/send {:method :join
                                         :params [(->token-ids token-ids)]
                                         :title  "Stake"
                                         :on-success
                                         (fn []
                                           (dialog/on-success)
                                           (rf/dispatch [::battlefield/init-raw])
                                           (rf/dispatch [::nft/init-raw]))}]))
        unstake-lords
        (fn [token-ids]
          #(rf/dispatch [::bfproxy/send
                         {:method :unstakeLords
                          :params [(->token-ids token-ids)]
                          :title  "Unstake"
                          :on-success
                          (fn []
                            (dialog/on-success)
                            (rf/dispatch [::battlefield/init-raw])
                            (rf/dispatch [::nft/init-raw])
                            (rf/dispatch [::bfproxy/init-raw]))}]))]
    (fn []
      (let [{:keys [type bf-approved]} @(rf/subscribe [::data])
            selected                   @(rf/subscribe [::selected])
            claim-checked?             @(rf/subscribe [::claim-checked])
            flee-checked?              @(rf/subscribe [::flee-checked])
            staked?                    (= type :staked)
            no-selected?               (not (seq selected))
            className                  (if staked? "grid-cols-3 gap-4" "grid-cols-1")
            unstake
            (fn []
              (let [skip? (js/localStorage.getItem "flee-checked")
                    f1    #(if flee-checked?
                             (js/localStorage.setItem "flee-checked" 1)
                             (js/localStorage.removeItem "flee-checked"))
                    f2    #(rf/dispatch
                            [::bfproxy/send
                             {:method :commitUnstake
                              :params [(->token-ids selected)]
                              :title  "Flee"
                              :on-success
                              (fn []
                                (dialog/on-success)
                                (rf/dispatch [::battlefield/init-raw])
                                (rf/dispatch [::nft/init-raw])
                                (rf/dispatch [::bfproxy/init-raw]))}])]
                (if skip?
                  (f2)
                  (rf/dispatch [::dialog/set
                                :open true
                                :title ""
                                :desc [:<>
                                       [:p.ffd.text-base "Notice: If you choose to FLEE, You have 50% chance of receiving all earned
§Glory without any taxing and it WILL NOT be locked as $sGlarx, However,
by doing this, there's 50% chance of AlL.of your accumulated $Glory being
seized by the Council, distributed to Lords accordingly."]
                                       [:br]
                                       [flee-checkbox]]
                                :actions [:<>
                                          [btn/ui {:t :osm :on-click (comp f2 f1) :className "mr-8"} "Confirm"]
                                          [btn/ui {:t :bsm :on-click #(rf/dispatch [::dialog/set :remove true])} "Cancel"]]]))))
            claim
            (fn []
              (let [skip? (js/localStorage.getItem "claim-checked")
                    f1    #(if claim-checked?
                             (js/localStorage.setItem "claim-checked" 1)
                             (js/localStorage.removeItem "claim-checked"))
                    f2    #(rf/dispatch
                            [::bfproxy/send
                             {:method :claim
                              :params [(->token-ids selected)]
                              :title  "Claim"
                              :on-success
                              (fn []
                                (dialog/on-success)
                                (rf/dispatch [::gold/init-raw])
                                (rf/dispatch [::glory/init-raw]))}])]
                (if (and skip? (not battlefield?))
                  (f2)
                  (rf/dispatch [::dialog/set
                                :open true
                                :title ""
                                :desc [:<>
                                       [:p.ffd.text-base "Notice: If you choose to CLAIM, You are guaranteed to receive 80% your
yield $Glory as $sGlark, locked for 16 weeks by default. Rest 20% as taxes
to the Council, distributed to Lords accordingly."]
                                       [:br]
                                       [claim-checkbox]]
                                :actions [:<>
                                          [btn/ui {:t :osm :on-click (comp f2 f1) :className "mr-8"} "Confirm"]
                                          [btn/ui {:t :bsm :on-click #(rf/dispatch [::dialog/set :remove true])} "Cancel"]]]))))]
        [:div.cs3.ce4.rs1.re2.justify-self-end
         {:className className}
         (and (not bf-approved)
              [btn/ui
               {:t         :bsm
                :className "mr-4"
                :on-click  approve}
               "APPROVE"])
         (and bf-approved
              (not staked?)
              [btn/ui
               {:t         :bsm
                :disabled  no-selected?
                :className "mr-4"
                :on-click  (enter selected)}
               "ENTER"])
         (and bf-approved staked?
              [btn/ui
               {:t         :bsm
                :disabled  no-selected?
                :className "mr-4"
                :on-click  unstake}
               "FLEE"])
         (and bf-approved staked?
              [btn/ui
               {:disabled no-selected?
                :on-click claim
                :t        :olg}
               "CLAIM"])]))))

(defn main [cur-route]
  (r/create-class
   {:component-did-mount maybe-show-reveal-dialog
    :reagent-render
    (fn []
      [:div
       {:style {:padding "2%" :width "98%"}}
       [select]
       [separator/ui {:className "my-4"}]
       [:div.overflow-x-auto
        [cards (-> cur-route :data :name)]]
       [:div.grid.gap-4.mt-4
        [select-all (-> cur-route :data :name)]
        [btns (-> cur-route :data :name)]]])}))
