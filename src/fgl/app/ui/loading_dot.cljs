(ns fgl.app.ui.loading-dot)

(defn ui
  ([] (ui {}))
  ([opt]
   [:span.inline-block.dot-flashing opt]))

(defn text
  ([t] (text t {}))
  ([t opt] [:span.flexrr opt t [ui {:className "ml-6"}]]))
