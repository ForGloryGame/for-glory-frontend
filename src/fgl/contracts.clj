(ns fgl.contracts)

(defmacro with-provider [contract provider & body]
  `(when ~provider
     (let [~contract (fgl.contracts/connect ~contract ~provider)
           ~'r                       (fgl.contracts/get-request-fn ~contract)]
       ~@body)))

(defmacro with-provider-call [contract provider & body]
  `(when ~provider
     (let [~contract (fgl.contracts/connect ~contract ~provider)
           ~'r                       (fgl.contracts/get-call-fn ~contract)]
       ~@body)))
