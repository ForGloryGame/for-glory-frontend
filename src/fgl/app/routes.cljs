(ns fgl.app.routes
  (:require
   ;; TODO: check promesa
   ;; [promesa.core :as p]
   [shadow.loader :as loader]
   [fgl.app.views.front :as front]
   [reitit.frontend :as rtf]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   ;; https://cljdoc.org/d/metosin/reitit/0.5.16/doc/coercion/malli
   [reitit.coercion.malli :as m]
   [reitit.coercion :as coercion]
   [reitit.exception :as exception]
   ;; [reitit.core :as r]
   [re-frame.core :as rf]
   [taoensso.timbre :refer [warn error info debug]]))

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
   ["/"
    {:name        :route/front
     :view        front/main
     :controllers (front/controllers)
     :lazy        false}]
   ["home"
    {:name        :route/home
     :view        #(resolve 'fgl.app.views.home/main)
     :controllers #(resolve 'fgl.app.views.home/controllers)
     :lazy        true
     :conflicting true}]
   ["login"
    {:name        :route/login
     :view        #(resolve 'fgl.app.views.login/main)
     :controllers #(resolve 'fgl.app.views.login/controllers)
     :lazy        true
     :conflicting true}]
   ["about"
    {:name        :route/about
     :view        front/main
     :controllers (front/controllers)
     :lazy        false}
    ;; {:name        :route/about
    ;;  :view        #(resolve 'fgl.app.views.about/main)
    ;;  :controllers #(resolve 'fgl.app.views.about/controllers)
    ;;  :lazy        true
    ;;  :conflicting true}
    ]
   ["roadmap"
    {:name        :route/roadmap
     :view        front/main
     :controllers (front/controllers)
     :lazy        false}]
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
     :conflicting true}]
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
      (warn (exception/format-exception :path-conflicts nil conflicts)))}))

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
                           (error %))))))

;;; init
(defn on-navigate [new-match]
  (when new-match
    (let [{:keys [name lazy]} (:data new-match)]
      (if lazy
        (fetch-router-view! name #(rf/dispatch [::navigated new-match]))
        (rf/dispatch [::navigated new-match])))))

(defn init! []
  (info "initializing routes")
  (rfe/start! router on-navigate {:use-fragment true}))
