(ns fgl.app.views.merchant-mint
  (:require
   ["@radix-ui/react-dialog" :as D]
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.contracts.gameminter :as minter]
   [fgl.wallet.core :as w]
   [fgl.contracts.landeed :as landeed]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.loading-dot :as ld]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start #(rf/dispatch [::minter/init])
    :stop  identity}])

(defn- reveal [addr]
  (rf/dispatch
   [::minter/send
    {:method       :revealMint
     :params       [addr]
     :on-submitted #(rf/dispatch
                     [::dialog/set
                      :merchant-mint
                      :description
                      [dialog/pending]])
     :on-success   #(rf/dispatch
                     [::dialog/set
                      :merchant-mint
                      :close? true
                      :description
                      [dialog/confirmed]
                      :actions [dialog/close {:id :merchant-mint :t :bsm} "OK"]])
     :on-failure   (partial dialog/failed :merchant-mint)}])

  (rf/dispatch [::dialog/set :merchant-mint :description [dialog/submitting] :actions nil]))

(defn commit-confirmed []
  [::dialog/set :merchant-mint
   :description [:<>
                 [:span "TX Submitted"]
                 [:br]
                 [:span "TX Confirmed"]]])

(defn tx-failed [{:keys [title desc]}]
  (rf/dispatch
   [::dialog/set
    :merchant-mint
    :title
    title
    :description
    [:<>
     [:span "Reason:"]
     [:br]
     [:p (or desc title)]]
    :actions
    (dialog/close
     {:id :merchant-mint :t :bsm}
     "OK")]))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db
         to-reveal         (get-in db [addr ::minter/to-reveal])]
     {:addr      addr
      :to-reveal to-reveal})))

(defn maybe-show-reveal-dialog []
  (when-let [{:keys [addr to-reveal]} @(rf/subscribe [::data])]
    (rf/dispatch
     [::dialog/set
      :merchant-mint
      :open true
      :title
      "Reveal Mint Commits"
      :description
      [:<>
       [:p
        "Found " [:b to-reveal] " to-reveal commit" (and (> to-reveal 1) "s")]
       [:br]
       [:p "Click the REVEAL button below to reveal them"]]
      :actions [btn/ui {:t :bsm :on-click (partial reveal addr)} "REVEAL"]])))

(defn main []
  (maybe-show-reveal-dialog)
  [:div.flexb.px-24.w-full
   [dialog/root
    {:id          :merchant-mint
     :title       "Minting"
     :description [dialog/submitting]
     :modal       true}
    [dialog/trigger
     {:className "w-45% relative block"
      :on-click  #(do
                    (rf/dispatch
                     [::minter/send
                      {:method     :commitMint
                       :params     [1 false]
                       :on-submitted
                       (fn [_]
                         (rf/dispatch
                          [::dialog/set
                           :merchant-mint
                           :description
                           [dialog/pending]]))
                       :on-success
                       (fn [_]
                         (rf/dispatch (commit-confirmed))
                         (rf/dispatch [::minter/init-raw]))
                       :on-failure (partial dialog/failed :merchant-mint)}])
                    (rf/dispatch
                     [::dialog/set
                      :merchant-mint
                      :open
                      true]))}
     [:img {:src "/images/mint.png"}]
     [:div.absolute.bottom-10%.right-13%.flexr
      [gloryimg/ui "3rem"]
      [balance/ui "40000000000000000000" {:className "text-2xl"}]]]]

   [:img.w-45% {:src "/images/weapon.png"}]])
