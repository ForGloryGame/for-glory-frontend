(ns xxx.app.views
  (:require
   [girouette.core :refer [css]]
   [re-frame.core :as rf]
   [breaking-point.core :as bp]
   [re-pressed.core :as rp]
   [xxx.app.routes :as routes]
   [xxx.app.events :as events]
   [xxx.app.subs :as subs]
   [xxx.config :as config]))

(defn dispatch-keydown-rules []
  (rf/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[::events/set-re-pressed-example "Hello, world!"]
                   [{:keyCode 72} ;; h
                    {:keyCode 69} ;; e
                    {:keyCode 76} ;; l
                    {:keyCode 76} ;; l
                    {:keyCode 79} ;; o
                    ]]]

     :clear-keys
     [[{:keyCode 27} ;; escape
       ]]}]))

(defn display-re-pressed-example []
  (let [re-pressed-example (rf/subscribe [::subs/re-pressed-example])]
    [:div

     [:p
      "Re-pressed is listening for keydown events. However, re-pressed
      won't trigger any events until you set some keydown rules."]

     [:div
      [:button
       {:on-click dispatch-keydown-rules}
       "set keydown rules"]]

     [:p
      [:span
       "After clicking the button, you will have defined a rule that
       will display a message when you type "]
      [:strong [:code "hello"]]
      [:span ". So go ahead, try it out!"]]

     (when-let [rpe @re-pressed-example]
       [:div
        {:style {:padding          "16px"
                 :background-color "lightgrey"
                 :border           "solid 1px grey"
                 :border-radius    "4px"
                 :margin-top       "16px"}}
        rpe])]))

(defn main-panel []
  (let [current-route                      @(rf/subscribe [::routes/current-route])
        {:keys [name view] :as route-data} (get current-route :data {})]
    [:div
     [:h1
      "Current route " [:code name] ". Site Version " config/version]
     [display-re-pressed-example]
     [:div
      [:h3 (str "screen-width: " @(rf/subscribe [::bp/screen-width]))]
      [:h3 (str "screen: " @(rf/subscribe [::bp/screen]))]]
     [:div
      (if current-route
        [view route-data]
        [:p "Loading..."])]]))
