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

(def img-class
  {1 "w-34"
   2 "w-32 -translate-y-5%"
   3 "w-30 translate-y-5%"
   4 "w-32 -translate-y-1%"
   5 "w-42 translate-y-5%"})

(def img-lg-class
  {1 "w-48"
   2 "w-46 -translate-y-5%"
   3 "w-46 translate-y-5%"
   4 "w-46 -translate-y-1%"
   5 "w-56 translate-y-5%"})

(def img-bg
  {1 "/images/select-kingdom-woff-bg.png"
   2 "/images/select-kingdom-lion-bg.png"
   3 "/images/select-kingdom-griffin-bg.png"
   4 "/images/select-kingdom-dragon-bg.png"
   5 "/images/select-kingdom-phoenix-bg.png"})

(def bg-gradient
  {1 "#5788c640"
   2 "#53b5c440"
   3 "#d1ac5b40"
   4 "#be675240"
   5 "#6d57c640"})

(def desc1
  {1 "A highland covered with eternal ice located on the southeast of the Gloryverse. Severe climate make it uninhabitable for almost all creatures. Wolves, however, treat it as unrestrained paradise. Some said they are governed by Fenrir, one of the most mythical and powerful beasts."
   2 "A grand desert covered with heated sand landed on the southwest of the Gloryverse. Creatures there follow the rules formulate by the Lion. Born, Hunt, Die - they had lived by the natural orders, isolated from other civilizations for decades."
   3 "At the age of chaos, to get blessing from the Mythical Beasts can be an extravagant hope for the most mortals. One day, a giant Griffin flied from the sky to the largest ghetto on the Gloryverse. Spreading his wisdom and enlightenment to the poors at no cost."
   4 "Dragons are ancient rulers of the continent. Their presence stands for ultimate power that inhabited from the beginning of the Gloryverse. The had ruled the continent for decades and mysterious disappeared at some point."
   5 "When there’s light, there’s shadow. Under mighty primitive power of Dragon, a rival who has faith in the protection and celebration from the wisdom of Phoenix among the stars had gradually grown up. They call themselves the Phoenix Academy."})

(defn left []
  (let [{:keys [hover select]} @(rf/subscribe [::data])
        current                (or hover select 1)]
    [:div {:className "relative w-1/2 h-full flex"
           :style     {:background (str "linear-gradient(120deg, " (get bg-gradient current) ", transparent 50%)")}}
     [:div {:className "w-40% h-full ml-32 bg-no-repeat z-2 text-center"
            :style     {:backgroundImage (str "url(" (get img-bg current) ")")
                        :backgroundSize  "100% 100%"}}
      [:img {:className (str "inline-block mt-16 mb-12 transform " (get img-lg-class current))
             :src       (get img current)}]
      ;; [:div {:className "absolute top-50% transform -translate-y-70% w-92"}
      ;;  [:p {:className "text-2xl tracking-widest mb-4 "}
      ;;   (get kingdom/kingdoms-name current)]
      ;;  [:div {:className "text-xs text-C97979a text-left ffd px-9 section"}
      ;;   (get desc1 current)]]
      [:p {:className "text-2xl tracking-widest mb-4 "}
       (get kingdom/kingdoms-name current)]
      [:div {:className "text-xs text-C97979a text-left ffd px-9 section"}
       (get desc1 current)]]]))

(def img-flag
  {1 "/images/select-kingdom-woff-flag.png"
   2 "/images/select-kingdom-lion-flag.png"
   3 "/images/select-kingdom-griffin-flag.png"
   4 "/images/select-kingdom-dragon-flag.png"
   5 "/images/select-kingdom-phoenix-flag.png"})

(def desc2
  {1 "Decades passed, villages nearby started to get constantly raided by powerful rangers. Riding on the wolf, fighting with primitive power and treating enemies with no mercy - Notorious name of “The Astas” had soon spread over the Gloryverse. According to rumors from the survivors, they are orphans raised by wolves. Aiming to make a blood sacrifice to Fenrir for some reason..."
   2 "One day, giant amount of panic creatures scrambled out of the grand desert, leaving those human habitants behind. They start to construct castles and forge armies, causing huge threats to neighboring Kingdoms. The Dawns, as people said, had an unpredictable cultural shift for some reasons. It might be related to the vanishment of the Lion."
   3 "The non-stop and selfless dedication from the Griffin had lasted for decades, until its body slowly became fully petrified. Those who received enlightenment from the Griffin call themselves the Luciars, gathered and formed a Kingdom to preach their belief ever since then. They believe one day the Griffin will revive with the wide spreading of their belief."
   4 "After countless decades, though no presence of Dragon can be found nowadays, The MorningStar Family rose from Dragon worshipping tribes and soon developed into a Kingdom. There’s rumor that they’re descendants of Half-Dragon. Their sudden rise depends on the ancient power bonded by their blood."
   5 "The apprentices of the Academy had never stopped through the vicissitude of the Gloryverse in decades. But the pure worship of the Phoenix had steadily turned into endless desire for wisdom. The Flamingo Family exploited these desires to eliminate dissidents and soon became the actual ruler of the academy. Someone might doubt if they still have blessing from the Phoenix..."})

(defn kingdom [id]
  (let [{:keys [select hover]} @(rf/subscribe [::data])
        selected?              (= select id)
        hovered?               (= hover id)]
    [:button.block
     {:className    "relative"
      :onMouseEnter #(rf/dispatch [::hover id])
      ;; :onMouseLeave #(rf/dispatch [::hover])
      :on-click     #(rf/dispatch [::select id])
      :style        {:filter   (if (or hovered? selected?) "" "brightness(0.6)")
                     :minWidth "6.3rem"}}
     [:img {:src (get img-flag id)}]
     [:img
      {:className (str
                   "absolute max-w-none top-2 left-1/2 transform -translate-x-1/2"
                   " "
                   (get img-class id))
       :src       (get img id)}]
     [:p {:className "absolute w-full text-sm text-Cd5e4e8"
          :style     {:bottom "15%"}}
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
  (let [{:keys [hover select]} @(rf/subscribe [::data])
        current                (or hover select 1)]
    [:div {:className "absolute top-0 right-0 w-2/3 h-full px-24"
           :style     {:paddingTop "10%"}}
     [:p {:className "text-Cd5e4e8 text-3xl tracking-wider"}
      "Choose Your Kingdom"]
     [:div {:className "ffd text-sm my-1"
            :style     {:minHeight "6.75rem"}}
      (get desc2 current)]
     [:p {:className "text-sm text-Cababab ffd font-bold italic"}
      "You can modify the kingdom after selection"]
     [:div
      {:className "relative grid grid-cols-5 gap-x-16 mt-8 text-center"
       :style     {:minWidth "30rem"}}
      [kingdom 1]
      [kingdom 2]
      [kingdom 3]
      [kingdom 4]
      [kingdom 5]]
     [confirm]]))

(defn main []
  [:<>
   [style]
   [:div {:className "fixed top-0 left-0 z-100 kingdom w-screen h-screen bg-cover bg-center"
          :style     {:backgroundImage "url(/images/select-kingdom-bg.png)"}}
    [:div {:className "mask absolute w-full h-full top-0"}]
    [left]
    [right]]])
