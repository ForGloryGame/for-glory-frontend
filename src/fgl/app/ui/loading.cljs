(ns fgl.app.ui.loading
  (:require
   [taoensso.encore :as enc]))

(defn ui
  ([] (ui {}))
  ([opts]
   [:img.animate-spin.block
    (enc/nested-merge {:src "/images/loading.svg"} opts)]))
