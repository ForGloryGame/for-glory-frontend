(ns fgl.app.core-ssr
  (:require
   [fgl.app.views :as views]
   [reagent.dom.server :as rs]))

(defn -main []
  (rs/render-to-static-markup [views/main-panel]))

(js/console.log (-main))
