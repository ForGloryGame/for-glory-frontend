(ns fgl.config)

(def debug?
  ^boolean goog.DEBUG)

(goog-define ^js/String version "unknown")

(goog-define ^js/String contract-addr-gold "unknown")
(goog-define ^js/String contract-addr-glory "unknown")
(goog-define ^js/String contract-addr-landeed "unknown")
(goog-define ^js/String contract-addr-sgold "unknown")
(goog-define ^js/String contract-addr-kingdoms "unknown")
(goog-define ^js/String contract-addr-gamenft "unknown")
(goog-define ^js/String contract-addr-gamepass "unknown")
(goog-define ^js/String contract-addr-battlefield "unknown")
(goog-define ^js/String contract-addr-minter "unknown")
(goog-define ^js/String contract-addr-battlefield-proxy "unknown")
(goog-define ^js/String target-chain-id "unknown")

(set! (.-kkkkk js/window) [contract-addr-gold
                           contract-addr-glory
                           contract-addr-landeed
                           contract-addr-sgold
                           contract-addr-kingdoms
                           contract-addr-gamenft
                           contract-addr-gamepass
                           contract-addr-battlefield
                           contract-addr-minter
                           contract-addr-battlefield-proxy
                           target-chain-id])
