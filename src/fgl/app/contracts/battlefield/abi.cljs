(ns fgl.app.contracts.battlefield.abi)

(defonce data
  [{:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "uint256",
      :name         "_commitId",
      :type         "uint256"}
     {:indexed      true,
      :internalType "address",
      :name         "_caller",
      :type         "address"}
     {:indexed      false,
      :internalType "uint16[]",
      :name         "_tokenIds",
      :type         "uint16[]"}
     {:indexed      false,
      :internalType "bool",
      :name         "_unstake",
      :type         "bool"}],
    :name      "ClaimCommit",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "address",
      :name         "_caller",
      :type         "address"}],
    :name      "CleaningCommit",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      false,
      :internalType "address",
      :name         "account",
      :type         "address"}],
    :name      "Paused",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "uint256",
      :name         "_commitId",
      :type         "uint256"}
     {:indexed      true,
      :internalType "address",
      :name         "_caller",
      :type         "address"}],
    :name      "RevealClaimCommit",
    :type      "event"}
   {:anonymous false,
    :inputs
    [{:indexed      true,
      :internalType "address",
      :name         "_caller",
      :type         "address"}],
    :name      "RevealCleaningCommit",
    :type      "event"}
   {:anonymous false,
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
      :name         "account",
      :type         "address"}],
    :name      "Unpaused",
    :type      "event"}
   {:inputs          [],
    :name            "DEFAULT_ADMIN_ROLE",
    :outputs         [{:internalType "bytes32", :name "", :type "bytes32"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "battlefield",
    :outputs         [{:internalType "address", :name "", :type "address"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [{:internalType "uint256", :name "", :type "uint256"}],
    :name            "claimCommits",
    :outputs
    [{:internalType "address", :name "owner", :type "address"}
     {:internalType "uint64", :name "height", :type "uint64"}
     {:internalType "bool", :name "unstake", :type "bool"}
     {:internalType "uint256", :name "tokens", :type "uint256"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [{:internalType "address", :name "", :type "address"}],
    :name            "cleaningCommits",
    :outputs
    [{:internalType "uint64", :name "height", :type "uint64"}
     {:internalType "uint16", :name "amount", :type "uint16"}
     {:internalType "uint176", :name "salt", :type "uint176"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "uint16[]", :name "_tokenIds", :type "uint16[]"}
     {:internalType "bool", :name "_unstake", :type "bool"}],
    :name            "commitClaim",
    :outputs         [{:internalType "uint256", :name "", :type "uint256"}],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "uint256", :name "_times", :type "uint256"}
     {:internalType "bool", :name "_force", :type "bool"}],
    :name            "commitCleaning",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [],
    :name            "devWallet",
    :outputs         [{:internalType "address", :name "", :type "address"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "enableCleaning",
    :outputs         [{:internalType "bool", :name "", :type "bool"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [{:internalType "bytes32", :name "role", :type "bytes32"}],
    :name            "getRoleAdmin",
    :outputs         [{:internalType "bytes32", :name "", :type "bytes32"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "goldPerCleaning",
    :outputs         [{:internalType "uint256", :name "", :type "uint256"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "goldToken",
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
   {:inputs          [{:internalType "uint256", :name "", :type "uint256"}],
    :name            "height2hash",
    :outputs         [{:internalType "bytes32", :name "", :type "bytes32"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs
    [{:internalType "address", :name "_treasury", :type "address"}
     {:internalType "address", :name "_devWallet", :type "address"}
     {:internalType "address", :name "_goldToken", :type "address"}
     {:internalType "address", :name "_battlefield", :type "address"}],
    :name            "initialize",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "uint256[]", :name "_tokenIds", :type "uint256[]"}],
    :name            "join",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [],
    :name            "paused",
    :outputs         [{:internalType "bool", :name "", :type "bool"}],
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
    [{:internalType "uint256", :name "_commitId", :type "uint256"}],
    :name            "revealClaim",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [],
    :name            "revealCleaning",
    :outputs         [],
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
    [{:internalType "bool", :name "_enableCleaning", :type "bool"}],
    :name            "setEnableCleaning",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs          [{:internalType "bool", :name "pause", :type "bool"}],
    :name            "setPaused",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "uint256[]", :name "_heights", :type "uint256[]"}
     {:internalType "bytes32[]", :name "_hashes", :type "bytes32[]"}],
    :name            "setRevealHints",
    :outputs         [],
    :stateMutability "nonpayable",
    :type            "function"}
   {:inputs
    [{:internalType "bytes4", :name "interfaceId", :type "bytes4"}],
    :name            "supportsInterface",
    :outputs         [{:internalType "bool", :name "", :type "bool"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [{:internalType "uint256", :name "", :type "uint256"}],
    :name            "tokenClaimCommits",
    :outputs
    [{:internalType "uint64", :name "commitId", :type "uint64"}
     {:internalType "uint64", :name "height", :type "uint64"}
     {:internalType "uint128", :name "salt", :type "uint128"}],
    :stateMutability "view",
    :type            "function"}
   {:inputs          [],
    :name            "treasury",
    :outputs         [{:internalType "address", :name "", :type "address"}],
    :stateMutability "view",
    :type            "function"}])
