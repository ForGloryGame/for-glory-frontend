(ns fgl.app.views.mint
  (:require
   ["ethers" :as ethers]
   [re-frame.db :as rdb]
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
   [fgl.app.ui.balance :as balance]
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

(rf/reg-sub
 ::data
 (fn [db]
   (let [{::w/keys [addr]} db
         addr-db           (get db addr)

         {nft-balance   ::nft/balance
          nft-token-ids ::nft/token-ids

          landeed-balance   ::landeed/balance
          landeed-token-ids ::landeed/token-ids

          kingdoms   ::kingdoms/kingoms
          kingdom-id ::kingdoms/kingom-id

          gold-balance ::gold/balance

          glory-balance ::glory/balance

          gamepass-balance ::gamepass/balance

          sgold-balance  ::sgold/balance
          sgold-locked   ::sgold/locked
          sgold-unlocked ::sgold/unlocked
          sgold-info     ::sgold/info

          battlefield-token-ids ::battlefield/token-ids}
         addr-db

         sgold-info (sgold/->unlock-info sgold-info)]
     {:addr addr

      :nft-balance   nft-balance
      :nft-token-ids nft-token-ids

      :landeed-balance   landeed-balance
      :landeed-token-ids landeed-token-ids

      :kingdoms   kingdoms
      :kingdom-id kingdom-id

      :gold-balance gold-balance

      :glory-balance glory-balance

      :gamepass-balance gamepass-balance

      :sgold-balance  sgold-balance
      :sgold-locked   sgold-locked
      :sgold-unlocked sgold-unlocked
      :sgold-info     sgold-info

      :battlefield-token-ids battlefield-token-ids})))

(defn main [_]
  (init-data)
  (let [minter-role (ethers/utils.keccak256 (ethers/utils.toUtf8Bytes "MINTER_ROLE"))
        d           (rf/subscribe [::data])
        {:keys [nft-balance
                nft-token-ids

                landeed-balance
                landeed-token-ids

                kingdoms
                kingdom-id

                gold-balance

                glory-balance

                gamepass-balance

                sgold-balance
                sgold-locked
                sgold-unlocked
                sgold-info

                battlefield-token-ids]}
        @d

        addr
        @(rf/subscribe [::w/addr])]
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
      [:li>span "balance: " [balance/ui gold-balance]]]
     [:ul.list-disc.mt-10 "Glory Token"
      [:li>span "balance: " [balance/ui glory-balance]]]
     [:ul.list-disc.mt-10 "Game Pass Token"
      [:li>span "balance: " [balance/ui gamepass-balance]]]
     [:ul.list-disc.mt-10 "Minter"
      [:li>button.border {:on-click #(rf/dispatch [::minter/send {:method :commitMint :params [1 false]}])}
       "Mint Glory NFT"]
      [:li>button.border {:on-click #(rf/dispatch [::minter/send {:method :commitMint :params [1 true]}])}
       "Mint Glory NFT and Stake"]
      [:li>button.border {:on-click #(rf/dispatch [::minter/send {:method :revealMint :params [addr]}])} "revealMint"]]
     [:ul.list-disc.mt-10 "Glory NFT"
      [:li>span "glory nft balance: " [balance/ui nft-balance]]
      [:li>span (str "glory nft token ids: " nft-token-ids)]]
     [:ul.list-disc.mt-10 "Landeed NFT"
      [:li>span "Landeed nft balance: " [balance/ui landeed-balance]]
      [:li>span (str "Landeed nft token ids: " landeed-token-ids)]]
     [:ul.list-disc.mt-10 "Kingdom"
      [:li (str "Kingdoms: " kingdoms)]
      [:li (str "Which: " kingdom-id)]
      [:li>button.border {:on-click #(rf/dispatch [::kingdoms/send {:method :join :params [2]}])} "Join kingdom 2"]
      [:li>button.border {:on-click #(rf/dispatch [::kingdoms/send {:method :leave}])} "Leave current kingdom"]]
     [:ul.list-disc.mt-10 "sGold"
      [:li "User balance: " [balance/ui sgold-balance]]
      [:li "User locked: " [balance/ui sgold-locked]]
      [:li "User unlocked: " [balance/ui sgold-unlocked]]
      [into [:li "User UnlockInfo Info: "]
       (map (fn [{:keys [amount date]}]
              [:ul
               [:li "Amount: " [balance/ui amount]]
               [:li (str "Date: " date)]])
            sgold-info)]
      [:li>button.border {:on-click #(rf/dispatch [::sgold/send {:method :lock :params [1 1]}])} "Lock 1 sgold for 1 week"]
      [:li>button.border {:on-click #(rf/dispatch [::sgold/send {:method :withdraw}])} "Withdraw all unlocked sgold"]]
     [:ul.list-disc.mt-10 "Battlefield"
      [:li (str "User staked NFT token ids: " battlefield-token-ids)]
      [:li
       (str
        "User pending reward gold: "
        @(rf/subscribe
          [::battlefield/reward addr
           (first
            battlefield-token-ids)
           :gold]))]
      [:li
       (str
        "User pending reward glory: "
        @(rf/subscribe
          [::battlefield/reward addr
           (first
            battlefield-token-ids)
           :glory]))]]
     [:ul.list-disc.mt-10 "Battlefield Proxy"
      [:li>button.border
       {:on-click #(rf/dispatch [::nft/send {:method :approve :params [conf/contract-addr-battlefield (first nft-token-ids)]}])}
       "Approve Battlefield spend glory nft"]
      [:li>button.border {:on-click #(rf/dispatch [::bfproxy/send {:method :join :params [#js [(first nft-token-ids)]]}])}
       "Stake"]
      [:li>button.border {:on-click #(rf/dispatch [::bfproxy/send {:method :claim}])}
       "Claim"]
      [:li>button.border {:on-click #(rf/dispatch [::bfproxy/send {:method :commitUnstake}])}
       "Unstake"]]]))
