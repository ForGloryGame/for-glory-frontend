(ns fgl.contracts)

(defmacro with-provider [contract provider & body]
  (let [maybe-address (first body)]
    `(let [('(name ~contract)) (connect (symbol (name ~contract)) ~provider)
           '(name ~contract)   (if (string? maybe-address) (.attach ~contract maybe-address) contract)]
       ~@body)))
