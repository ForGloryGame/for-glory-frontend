(ns fgl.app.views.guild-vote
  (:require
   ["ethers" :as ethers]
   [clojure.string :as s]
   [fgl.app.snapshot :as snapshot]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.sgold :as sgold]
   [fgl.re-frame]
   [fgl.utils :refer [shorten-addr scan-addr-url]]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [taoensso.encore :as enc]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(rf/reg-sub
 ::data
 (fn [db [_ proposal-id]]
   (let [proposal (get-in db [::snapshot/proposal proposal-id])
         votes    (get-in db [::snapshot/votes proposal-id])]
     {:proposal proposal
      :votes    votes})))

(rf/reg-sub
 ::proposals
 (fn [db [_ space]]
   (let [proposals  (get-in db [::snapshot/proposals space])]
     {:proposals proposals})))

(defn item [id title author body state votes]
  ^{:key id}
  [:div.bg-C81c6dd1a.rounded-md.p-4.fb.mx-8.my-4
   [:div.flexrr.justify-between
    [:div.flexrr.items-center
     [:span.text-4xl.mr-4.uppercase title]
     [:div.bg-Ced8e28.px-12.rounded-md.text-base.capitalize state]]
    [btn/ui
     {:t         :blg
      :className "justify-self-end"
      :on-click  #(rf/dispatch [:navigate :route/guild-vote-proposal {:proposal-id id}])}
     "Check detail"]]
   [:div.flexrr.items-center.mt-4.text-Cd6d6d6.cursor-pointer
    {:on-click #(js/open (scan-addr-url author))}
    [:span.block.mr-2 (str "by " (shorten-addr author))]
    [:a [:img.w-3 {:src "/images/share.png"}]]]
   [:div.bg-C81c6dd1a.p-4.text-Cd6d6d6.rounded-md.mt-4
    [:h3.text-xl "DESCRIPTIONS"]
    [:p.ffd.text-base body]]
   [:div.flexrr.justify-end.mt-2 [:img.w-2.mr-2 {:src "/images/votes-no.png"}] "NO" [:span.text-Cd6d6d6.mx-1 votes] [:span.text-Cd6d6d6 "VOTES"]]])

(defn proposals []
  (r/create-class
   {:component-did-mount
    (fn []
      (rf/dispatch [::snapshot/proposals "test1234567.eth"]))
    :reagent-render
    (fn []
      (let [{:keys [proposals]} @(rf/subscribe [::proposals "test1234567.eth"])]
        [into [:div.overflow-y-auto]
         (map
          (fn
            [{:keys [id title author body state votes]}]
            [item id title author body state votes])
          proposals)]))}))

(defn proposal [proposal-id]
  (r/create-class
   {:component-did-mount
    (fn []
      (if proposal-id
        (do (rf/dispatch [::snapshot/proposal proposal-id])
            (rf/dispatch [::snapshot/votes proposal-id]))))
    :reagent-render
    (fn []
      (let [{:keys [proposal votes]} @(rf/subscribe [::data proposal-id])]
        [:div "vote"
         [:pre (str proposal)]
         [:pre (str votes)]]))}))

(defn main [{:keys [path-params]}]
  (let [{:keys [proposal-id]} path-params]
    (if proposal-id
      [proposal proposal-id]
      [proposals])))
