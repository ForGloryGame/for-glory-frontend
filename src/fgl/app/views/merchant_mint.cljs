(ns fgl.app.views.merchant-mint
  (:require
   ["ethers" :as ethers]
   [taoensso.encore :as enc]
   [fgl.re-frame]
   [fgl.app.ui.balance :as balance]
   [fgl.wallet.core :as w]
   [fgl.contracts.landeed :as landeed]
   [fgl.app.ui.glory-img :as gloryimg]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start identity
    :stop  identity}])

(defn main []
  [:div.flexb.px-24.w-full
   [:button.w-45%.relative.block
    ;; TODO: purchase params
    {:on-click #(rf/dispatch [::landeed/send {:method :purchase}])}
    [:img {:src "/images/mint.png"}]
    [:div.absolute.bottom-10%.right-13%.flexr
     [gloryimg/ui "3rem"]
     [balance/ui "40000000000000000000" {:className "text-2xl"}]]]
   [:img.w-45% {:src "/images/weapon.png"}]])
