(ns fgl.re-frame
  (:require
   [lambdaisland.glogi :as log]
   [re-frame.loggers     :refer [console]]
   [re-frame.fx :as rfx]
   [re-frame.router :as router]
   [re-frame.interop :as rfi]
   [re-frame.core :as rf]))

(defonce cache (atom {}))

(defn dispatch-periodically
  [{:keys [ms dispatch id] :as effect}]
  (when (or (not (get @cache id)) (::dispatch-periodically effect))
    (if (or (empty? dispatch) (not (number? ms)) (not id))
      (console :error "re-frame: ignoring bad :dispatch-later value:" effect)
      (do (router/dispatch dispatch)
          (rfi/set-timeout! #(dispatch-periodically (assoc effect ::dispatch-periodically true)) ms))))
  (when-not (get @cache id) (swap! cache #(assoc % id true))))

(rf/reg-fx
 :dispatch-p
 (fn [value]
   (if (map? value)
     (dispatch-periodically value)
     (doseq [effect (remove nil? value)]
       (dispatch-periodically effect)))))

(defn append-raw [k]
  (keyword (namespace k) (str (name k) "-raw")))

(defn dispatch-p
  ([id] (dispatch-p id 10000 [id]))
  ([id ms] (dispatch-p id ms [id]))
  ([id ms dispatch]
   (fn [_ _]
     {:dispatch-p {:id id :ms ms :dispatch dispatch}})))

(defn reg-event-pfx
  ([id ms handler] (reg-event-pfx id ms [] handler))
  ([id ms interceptors handler]
   (let [raw-id (append-raw id)]
     (rf/reg-event-fx raw-id interceptors handler)
     (rf/reg-event-fx
      id
      ;; interceptors handler

      (fn [_ _]
        {:dispatch-p {:id id :ms ms :dispatch [raw-id]}})))))
