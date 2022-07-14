(ns portal
  (:require
   ;; [portal.api :as p]
   [portal.web :as p]))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def p (p/open))
(add-tap #'p/submit) ; Add portal as a tap> target
