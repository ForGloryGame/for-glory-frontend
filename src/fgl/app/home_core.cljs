(ns fgl.app.home-core
  (:require-macros [fgl.app.preload :as preload])
  (:require
   ;; [re-pressed.core :as rp]
   ;; [breaking-point.core :as bp]
   [fgl.app.routes :as routes]
   [fgl.app.events :as events]
   [fgl.app.views :as views]
   [fgl.config :as config]
   [fgl.re-frame]
   [lambdaisland.glogi :as log]
   [lambdaisland.glogi.console :as glogi-console]
   [re-frame.core :as rf]
   [reagent.dom :as rdom]))

(glogi-console/install!)
(log/set-levels {:glogi/root (if goog.DEBUG :all :info)})

(rf/reg-event-db
 :all-image-loaded
 (fn [db _]
   (if (some? (:all-image-loaded db))
     (update db :all-image-loaded inc)
     (assoc db :all-image-loaded 1))))

(def all-images (preload/images #(rf/dispatch [:all-image-loaded])))

(rf/reg-sub
 :all-image-loaded
 (fn [db _]
   (when-let [all-image-loaded (db :all-image-loaded)]
     (>= all-image-loaded (first all-images)))))

(defn dev-setup []
  (when config/debug?
    (log/debug :mode :dev-mode)))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (js/setTimeout #(rdom/render (second all-images) (.getElementById js/document "extra")) 0)
    ;; (when (not config/debug?)
    ;;   (js/setTimeout #(rdom/render (second all-images) (.getElementById js/document "extra")) 0))
    (rdom/render [views/main-panel] root-el))
  (routes/init!))

(defn init []
  (log/debug :init :root)
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
