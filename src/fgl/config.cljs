(ns fgl.config)

(def debug?
  ^boolean goog.DEBUG)

(goog-define ^js/String version "unknown")
(goog-define ^js/String contract-addr "unknown")
(goog-define ^js/String target-chain-link "unknown")
