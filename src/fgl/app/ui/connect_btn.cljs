(ns fgl.app.ui.connect-btn
  (:require
   [re-frame.core :as rf]
   [fgl.wallet.core :as w]
   [oops.core :refer [ocall oget]]
   [reagent.core :as r]
   [element-resize.core :as elr]))

(w/init!)

(defonce max-content-style (atom nil))

(defn- get-width [el]
  (-> el
      (ocall "getBoundingClientRect")
      (oget "width")))

(defn- get-computed-style [el]
  (let [style (-> el (ocall "computedStyleMap"))]
    {:width              (get-width el)
     :border-left-width  (-> style
                             (ocall "get" "border-left-width")
                             (oget "value"))
     :border-right-width (-> style
                             (ocall "get" "border-right-width")
                             (oget "value"))
     :padding-left       (-> style
                             (ocall "get" "padding-left")
                             (oget "value"))
     :padding-right      (-> style
                             (ocall "get" "padding-right")
                             (oget "value"))}))

(defn- get-px-of-each-char [computed-styles num]
  (let [{:keys [width border-left-width border-right-width padding-left padding-right]}
        computed-styles
        width (- width border-left-width border-right-width padding-left padding-right)]
    (when-not (zero? num)
      [width (/ (- width border-left-width border-right-width padding-left padding-right) num)])))

(rf/reg-event-db
 ::style-data
 [rf/trim-v]
 (fn [db [data]]
   (assoc db ::style-data data)))

(rf/reg-sub
 ::style-data
 (fn [db _]
   (::style-data db)))

(defn- shorten-addr [width addr]
  (if (or (not @max-content-style) (>= width (first @max-content-style)))
    addr
    (let [[max-width px-each-char] @max-content-style
          each-trucate
          (-> max-width
              (- width)
              (/ px-each-char)
              (/ 2)
              (as-> each-trucate (js/Math.max 1.5 each-trucate))
              js/Math.ceil)]
      (str "0x" (.slice addr 2 (- 22 each-trucate))
           "..." (.slice addr 23 (- 42 each-trucate))))))

(rf/reg-sub
 ::text
 :<- [::w/state]
 :<- [::w/addrs]
 :<- [::elr/nodes :connect-btn]
 (fn [[s addrs {:keys [width]}] _]
   (case s
     :installed   "Connect"
     :uninstalled "Wallet not installed"
     (if addrs [addrs (shorten-addr width (first addrs))] ["" ""]))))

(defn ui []
  (r/create-class
   {:component-did-mount
    (fn [_]
      (elr/observe! :connect-btn (js/document.getElementById "connect-btn")))
    :reagent-render
    (fn []
      (let [state               (rf/subscribe [::w/state])
            [text shorten-text] @(rf/subscribe [::text ;; trucate-length
                                                ])
            disabled            (case @state
                                  :installed false
                                  true)
            x                   @(rf/subscribe [::elr/nodes :connect-btn])]
        (js/setTimeout
         #(reset! max-content-style
                  (let [el    (js/document.getElementById "connect-btn-span")
                        computed-styles (get-computed-style el)]
                    (get-px-of-each-char computed-styles 42)))
         0)
        [:div#connect-btn-wrapper.min-w-0.break-all
         [:button#connect-btn
          {:disabled disabled
           :name     "Connect wallet"
           :on-click w/connect!}
          shorten-text]
         [:span#connect-btn-span.invisible.absolute.w-max text]]))}))

(comment
  (js/console.log (js->clj (:current @(rf/subscribe [::elr/nodes :connect-btn])) :keywordize-keys true)))
