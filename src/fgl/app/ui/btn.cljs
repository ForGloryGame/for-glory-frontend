(ns fgl.app.ui.btn
  (:require
   [taoensso.encore :as enc]
   [fgl.utils :refer [calc]]))

(defn ui
  ([color text width] (ui color text width {}))
  ([color text width opts]
   [:button.bg-no-repeat
    (enc/nested-merge
     {:style
      {:width              width
       :height             (calc width 0.15714286)
       :backgroundPosition "83% 46%"
       :backgroundSize     "111%"
       :backgroundImage    (str
                            "url("
                            (case color
                              :blue
                              "/images/blue-btn.svg"
                              :orange
                              "/images/orange-btn.svg")
                            ")")}}
     opts)
    text]))
