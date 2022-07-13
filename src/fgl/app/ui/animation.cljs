(ns fgl.app.ui.animation
  (:require
   [re-frame.core :as rf]))

(rf/reg-event-db
 :anim
 (fn [db [_ type v]]
   (let [v (if (some? v) v (not (get db type)))]
     (assoc db type v))))

(rf/reg-sub
 :anim
 (fn [db [_ type]]
   (get db type)))

(defn anim? [type]
  (rf/subscribe [:anim type]))

(defn anim!
  ([type] (anim! type nil))
  ([type value] (rf/dispatch [:anim type value])))
