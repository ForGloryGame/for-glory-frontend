(ns fgl.app.routes
  (:require
   ;; [promesa.core :as p]
   [reitit.core :as r]
   [shadow.loader :as loader]
   ;; [fgl.app.views.front :as front]
   [reitit.frontend :as rtf]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   ;; https://cljdoc.org/d/metosin/reitit/0.5.16/doc/coercion/malli
   [reitit.coercion.malli :as m]
   [reitit.coercion :as coercion]
   [fgl.app.views.home :as home]
   ;; [reitit.exception :as exception]
   ;; [reitit.core :as r]
   [re-frame.core :as rf]
   [lambdaisland.glogi :as log]))

(declare router)

(comment
  ;; cljs.core.subs(location.hash, 1)
  (subs (.. js/window -location -hash) 1)
  (r/route-names router)
  (-> (r/match-by-path router (subs (.. js/window -location -hash) 1))
      :data
      :name)
  (-> (r/match-by-path router "")
      :data
      :name)
  (-> (r/match-by-path router "/")
      :data
      :name))

;; https://github.com/russmatney/starters/blob/master/fullstack/src/fullstack/ui/routes.cljs
;;; Subs
(rf/reg-sub
 ::current-route
 (fn [db] (:current-route db)))

;;; Events
;; navigate with (rf/dispatch [:navigate :routes/home])
(rf/reg-event-fx
 :navigate
 (fn [_cofx [_ & route]] {::navigate! route}))

;; Triggering navigation from events.
(rf/reg-fx
 ::navigate!
 (fn [route] (apply rfe/push-state route)))

(rf/reg-event-db
 ::navigated
 (fn [db [_ new-match]]
   (let [{:keys [lazy view controllers]}
         (:data new-match)

         controllers
         (if (and lazy (fn? controllers)) ((controllers)) controllers)

         new-match
         (if lazy
           (-> new-match
               (assoc-in [:data :view] (view))
               (assoc-in [:data :controllers] controllers))
           new-match)

         old-match   (:current-route db)
         controllers (rfc/apply-controllers (:controllers old-match) new-match)]
     (assoc db :current-route (assoc new-match :controllers controllers)))))

;;; Routes
(def routes
  [""
   {:name        :route/home
    :view        home/main
    :controllers (home/controllers)
    ;; :lazy        true
    :conflicting true}
   ["/"
    ["" {:name        :route/home
         :view        home/main
         :controllers (home/controllers)
         ;; :lazy        true
         :conflicting true}]
    ["dashboard"
     {:name        :route/dashboard
      :panel-name  "Personal Center"
      :view        #(resolve 'fgl.app.views.dashboard/main)
      :controllers #(resolve 'fgl.app.views.dashboard/controllers)
      :lazy        true
      :conflicting true}]
    ["battlefield"
     {:name        :route/battlefield
      :panel-name  "Battlefield"
      :view        #(resolve 'fgl.app.views.battlefield/main)
      :controllers #(resolve 'fgl.app.views.battlefield/controllers)
      :lazy        true
      :conflicting true}]
    ["council"
     {:name        :route/council
      :panel-name  "Council"
      :view        #(resolve 'fgl.app.views.council/main)
      :controllers #(resolve 'fgl.app.views.council/controllers)
      :lazy        true
      :conflicting true}]
    ["guild/basic"
     {:name        :route/guild-basic
      :panel-name  "Guild"
      :view        #(resolve 'fgl.app.views.guild-basic/main)
      :controllers #(resolve 'fgl.app.views.guild-basic/controllers)
      :lazy        true
      :conflicting true}]
    ["guild/alter"
     {:name        :route/guild-alter
      :panel-name  "Guild"
      :view        #(resolve 'fgl.app.views.guild-alter/main)
      :controllers #(resolve 'fgl.app.views.guild-alter/controllers)
      :lazy        true
      :conflicting true}]
    ["mint"
     {:name        :route/mint
      :view        #(resolve 'fgl.app.views.mint/main)
      :controllers #(resolve 'fgl.app.views.mint/controllers)
      :lazy        true
      :conflicting true}]
    ["404"
     {:name        :route/not-found
      :view        #(resolve 'fgl.app.views.v404/main)
      :lazy        true
      :conflicting true}]
    ["500"
     {:name        :route/server-error
      :view        #(resolve 'fgl.app.views.v500/main)
      :lazy        true
      :conflicting true}]
    [":fallback"
     {:name        :route/fallback
      :view        #(resolve 'fgl.app.views.v404/main)
      :lazy        true
      :conflicting true}]]
   ;; [":id"
   ;;  {:name        :route/profile
   ;;   :view        #(resolve 'fgl.app.views.profile/main)
   ;;   :controllers #(resolve 'fgl.app.views.profile/controllers)
   ;;   :lazy        true
   ;;   :parameters  {:path [:map [:id string?]]}
   ;;   :conflicting true}]
   ])

(def router
  (rtf/router
   routes
   {:data    {:coercion m/coercion}
    :compile coercion/compile-request-coercers
    :conflicts
    (fn [conflicts]
      ;; (warn (exception/format-exception :path-conflicts nil conflicts))
      )}))

(defn- fetch-router-view!
  "Load lazy route with pages-conf"
  [route-name dispatch-fn]
  (if (loader/loaded? (name route-name))
    (dispatch-fn)
    ;; load lazy page
    (->
     (loader/load (name route-name))
     (.then dispatch-fn #(do
                           (rf/dispatch [:navigate :route/server-error])
                           ;; (log/error %)
                           )))))

;;; init
(defn on-navigate [new-match]
  (when new-match
    (let [{:keys [name lazy]} (:data new-match)]
      (log/debug :route-match name)
      (if lazy
        (fetch-router-view! name #(rf/dispatch [::navigated new-match]))
        (rf/dispatch [::navigated new-match])))))

(defn init! []
  (log/debug :init :routes)
  (rfe/start! router on-navigate {:use-fragment true}))
