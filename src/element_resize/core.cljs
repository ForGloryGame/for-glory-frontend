(ns element-resize.core
  (:require
   [oops.core :refer [oget ocall]]
   [re-frame.core :as rf]))

(defonce cache (atom {}))

(defn- compatible? []
  (if js/ResizeObserver
    true
    (do
      (js/console.error "Don't support old browser, can't find `window.ResizeObserver`")
      false)))

(rf/reg-sub
 ::nodes
 (fn [db [_ k]]
   (let [c (get-in db [::nodes k])]
     (and c (-> c (ocall "toJSON") (js->clj :keywordize-keys true))))))

(rf/reg-event-db
 ::on-resize
 [rf/trim-v]
 (fn [db [k data]]
   (assoc-in db [::nodes k] data)))

(defn- resize-oberver-rst->rec-data [k obersver-rsts]
  (-> obersver-rsts
      first
      (oget "contentRect")
      (as-> data (rf/dispatch [::on-resize k data]))))

(defn observe!
  ([k element] (observe! k element false))
  ([k element from-effect?]
   (when (and element (compatible?))
     (when-let [dup-observer (get @cache k)]
       (ocall dup-observer "disconnect"))
     (let [observer (js/ResizeObserver. (partial resize-oberver-rst->rec-data k))]
       (swap! cache #(assoc % k observer))
       (ocall observer "observe" element)
       (if from-effect?
         {}
         [::nodes k])))))

(rf/reg-event-fx
 ::observe!
 [rf/trim-v]
 (fn [_ [k el]]
   (observe! k el true)))
