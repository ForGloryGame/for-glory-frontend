(ns fgl.contracts.gamepass.abi)

(defonce data
  [{:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "account"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "operator"
      :type         "address"}
     {:indexed      false
      :internalType "bool"
      :name         "approved"
      :type         "bool"}]
    :name      "ApprovalForAll"
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
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "operator"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "from"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "to"
      :type         "address"}
     {:indexed      false
      :internalType "uint256[]"
      :name         "ids"
      :type         "uint256[]"}
     {:indexed      false
      :internalType "uint256[]"
      :name         "values"
      :type         "uint256[]"}]
    :name      "TransferBatch"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "operator"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "from"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "to"
      :type         "address"}
     {:indexed      false
      :internalType "uint256"
      :name         "id"
      :type         "uint256"}
     {:indexed      false
      :internalType "uint256"
      :name         "value"
      :type         "uint256"}]
    :name      "TransferSingle"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      false
      :internalType "string"
      :name         "value"
      :type         "string"}
     {:indexed      true
      :internalType "uint256"
      :name         "id"
      :type         "uint256"}]
    :name      "URI"
    :type      "event"}
   {:inputs          []
    :name            "DEFAULT_ADMIN_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "MINTER_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "WEAPON_PASS_TOKEN_ID"
    :outputs         [{:internalType "uint8" :name "" :type "uint8"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "account" :type "address"}
     {:internalType "uint256" :name "id" :type "uint256"}]
    :name            "balanceOf"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address[]" :name "accounts" :type "address[]"}
     {:internalType "uint256[]" :name "ids" :type "uint256[]"}]
    :name            "balanceOfBatch"
    :outputs         [{:internalType "uint256[]" :name "" :type "uint256[]"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "account" :type "address"}
     {:internalType "uint256" :name "id" :type "uint256"}
     {:internalType "uint256" :name "value" :type "uint256"}]
    :name            "burn"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "account" :type "address"}
     {:internalType "uint256[]" :name "ids" :type "uint256[]"}
     {:internalType "uint256[]" :name "values" :type "uint256[]"}]
    :name            "burnBatch"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}]
    :name            "getRoleAdmin"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
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
   {:inputs          []
    :name            "initialize"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "account" :type "address"}
     {:internalType "address" :name "operator" :type "address"}]
    :name            "isApprovedForAll"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_recipient" :type "address"}
     {:internalType "uint256" :name "_amount" :type "uint256"}]
    :name            "mintWeaponPass"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "name"
    :outputs         [{:internalType "string" :name "" :type "string"}]
    :stateMutability "pure"
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
   {:inputs
    [{:internalType "address" :name "from" :type "address"}
     {:internalType "address" :name "to" :type "address"}
     {:internalType "uint256[]" :name "ids" :type "uint256[]"}
     {:internalType "uint256[]" :name "amounts" :type "uint256[]"}
     {:internalType "bytes" :name "data" :type "bytes"}]
    :name            "safeBatchTransferFrom"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "from" :type "address"}
     {:internalType "address" :name "to" :type "address"}
     {:internalType "uint256" :name "id" :type "uint256"}
     {:internalType "uint256" :name "amount" :type "uint256"}
     {:internalType "bytes" :name "data" :type "bytes"}]
    :name            "safeTransferFrom"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "operator" :type "address"}
     {:internalType "bool" :name "approved" :type "bool"}]
    :name            "setApprovalForAll"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "bytes4" :name "interfaceId" :type "bytes4"}]
    :name            "supportsInterface"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "symbol"
    :outputs         [{:internalType "string" :name "" :type "string"}]
    :stateMutability "pure"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "" :type "uint256"}]
    :name            "uri"
    :outputs         [{:internalType "string" :name "" :type "string"}]
    :stateMutability "view"
    :type            "function"}])