(ns fgl.contracts.exchange.abi)

(def data
  [{:anonymous false
    :inputs
    [{:indexed      true
      :internalType "bytes32"
      :name         "role"
      :type         "bytes32"}
     {:indexed      true
      :internalType "bytes32"
      :name         "previousAdminRole"
      :type         "bytes32"}
     {:indexed      true
      :internalType "bytes32"
      :name         "newAdminRole"
      :type         "bytes32"}]
    :name      "RoleAdminChanged"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "bytes32"
      :name         "role"
      :type         "bytes32"}
     {:indexed      true
      :internalType "address"
      :name         "account"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "sender"
      :type         "address"}]
    :name      "RoleGranted"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "bytes32"
      :name         "role"
      :type         "bytes32"}
     {:indexed      true
      :internalType "address"
      :name         "account"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "sender"
      :type         "address"}]
    :name      "RoleRevoked"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "_sender"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "_recipient"
      :type         "address"}
     {:indexed      false
      :internalType "uint256"
      :name         "_amountIn"
      :type         "uint256"}
     {:indexed      false
      :internalType "uint256"
      :name         "_amountOut"
      :type         "uint256"}]
    :name      "Swap"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "_caller"
      :type         "address"}
     {:indexed      false
      :internalType "uint256"
      :name         "_decayInterval"
      :type         "uint256"}]
    :name      "UpdateDecayInterval"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "_caller"
      :type         "address"}
     {:indexed      false
      :internalType "uint256"
      :name         "_floorPrice"
      :type         "uint256"}]
    :name      "UpdateFloorPrice"
    :type      "event"}
   {:inputs          []
    :name            "DEFAULT_ADMIN_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "devWallet"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "getReserves"
    :outputs
    [{:internalType "uint256" :name "_rGold" :type "uint256"}
     {:internalType "uint256" :name "_rGlory" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}]
    :name            "getRoleAdmin"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "gloryToken"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "goldToken"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "bytes32" :name "role" :type "bytes32"}
     {:internalType "address" :name "account" :type "address"}]
    :name            "grantRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "bytes32" :name "role" :type "bytes32"}
     {:internalType "address" :name "account" :type "address"}]
    :name            "hasRole"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_treasury" :type "address"}
     {:internalType "address" :name "_devWallet" :type "address"}
     {:internalType "address" :name "_goldToken" :type "address"}
     {:internalType "address" :name "_gloryToken" :type "address"}
     {:internalType "uint112" :name "_reserveGold" :type "uint112"}
     {:internalType "uint112" :name "_reserveGlory" :type "uint112"}
     {:internalType "uint32" :name "_decayInterval" :type "uint32"}]
    :name            "initialize"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "bytes32" :name "role" :type "bytes32"}
     {:internalType "address" :name "account" :type "address"}]
    :name            "renounceRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "reserveControlInfo"
    :outputs
    [{:internalType "uint112" :name "floorPrice" :type "uint112"}
     {:internalType "uint112" :name "multiplier" :type "uint112"}
     {:internalType "uint32" :name "decayInternal" :type "uint32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "bytes32" :name "role" :type "bytes32"}
     {:internalType "address" :name "account" :type "address"}]
    :name            "revokeRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "bytes4" :name "interfaceId" :type "bytes4"}]
    :name            "supportsInterface"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_recipient" :type "address"}
     {:internalType "uint256" :name "_amountIn" :type "uint256"}
     {:internalType "uint256" :name "_minAmountOut" :type "uint256"}]
    :name            "swap"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "tradeInfo"
    :outputs
    [{:internalType "uint112" :name "reserveGold" :type "uint112"}
     {:internalType "uint112" :name "reserveGlory" :type "uint112"}
     {:internalType "uint32" :name "lastTimestamp" :type "uint32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "treasury"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint32" :name "_decayInterval" :type "uint32"}]
    :name            "updateDecayInterval"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "uint112" :name "_floorPrice" :type "uint112"}]
    :name            "updateFloorPrice"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}])
