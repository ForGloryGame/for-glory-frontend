(ns fgl.app.views.merchant-mint
  (:require
   ["@radix-ui/react-dialog" :as D]
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.contracts.landeed :as landeed]
   [fgl.contracts.gameminter :as minter]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.loading-dot :as ld]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn submitting []
  [ld/text "Waiting For Wallet Confirmation"])

(defn- reveal [addr]
  (rf/dispatch [::minter/send {:method :revealMint :params [addr]}])
  (rf/dispatch [::dialog/set :merchant-mint :description [submitting] :actions nil]))

(defn pending []
  [:<>
   [:span "TX Submitted"]
   [:br]
   [ld/text "Waiting For TX Confirmation"]])

(defn commit-confirmed [addr]
  [::dialog/set :merchant-mint
   :description [:<>
                 [:span "TX Submitted"]
                 [:br]
                 [:span "TX Confirmed"]
                 [:br]
                 [:span "Click below button to reveal the MINT"]]
   :actions [btn/ui {:t :bsm :on-click (partial reveal addr)} "REVEAL"]])

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
     [:p desc]]
    :actions
    (dialog/close
     {:id :merchant-mint :t :bsm}
     "OK")]))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db]
     {:addr addr})))

(defn main []
  (let [{:keys [addr]} @(rf/subscribe [::data])]
    [:div.flexb.px-24.w-full
     [dialog/root
      {:id          :merchant-mint
       :title       "Minting"
       :description [submitting]
       :modal       true
       :close       false}
      [dialog/trigger
       {:className "w-45% relative block"
        :on-click  #(do
                      (rf/dispatch
                       [::minter/send
                        {:method :commitMint
                         :params [1 false]
                         :on-submitted
                         (fn [_]
                           (rf/dispatch
                            [::dialog/set
                             :merchant-mint
                             :description
                             [pending]]))
                         :on-success
                         (fn [_]
                           (rf/dispatch (commit-confirmed addr)))
                         :on-failure tx-failed}])
                      (rf/dispatch
                       [::dialog/set
                        :merchant-mint
                        :open
                        true]))}
       [:img {:src "/images/mint.png"}]
       [:div.absolute.bottom-10%.right-13%.flexr
        [gloryimg/ui "3rem"]
        [balance/ui "40000000000000000000" {:className "text-2xl"}]]]]

     [:img.w-45% {:src "/images/weapon.png"}]]))
