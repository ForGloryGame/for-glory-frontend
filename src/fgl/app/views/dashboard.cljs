(ns fgl.app.views.dashboard
  (:require
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.checkbox :as checkbox]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.gold-img :as goldimg]
   [fgl.app.ui.header-tag :as header-tag]
   [fgl.app.ui.landeed-img :as landeedimg]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.contracts.battlefield :as battlefield]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.glory :as glory]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.landeed :as landeed]
   [fgl.contracts.sgold :as sgold]
   [fgl.utils :refer [scan-addr-url shorten-addr]]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [util :refer [copy-to-clipboard]]))

(defn controllers []
  [{:start
    #(do ;; (rf/dispatch [::nft/init])
       (rf/dispatch [::sgold/init])
       (rf/dispatch [::landeed/init])
       (rf/dispatch [::gold/init])
       (rf/dispatch [::glory/init])
       (rf/dispatch [::kingdom/init]))
    :stop identity}])

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db
         addr-db           (get db addr)

         {gold-balance    ::gold/balance
          glory-balance   ::glory/balance
          sgold-balance   ::sgold/balance
          landeed-balance ::landeed/balance
          sgold-info      ::sgold/info
          kingdom-id      ::kingdom/kingdom-id
          kingdom-role ::kingdom/role}
         addr-db]

     {:addr       addr
      :gold       gold-balance
      :glory      glory-balance
      :sgold      sgold-balance
      :landeed    landeed-balance
      :kingdom-id kingdom-id
      :kingdom-role kingdom-role
      :sgold-info (sgold/->unlock-info sgold-info)})))

(when-not (js/localStorage.getItem "user-avatar")
  (js/localStorage.setItem "user-avatar" "knight"))

(defn user-avatar []
  (let [type (keyword (or (js/localStorage.getItem "user-avatar") "knight"))]
    [:div.rounded-full.bg-C25376f.w-40.h-40.mr-8.self-start
     [:img {:src (case type :knight "/images/avatar-knight.png" "/images/avatar-lord.png")}]]))

(rf/reg-event-db
 ::leave-checked
 (fn [db [_ checked?]]
   (assoc db ::leave-checked checked?)))

(rf/reg-sub
 ::leave-checked
 (fn [db _]
   (get db ::leave-checked false)))

(defn leave-checkbox []
  (let [checked? @(rf/subscribe [::leave-checked])]
    [checkbox/ui
     {:width "1.5rem"
      :text  [:span.text-xl.ml-2.fb "Do not remind me next time"]}
     {:className       "flex flex-row cs1 ce2 rs1 re2 justify-self-start items-center"
      :onCheckedChange #(rf/dispatch [::leave-checked %])
      :checked         checked?}]))

(defn user-info []
  (let [{:keys [addr kingdom-id kingdom-role]} @(rf/subscribe [::data])
        role                                   (or kingdom-role :member)
        leave-checked?                         @(rf/subscribe [::leave-checked])

        skip? #(js/localStorage.getItem "leave-checked")
        f1    #(if leave-checked?
                 (js/localStorage.setItem "leave-checked" 1)
                 (js/localStorage.removeItem "leave-checked"))
        f2    #(rf/dispatch
                [::kingdom/send
                 {:method :leave
                  :title  "Leave Kingdom"
                  :on-success
                  (fn []
                    (dialog/on-success)
                    (rf/dispatch [::kingdom/get-account-info true]))}])
        leave
        (fn []
          (if (skip?)
            (f2)
            (rf/dispatch
             [::dialog/set
              :open true
              :title ""
              :desc [:<>
                     [:p.ffd.text-base
                      "Notice: Since you are trying to leave your current Kingdom, 10% of your Glony will be charged as handling fee. And 24h cool-down will be applied. Click Confirm to proceed."]
                     [:br]
                     [leave-checkbox]]
              :actions [:<>
                        [btn/ui {:t :osm :on-click (comp f2 f1) :className "mr-8"} "Confirm"]
                        [btn/ui {:t :bsm :on-click #(rf/dispatch [::dialog/set :remove true])} "Cancel"]]])))]
    [:div.flexrs.mb-4
     ;; user
     [:div.mr-8
      [:div.text-3xl.fi.mb-4 (and addr (shorten-addr addr))]
      [:div.flex.flex-row.justify-between.items-center
       [btn/ui
        {:on-click  #(js/open (scan-addr-url addr))
         :t         :bxs
         :className "mr-4"}
        [:span.flexr [:img.w-4.mr-2 {:src "/images/share.png"}] "View on explorer"]]
       [btn/ui
        {:t :bxs
         :on-click
         #(do (copy-to-clipboard addr)
              (rf/dispatch [:toast/success {:title "Copied!" :no-close true}]))}
        [:span.flexr [:img.mr-2.w-4 {:src "/images/copy.png"}] "Copy Address"]]]]

     ;; user kingdom
     (and kingdom-id (pos? kingdom-id)
          [:div.flexr
           [:img.h-30.mr-4 {:src (str "/images/k" kingdom-id ".png")}]
           [:div
            [:div.flex.flex-row.items-baseline.mb-4
             [:div.uppercase.text-3xl.fi.mr-4 (get kingdom/kingdoms-name kingdom-id)]
             [:div.text-xl.uppercase (str "ROLE:" (name role))]]
            [btn/ui
             {:on-click leave
              :t        :bxs}
             "Leave My Kingdom"]]])]))

(defn balances []
  (let [{:keys [glory gold sgold landeed]} @(rf/subscribe [::data])]
    [:div.flex.flex-row.justify-around.items-cednter.bg-C81c6dd33.py-3.rounded-lg.fb
     [header-tag/ui
      {:className "w-32"}
      [:div.flex.flex-row.justify-between.items-center.text-xl
       [goldimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
       [balance/ui gold {:className "mr-2"}]]]
     [header-tag/ui
      {:className "w-32"}
      [:div.flex.flex-row.justify-between.items-center.text-xl
       [gloryimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
       [balance/ui glory {:className "mr-2"}]]]
     [header-tag/ui
      {:className "w-32"}
      [:div.flex.flex-row.justify-between.items-center.text-xl
       [sgoldimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
       [balance/ui sgold {:className "mr-2"}]]]
     [header-tag/ui
      {:className "w-32"}
      [:div.flex.flex-row.justify-between.items-center.text-xl
       [landeedimg/ui "3rem" {:style {:margin "-1.4rem 0 -1.4rem -1.4rem"}}]
       [balance/ui landeed {:className "mr-2"}]]]]))

(defn avatars []
  [:div.flex.flex-row.justify-between.items-center.p-8
   [user-avatar]
   [:div.grow
    [user-info]
    [balances]]])

(defn- append-zero [d]
  (if (< d 10)
    (str "0" d)
    d))

(defn- simple-date-str [d]
  (str (.getFullYear d)
       "."
       (append-zero (inc (.getMonth d)))
       "."
       (append-zero (.getDate d))))

(defn sgold-info-row [[idx {:keys [amount date]}]]
  ^{:key idx}
  [:div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold.items-center
   {:style {:gridTemplateColumns "1fr 3fr 1fr"}}
   [:span (simple-date-str date)]
   [:span.flexr [goldimg/ui "1.5rem"] [:span "Gold"]]
   [balance/ui amount]])

(defn table []
  [:div.px-6
   {:style {:height "calc(100% - 180px - 7.5rem)"}}
   [:div.rounded-sm.text-base.p-2.pb-0.grow
    {:style {:height     "calc(100% - 2.25rem)"
             :outline    "2px ridge #50929e33"
             :background "linear-gradient(
    rgba(163, 218, 226, 0.1),
    rgba(163, 218, 226, 0.05)
  )"}}
    [:div.rounded-sm.grid.justify-items-center.py-0_5.bg-C79c5da66.fb
     {:style {:gridTemplateColumns "1fr 3fr 1fr"}}
     [:span "DATE"]
     [:span "ASSET"]
     [:span "sGold locked"]]
    (let [{:keys [sgold-info]} @(rf/subscribe [::data])]
      [into
       [:div.overflow-auto
        {:style {:height "calc(100% - 1.25rem)"}}]
       (map-indexed (fn [idx info] [sgold-info-row [idx info]]) sgold-info)])]])

(defn main [_]
  (let []
    [:div.h-full.w-full
     [avatars]
     [table]]))
