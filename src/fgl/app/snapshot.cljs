(ns fgl.app.snapshot
  (:require
   [lambdaisland.glogi :as log]
   [oops.core :refer [oget ocall]]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(defn- ->body [x]
  (-> x clj->js js/JSON.stringify))

(defn post [body cb]
  (-> (js/fetch
       "https://hub.snapshot.org/graphql"
       (clj->js
        {:method  :post
         :mode    :cors
         :headers {"content-type" "application/json"}
         :body    (->body body)}))
      (p/then (fn [res]
                (if (oget res "ok")
                  (ocall res "json")
                  (do
                    (js/console.error "Error in response" res)
                    (throw (js/Error. res))))))
      (p/then cb)
      (p/catch js/console.error)))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(rf/reg-event-fx
 ::proposal
 (fn [_ [_ id]]
   (post
    {:operationName "Proposal"
     :variables     {:id id}
     :query
     "query Proposal($id: String!) {
proposal(id: $id) {
id
ipfs
title
body
discussion
choices
start
end
snapshot
state
author
created
plugins
network
type
quorum
symbol
strategies {
name
network
params
}
space {
id
name
}
scores_state
scores
scores_by_strategy
scores_total
votes
}
}"}
    #(rf/dispatch [::set (-> % (oget "data.proposal")
                             (js->clj  :keywordize-keys true)) ::proposal id]))
   {}))

(rf/reg-event-fx
 ::proposals
 (fn [_ [_ space]]
   (post
    {:operationName "Proposals",
     :variables     {:first     6
                     :skip      0
                     :space     (or space "test1234567.eth")
                     :state     "all"
                     :author_in []},
     :query         "query Proposals($first: Int!, $skip: Int!, $state: String!, $space: String, $space_in: [String], $author_in: [String]) {
proposals(
first: $first
skip: $skip
where: {space: $space, state: $state, space_in: $space_in, author_in: $author_in}
) {
id
ipfs
title
body
start
end
state
author
created
choices
space {
id
name
members
avatar
symbol
}
scores_state
scores_total
scores
votes
quorum
symbol
}
}"}
    #(rf/dispatch [::set (-> % (oget "data.proposals")
                             (js->clj  :keywordize-keys true))
                   ::proposals
                   (or space "test1234567.eth")]))
   {}))

(rf/reg-event-fx
 ::votes
 (fn [_ [_ id]]
   (post
    {:operationName "Votes"
     :variables
     {:id             id
      :orderBy        "vp"
      :orderDirection "desc"
      :first          1
      :voter          ""}
     :query
     "query Votes($id: String!, $first: Int, $skip: Int, $orderBy: String, $orderDirection: OrderDirection, $voter: String) {
votes(
first: $first
skip: $skip
where: {proposal: $id, vp_gt: 0, voter: $voter}
orderBy: $orderBy
orderDirection: $orderDirection
) {
ipfs
voter
choice
vp
vp_by_strategy
}
}"}
    #(rf/dispatch [::set (-> % (oget "data.votes")
                             (js->clj  :keywordize-keys true)) ::votes id]))
   {}))
