(ns fgl.contracts.kingdom.abi)

(defonce data
  [{:anonymous false
    :inputs
    [{:indexed      true
      :internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}
     {:indexed      true
      :internalType "address"
      :name         "_account"
      :type         "address"}]
    :name      "Join"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}
     {:indexed      true
      :internalType "address"
      :name         "_account"
      :type         "address"}]
    :name      "Leave"
    :type      "event"}
   {:anonymous false
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
   {:inputs          []
    :name            "DEFAULT_ADMIN_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}
     {:internalType "string" :name "_name" :type "string"}]
    :name            "changeKingdomName"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "devWallet"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "entryFee"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_account" :type "address"}]
    :name            "getAccountInfo"
    :outputs
    [{:internalType "enum Kingdoms.Kingdom" :name "" :type "uint8"}
     {:internalType "uint256" :name "" :type "uint256"}
     {:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}]
    :name            "getElders"
    :outputs
    [{:internalType "address[5]" :name "" :type "address[5]"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}]
    :name            "getMemberCount"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}
     {:internalType "uint256" :name "_offset" :type "uint256"}
     {:internalType "uint256" :name "_count" :type "uint256"}]
    :name            "getMemberList"
    :outputs         [{:internalType "address[]" :name "" :type "address[]"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}]
    :name            "getRoleAdmin"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}]
    :name            "getSenators"
    :outputs
    [{:internalType "address[10]" :name "" :type "address[10]"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}
     {:internalType "address" :name "_account" :type "address"}]
    :name            "getVotingPower"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
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
    [{:internalType "address" :name "_goldToken" :type "address"}
     {:internalType "address" :name "_sGold" :type "address"}
     {:internalType "address" :name "_treasury" :type "address"}
     {:internalType "address" :name "_devWallet" :type "address"}]
    :name            "initialize"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom"
      :name         "_kingdom"
      :type         "uint8"}]
    :name            "join"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "enum Kingdoms.Kingdom" :name "" :type "uint8"}]
    :name            "kingdoms"
    :outputs
    [{:internalType "address" :name "treasury" :type "address"}
     {:internalType "uint256" :name "power" :type "uint256"}
     {:internalType "uint256" :name "nonce" :type "uint256"}
     {:internalType "string" :name "name" :type "string"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "leave"
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
   {:inputs
    [{:internalType "bytes32" :name "role" :type "bytes32"}
     {:internalType "address" :name "account" :type "address"}]
    :name            "revokeRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "sGold"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "bytes4" :name "interfaceId" :type "bytes4"}]
    :name            "supportsInterface"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "treasury"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "_entryFee" :type "uint256"}]
    :name            "updateEntryFee"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_account" :type "address"}
     {:internalType "uint256" :name "_newPower" :type "uint256"}]
    :name            "updatePower"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}])
