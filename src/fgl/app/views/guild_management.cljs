(ns fgl.app.views.guild-management
  (:require
   ["@radix-ui/react-select" :as S]
   ["ethers" :as ethers]
   [clojure.string :as s]
   [fgl.app.backend :as backend]
   [fgl.app.snapshot :as snapshot]
   [fgl.app.ui.balance :as balance]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.glory-img :as gloryimg]
   [fgl.app.ui.loading-dot :as ld]
   [fgl.app.ui.separator :as separator]
   [fgl.app.ui.sgold-img :as sgoldimg]
   [fgl.contracts.gamenft :as nft]
   [fgl.contracts.gold :as gold]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.contracts.sgold :as sgold]
   [fgl.re-frame]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oget ocall]]
   [promesa.core :as p]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [taoensso.encore :as enc]))

(defn controllers []
  [{:start #(rf/dispatch [::kingdom/init])
    :stop  identity}])

(rf/reg-event-db
 ::set-type
 (fn [db [_ type]]
   (assoc db ::type type)))

(rf/reg-event-db
 ::title
 (fn [db [_ title]]
   (assoc db ::title (or title ""))))

(rf/reg-event-db
 ::desc
 (fn [db [_ desc]]
   (assoc db ::desc (or desc ""))))

(rf/reg-event-fx
 ::proposal
 (fn [{:keys [db]} _]
   (p/let [{::w/keys [addr provider]
            ::keys   [;; title
                      desc type]}
           db
           kingdom-id   (get-in db [addr ::kingdom/kingdom-id])
           kingdom-name (get kingdom/kingdoms-name-2 kingdom-id)
           message      {:account     addr
                         :kind        (or type "AvatarChange")
                         :kingdom     kingdom-name
                         :snapshot    snapshot/id
                         :description desc}
           ;; network (ocall provider "getNetwork")
           ;; chainId (oget network "chainId")
           block_number (w/request "eth_blockNumber")
           _ (dialog/submitting)
           ;; _ (enc/log (clj->js backend/proposal-domain) (clj->js backend/proposal-types) (clj->js message))
           signature (-> provider
                         (ocall "getSigner")
                         (ocall "_signTypedData"
                                (clj->js backend/proposal-domain)
                                ;; (clj->js (assoc backend/proposal-domain
                                ;;                 :chainId chainId))
                                (clj->js backend/proposal-types)
                                (clj->js message))
                         (p/catch #(dialog/failed {:title "Typed Sign Failed" :desc "User rejected typed sign in wallet"})))
           _ (rf/dispatch [::dialog/set
                           :open true
                           :desc [:<>
                                  [:span "Message signed"]
                                  [:br]
                                  [ld/text "Submitting proposal"]]])
           message (assoc message
                          :blockNumber (-> block_number
                                           (js/parseInt 16))
                          :signature signature)]
     (rf/dispatch [::backend/new-proposal message
                   (fn [res] (if (instance? js/Error res)
                               (rf/dispatch [::dialog/set
                                             :open true
                                             :close true
                                             :desc
                                             [:<>
                                              [:span "Message signed"]
                                              [:br]
                                              [:span (oget res "message")]
                                              [:span "Failed to create proposal, please try again"]]])
                               (rf/dispatch [::dialog/set
                                             :open true
                                             :close true
                                             :desc
                                             [:<>
                                              [:span "Message signed"]
                                              [:br]
                                              [:span "Proposal created"]]])))]))
   {}))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::keys [type title desc]} db]
     {:type (or type :AvatarChange)
      :title (or title "")
      :desc (or desc "")})))

(defn select []
  (let [set-type #(rf/dispatch [::set-type (keyword %)])]
    (fn []
      (let [{:keys [type]} @(rf/subscribe [::data])]
        [:> S/Root
         {:name          "Proposal Type"
          :defaultValue  "AvatarChange"
          :value         (name type)
          ;; :open true
          :onValueChange set-type}
         [:> S/Trigger
          {:className "text-xl text-center pl-14 pr-10 py-0.5 rounded fb"
           :style     {:backgroundColor "#355661"}}
          [:> S/Value type]
          [:> S/Icon [:img.inline-block.ml-6
                      {:style {:width "0.825rem"}
                       :src   "/images/select-down-arrow.svg"}]]]
         [:> S/Content
          {:className "text-xl text-center rounded fb pl-14 pr-10"
           :style     {:backgroundColor "#355661"}}
          [:> S/Viewport
           ^{:key 'AvatarChange}
           [:> S/Item
            {:value :AvatarChange}
            [:> S/ItemText "AvatarChange"]
            [:> S/ItemIndicator (or (nil? type) (= type :AvatarChange))]
            [:img.inline-block.ml-6
             {:style {:width      "0.825rem"
                      :visibility (if (or (nil? type) (= type :AvatarChange))
                                    "visible"
                                    "hidden")}
              :src   "/images/select-down-arrow.svg"}]]
           ^{:key 'NameChange}
           [:> S/Item
            {:value :NameChange}
            [:> S/ItemText "NameChange"]
            [:> S/ItemIndicator (or (nil? type) (= type :NameChange))]
            [:img.inline-block.ml-6
             {:style {:width      "0.825rem"
                      :visibility (if (or (nil? type) (= type :NameChange))
                                    "visible"
                                    "hidden")}
              :src   "/images/select-down-arrow.svg"}]]
           ^{:key 'Revocation}
           [:> S/Item
            {:value :Revocation}
            [:> S/ItemText "Revocation"]
            [:> S/ItemIndicator (or (nil? type) (= type :Revocation))]
            [:img.inline-block.ml-6
             {:style {:width      "0.825rem"
                      :visibility (if (or (nil? type) (= type :Revocation))
                                    "visible"
                                    "hidden")}
              :src   "/images/select-down-arrow.svg"}]]]]]))))

(defn main []
  (let [{:keys [title desc]} @(rf/subscribe [::data])]
    [:div.py-8.px-6
     [select]
     [:div.p-4
      [:p.pl-4.mb-2.ffd "TITLE"]
      [:input.tracking-wide.uppercase.bg-C81c6dd1a.guild-font-family.text-4xl.py-2.px-4.w-full
       {:value title
        :onChange
        #(rf/dispatch
          [::title (oget % "target.value")])
        :type  "text"}]
      [:p.pl-4.mt-4.mb-2.flex.justify-between.ffd
       [:span {:ffd "ffd"} "DESCRIPTIONS"]
       [:span.text-Cffffff80.tracking-wide "30/1,500"]]
      [:div.w-full.bg-C81c6dd1a.text-Cd6d6d6.p-1_5
       [:textarea.tracking-wide.bg-transparent.py-2.px-2_5.text-lg.w-full.h-44.ffd
        {:value desc
         :onChange
         #(rf/dispatch
           [::desc (oget % "target.value")])}]
       [:p.w-full.text-right.ffd.p-2.border-t-1px.border-solid.border-C96a1ae
        "Add files by dragging and dropping selections and pasting"]]
      [:div.flexrr.justify-end
       [btn/ui
        {:t         :olg
         :disabled  (or (= title "") (= desc ""))
         :className "mt-12"
         :on-click #(rf/dispatch [::proposal])}
        "Confirm"]]]]))
