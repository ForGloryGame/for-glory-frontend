(ns fgl.app.front-core
  (:require
   ;; [re-pressed.core :as rp]
   ;; [breaking-point.core :as bp]
   [fgl.app.routes :as routes]
   [fgl.app.events :as events]
   [fgl.app.views.front :as front-view]
   [fgl.config :as config]
   [lambdaisland.glogi :as log]
   [lambdaisland.glogi.console :as glogi-console]
   [re-frame.core :as rf]
   [reagent.dom :as rdom]))

(glogi-console/install!)
(log/set-levels {:glogi/root (if goog.DEBUG :all :info)})

(defn dev-setup []
  (when config/debug?
    (log/debug :mode "dev mode")))

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
