(ns fgl.app.events
  (:require
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ;; [re-pressed.core :as rp]
   [fgl.app.db :as db]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))
