(ns fgl.app.home-core
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

(defn dev-setup []
  (when config/debug?
    (log/debug :mode :dev-mode)))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el))
  (routes/init!))

(defn init []
  (log/debug :init :root)
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
