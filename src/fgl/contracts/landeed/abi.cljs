(ns fgl.contracts.landeed.abi)

(defonce data
  [{:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "owner"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "approved"
      :type         "address"}
     {:indexed      true
      :internalType "uint256"
      :name         "tokenId"
      :type         "uint256"}]
    :name      "Approval"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "owner"
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
      :internalType "uint256"
      :name         "_tokenId"
      :type         "uint256"}
     {:indexed      false
      :internalType "enum LandDeed.DeedType"
      :name         "_type"
      :type         "uint8"}
     {:indexed      false
      :internalType "uint256"
      :name         "_amountLP"
      :type         "uint256"}]
    :name      "Purchase"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "uint256"
      :name         "_tokenId"
      :type         "uint256"}
     {:indexed      false
      :internalType "uint256"
      :name         "_amountLP"
      :type         "uint256"}]
    :name      "Redeem"
    :type      "event"}
   {:anonymous false
    :inputs
    [{:indexed      true
      :internalType "address"
      :name         "from"
      :type         "address"}
     {:indexed      true
      :internalType "address"
      :name         "to"
      :type         "address"}
     {:indexed      true
      :internalType "uint256"
      :name         "tokenId"
      :type         "uint256"}]
    :name      "Transfer"
    :type      "event"}
   {:inputs
    [{:internalType "address" :name "to" :type "address"}
     {:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "approve"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          [{:internalType "address" :name "owner" :type "address"}]
    :name            "balanceOf"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum LandDeed.DeedType" :name "" :type "uint8"}]
    :name            "deedsInfo"
    :outputs
    [{:internalType "uint128" :name "price" :type "uint128"}
     {:internalType "uint64" :name "cap" :type "uint64"}
     {:internalType "uint64" :name "minted" :type "uint64"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "getDeedType"
    :outputs         [{:internalType "enum LandDeed.DeedType" :name "" :type "uint8"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "getApproved"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "gold"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "owner" :type "address"}
     {:internalType "address" :name "operator" :type "address"}]
    :name            "isApprovedForAll"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "" :type "uint256"}]
    :name            "locked"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "maxGoldAmount"
    :outputs         [{:internalType "uint128" :name "" :type "uint128"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "minGoldAmount"
    :outputs         [{:internalType "uint128" :name "" :type "uint128"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "name"
    :outputs         [{:internalType "string" :name "" :type "string"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "ownerOf"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "pair"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "paused"
    :outputs         [{:internalType "bool" :name "" :type "bool"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "enum LandDeed.DeedType"
      :name         "_type"
      :type         "uint8"}
     {:internalType "uint256" :name "_minLPOut" :type "uint256"}]
    :name            "purchase"
    :outputs         []
    :stateMutability "payable"
    :type            "function"}
   {:inputs
    [{:internalType "uint256[]" :name "_tokenIds" :type "uint256[]"}
     {:internalType "uint256" :name "_minETHOut" :type "uint256"}
     {:internalType "uint256" :name "_minGoldOut" :type "uint256"}]
    :name            "redeem"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "redeemPercentage"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "from" :type "address"}
     {:internalType "address" :name "to" :type "address"}
     {:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "safeTransferFrom"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "from" :type "address"}
     {:internalType "address" :name "to" :type "address"}
     {:internalType "uint256" :name "tokenId" :type "uint256"}
     {:internalType "bytes" :name "_data" :type "bytes"}]
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
   {:inputs          [{:internalType "string" :name "baseURI" :type "string"}]
    :name            "setBaseURI"
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
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "uint256" :name "_index" :type "uint256"}]
    :name            "tokenByIndex"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "owner" :type "address"}
     {:internalType "uint256" :name "index" :type "uint256"}]
    :name            "tokenOfOwnerByIndex"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "tokenURI"
    :outputs         [{:internalType "string" :name "" :type "string"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          [{:internalType "address" :name "owner" :type "address"}]
    :name            "tokensOfOwner"
    :outputs         [{:internalType "uint256[]" :name "" :type "uint256[]"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs          []
    :name            "totalSupply"
    :outputs         [{:internalType "uint256" :name "" :type "uint256"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "address" :name "from" :type "address"}
     {:internalType "address" :name "to" :type "address"}
     {:internalType "uint256" :name "tokenId" :type "uint256"}]
    :name            "transferFrom"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "treasury"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}
   {:inputs
    [{:internalType "uint128" :name "_minGoldAmount" :type "uint128"}
     {:internalType "uint128" :name "_maxGoldAmount" :type "uint128"}]
    :name            "updateGoldAmountBound"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs
    [{:internalType "uint256" :name "_percentage" :type "uint256"}]
    :name            "updateRedeemPercentage"
    :outputs         []
    :stateMutability "nonpayable"
    :type            "function"}
   {:inputs          []
    :name            "weth"
    :outputs         [{:internalType "address" :name "" :type "address"}]
    :stateMutability "view"
    :type            "function"}])
