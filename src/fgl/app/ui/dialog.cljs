(ns fgl.app.ui.dialog
  (:require
   [taoensso.encore :as enc]
   ["@radix-ui/react-dialog" :as D]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [fgl.app.ui.loading :as loading]
   [fgl.app.ui.btn :as btn]
   [lambdaisland.glogi :as log]))

(rf/reg-event-db
 ::set
 (fn [db [_ id & kvs]]
   (if (->> kvs
            (partition 2)
            (some (fn [[k v]] (and (= k :remove) v))))
     (enc/dissoc-in db [::data id])
     (assoc-in db [::data id]
               (apply assoc (get-in db [::data id] {}) kvs)))))

(rf/reg-sub
 ::data
 (fn [db [_ id]]
   (get-in db [::data id] {})))

(defn root [{:keys [id defaultOpen title description loading close actions] :as opt} trigger]
  (r/create-class
   {:component-did-mount
    (fn []
      (rf/dispatch
       [::set id
        :open defaultOpen
        :title title
        :loading loading
        :description description
        :actions actions
        :close? close]))
    :reagent-render
    (fn []
      (let [{:keys [open title description loading close? actions]}
            @(rf/subscribe [::data id :open])

            close #(rf/dispatch [::set id :open false :remove true])

            opt
            (-> opt (assoc :id (name id))
                (assoc :open open)
                (dissoc :loading)
                (dissoc :actions)
                (dissoc :title)
                (dissoc :description))]
        ^{:key id}
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
            description
            [:>
             D/Description
             {:className "px-16 text-lg flex flex-col justify-center h-76% overflow-auto" :asChild true}
             [:div
              (and loading [loading/ui])
              (and description description)]])
           (and actions [:div.w-full.flexr actions])
           (and close?
                [:> D/Close {:className "absolute top-4 right-4"
                             :on-click  close} [:img.w-8 {:style {:marginRight "-0.2rem"
                                                                  :transform   "translateX(10%)"}
                                                          :src   "/images/panel-close.svg"}]])]]]))}))

(defn trigger [& args]
  [into [:> D/Trigger] args])

(defn close [{:keys [id] :as opts} text]
  [:>
   D/Close
   {:asChild true}
   [btn/ui
    (-> opts
        (dissoc :id)
        (assoc :on-click #(rf/dispatch [::set id :remove true])))
    text]])
