(ns fgl.app.backend
  (:require
   [fgl.app.config :as conf]
   [fgl.wallet.core :as w]
   [oops.core :refer [oget ocall]]
   [promesa.core :as p]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::set
 (fn [db [_ v & paths]]
   (assoc-in db paths v)))

(defn- ->body [x]
  (-> x clj->js js/JSON.stringify))

(defn gget [uri cb]
  (-> (js/fetch
       (str "" uri)
       (clj->js
        {:method  :get
         :mode    :cors
         :headers {"content-type" "application/json"}}))
      (p/then (fn [res]
                (if (oget res "ok")
                  (ocall res "json")
                  (do
                    (js/console.error "Error in response" res)
                    (throw (js/Error. res))))))
      (p/then cb)
      (p/catch js/console.error)))

(defn post [uri body cb]
  (-> (js/fetch
       (str "" uri)
       (clj->js
        {:method  :get
         :mode    :cors
         :headers {"content-type" "application/json"}
         :body    (when body (->body body))}))
      (p/then (fn [res]
                (if (oget res "ok")
                  (ocall res "json")
                  (do
                    (js/console.error "Error in response" res)
                    (throw (js/Error. res))))))
      (p/then cb)
      (p/catch js/console.error)))

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
 (fn [_ [_ data]]
   (post "/proposals/create" data identity)
   {}))
