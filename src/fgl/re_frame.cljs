(ns fgl.re-frame
  (:require
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [re-frame.fx :as rfx]
   [re-frame.interop :as rfi]
   [re-frame.loggers     :refer [console]]
   [re-frame.router :as router]
   [reagent.core :as r]))

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
      ;; interceptors
      (fn [_ _]
        {:dispatch-p {:id id :ms ms :dispatch [raw-id]}})))))

(defn- ignore-dispose?
  [query-vector]
  (:ignore-dispose (meta query-vector)))

(defn- dispose-maybe
  "Dispose of `ratom-or-reaction` iff it has no watches."
  [query-vector ratom-or-reaction]
  (when-not (seq (.-watches ^r/RAtom ratom-or-reaction))
    (when-not (ignore-dispose? query-vector)
      (console :warn "Disposing of injected subscription:" query-vector))
    (rfi/dispose! ratom-or-reaction)))

(defmulti ^:private inject
  "Inject the `:sub` cofx.

  If `query-vector-or-event->query-vector-fn` is a subscription vector, subscribe and
  dereference that subscription before assoc'ing its value in the coeffects map
  under the id of the subscription and disposing of it.

  If `query-vector-or-event->query-vector-fn` is a fn, it should take a single
  argument which is the event args vector for that handler (similar to the
  2-arity of `re-frame.core/reg-sub`). Its return value should be a query-vector
  or nil. From there on the behavior is similar to when passing a query-vector.

  NOTE that if there are no components subscribed to that subscription the cofx
  will dispose of it in order to prevent leaks. However there is a performance
  penalty to doing this since we pay for a re-frame subscription cache miss
  every time we inject it. In such cases the cofx will log a warning which can
  be ignored by setting `:ignore-dispose` on the subscription vector's meta. A
  rule of thumb for what to do here would be that if an injected sub is disposed
  of very often, we should either rework the subscription graph so that it ends
  up used by a component and thus cached, or we should extract the db lookup
  logic into a function that can be used to get the value straight from the db
  inside the handler. It seems safe to decide to ignore the warning when the
  disposal doesn't happen too often and it is just more convenient to reuse the
  subscription's logic.

  Examples:

  (require '[vimsical.re-frame.cofx.inject :as inject])

  ;; Injecting a simple subscription:

  (re-frame/reg-sub ::simple ...)

  (re-frame/reg-event-fx
   ::simple-handler
   [(re-frame/inject-cofx :inject/sub [::simple]]]
   (fn [{:as cofx {::keys [simple]} params]
     ...)


  ;; Injecting a dynamic subscription depending on the event parameters:

  (re-frame/reg-sub ::dynamic (fn [_ [_ arg1 arg2]] ...))

  (re-frame/reg-event-fx
   ::dynamic-handler
   [(re-frame/inject-cofx :inject/sub
      (fn [[_ arg1 arg2]]
        ...
        [::dynamic arg1 arg2]))]
   (fn [{:as cofx {::keys [dynamic]} [_ arg1 arg-2]]
     ...)
  "
  (fn [coeffects query-vector-or-event->query-vector-fn]
    (cond
      (vector? query-vector-or-event->query-vector-fn) ::query-vector
      (ifn? query-vector-or-event->query-vector-fn)    ::event->query-vector-fn)))

(defmethod inject ::query-vector
  [coeffects [id :as query-vector]]
  (let [sub (rf/subscribe query-vector)
        val (deref sub)]
    (dispose-maybe query-vector sub)
    (assoc coeffects id val)))

(defmethod inject ::event->query-vector-fn
  [{:keys [event] :as coeffects} event->query-vector-fn]
  (if-some [[id :as query-vector] (event->query-vector-fn event)]
    (let [sub (rf/subscribe query-vector)
          val (deref sub)]
      (dispose-maybe query-vector sub)
      (assoc coeffects id val))
    coeffects))

;;
;; * Entry point
;;

(rf/reg-cofx :inject/sub inject)
