(ns fgl.contracts)

(defmacro with-provider [contract provider & body]
  `(when ~provider
     (let [~(symbol (name contract)) (fgl.contracts/connect ~contract ~provider)
           ~'r                       (fgl.contracts/get-request-fn ~(symbol (name contract)))]
       ~@body)))
