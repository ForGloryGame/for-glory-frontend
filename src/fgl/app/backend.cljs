(ns fgl.app.backend
  (:require
   [fgl.app.config :as conf]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [oops.core :refer [oget ocall]]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(defn ->body [x]
  (-> x clj->js js/JSON.stringify))

(defn then-res [res]
  (if (oget res "ok")
    (ocall res "json")
    (do
      (js/console.error "Error in response" res)
      (throw (js/Error. res)))))

(defn gget [uri cb]
  (-> (js/fetch
       (str conf/backend-endpoint uri)
       (clj->js
        {:method  :get
         :mode    :cors
         :headers {"content-type" "application/json"}}))
      (p/then then-res)
      (p/then cb)
      (p/catch js/console.error)))

(defn res->err-txt [res]
  (p/let [res (or (ocall res "json")
                  (ocall res "text"))
          res (if (instance? js/Object res)
                (or
                 (oget res "error")
                 (js/JSON.stringify res))
                res)]
    res))

(defn post [uri body cb]
  (-> (js/fetch
       (str conf/backend-endpoint uri)
       (clj->js
        {:method  :post
         :mode    :cors
         :headers {"content-type" "application/json"}
         :body    (when body (->body body))}))
      (p/then (fn [res]
                (if (oget res "ok")
                  (ocall res "json")
                  (p/then (res->err-txt res)
                          (fn [errmsg]
                            (js/console.error "Error in response" errmsg)
                            (throw (js/Error. errmsg)))))))
      (p/then cb)
      (p/catch (fn [err]
                 (js/console.error err)
                 (when (fn? cb)
                   (cb err))))))

(rf/reg-event-fx
 ::kingdom-proposals
 (fn [{:keys [db]} [_ kingdom-name]]
   (let [{::w/keys [addr]} db]
     (when addr
       (gget
        (str "/proposals/" kingdom-name)
        (fn [d]
          (let [d (-> d (js->clj :keywordize-keys true) :proposals)]
            (rf/dispatch [::set d ::kingdom-proposals kingdom-name]))))))
   {}))

(rf/reg-event-fx
 ::rank-personal
 (fn [{:keys [db]}]
   (let [{::w/keys [addr]} db]
     (when addr
       (gget
        (str "/leaderboard/personal/" addr)
        (fn [d]
          (let [d
                (-> d (js->clj :keywordize-keys true) :data)
                {:keys [leaderboard me]} d]
            (rf/dispatch [::set leaderboard ::rank-personal-leaderboard])
            (rf/dispatch [::set me ::rank-personal-me]))))))
   {::w/raddr ::rank-personal}))

(rf/reg-event-fx
 ::rank-kingdom
 (fn [{:keys [db]}]
   (let [{::w/keys [addr]} db]
     (when addr
       (gget
        (str "/leaderboard/kingdom" addr)
        (fn [d]
          (let [d (-> d (js->clj :keywordize-keys true) :data)]
            (rf/dispatch [::set d ::rank-kingdom]))))))
   {}))

(rf/reg-event-fx
 ::new-proposal
 (fn [_ [_ data cb]]
   (post "/proposals/create" (clj->js data) (or cb identity))
   {}))

(rf/reg-event-fx
 ::vote
 (fn [_ [_ kingdom-id data cb]]
   (post (str "/proposals/" kingdom-id)  (clj->js data) (or cb identity))
   {}))

(defonce proposal-domain
  {:name "glory game" :version "1.0.0"})

(defonce proposal-types
  {:Proposal
   [{:name :account :type :address}
    {:name :kind :type :string}
    {:name :kingdom :type :string}
    {:name :snapshot :type :uint64}
    {:name :description :type :string}]})
