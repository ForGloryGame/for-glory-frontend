(ns fgl.app.views.logic.basic
  (:require
   [re-frame.core :as rf]
   [glog :refer [warn]]
   [fgl.wallet.core :as w]))

(rf/reg-event-fx
 ::not-connected
 (fn [_ _]
   (warn "not connected, back to home page")
   {:fx [[:dispatch-later {:ms 500 :dispatch [:navigate :route/home]}]]}))

(rf/reg-event-fx
 ::init
 [rf/trim-v]
 (fn [{:keys [db]} extra-inits]
   (if (first (get db ::w/addrs))
     {:fx
      (mapv #(vector :dispatch (if (keyword? %) [%] %))
            (concat [::init-gold ::int-glory] extra-inits))}
     {:fs [[:dispatch [::not-connected]]]})))