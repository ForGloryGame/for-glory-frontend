(ns fgl.app.views.guild-vote
  (:require
   ["ethers" :as ethers]
   [fgl.app.backend :as backend]
   [fgl.app.snapshot :as snapshot]
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.dialog :as dialog]
   [fgl.config :as conf]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.re-frame]
   [fgl.utils :refer [shorten-addr scan-addr-url format-date]]
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

(rf/reg-sub
 ::data
 (fn [db [_ proposal-id]]
   (let [proposal (get-in db [::snapshot/proposal proposal-id])
         votes    (get-in db [::snapshot/votes proposal-id])]
     {:proposal proposal
      :votes    votes})))

(rf/reg-sub
 ::proposals
 (fn [db [_ kingdom-name]]
   (let [proposals (get-in db [::backend/kingdom-proposals kingdom-name])]
     {:proposals proposals})))

(defn item [id]
  (r/create-class
   {:component-did-mount
    (fn []
      (when id
        (rf/dispatch [::snapshot/proposal id])
        (rf/dispatch [::snapshot/votes id])))
    :reagent-render
    (fn []
      (let [{:keys [proposal votes]}
            @(rf/subscribe [::data id])

            {:keys [title author body state votes]}
            proposal]
        ^{:key id}
        [:div.bg-C81c6dd1a.rounded-md.p-4.fb.mx-8.my-4
         [:div.flexrr.justify-between
          [:div.flexrr.items-center
           [:span.text-4xl.mr-4.uppercase title]
           [:div.bg-Ced8e28.px-12.rounded-md.text-base.capitalize state]]
          [btn/ui
           {:t         :blg
            :className "justify-self-end"
            :on-click  #(rf/dispatch [:navigate :route/guild-vote-proposal {:proposal-id id}])}
           "Check detail"]]
         [:div.flexrr.items-center.mt-4.text-Cd6d6d6.cursor-pointer
          {:on-click #(js/open (scan-addr-url author))}
          [:span.block.mr-2 (str "by " (shorten-addr author))]
          [:a [:img.w-3 {:src "/images/share.png"}]]]
         [:div.bg-C81c6dd1a.p-4.text-Cd6d6d6.rounded-md.mt-4
          [:h3.text-xl "DESCRIPTIONS"]
          [:p.ffd.text-base body]]
         [:div.flexrr.justify-end.mt-2 [:img.w-2.mr-2 {:src "/images/votes-no.png"}] "NO" [:span.text-Cd6d6d6.mx-1 votes] [:span.text-Cd6d6d6 "VOTES"]]]))}))

(defn proposals []
  (r/create-class
   {:reagent-render
    (fn []
      (let [kingdom-id          @(rf/subscribe [::kingdom/kingdom-id])
            kingdom-name        (get kingdom/kingdoms-name-2 kingdom-id)
            {:keys [proposals]} @(rf/subscribe [::proposals kingdom-name])]
        (when kingdom-name
          (rf/dispatch [::backend/kingdom-proposals kingdom-name])
          [into [:div.overflow-y-auto]
           (map
            (fn
              [{:keys [id kind]}]
              [item id kind])
            proposals)])))}))

(rf/reg-event-db
 ::choice
 (fn [db [_ id choice]]
   (assoc-in db [::choice id] choice)))

(rf/reg-sub
 ::pdata
 (fn [db [_ id]]
   (let [{::keys   [choice]
          ::w/keys [addr]}
         db

         kingdom-id
         (get-in db [addr ::kingdom/kingdom-id])]
     {:choice (get choice id 1)
      :kingdom-id kingdom-id
      :addr addr})))

(rf/reg-event-fx
 ::vote
 (fn [{:keys [db]} [_ proposal-id choice]]
   (let [{::w/keys [provider addr]} db
         domain                     {:name "snapshot" :version "0.1.4"}
         types                      {:Vote [{:name :from :type :address}
                                            {:name :space :type :string}
                                            {:name :timestamp :type :uint64}
                                            {:name :proposal :type :bytes32}
                                            {:name :choice :type :uint32}
                                            {:name :metadata :type :string}]}
         msg                        {:space     conf/snapshot-space
                                     :from      addr
                                     :type      "single-choice"
                                     :choice    choice
                                     :timestamp (js/parseInt (/ (.getTime (js/Date.)) 1000))
                                     :proposal  proposal-id
                                     :metadata  "{}"}]
     (p/let [_ (dialog/submitting)
             sig
             (-> provider
                 (ocall "getSigner")
                 (ocall "_signTypedData"
                        (clj->js domain)
                        (clj->js types)
                        (clj->js msg))
                 (p/catch #(dialog/failed
                            {:title "Typed Sign Failed" :desc "User rejected typed sign in wallet"})))

             res (-> (js/fetch "https://hub.snapshot.org/api/msg"
                               {:method  :post
                                :mode    :cors
                                :headers {"content-type" "application/json"}
                                :body    (backend/->body
                                          {:address addr
                                           :sig     sig
                                           :data    {:domain  domain
                                                     :types   types
                                                     :message msg}})})
                     (p/then backend/then-res)
                     (p/catch #(rf/dispatch [::dialog/set
                                             :open true
                                             :close true
                                             :desc
                                             [:<>
                                              [:span "Message signed"]
                                              [:br]
                                              [:span "Failed to create proposal, please try again"]
                                              [:span (oget % "message")]]])))]
       (when res
         (rf/dispatch [::snapshot/votes proposal-id])
         (rf/dispatch [::dialog/set
                       :open true
                       :close true
                       :desc
                       [:<>
                        [:span "Message signed"]
                        [:br]
                        [:span "Vote created"]]]))))
   {}))

(defn vote [addr choice ifps]
  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
   {:style {:gridTemplateColumns "3fr 1fr"}}
   [:span addr]
   [:span choice]])

(defn proposal [proposal-id]
  (r/create-class
   {:component-did-mount
    (fn []
      (if proposal-id
        (do (rf/dispatch [::snapshot/proposal proposal-id])
            (rf/dispatch [::snapshot/votes proposal-id]))))
    :reagent-render
    (fn []
      (let [{:keys [proposal votes]}
            @(rf/subscribe [::data proposal-id])

            {:keys [title author body state choices ipfs start end snapshot] voute-count :votes}
            proposal

            {:keys [choice kingdom-id]}
            @(rf/subscribe [::pdata proposal-id])

            start (and start (format-date (js/Date. (* 1000 start))))
            end   (and end (format-date (js/Date. (* 1000 end))))

            votes-choices  (map :choice votes)
            choices-count  (count votes-choices)
            choice-1-count (->> votes-choices (filter #(= 1 %)) count)
            choice-2-count (- choices-count choice-1-count)]
        [:div.py-6.px-8
         [:style
          "
      .vote-desc {
        height: calc(100% - 1.75rem);
      }

"]
         [:div.p-3.pb-3.bg-C81c6dd1a.h-full
          [:div.grid.grid-rows-2.gap-x-10.gap-y-8.h-full.pr-9
           {:style {:gridTemplateColumns "1fr 2.8fr"}}
           [:div.col-span-2
            [:p.guild-font-family.text-2xl.uppercase.tracking-wider title]
            [:p.text-xs.text-Cd6d6d6.pl-1
             [:span.align-middle (str "by " (shorten-addr author))]
             [:span.align-middle.inline-block.w-0_65rem.h-0_65rem.bg-no-repeat.bg-contain.bg-center
              {:className "bg-url(/images/link.png)"}]]
            [:div.bg-C81c6dd1a.rounded-sm.p-3.mt-2.text-Cd6d6d6.h-48
             [:div.vote-desc.overflow-auto.text-sm
              [:span.uppercase.pr-2 "descriptions:"]
              body]
             (and
              (= state "active")
              [:div.mt-1.flexb
               [:div
                [:button.guild-font-family.text-sm.tracking-wider.rounded.px-16.py-0_5
                 {:className (if (= choice 1) "bg-C71edf59c" "bg-Cffffff26 text-Cb2f4fa")
                  :on-click
                  #(rf/dispatch [::choice proposal-id 1])}
                 (first choices)]
                [:button.guild-font-family.text-sm.tracking-wider.rounded.px-16.py-0_5.ml-2
                 {:className (if (= choice 2) "bg-C71edf59c" "bg-Cffffff26 text-Cb2f4fa")
                  :on-click
                  #(rf/dispatch [::choice proposal-id 2])}
                 (second choices)]]
               [btn/ui
                {:t         :osm
                 :className "text-white"
                 :on-click
                 #(rf/dispatch [::vote proposal-id choice])}
                "VOTE"]])]]
           [:div.text-xs
            [:p.guild-font-family.text-base.mb-1 "MESSAGE"]
            [:p.grid.gap-y-1
             {:style {:gridTemplateColumns "max-content 1fr"}}
             [:span.text-C96a1ae "IPFS"]
             [:span.justify-self-end ipfs]
             [:span.text-C96a1ae "Start date"]
             [:span.justify-self-end start]
             [:span.text-C96a1ae "End date"]
             [:span.justify-self-end end]
             [:span.text-C96a1ae "Snapshot"]
             [:span.justify-self-end snapshot]]
            [:p.guild-font-family.text-base.mt-3.mb-1
             "THE CURRENT RESULT"]
            [:p.flex.justify-between.mb-1_5
             [:span (first choices)]
             (and (pos? choices-count) (> choice-1-count choice-2-count)
                  [:span "The current result"])
             (and (pos? choices-count) (< choice-1-count choice-2-count)
                  [:span (str choice-1-count
                              (if (> choice-1-count 1) " Votes " " Vote ")
                              (if (pos? choices-count)
                                (str (-> choice-1-count (/ choices-count) (* 100)))
                                "0")
                              "%")])]
            [:div.relative.w-full.h-1_5.rounded-l-full.bg-C00000033.mb-4
             [:div.absolute.h-1_5.rounded-l-full.bg-C96a1ae
              {:style {:width (if (pos? choices-count)
                                (str (-> choice-1-count (/ choices-count) (* 100)) "%")
                                0)}}]]
            [:p.flex.justify-between.mb-1_5
             [:span (second choices)]
             (and (pos? choices-count) (> choice-2-count choice-1-count)
                  [:span "The current result"])
             (and (pos? choices-count) (< choice-2-count choice-1-count)
                  [:span (str choice-2-count
                              (if (> choice-2-count 1) " Votes " " Vote ")
                              (if (pos? choices-count)
                                (str (-> choice-2-count (/ choices-count) (* 100)))
                                "0")
                              "%")])]
            [:div.relative.w-full.h-1_5.rounded-l-full.bg-C00000033
             [:div.absolute.h-1_5.rounded-l-full.bg-C96a1ae
              {:style {:width (if (pos? choices-count)
                                (str "" (-> choice-2-count (/ choices-count) (* 100)) "%")
                                0)}}]]]
           [:div.flex.flex-col
            [:div.rounded-sm.bg-C79c5da66.text-lg.text-center
             (str "VOTE (" voute-count ")")]
            [into [:div.flex-1.guild-table-body.text-sm.overflow-auto]
             (mapv (fn [{:keys [voter ipfs choice]}]
                     [vote voter (nth choices (dec choice)) ipfs])
                   votes)]
            ;; [:div.flex-1.guild-table-body.text-sm.overflow-auto
            ;;  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
            ;;   {:style {:gridTemplateColumns "1fr 2fr 1fr"}}
            ;;   [:span "2022.04.05"]
            ;;   [:span "0xa202...F0BF"]
            ;;   [:span "YES"]]
            ;;  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
            ;;   {:style {:gridTemplateColumns "1fr 2fr 1fr"}}
            ;;   [:span "2022.04.05"]
            ;;   [:span "0xa202...F0BF"]
            ;;   [:span "YES"]]
            ;;  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
            ;;   {:style {:gridTemplateColumns "1fr 2fr 1fr"}}
            ;;   [:span "2022.04.05"]
            ;;   [:span "0xa202...F0BF"]
            ;;   [:span "YES"]]
            ;;  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
            ;;   {:style {:gridTemplateColumns "1fr 2fr 1fr"}}
            ;;   [:span "2022.04.05"]
            ;;   [:span "0xa202...F0BF"]
            ;;   [:span "YES"]]
            ;;  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
            ;;   {:style {:gridTemplateColumns "1fr 2fr 1fr"}}
            ;;   [:span "2022.04.05"]
            ;;   [:span "0xa202...F0BF"]
            ;;   [:span "YES"]]
            ;;  [:div.grid.py-3.justify-items-center.border-b-1px.border-solid.border-C79c5da
            ;;   {:style {:gridTemplateColumns "1fr 2fr 1fr"}}
            ;;   [:span "2022.04.05"]
            ;;   [:span "0xa202...F0BF"]
            ;;   [:span "YES"]]]
            ]]]]))}))

(defn main [{:keys [path-params]}]
  (let [{:keys [proposal-id]} path-params]
    (if proposal-id
      [proposal proposal-id]
      [proposals])))
