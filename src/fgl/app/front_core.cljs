(ns fgl.app.front-core
  (:require
   [log]
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   ;; [re-pressed.core :as rp]
   ;; [breaking-point.core :as bp]
   [fgl.app.routes :as routes]
   [fgl.app.events :as events]
   [fgl.app.views.front :as front-view]
   [fgl.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [front-view/main] root-el))
  (routes/init!))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
