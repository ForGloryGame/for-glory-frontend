(ns fgl.contracts.battlefield.abi)

(defonce data
  [{:anonymous false
    :inputs    [{:indexed      true
                 :internalType "address"
                 :name         "owner"
                 :type         "address"}
                {:indexed      true
                 :internalType "uint256"
                 :name         "tokenId"
                 :type         "uint256"}
                {:indexed      false
                 :internalType "bool"
                 :name         "unstake"
                 :type         "bool"}]
    :name      "KnightClaimed"
    :type      "event"}
   {:anonymous false
    :inputs    [{:indexed      true
                 :internalType "address"
                 :name         "owner"
                 :type         "address"}
                {:indexed      true
                 :internalType "uint256"
                 :name         "tokenId"
                 :type         "uint256"}
                {:indexed      false
                 :internalType "bool"
                 :name         "unstake"
                 :type         "bool"}]
    :name      "LordClaimed"
    :type      "event"}
   {:anonymous false
    :inputs    [{:indexed      true
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
    :inputs    [{:indexed      true
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
    :inputs    [{:indexed      true
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
    :inputs    [{:indexed      true
                 :internalType "address"
                 :name         "owner"
                 :type         "address"}
                {:indexed      true
                 :internalType "uint256"
                 :name         "tokenId"
                 :type         "uint256"}
                {:indexed      false
                 :internalType "bool"
                 :name         "isLord"
                 :type         "bool"}]
    :name      "TokenStaked"
    :type      "event"}
   {:anonymous false
    :inputs    [{:indexed      true
                 :internalType "uint256"
                 :name         "_peerage"
                 :type         "uint256"}
                {:indexed      false
                 :internalType "uint256"
                 :name         "_start"
                 :type         "uint256"}
                {:indexed      false
                 :internalType "uint256"
                 :name         "_dailyRate"
                 :type         "uint256"}]
    :name      "UpdateGloryRewardRate"
    :type      "event"}
   {:anonymous false
    :inputs    [{:indexed      false
                 :internalType "uint256"
                 :name         "_start"
                 :type         "uint256"}
                {:indexed      false
                 :internalType "uint256"
                 :name         "_dailyRate"
                 :type         "uint256"}]
    :name      "UpdateGoldRewardRate"
    :type      "event"}
   {:anonymous false
    :inputs    [{:indexed      false
                 :internalType "uint256"
                 :name         "_tax"
                 :type         "uint256"}]
    :name      "UpdateKnightClaimTax"
    :type      "event"}
   {:anonymous false
    :inputs    [{:indexed      false
                 :internalType "uint256"
                 :name         "_prob"
                 :type         "uint256"}]
    :name      "UpdateKnightUnstakeTakeAllProb"
    :type      "event"}
   {:inputs          []
    :name            "DEFAULT_ADMIN_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "GAME_PROXY_ROLE"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "accGoldPerRank"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "_seed" :type "uint256"}
                      {:internalType "address" :name "_owner" :type "address"}
                      {:internalType "uint256[]"
                       :name         "_tokenIds"
                       :type         "uint256[]"}
                      {:internalType "bool" :name "_unstake" :type "bool"}]
    :name            "claim"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "address"
                       :name         "_account"
                       :type         "address"}
                      {:internalType "uint256" :name "_seed" :type "uint256"}]
    :name            "clean"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "address"
                       :name         "_account"
                       :type         "address"}]
    :name            "depositsOf"
    :outputs         [{:internalType "uint256[]" :name "" :type "uint256[]"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}]
    :name            "getRoleAdmin"
    :outputs         [{:internalType "bytes32" :name "" :type "bytes32"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "gloryGamePass"
    :outputs         [{:internalType "address" :name "" :type "address"}]
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
   {:inputs          []
    :name            "goldToken"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}
                      {:internalType "address" :name "account" :type "address"}]
    :name            "grantRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}
                      {:internalType "address" :name "account" :type "address"}]
    :name            "hasRole"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "address"
                       :name         "_goldToken"
                       :type         "address"}
                      {:internalType "address" :name "_sGold" :type "address"}
                      {:internalType "address"
                       :name         "_gloryToken"
                       :type         "address"}
                      {:internalType "address"
                       :name         "_gloryNFT"
                       :type         "address"}
                      {:internalType "address"
                       :name         "_gloryGamePass"
                       :type         "address"}]
    :name            "initialize"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "address"
                       :name         "_account"
                       :type         "address"}
                      {:internalType "uint256[]"
                       :name         "_tokenIds"
                       :type         "uint256[]"}]
    :name            "join"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "knightClaimTax"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "knightStakedCount"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "knightUnstakeTakeAllProb"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "lordRankStakedCount"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256"
                       :name         "_tokenId"
                       :type         "uint256"}]
    :name            "pendingReward"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}
                      {:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "_seed" :type "uint256"}]
    :name            "randomSelectLord"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}
                      {:internalType "address" :name "account" :type "address"}]
    :name            "renounceRole"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "bytes32" :name "role" :type "bytes32"}
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
   {:inputs          [{:internalType "bytes4"
                       :name         "interfaceId"
                       :type         "bytes4"}]
    :name            "supportsInterface"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256"
                       :name         "_tokenId"
                       :type         "uint256"}]
    :name            "tokenOwner"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "undistributedGoldReward"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256"
                       :name         "_peerage"
                       :type         "uint256"}
                      {:internalType "uint32" :name "_start" :type "uint32"}
                      {:internalType "uint112"
                       :name         "_dailyRate"
                       :type         "uint112"}]
    :name            "updateGloryRewardRate"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "uint32" :name "_start" :type "uint32"}
                      {:internalType "uint112"
                       :name         "_dailyRate"
                       :type         "uint112"}]
    :name            "updateGoldRewardRate"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "_tax" :type "uint256"}]
    :name            "updateKnightClaimTax"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "_prob" :type "uint256"}]
    :name            "updateKnightUnstakeTakeAllProb"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}])
