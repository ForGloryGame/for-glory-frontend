(ns fgl.app.views.logic.basic
  (:require
   [fgl.wallet.core :as w]
   [glog :refer [warn]]
   [re-frame.core :as rf]))

(rf/reg-event-fx
 ::not-connected
 (fn [_ _]
   (warn "not connected, back to home page")
   {:fx [[:dispatch-later {:ms 500 :dispatch [:navigate :route/home]}]]}))

(rf/reg-event-fx
 ::init
 [rf/trim-v]
 (fn [{:keys [db]} extra-inits]
   (if (get db ::w/addr)
     {:fx
      (mapv #(vector :dispatch (if (keyword? %) [%] %))
            (concat [::init-gold ::int-glory] extra-inits))}
     {:fs [[:dispatch [::not-connected]]]})))
