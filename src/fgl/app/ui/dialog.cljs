(ns fgl.app.ui.dialog
  (:require
   [taoensso.encore :as enc]
   ["@radix-ui/react-dialog" :as D]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [fgl.app.ui.loading-dot :as ld]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]))

(defonce !refresh-count (r/atom 0))

(rf/reg-event-fx
 ::set
 (fn [{:keys [db]} [_ & kvs]]
   (let [id :gglobal
         rst
         {:db (cond
                (and (= (first kvs) :remove)
                     (true? (second kvs)))
                (assoc-in db [::data id] (get-in db [::default-data id]))
                (nil? (get-in db [::default-data id]))
                (-> db
                    (assoc-in [::data id]
                              (apply assoc (get-in db [::data id] {}) kvs))
                    (assoc-in [::default-data id]
                              (apply assoc (get-in db [::data id] {}) kvs)))
                :else
                (assoc-in db [::data id]
                          (apply assoc (get-in db [::data id] {}) kvs)))}

         rst (if (and (not (get-in db [::data id :open])) (some (fn [[k v]] (and (= k :open) v)) (partition 2 kvs)))
               (assoc rst :fx [[:dispatch-later {:ms 5000 :dispatch [::set :close? true]}]])
               rst)]
     rst)))

(rf/reg-sub
 ::data
 (fn [db _]
   (get-in db [::data :gglobal] {})))

(defn root [{:keys [defaultOpen title desc close actions] :as opt} trigger]
  (r/create-class
   {:component-did-mount
    (fn []
      (rf/dispatch
       [::set
        :open defaultOpen
        :title title
        :desc desc
        :actions actions
        :close? close]))
    :reagent-render
    (fn []
      (let [{:keys [open title desc close? actions]}
            @(rf/subscribe [::data :open])

            close #(rf/dispatch [::set :remove true])

            opt
            (-> opt (assoc :id "gglobal")
                (assoc :open open)
                (dissoc :actions)
                (dissoc :title)
                (dissoc :desc))]
        [:> D/Root opt
         trigger
         [:> D/Portal
          [:> D/Overlay {:className "fixed inset-0 bg-black opacity-20" :on-click (if close? close identity)}]
          [:> D/Content
           {:className "fixed top-50% left-50% transform -translate-x-50% -translate-y-50% bg-no-repeat p-4 fb"
            :style     {:backgroundImage "url(/images/popup.png)"
                        :backgroundSize  "100%"
                        :width           "40rem"
                        :height          "20.4775396rem"}}
           (and title [:> D/Title {:className "flexr text-center text-2xl"} title])
           (and
            desc
            [:>
             D/Description
             {:className "px-16 text-lg flex flex-col justify-center h-76% overflow-auto" :asChild true}
             [:div
              (and desc desc)]])
           (and actions [:div.w-full.flexr actions])
           (and close?
                [:> D/Close {:className "absolute top-4 right-4"
                             :on-click  close} [:img.w-8 {:style {:marginRight "-0.2rem"
                                                                  :transform   "translateX(10%)"}
                                                          :src   "/images/panel-close.svg"}]])]]]))}))

(defn trigger [& args]
  [into [:> D/Trigger] args])

(defn close [opts text]
  [:>
   D/Close
   {:asChild true}
   [btn/ui
    (-> opts
        (assoc :on-click #(rf/dispatch [::set :remove true])))
    text]])

(defn submitting-desc []
  [ld/text "Waiting For Wallet Confirmation"])

(defn pending []
  [:<>
   [:span "TX Submitted"]
   [:br]
   [ld/text "Waiting For TX Confirmation"]])

(defn confirmed []
  [:<>
   [:span "TX Submitted"]
   [:br]
   [:span "TX Confirmed"]])

(defn on-success []
  (rf/dispatch [::set
                :open true
                :close? true
                :desc [confirmed]
                :actions [close {:t :bsm} "OK"]]))

(defn on-submitted []
  (rf/dispatch [::set :open true :desc [pending]]))

(defn submitting []
  (rf/dispatch [::set :open true :desc [submitting-desc] :actions nil]))

(defn failed [{:keys [title desc]}]
  (rf/dispatch
   [::set
    :open true
    :title title
    :desc [:<>
           [:span "Reason:"]
           [:br]
           [:p (or desc title)]]
    :actions (close
              {:t :bsm}
              "OK")]))

(defn ui []
  [root
   {:id          :gglobal
    :title       "TX Status"
    :desc [:div]
    :modal       true}
   [trigger
    {:className "invisible"}]])
