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
(goog-define ^js/String contract-addr-exchange "0x0000000000000000000000000000000000000000")
(goog-define ^js/String target-chain-id "unknown")

(defonce explorer-url (case target-chain-id
                        "0x4" "https://rinkeby.etherscan.io"
                        "https://etherscan.io"))
