(ns fgl.app.contracts.sgold.abi)

(defonce data
  [{:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "bytes32",
      :name         "role",
      :type         "bytes32"}
     {:indexed      true,
      :internalType "bytes32",
      :name         "previousAdminRole",
      :type         "bytes32"}
     {:indexed      true,
      :internalType "bytes32",
      :name         "newAdminRole",
      :type         "bytes32"}],
    :name      "RoleAdminChanged",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "bytes32",
      :name         "role",
      :type         "bytes32"}
     {:indexed      true,
      :internalType "address",
      :name         "account",
      :type         "address"}
     {:indexed      true,
      :internalType "address",
      :name         "sender",
      :type         "address"}],
    :name      "RoleGranted",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "bytes32",
      :name         "role",
      :type         "bytes32"}
     {:indexed      true,
      :internalType "address",
      :name         "account",
      :type         "address"}
     {:indexed      true,
      :internalType "address",
      :name         "sender",
      :type         "address"}],
    :name      "RoleRevoked",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      false,
      :internalType "address",
      :name         "_caller",
      :type         "address"}
     {:indexed      true,
      :internalType "address",
      :name         "_recipient",
      :type         "address"}
     {:indexed      false,
      :internalType "uint256",
      :name         "_amount",
      :type         "uint256"}
     {:indexed      false,
      :internalType "uint256",
      :name         "_weeks",
      :type         "uint256"}],
    :name      "Sacrifice",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "address",
      :name         "caller",
      :type         "address"}
     {:indexed      false,
      :internalType "uint256",
      :name         "_amount",
      :type         "uint256"}],
    :name      "Withdraw",
    :type      "event"}
   {:inputs          [],
    :name            "DEFAULT_ADMIN_ROLE",
    :outputs         [{:internalType "bytes32", :name "", :type "bytes32"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "address", :name "_account", :type "address"}],
    :name            "balanceOf",
    :outputs         [{:internalType "uint256", :name "", :type "uint256"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "decimals",
    :outputs         [{:internalType "uint8", :name "", :type "uint8"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [{:internalType "bytes32", :name "role", :type "bytes32"}],
    :name            "getRoleAdmin",
    :outputs         [{:internalType "bytes32", :name "", :type "bytes32"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "address", :name "_account", :type "address"}],
    :name            "getUserGoldInfo",
    :outputs
    [{:internalType "uint256", :name "_locked", :type "uint256"}
     {:internalType "uint256", :name "_unlocked", :type "uint256"}
     {:components
      [{:internalType "uint256", :name "gold", :type "uint256"}
       {:internalType "uint256", :name "week", :type "uint256"}],
      :internalType "struct SacrificedGold.UnlockInfo[]",
      :name         "_locks",
      :type         "tuple[]"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "gold",
    :outputs         [{:internalType "address", :name "", :type "address"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "bytes32", :name "role", :type "bytes32"}
     {:internalType "address", :name "account", :type "address"}],
    :name            "grantRole",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "bytes32", :name "role", :type "bytes32"}
     {:internalType "address", :name "account", :type "address"}],
    :name            "hasRole",
    :outputs         [{:internalType "bool", :name "", :type "bool"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [{:internalType "address", :name "_gold", :type "address"}],
    :name            "initialize",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [],
    :name            "kingdoms",
    :outputs         [{:internalType "address", :name "", :type "address"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "uint256", :name "_amount", :type "uint256"}
     {:internalType "uint256", :name "_weeks", :type "uint256"}],
    :name            "lock",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "address", :name "_recipient", :type "address"}
     {:internalType "uint256", :name "_amount", :type "uint256"}
     {:internalType "uint256", :name "_weeks", :type "uint256"}],
    :name            "lockFor",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [],
    :name            "name",
    :outputs         [{:internalType "string", :name "", :type "string"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "penatyFeePercentage",
    :outputs         [{:internalType "uint256", :name "", :type "uint256"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "bytes32", :name "role", :type "bytes32"}
     {:internalType "address", :name "account", :type "address"}],
    :name            "renounceRole",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "address", :name "_account", :type "address"}],
    :name            "requestLeavePenaty",
    :outputs         [{:internalType "uint256", :name "", :type "uint256"}],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "bytes32", :name "role", :type "bytes32"}
     {:internalType "address", :name "account", :type "address"}],
    :name            "revokeRole",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "bytes4", :name "interfaceId", :type "bytes4"}],
    :name            "supportsInterface",
    :outputs         [{:internalType "bool", :name "", :type "bool"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "symbol",
    :outputs         [{:internalType "string", :name "", :type "string"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "address", :name "_kingdoms", :type "address"}],
    :name            "updateKindoms",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "uint256", :name "_percentage", :type "uint256"}],
    :name            "updatePenatyFeePercentage",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [],
    :name            "withdraw",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}])
