(ns fgl.app.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [re-pressed.core :as rp]
   [breaking-point.core :as bp]
   [fgl.app.routes :as routes]
   [fgl.app.events :as events]
   [fgl.app.views :as views]
   [fgl.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el))
  (routes/init!))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  ;; (rf/dispatch-sync [::rp/add-keyboard-event-listener "keydown"])
  ;; (rf/dispatch-sync [::bp/set-breakpoints
  ;;                    {:breakpoints [:mobile
  ;;                                   768
  ;;                                   :tablet
  ;;                                   992
  ;;                                   :small-monitor
  ;;                                   1200
  ;;                                   :large-monitor]
  ;;                     :debounce-ms 166}])
  (dev-setup)
  (mount-root))
