(ns debug
  (:require
   [cljs.pprint :refer [pprint]]))

(defn ^:export tap
  "Transducer function that can execute a side-effect `effect-fn` per input"
  [effect-fn]

  (fn [rf]
    (fn
      ([] (rf))
      ([result] (rf result))
      ([result input]
       (effect-fn input)
       (rf result input)))))

(defn ^:export logjs
  ([str] (tap (partial logjs str)))
  ([str val]
   (js/console.log str (clj->js val))
   val))

(when (exists? js/window)
  (set! (.-dbg ^js js/window) clj->js)
  (set! (.-pp ^js js/window) pprint))
