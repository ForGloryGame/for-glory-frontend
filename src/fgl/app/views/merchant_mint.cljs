(ns fgl.app.views.merchant-mint
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.contracts.gameminter :as minter]
   [fgl.wallet.core :as w]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.dialog :as dialog]
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
                      :desc
                      [dialog/pending]])
     :on-success   #(rf/dispatch
                     [::dialog/set
                      :close? true
                      :desc
                      [dialog/confirmed]
                      :actions [dialog/close {:t :bsm} "OK"]])
     :on-failure   dialog/failed}])

  (rf/dispatch [::dialog/set :desc [dialog/submitting] :actions nil]))

(defn commit-confirmed []
  [::dialog/set
   :desc [:<>
          [:span "TX Submitted"]
          [:br]
          [:span "TX Confirmed"]]])

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db
         to-reveal         (get-in db [addr ::minter/to-reveal])]
     {:addr      addr
      :to-reveal to-reveal})))

(defn maybe-show-reveal-dialog []
  (let [{:keys [addr to-reveal]} @(rf/subscribe [::data])]
    (when to-reveal
      (rf/dispatch
       [::dialog/set
        :open true
        :title
        "Reveal Mint Commits"
        :desc
        [:<>
         [:p "Found pending commit"]
         [:br]
         [:p "Click the REVEAL button below to reveal them"]]
        :actions [btn/ui {:t :bsm :on-click (partial reveal addr)} "REVEAL"]]))))

(defn main []
  (maybe-show-reveal-dialog)
  [:div.flexb.px-24.w-full
   [:button
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
                          :desc
                          [dialog/pending]]))
                      :on-success
                      (fn [_]
                        (rf/dispatch (commit-confirmed))
                        (rf/dispatch [::minter/init-raw]))
                      :on-failure dialog/failed}])
                   (rf/dispatch
                    [::dialog/set
                     :open
                     true]))}
    [:img {:src "/images/mint.png"}]
    [:div.absolute.bottom-10%.right-13%.flexr
     [gloryimg/ui "3rem"]
     [balance/ui "40000000000000000000" {:className "text-2xl"}]]]

   [:img.w-45% {:src "/images/weapon.png"}]])
