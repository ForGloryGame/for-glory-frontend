(ns fgl.app.views.personal-rank
  (:require
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.header-tag :as header-tag]
   [fgl.app.ui.landeed-img :as landeedimg]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn style []
  [:style "
      .guild-rank .rank-table > *:nth-child(n + 3) {
        border-top: 1px solid rgb(121, 197, 218);
      }

      .rank-table .active {
        background-color: rgb(129, 198, 221);
        border-radius: 6px;
      }"])

(defn deeds [amount]
  (let [amount (or amount "10000000000000000000000")]
    [:div.w-full.flexr
     [header-tag/ui
      {:style {:margin "1rem 1% 0 1%" :maxWidth "20rem"}}
      [:div.flex.flex-row.justify-between.items-center.text-xl.-my-1
       [landeedimg/ui "2rem" {:style {:margin "-1.4rem 0 -1.4rem 0"}}]
       [balance/ui amount {:className "fi mr-1"}]]]]))

(defn main []
  [:<>
   [style]
   [:div.guild-rank.relative.px-10.pb-4.bg-cover.flex.flex-col
    ;; {:style {:backgroundImage "url(/images/rank-bg.png)"}}
    [:div.grid.grid-cols-3.px-9
     [:figure.relative.justify-self-center
      [:img.w-10rem {:src "/images/rank-2.png"}]
      [:figcaption.absolute.top-40.left-50%.transform.-translate-x-50%.text-center
       [:p.text-Cd5e4e8.text-xl.tracking-wider "RANK 2"]
       [:p.text-lg.mt-1 "0X0.....74DAV"]
       [deeds]]]
     [:figure.relative.justify-self-center
      [:img.w-16rem {:src "/images/rank-1.png"}]
      [:figcaption.absolute.top-44.left-50%.transform.-translate-x-50%.text-center
       [:p.text-Cefd696.text-3xl.tracking-wider "RANK 1"]
       [:p.text-lg.mt-1 "0X0.....74DAV"]
       [deeds]]]
     [:figure.relative.justify-self-center
      [:img.w-9_2rem {:src "/images/rank-3.png"}]
      [:figcaption.absolute.top-9_6rem.left-50%.transform.-translate-x-50%.text-center
       [:p.text-Cbbb9b5.text-xl.tracking-wider "RANK 3"]
       [:p.text-lg.mt-1 "0X0.....74DAV"]
       [deeds]]]]
    [:div.flex-1.bg-top.bg-no-repeat.pt-12
     {:style {:backgroundImage "url(images/rank-line-bg.svg)"
              :backgroundSize  "100%"}}
     [:div.rank-table.h-full.rounded-sm.outline-black.p-3.pb-0.text-center
      {:style {:background "linear-gradient(#A3DAE220, #A3DAE208)"
               :boxShadow  "0 1px #516b74"}}
      [:div.bg-C79c5da66.rounded-sm.grid.grid-cols-3.ffd.tracking-wide.py-0_5
       [:span "RANK"]
       [:span "ADDRESS"]
       [:span "LAND DEED"]]
      [:div.grid.grid-cols-3.py-2.text-sm
       [:span "1"]
       [:span "0X03DA.....74DAV"]
       [:span "100000"]]
      [:div.grid.grid-cols-3.py-2.text-sm
       [:span "2"]
       [:span "0X03DA.....74DAV"]
       [:span "100000"]]
      [:div.grid.grid-cols-3.py-2.text-sm.active
       [:span "3"]
       [:span "0X03DA.....74DAV"]
       [:span "100000"]]]]]])
