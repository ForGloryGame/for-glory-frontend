(ns fgl.contracts.gameminter.abi)

(defonce data
  [{:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "_recipient"
      :type         "address"}
     {:indexed      false
      :internalType "uint256"
      :name         "_amount"
      :type         "uint256"}
     {:indexed      false
      :internalType "bool"
      :name         "_stake"
      :type         "bool"}]
    :name      "MintCommit"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      false
      :internalType "address"
      :name         "_caller"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "_account"
      :type         "address"}]
    :name      "Reveal"
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
      :internalType "uint256"
      :name         "_tokenId"
      :type         "uint256"}
     {:indexed      false
      :internalType "address"
      :name         "_from"
      :type         "address"}
     {:indexed      false
      :internalType "address"
      :name         "_thief"
      :type         "address"}]
    :name      "Steal"
    :type      "event"}
   {:inputs          []
    :name            "DEFAULT_ADMIN_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "MAX_SUPPLY"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "assigned"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "battlefield"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "_amount" :type "uint256"}
     {:internalType "bool" :name "_stake" :type "bool"}]
    :name            "commitMint"
    :outputs         []
    :stateMutability "payable"
    :type            "function"}
   {:inputs          []
    :name            "devWallet"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}]
    :name            "getRoleAdmin"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "gloryNFT"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "gloryToken"
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
   {:inputs          [{:internalType "uint256" :name "" :type "uint256"}]
    :name            "height2hash"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_treasury" :type "address"}
     {:internalType "address" :name "_devWallet" :type "address"}
     {:internalType "address" :name "_gloryNFT" :type "address"}
     {:internalType "address" :name "_gloryToken" :type "address"}
     {:internalType "address" :name "_battlefield" :type "address"}]
    :name            "initialize"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "address" :name "" :type "address"}]
    :name            "mintCommits"
    :outputs
    [{:internalType "uint64" :name "height" :type "uint64"}
     {:internalType "uint16" :name "offset" :type "uint16"}
     {:internalType "uint16" :name "amount" :type "uint16"}
     {:internalType "uint152" :name "salt" :type "uint152"}
     {:internalType "bool" :name "stake" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "bytes32" :name "role" :type "bytes32"}
     {:internalType "address" :name "account" :type "address"}]
    :name            "renounceRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "_account" :type "address"}]
    :name            "revealMint"
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
    [{:internalType "uint256[]" :name "_heights" :type "uint256[]"}
     {:internalType "bytes32[]" :name "_hashes" :type "bytes32[]"}]
    :name            "setRevealHints"
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
    :name            "treasury"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}])
