(ns fgl.app.views.dashboard
  (:require
   [util :refer [copy-to-clipboard]]
   [fgl.utils :refer [scan-addr-url]]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.gamepass :as gamepass]
   [fgl.contracts.glory :as glory]
   [fgl.contracts.gold :as gold]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(defn controllers []
  [{:start
    #(do (rf/dispatch [::nft/init])
         (rf/dispatch [::gold/init])
         (rf/dispatch [::glory/init])
         ;; (rf/dispatch [::gamepass/init])
         )
    :stop identity}])

(defn close []
  (let [to-home #(rf/dispatch [:navigate :route/home])]
    (fn []
      [:button.cs2.ce3.rs1.re2 {:on-click to-home} "x"])))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::w/keys [addr]} db]
     [addr])))

(defn avatars []
  (let [[addr] @(rf/subscribe [::data])]
    [:div.cs1.ce3.rs2.re3.grid.border
     [:div.cs1.ce2.rs1.re4 "avatar1 missing logic here"]
     [:div.cs2.ce4.rs1.re2 addr]
     [:div.cs2.ce3.rs2.re3 [:a {:href (scan-addr-url addr) :target :_blank} "View on explorer"]]
     [:div.cs3.ce4.rs2.re3 [:button {:on-click #(do (copy-to-clipboard addr)
                                                    (rf/dispatch [:toast/success {:title "Copied!" :no-close true}]))} "Copy Address"]]
     [:div.cs4.ce5.rs1.re3 "kingdom avatar missing logic here"]
     [:div.cs5.ce6.rs1.re2 "kingdom name"]
     [:div.cs6.ce7.rs1.re2 "role"]
     [:div.cs5.ce7.rs2.re3 "view on explorer"]]))

(defn table []
  (let []
    [:div.cs1.ce3.rs3.re4.grid.border "table"]))

(defn main [_]
  (let []
    (fn []
      (r/create-class
       {:reagent-render
        (fn []
          (let []
            [:div.grid.p-1.border.justify-between
             [:h1.cs1.ce2.rs1.re2 "Personal Center"]
             [close]
             [avatars]
             [table]]))}))))
