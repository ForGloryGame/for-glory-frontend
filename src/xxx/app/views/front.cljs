(ns xxx.app.views.front
  (:require
   ;; [re-frame.core :as rf]
   ;; [taoensso.timbre :as log]
   [xxx.app.views.front.header :as header]
   [xxx.app.views.front.body :as body]
   [xxx.app.views.front.footer :as footer]))

(defn jump []
  (when-let [el (and (not (= "" js/location.hash)) (js/document.getElementById (.slice js/location.hash 1)))]
    ;; (js/scrollTo #js {:top (= js/window.scrollY (.-top (.getBoundingClientRect el)))})
    (.scrollIntoView el)))

(defn controllers []
  [{:start #(js/setTimeout jump 500)
    :stop  identity}])

(defn main [_]
  [:div.main
   [header/ui]
   [body/ui]
   [:link {:rel "stylesheet" :href "/fonts/family.css"}]
   [footer/ui]])
