(ns fgl.app.views.mint
  (:require
   ["ethers" :as ethers]
   [fgl.contracts.gamepass :as gamepass]
   [fgl.contracts.bfproxy :as bfproxy]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.glory :as glory]
   [fgl.contracts.sgold :as sgold]
   [fgl.contracts.kingdoms :as kingdoms]
   [fgl.contracts.battlefield :as battlefield]
   [fgl.contracts.gameminter :as minter]
   [fgl.config :as conf]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.landeed :as landeed]
   [fgl.wallet.core :as w]
   [re-frame.core :as rf]
   [lambdaisland.glogi :as log]))

(defn controllers []
  [{:start identity
    :stop identity}])

(defn init-data []
  (rf/dispatch [::nft/init])
  (rf/dispatch [::kingdoms/init])
  (rf/dispatch [::sgold/init])
  (rf/dispatch [::battlefield/init])
  (rf/dispatch [::landeed/init]))

(defn main [_]
  (init-data)
  (let [minter-role (ethers/utils.keccak256 (ethers/utils.toUtf8Bytes "MINTER_ROLE"))
        provider           @(rf/subscribe [::w/provider])
        addr           @(rf/subscribe [::w/addr])
        nft-ids           @(rf/subscribe [::nft/token-ids addr])
        kingdom-id           @(rf/subscribe [::kingdoms/kingdom-id addr])]
    [:div
     [:h1.text-4xl "Mint"]
     [:ul.list-disc.mt-10 "Admin"
      [:li>button.border {:on-click #(rf/dispatch [::gold/send {:method :grantRole :params [minter-role conf/contract-addr-minter]}])}
       "Grant Minter to mint Gold Token"]
      [:li>button.border {:on-click #(rf/dispatch [::glory/send {:method :grantRole :params [minter-role conf/contract-addr-minter]}])}
       "Grant Minter to mint Glory Token"]
      [:li>button.border {:on-click #(rf/dispatch [::gamepass/send {:method :grantRole :params [minter-role conf/contract-addr-minter]}])}
       "Grant Minter to mint Game Pass"]
      [:li>button.border {:on-click #(rf/dispatch [::glory/send {:method :grantRole :params [minter-role addr]}])}
       "Grant Admin to mint Glory Token"]
      ;; [:li>button.border
      ;;  {:on-click
      ;;   #(rf/dispatch
      ;;     [::glory/send
      ;;      :method :grantRole
      ;;      :params
      ;;      [(ethers/utils.keccak256
      ;;        (ethers/utils.toUtf8Bytes
      ;;         "WHITELIST_TRANSFER_ROLE"))
      ;;       "0x28B19b15AbD3B20C71C7c808403AB934f2fd9Cb1"
      ;;       ;; addr
      ;;       ]])}
      ;;  "Grant mint Glory Token transfer role"]
      [:li>button.border {:on-click #(rf/dispatch [::nft/send {:method :grantRole :params [minter-role conf/contract-addr-minter]}])}
       "Grant Minter to mint Glory NFT"]
      [:li>button.border {:on-click #(rf/dispatch [::sgold/send {:method :updateKindoms :params [conf/contract-addr-kingdoms]}])}
       "Set sgold kingdoms contract addr"]
      [:li>button.border
       {:on-click #(rf/dispatch
                    [::battlefield/send
                     {:method :grantRole :params
                      [(ethers/utils.keccak256
                        (ethers/utils.toUtf8Bytes
                         "GAME_PROXY_ROLE"))
                       conf/contract-addr-battlefield-proxy]}])}
       "Grant Battlefield Proxy GAME_PROXY_ROLE role"]
      [:li>button.border
       {:on-click #(rf/dispatch
                    [::glory/send
                     {:method :grantRole :params
                      [minter-role
                       conf/contract-addr-battlefield]}])}
       "Grant Battlefield To Mint Glory"]
      [:li>button.border
       {:on-click #(rf/dispatch
                    [::gold/send
                     {:method :grantRole :params
                      [minter-role
                       conf/contract-addr-battlefield]}])}
       "Grant Battlefield To Mint Gold"]
      [:li>button.border
       {:on-click #(rf/dispatch [::gold/send {:method :mint :params [addr (-> "10"
                                                                              ethers/BigNumber.from
                                                                              (.pow 18))]}])}
       "Mint Gold"]
      [:li>button.border
       {:on-click #(rf/dispatch [::glory/send {:method :mint :params [addr (-> "10"
                                                                               ethers/BigNumber.from
                                                                               (.pow 18))]}])}
       "Mint Glory"]]
     [:ul.list-disc.mt-10 "Gold Token"
      [:li>span (str "balance: " @(rf/subscribe [::gold/balance addr]))]]
     [:ul.list-disc.mt-10 "Glory Token"
      [:li>span (str "balance: " @(rf/subscribe [::glory/balance addr]))]]
     [:ul.list-disc.mt-10 "Game Pass Token"
      [:li>span (str "balance: " @(rf/subscribe [::gamepass/balance addr]))]]
     [:ul.list-disc.mt-10 "Minter"
      [:li>button.border {:on-click #(rf/dispatch [::minter/send {:method :commitMint :params [1 false]}])}
       "Mint Glory NFT"]
      [:li>button.border {:on-click #(rf/dispatch [::minter/send {:method :commitMint :params [1 true]}])}
       "Mint Glory NFT and Stake"]
      [:li>button.border {:on-click #(rf/dispatch [::minter/send {:method :revealMint :params [addr]}])} "revealMint"]]
     [:ul.list-disc.mt-10 "Glory NFT"
      [:li>span (str "glory nft balance: " @(rf/subscribe [::nft/balance addr]))]
      [:li>span (str "glory nft token ids: " nft-ids)]]
     [:ul.list-disc.mt-10 "Landeed NFT"
      [:li>span (str "Landeed nft balance: " @(rf/subscribe [::landeed/balance addr]))]
      [:li>span (str "Landeed nft token ids: " @(rf/subscribe [::landeed/token-ids addr]))]]
     [:ul.list-disc.mt-10 "Kingdom"
      [:li (str "Kingdoms: " @(rf/subscribe [::kingdoms/kingdoms]))]
      [:li (str "Which: " kingdom-id)]
      [:li>button.border {:on-click #(rf/dispatch [::kingdoms/send {:method :join :params [2]}])} "Join kingdom 2"]
      [:li>button.border {:on-click #(rf/dispatch [::kingdoms/send {:method :leave}])} "Leave current kingdom"]]
     [:ul.list-disc.mt-10 "sGold"
      [:li (str "User balance: " @(rf/subscribe [::sgold/balance]))]
      [:li (str "User locked: " @(rf/subscribe [::sgold/locked]))]
      [:li (str "User unlocked: " @(rf/subscribe [::sgold/unlocked]))]
      [:li (str "User UnlockInfo Info: " @(rf/subscribe [::sgold/info]))]
      [:li>button.border {:on-click #(rf/dispatch [::sgold/send {:method :lock :params [1 1]}])} "Lock 1 sgold for 1 week"]
      [:li>button.border {:on-click #(rf/dispatch [::sgold/send {:method :withdraw}])} "Withdraw all unlocked sgold"]]
     [:ul.list-disc.mt-10 "Battlefield"
      [:li (str "User staked NFT token ids: " @(rf/subscribe [::battlefield/token-ids addr]))]
      [:li
       (str
        "User pending reward gold: "
        @(rf/subscribe
          [::battlefield/reward addr
           (first
            @(rf/subscribe [::battlefield/token-ids addr]))
           :gold]))]
      [:li
       (str
        "User pending reward glory: "
        @(rf/subscribe
          [::battlefield/reward addr
           (first
            @(rf/subscribe [::battlefield/token-ids addr]))
           :glory]))]]
     [:ul.list-disc.mt-10 "Battlefield Proxy"
      [:li>button.border
       {:on-click #(rf/dispatch [::nft/send {:method :approve :params [conf/contract-addr-battlefield (first nft-ids)]}])}
       "Approve Battlefield spend glory nft"]
      [:li>button.border {:on-click #(rf/dispatch [::bfproxy/send {:method :join :params [#js [(first nft-ids)]]}])}
       "Stake"]
      [:li>button.border {:on-click #(rf/dispatch [::bfproxy/send {:method :claim}])}
       "Claim"]
      [:li>button.border {:on-click #(rf/dispatch [::bfproxy/send {:method :commitUnstake}])}
       "Unstake"]]]))
