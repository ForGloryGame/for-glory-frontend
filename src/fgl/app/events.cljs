(ns fgl.app.events
  (:require
   [re-frame.core :as rf]
   [re-pressed.core :as rp]
   [fgl.app.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(rf/reg-event-db
 ::set-re-pressed-example
 (fn [db [_ value]]
   (assoc db :re-pressed-example value)))
