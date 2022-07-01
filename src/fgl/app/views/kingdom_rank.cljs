(ns fgl.app.views.kingdom-rank
  (:require
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.header-tag :as header-tag]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn style []
  [:style "
      .guild-rank .rank-bg{
        position: absolute;
        width: 100%;
        top: 0;
        bottom: -1.6rem;
        background-image: linear-gradient(to bottom, transparent 80%, #81c6dd08 );
      }

      .guild-rank .rank-bg.active{
        background-image: linear-gradient(to bottom, transparent 80%, #81c6dd50);
      } "])

(defn sgold [amount]
  (let [amount (or amount "10000000000000000000000")]
    [:div.w-full.flexr
     [header-tag/ui
      {:style {:margin "1rem 10% 0 10%" :maxWidth "7rem"}}
      [:div.flex.flex-row.justify-between.items-center.text-xl.-my-1
       [sgoldimg/ui "2rem" {:style {:margin "-1.4rem 0 -1.4rem 0"}}]
       [balance/ui amount {:className "fi mr-1"}]]]]))

(defn kingdom->img [k]
  (let [n (-> kingdom/kingdoms-name
              (get k)
              .toLowerCase)]
    (str "/images/kingdom-" n ".png")))

(def img-class
  {1 "w-42"
   2 "w-40 -translate-y-5%"
   3 "w-38 translate-y-5%"
   4 "w-40 -translate-y-1%"
   5 "w-50 translate-y-5%"})

(def imgr
  {1 [:img.w-48 {:src "/images/kingdom-rank-1.png"}]
   2 [:img.w-40 {:src "/images/kingdom-rank-2.png"}]
   3 [:img.w-40 {:src "/images/kingdom-rank-3.png"}]
   4 [:img.w-32 {:src "/images/kingdom-rank-4.png"}]
   5 [:img.w-32 {:src "/images/kingdom-rank-5.png"}]})
(def p
  {1 [:p.text-Cefd696.text-3xl.tracking-wider "RANK 1"]
   2 [:p.text-Cd5e4e8.text-xl.tracking-wider "RANK 2"]
   3 [:p.text-Cd5e4e8.text-xl.tracking-wider "RANK 3"]
   4 [:p.text-Cd5e4e8.tracking-wider "RANK 4"]
   5 [:p.text-Cd5e4e8.tracking-wider "RANK 5"]})

(def imgk
  {1 :img.w-28.inline-block.mt-7.mb-12
   2 :img.w-24.inline-block.mt-5.mb-8
   3 :img.w-24.inline-block.mt-5.mb-8
   4 :img.w-4_5rem.inline-block.mt-3.mb-5
   5 :img.w-28.inline-block.mt-3.mb-5})

(rf/reg-sub
 ::data
 (fn [db [_ k]]))

(defn rank
  "r rank, k kingdom id"
  [r k mine?]
  (let []
    [:div.relative
     [get imgr r]
     [:div.absolute.top-4.left-50%.transform.-translate-x-50%.text-center.w-full
      [get p r]
      [(get imgk r)
       {:src (kingdom->img k)}]
      [:p.text-xl.tracking-wider
       (-> kingdom/kingdoms-name
           (get k))]
      [sgold]]
     [:div.rank-bg
      {:className (if mine? "active" "")}]
     (and mine?
          [:p.absolute.text-center.text-sm.text-Cffffff66.w-full.-bottom-5.tracking-wider.ffd
           "MY KINGDOM"])]))

(defn main []
  (r/create-class
   {:component-did-mount
    #(rf/dispatch [::kingdom/get-account-info false])
    :reagent-render
    (fn []
      [:<>
       [style]
       [:div.guild-rank.relative.px-10.bg-cover.flex.flex-col.h-full
        ;; {:style {:backgroundImage "url(/images/rank-bg.png)"}}
        [:div.flex.justify-around.flex-1
         [rank 4 3]
         [rank 2 5 true]
         [rank 1 1]
         [rank 3 4]
         [rank 5 2]]
        [:div.bg-top.bg-no-repeat.pt-12
         {:style {:backgroundImage "url(images/rank-line-bg.svg)"
                  :backgroundSize  "100%"}}]]])}))
