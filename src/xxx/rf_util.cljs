(ns xxx.rf-util
  (:require
   [re-frame.core :as rf]))

(defn simple-reg
  ([k] (simple-reg k {}))
  ([k {:keys [on-v]}]
   (rf/reg-sub k (fn [db] (get db k)))
   (rf/reg-event-db k (fn [db [_ v]]
                        (and (fn? on-v) (on-v))
                        (assoc db k v)))))
