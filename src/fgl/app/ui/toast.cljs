(ns fgl.app.ui.toast
  (:require
   [lambdaisland.glogi :as log]
   ["@radix-ui/react-toast" :as Toast]
   [taoensso.encore :refer [dissoc-in]]
   [re-frame.core :as rf]))

(defonce toast-id (atom 0))

(rf/reg-sub
 :toast/data
 (fn [db _]
   (get db :toast/data [])))

(rf/reg-event-db
 :toast/success
 (fn [db [_ payload]]
   (if-let [title (and (map? payload) (:title payload))]
     (assoc-in db [:toast/data (swap! toast-id inc)] {:title title :desc (:desc payload) :type :success})
     (assoc-in db [:toast/data (swap! toast-id inc)] {:title [:h1 "Success"] :desc [:p] :type :success}))))

(rf/reg-event-db
 :toast/failure
 (fn [db [_ payload]]
   (if-let [title (and (map? payload) (:title payload))]
     (assoc-in db [:toast/data (swap! toast-id inc)] {:title title :desc (:desc payload) :type :failure})
     (assoc-in db [:toast/data (swap! toast-id inc)] {:title [:h1 "Failed"] :desc [:p] :type :failure}))))

(rf/reg-event-db
 :toast/close
 (fn [db [_ id]]
   (dissoc-in db [:toast/data id])))

(defn toast [id title desc]
  (let [title (if (string? title) [:h1 title] title)
        desc (if (string? desc) [:p desc] desc)]
    [:> Toast/Root
     {:key id :onOpenChange #(and (not %) (rf/dispatch [:toast/close id]))}
     [:> Toast/Title title]
     [:> Toast/Description desc]
     [:> Toast/Action {:altText "altText"} "OK"]
     [:> Toast/Close "x"]]))

(defn toasts []
  (let [data (rf/subscribe [:toast/data])
        ui
        (map (fn [[id {:keys [title desc]}]] ^{:key id} [toast id title desc]) @data)]
    ui))

(defn ui []
  [into
   ^{:key 'toast}
   [:> Toast/Provider
    [:> Toast/Viewport {:asChild true}
     [:div.fixed.bottom-0.right-0.w-20.flex.flex-col.gap-10px.p-3.max-w-100vw.m-0.z-2147483647.list-none]]]
   (toasts)])
