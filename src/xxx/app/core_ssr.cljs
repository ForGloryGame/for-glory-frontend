(ns xxx.app.core-ssr
  (:require
   [xxx.app.views :as views]
   [reagent.dom.server :as rs]))

(defn -main []
  (rs/render-to-static-markup [views/main-panel]))

(js/console.log (-main))
