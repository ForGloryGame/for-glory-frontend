(ns fgl.contracts)

(defmacro with-provider [contract provider & body]
  `(let [(symbol (name ~contract)) (connect (symbol (name ~contract)) ~provider)]
     ~@body))
