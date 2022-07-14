(ns fgl.app.views
  #_{:clj-kondo/ignore [:unused-namespace]}
  (:require
   [fgl.app.events :as events]
   [fgl.app.routes :as routes]
   [fgl.app.subs :as subs]
   [fgl.app.ui.body :as body]
   [fgl.app.ui.header :as header]
   [fgl.app.ui.kingdom-map :as uikmap]
   [fgl.app.ui.map :as uimap]
   [fgl.config :as conf]
   [fgl.wallet.core :as w]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]))

(defn main-panel []
  (let [state                      (rf/subscribe [::w/state])
        wrong-network?             (rf/subscribe [::w/wrong-network])
        current-route              @(rf/subscribe [::routes/current-route])
        all-image-loaded           @(rf/subscribe [:all-image-loaded])
        {:keys [view] rname :name} (get current-route :data)
        home?                      (= rname :route/home)
        guild?                     (and (keyword? rname)
                                        (or (-> rname name (.startsWith "guild-"))
                                            (-> rname name (.startsWith "kingdom-"))))]
    (and (not conf/debug?)
         (js/setTimeout
          #(cond (and (not (= @state :connected)) rname (not (= rname :route/home)))
                 (do
                   (rf/dispatch [:navigate :route/home])
                   (w/connect!)
                   [:div])
                 (and (= @state :connected) @wrong-network? rname (not (= rname :route/home)))
                 (do
                   (rf/dispatch [:navigate :route/home])
                   (rf/dispatch [::w/switch-to-target-chain!])
                   [:div]))
          500))
    (if all-image-loaded
      [:div.grid.h-100vh.bg-cover.bg-no-repeat.bg-center
       {:style {:gridTemplate "\"header\" min-content \"main\""}}
       [header/ui]
       (and guild? [uikmap/ui])
       (and (not home?) (not guild?) [uimap/ui])
       [body/ui current-route (if view [view current-route] [:div])]
       [:link {:rel "stylesheet" :href "/fonts/family.css"}]]
      [:div "Loading..."])))
