(ns fgl.app.front-ssr
  (:require
   [fgl.app.views.front :as front-view]
   [reagent.dom.server :as rs]))

(defn main []
  (js/console.log (rs/render-to-static-markup [front-view/main])))
