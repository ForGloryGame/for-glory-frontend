(ns fgl.app.views.choose-kingdom
  (:require
   [fgl.app.ui.btn :as btn]
   [fgl.app.ui.dialog :as dialog]
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.re-frame]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]))

(defn style []
  [:style "
      .kingdom .mask {
        background: linear-gradient(#000000a6, #000000a6),
          linear-gradient(to top, transparent 80%, #000000a6),
          linear-gradient(to bottom, transparent 80%, #000000a6),
          linear-gradient(155deg, #ffffff, transparent 30%);
      }
      .kingdom .section {
        hyphens: auto;
      }"])

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::kingdom/init-all]))
    :stop identity}])

(rf/reg-event-db ::hover (fn [db [_ id]] (assoc db ::hover id)))
(rf/reg-event-db ::select (fn [db [_ id]] (assoc db ::select id)))

(rf/reg-sub
 ::data
 (fn [db _]
   (let [{::keys [select hover]} db]
     {:select select
      :hover hover})))

(def img
  {1 "/images/select-kingdom-woff.png"
   2 "/images/select-kingdom-lion.png"
   3 "/images/select-kingdom-griffin.png"
   4 "/images/select-kingdom-dragon.png"
   5 "/images/select-kingdom-phoenix.png"})

(def img-bg
  {1 "/images/select-kingdom-woff-bg.png"
   2 "/images/select-kingdom-lion-bg.png"
   3 "/images/select-kingdom-griffin-bg.png"
   4 "/images/select-kingdom-dragon-bg.png"
   5 "/images/select-kingdom-phoenix-bg.png"})

(def kingdom-desc
  {1 "A highland covered with eternal ice located on the southeast of the Gloryverse. Severe climate make it uninhabitable for almost all creatures. Wolves, however, treat it as unrestrained paradise. Some said they are governed by Fenrir, one of the most mythical and powerful beasts."
   2 "A highland covered with eternal ice located on the southeast of the Gloryverse. Severe climate make it uninhabitable for almost all creatures. Wolves, however, treat it as unrestrained paradise. Some said they are governed by Fenrir, one of the most mythical and powerful beasts."
   3 "A highland covered with eternal ice located on the southeast of the Gloryverse. Severe climate make it uninhabitable for almost all creatures. Wolves, however, treat it as unrestrained paradise. Some said they are governed by Fenrir, one of the most mythical and powerful beasts."
   4 "A highland covered with eternal ice located on the southeast of the Gloryverse. Severe climate make it uninhabitable for almost all creatures. Wolves, however, treat it as unrestrained paradise. Some said they are governed by Fenrir, one of the most mythical and powerful beasts."
   5 "A highland covered with eternal ice located on the southeast of the Gloryverse. Severe climate make it uninhabitable for almost all creatures. Wolves, however, treat it as unrestrained paradise. Some said they are governed by Fenrir, one of the most mythical and powerful beasts."})

(def bg-gradient
  {1 "#5788c640"
   2 "#53b5c440"
   3 "#d1ac5b40"
   4 "#be675240"
   5 "#6d57c640"})

(defn left []
  (let [{:keys [hover select]} @(rf/subscribe [::data])
        current                (or hover select 1)]
    [:div {:className "relative w-1/2 h-full flex"
           :style {:background (str "linear-gradient(120deg, " (get bg-gradient current) ", transparent 50%)")}}
     [:div {:className "w-40% h-full ml-32 bg-no-repeat z-2 text-center"
            :style     {:backgroundImage (str "url(" (get img-bg current) ")")
                        :backgroundSize  "100% 100%"}}
      [:img {:className "w-48 inline-block mt-16"
             :src       (get img current)}]
      [:p {:className "text-2xl tracking-widest mt-10 mb-4"}
       (get kingdom/kingdoms-name current)]
      [:div {:className "text-xs text-C97979a text-left ffd px-9 section"}
       (get kingdom-desc current)]]]))

(def img-flag
  {1 "/images/select-kingdom-woff-flag.png"
   2 "/images/select-kingdom-lion-flag.png"
   3 "/images/select-kingdom-griffin-flag.png"
   4 "/images/select-kingdom-dragon-flag.png"
   5 "/images/select-kingdom-phoenix-flag.png"})

(defn kingdom [id]
  (let [{:keys [select hover]} @(rf/subscribe [::data])
        selected?              (= select id)
        hovered?               (= hover id)]
    [:button.block
     {:className    "relative"
      :onMouseEnter #(rf/dispatch [::hover id])
      :onMouseLeave #(rf/dispatch [::hover])
      :on-click     #(rf/dispatch [::select id])
      :style        {:filter (if (or hovered? selected?) "" "brightness(0.6)")
                     :minWidth "6.3rem"}}
     [:img {:src (get img-flag id)}]
     [:img
      {:className "absolute w-28 max-w-none top-2 left-1/2 transform -translate-x-1/2"
       :src       (get img id)}]
     [:p {:className "absolute w-full text-sm text-Cd5e4e8"
          :style {:bottom "15%"}}
      (get kingdom/kingdoms-name id)]]))

(defn confirm []
  (let [{:keys [select]} @(rf/subscribe [::data])]
    [btn/ui
     {:t         :olg
      :on-click
      #(rf/dispatch
        [::kingdom/send
         {:method :join
          :params select
          :on-success
          (fn []
            (dialog/on-success)
            (rf/dispatch [:navigate :route/guild-basic]))}])
      :className "absolute bottom-12 right-16"}
     "CONFIRM"]))

(defn right []
  [:div {:className "absolute top-0 right-0 w-2/3 h-full px-24"
         :style     {:paddingTop "10%"}}
   [:p {:className "text-Cd5e4e8 text-3xl tracking-wider"}
    "Choose Your Kingdom"]
   [:div {:className "ffd text-sm my-2"}
    "Decades passed, villages nearby started to get constantly raided by powerful rangers. Riding on the wolf, fighting with primitive power and treating enemies with no mercy - Notorious name of “The Astas” had soon spread over the Gloryverse. According to rumors from the survivors, they are orphans raised by wolves. Aiming to make a blood sacrifice to Fenrir for some reason..."]
   [:p {:className "text-sm text-Cababab ffd font-bold italic"}
    "You can modify the kingdom after selection"]
   [:div
    {:className "relative grid grid-cols-5 gap-x-16 mt-8 text-center"
     :style {:minWidth "30rem"}}
    [kingdom 1]
    [kingdom 2]
    [kingdom 3]
    [kingdom 4]
    [kingdom 5]]
   [confirm]])

(defn main []
  [:<>
   [style]
   [:div {:className "fixed top-0 left-0 z-100 kingdom w-screen h-screen bg-cover bg-center"
          :style     {:backgroundImage "url(/images/select-kingdom-bg.png)"}}
    [:div {:className "mask absolute w-full h-full top-0"}]
    [left]
    [right]]])
