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
     "query Proposal($id: String!) {\n  proposal(id: $id) {\n    id\n    ipfs\n    title\n    body\n    discussion\n    choices\n    start\n    end\n    snapshot\n    state\n    author\n    created\n    plugins\n    network\n    type\n    quorum\n    symbol\n    strategies {\n      name\n      network\n      params\n    }\n    space {\n      id\n      name\n    }\n    scores_state\n    scores\n    scores_by_strategy\n    scores_total\n    votes\n  }\n}"}
    #(rf/dispatch [::set (-> % (oget "data.proposal")
                             (js->clj  :keywordize-keys true)) ::proposal id]))
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
     "query Votes($id: String!, $first: Int, $skip: Int, $orderBy: String, $orderDirection: OrderDirection, $voter: String) {\n  votes(\n    first: $first\n    skip: $skip\n    where: {proposal: $id, vp_gt: 0, voter: $voter}\n    orderBy: $orderBy\n    orderDirection: $orderDirection\n  ) {\n    ipfs\n    voter\n    choice\n    vp\n    vp_by_strategy\n  }\n}"}
    #(rf/dispatch [::set (-> % (oget "data.votes")
                             (js->clj  :keywordize-keys true)) ::votes id]))
   {}))
