(ns fgl.app.ui.token-logo
  (:require
   [fgl.app.ui.glory-img]
   [fgl.app.ui.gold-img]))

(defn ui [logo size]
  (cond
    (= logo :glory)
    [fgl.app.ui.glory-img/ui size]
    (= logo :gold)
    [fgl.app.ui.gold-img/ui size]
    :else
    nil))
