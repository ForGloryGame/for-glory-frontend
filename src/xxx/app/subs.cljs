(ns xxx.app.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::name
 (fn [db]
   (:name db)))

(rf/reg-sub
 ::re-pressed-example
 (fn [db _]
   (:re-pressed-example db)))
