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
   (assoc-in db [:toast/data (swap! toast-id inc)] (assoc payload :type :success))))

(rf/reg-event-db
 :toast/failure
 (fn [db [_ payload]]
   (assoc-in db [:toast/data (swap! toast-id inc)] (assoc payload :type :failure))))

(rf/reg-event-db
 :toast/close
 (fn [db [_ id]]
   (dissoc-in db [:toast/data id])))

(defn toast [id title desc type no-close actions]
  (let [title (if (string? title) [:h1 title] title)
        desc (if (string? desc) [:p desc] desc)]
    [:> Toast/Root
     {:key id :onOpenChange #(and (not %) (rf/dispatch [:toast/close id]))}
     [:> Toast/Title title]
     (and desc [:> Toast/Description desc])
     (and (seq actions) (map #(into [:> Toast/Action %]) actions))
     (and (not no-close) [:> Toast/Close "x"])]))

(defn toasts []
  (let [data (rf/subscribe [:toast/data])
        ui
        (map (fn [[id {:keys [title desc type no-close actions]}]] ^{:key id} [toast id title desc type no-close actions]) @data)]
    ui))

(defn ui []
  [into
   ^{:key 'toast}
   [:> Toast/Provider
    [:> Toast/Viewport {:asChild true}
     [:div.fixed.bottom-0.right-0.w-20.flex.flex-col.gap-10px.p-3.max-w-100vw.m-0.z-2147483647.list-none]]]
   (toasts)])
