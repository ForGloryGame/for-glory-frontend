(ns fgl.app.home-ssr
  (:require
   [fgl.app.views :as views]
   [reagent.dom.server :as rs]))

(defn main []
  (js/console.log (rs/render-to-static-markup [views/main-panel])))
