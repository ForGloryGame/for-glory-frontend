(ns fgl.app.views.guild-basic
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.sgold :as sgold]
   [fgl.contracts.bfproxy :as bfproxy]
   [fgl.contracts.gamenft :as nft]
   [fgl.re-frame]
   [fgl.utils :refer [->display-token ->token-ids]]
   [fgl.app.ui.panel :as panel]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do
       ;; get staked info
       (rf/dispatch [::kingdom/init])
       (rf/dispatch [::kingdom/init-all])
       ;; get unstaked info
       (rf/dispatch [::nft/init]))
    :stop identity}])

(rf/reg-sub
 ::data
 (fn [db [_ kingdom-id]]
   (let [kingdom-id        (or kingdom-id 1)

         {::w/keys [addr]} db

         kingdoms-db       (get db ::kingdom/kingdom)

         ranks (->> kingdoms-db
                    (map (fn [[k v]] [k (:power (or v (ethers/BigNumber.from 0)))]))
                    (sort-by second (fn [x y] (try (.gt x y)
                                                   (catch js/Error e true))))
                    (enc/reduce-indexed (fn [acc idx [k]] (assoc acc k (inc idx))) {}))

         kingdom-db (get kingdoms-db kingdom-id)

         {:keys [elders senators
                 member-count name]}
         kingdom-db

         role (cond
                (some #{addr} elders)   :elder
                (some #{addr} senators) :senator
                :else :member)]

     {:addr         addr
      :rank         (get ranks kingdom-id)
      :role         role
      :kname        name
      :elders       elders
      ;; :senators     senators
      :member-count (or (and member-count (.toNumber member-count)) 0)})))

(defn member-row [idx addr]
  (r/create-class
   {:component-did-mount
    (fn []
      (rf/dispatch [::sgold/balance addr]))
    :reagent-render
    (fn []
      (let [balance @(rf/subscribe [::sgold/balance addr])]
        ^{:key idx}
        [:div.grid.justify-items-center.pb-1.border-b.border-C79c5da.pt-1_125rem.fp.font-bold
         {:style {:gridTemplateColumns "1fr 3fr 1fr"}}
         [:span "Elder"]
         [:span addr]
         [balance/ui balance]]))}))

(defn kingdom-logo [src]
  [:img.w-36.mr-12 {:src src}])

(defn kingdom-info []
  (let [{:keys [kname member-count rank role]} @(rf/subscribe [::data])]
    [:div.flex.pt-9.pb-4.pl-11
     [kingdom-logo "/images/guild-avatar.png"]
     [:div.grid.grid-cols-3.text-3xl.gap-x-8.items-center
      [:span.text-4xl.col-span-full kname]
      [:span
       [:span member-count]
       [:span.text-xl.text-C74bfcee6 "/ Members"]]
      [:span
       [:span (str "No." rank)]
       [:span.text-xl.text-C74bfcee6 "/ Rank"]]
      [:span
       [:span "XXX"]
       [:span.text-xl.text-C74bfcee6 "/ Locked"]]
      [:span
       [:span.capitalize (name role)]
       [:span.text-xl.text-C74bfcee6 "/ Your Role"]]]]))

(defn kingdom-members []
  (let [{:keys [elders]} @(rf/subscribe [::data])]
    [:div.px-6.py-4
     {:style {:height "calc(100% - 180px - 3.5rem)"}}
     [:div.text-xl.mb-2 "Member status"]
     [:div.rounded-sm.text-base.p-2.pb-0.grow
      {:style {:height     "calc(100% - 2.25rem)"
               :outline    "2px ridge #50929e33"
               :background "linear-gradient(
    rgba(163, 218, 226, 0.1),
    rgba(163, 218, 226, 0.05)
  )"}}
      [:div.rounded-sm.grid.justify-items-center.py-0_5.bg-C79c5da66.fb
       {:style {:gridTemplateColumns "1fr 3fr 1fr"}}
       [:span "ROLE"]
       [:span "ADDRESS"]
       [:span "RUNE LOCKED"]]
      [into
       [:div.overflow-auto
        {:style {:height "calc(100% - 1.625rem)"}}]
       (map-indexed (fn [idx addr]  [member-row idx addr]) elders)]]]))

(defn main []
  [:div
   [kingdom-info]
   [separator/ui]
   [kingdom-members]])
