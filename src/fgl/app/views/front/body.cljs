(ns fgl.app.views.front.body
  (:require
   [re-frame.core :as rf]
   ["@radix-ui/react-avatar" :as Avatar]))

(defn h1 []
  [:h1.font-bold.text-center {:style {:fontSize "3.125rem"}} "The Future of Community-Driven Strategic MMO"])

(defn h3 []
  [:h3.font-bold.text-lg.mb-5 "To Spark In Glory, One Must Enter Chaos"])

(defn p1 []
  [:p.text-sm.text-center
   {:style {:maxWidth "50rem"}}
   "Tales in GloryLand once told, Five Kingdoms derived from beliefs of five Mythical Creatures. Greatest heroes gather as rising of the Kingdoms. Lords who voice themselves in council, and noble Knights who slay enemies fearlessly on the Battlefields, joining together inside the Five Kingdoms. Learn to fight alongside comrades who share the same belief and earn $Glory and $Gold via this Community-Driven Strategic gameplay you've never experienced before."])

(defn app-btn []
  [:a.block.font-bold.text-white.text-center.py-1
   {:className "px-3.25rem mr-2.5 w-11.25rem cursor-pointer"
    :style     {:backgroundImage "linear-gradient(178deg, #E5CB6F 0%, #AC852C 100%)"
                :boxShadow       "0 2px 4px 0 rgba(255,197,90,0.24)"
                :borderRadius    "1.25rem"
                :minWidth        "2.5rem"}
    :on-click #(rf/dispatch [:navigate :route/home])}
   "Start App"])

(defn whitepaper-btn []
  [:a.block.font-bold.text-white.text-center.py-1.border.border-white
   {:className "px-3.25rem w-11.25rem cursor-pointer"
    :style     {:boxShadow    "0 2px 4px 0 rgba(255,197,90,0.24)"
                :borderRadius "1.25rem"
                :minWidth     "2.5rem"}
    :rel       "noreferrer"
    :target    "_blank"
    :href      "https://for-glory.gitbook.io/for-glory/"}
   "Whitepaper"])

(defn avatar [src]
  [:> Avatar/Root
   {:className "block border-0.125rem border-rgb-8f816a"
    :style     {:borderRadius "50%"
                :width        "6.75rem"}}
   [:> Avatar/Image
    {:src       src
     :alt       "avatar"
     :className "border-0.125rem border-rgb-4f3926"
     :style     {:borderRadius "50%"
                 :boxShadow    "inset 0 0 3px 0 rgba(0,0,0,0.50)"
                 :width        "6.5rem"
                 :height       "6.5rem"}}]])

(defn avatar-info [t1 t2]
  [:figcaption
   {:className "mt-1.5"}
   [:ul.text-center.font-bold
    [:li [:span {:itemProp "name"} t1]]
    [:li.text-sm [:span {:itemProp "jobTitle"} t2]]]])

(defn ava [src t1 t2]
  [:figure
   {:itemScope true :itemType "https://schema.org/person"}
   [avatar src]
   [avatar-info t1 t2]])

(defn letter-p [text]
  [:p.text-sm.font-medium text])
(defn letter-br []
  [:br.text-sm.font-medium])
(defn letter-h1
  ([text] (letter-h1 {} text))
  ([opt text] [:h1.text-4xl.leading-10 (merge {:style {:fontFamily "old_londonregular"}} opt) text]))

(defn letter []
  [:article.border-rgb-480909.bg-rgb-f4e8d4.text-black.pt-10.text-justify
   {:id    "about"
    :style {:maxWidth         "43.75rem"
            :border           "0.3125rem"
            :background-image "url(\"/images/bg-2-1.png\")"
            :padding-left     "2.875rem"
            :padding-right    "2.875rem"
            :padding-bottom   "4.5rem"}}
   [letter-h1 "Once upon a time…"]
   [letter-br]
   [letter-p "Tales in GloryLand once told stories of Mythical Creatures and \"Gods\". But, one day, they vanished... Leaving No Trace behind."]
   [letter-br]
   [letter-p "Worshippers of these Mythical beast gave birth to five families: The MorningStars, The Luciars, The Flamingo, The Astas and the Dawns. Born of different beliefs and blood lines, these families were born of rivalry."]
   [letter-br]
   [letter-p "As 5 villages began to grow into towns and eventually kingdoms, the tension between them worsened. One day, following the discovery of $Gold and $Glory in the lands, conflict broke out."]
   [letter-br]
   [letter-p "Greatest heroes gathered within their corresponding Kingdoms. Lords who voiced themselves in council and noble Knights who slayed enemies fearlessly on the battlefields. Joining forces with each other, they learn to fight alongside comrades who share the same belief."]
   [letter-br]
   [letter-p "As the war amongst the Five Kingdoms intensifies, more and more heroes are recruited into each kingdom and the greed for these resources blinds even the wisest of all."]
   [letter-br]
   [letter-p "At the same time, archaeologist who thoroughly study the history of glory land begans to slowly uncover the hidden truth behind the disappearance of the Mythical Beasts. Where will this all lead to?"]
   [letter-br]
   [letter-h1 "The Team"]
   [:br]
   [:ul.avatars.flex.flex-row.justify-between.px-14
    [:li [ava "/images/avatar-1.png" "0xP" "Founder"]]
    [:li [ava "/images/avatar-2.png" "0xChiaki" "Head of Devs"]]
    [:li [ava "/images/avatar-3.png" "0xCT" "Game Design"]]]])

(defn h2 []
  [:h2.font-bold {:id    "roadmap"
                  :style {:fontSize     "2.375rem"
                          :marginBottom "2.375rem"}}
   "Roadmap"])

(defn card-h1 [x]
  [:h1.font-semibold.text-3xl x])
(defn card-h2
  ([x] (card-h2 {} x))
  ([opts x]
   [:h2.font-semibold.text-xl opts x]))
(defn card-ul [& x]
  [into [:ul.font-medium.text-sm] x])

(defn raw-card [{:keys [style]} & children]
  [into
   [:div.bg-rgb-f4e8d5.px-5.pb-5.flex.flex-col
    {:style (merge  {:padding-top   "1.125rem"
                     :margin-left   "1.8125rem"
                     :margin-right  "1.875rem"
                     :width         "25rem"
                     :border-radius "0.375rem"}
                    style)}]
   children])

(defn crown
  ([] (crown false))
  ([last?]
   [:div.flex.flex-col.justify-between.items-center
    [:img.block.m-5px.w-36px.h-36px {:src "/images/crown.svg"}]
    (and (not last?) [:div.w-6px.bg-rgb-f9e19c.grow.h-full])
    (and (not last?) [:img.block.m-5px.w-36px.h-36px.invisible {:src "/images/crown.svg"}])]))

(defn card
  [{:keys [left? last?]} & children]
  [:div.flex.flex-row.justify-between.text-black
   {:style {:marginBottom "-2.75rem"}}
   [apply raw-card {:style {:visibility (if left? "visible" "hidden")}} children]
   [crown last?]
   [apply raw-card {:style {:visibility (if left? "hidden" "visible")}} children]])

(defn ui []
  [:main.flex.flex-col.items-center.w-full
   {:style {:minWidth "42.5rem"}}
   [:div.w-full.fixed
    {:style {:backgroundImage     "radial-gradient(circle, transparent 10%, transparent 75%),linear-gradient(to right, rgba(0,0,0,0.6), rgba(0,0,0,0.6)),url(\"/images/bg-new.jpg\")"
             :backgroundSize      "cover"
             ;; :backgroundSize      "100%"
             :backgroundBlendMode "normal, darken"
             :backgroundPositionY "4rem"
             :backgroundPositionX "center"
             :marginTop           "-7rem"
             :height              "calc(100vh + 7rem)"
             :z-index             "-1"}}]
   [:section.section-1.w-full.flex.flex-col.items-center.pt-45vh.pb-60.mb-7rem.bg-no-repeat.bg-scroll
    {:style {;; :backgroundImage     "radial-gradient(circle, transparent 10%, transparent 75%),linear-gradient(to right, rgba(0,0,0,0.6), rgba(0,0,0,0.6)),url(\"/images/bg-new.jpg\")"
             ;; :backgroundSize      "cover" ;; "100%"
             ;; :backgroundBlendMode "normal, darken"
             ;; :backgroundPosition  "center center"
             :marginTop "-7rem"
             :height    "calc(100vh + 7rem)"}}
    [h1]
    [h3]
    [p1]
    [:div.flex.flex-row.mt-10
     [app-btn]
     [whitepaper-btn]]]
   [:section.section-2.w-full.flex.flex-col.items-center.py-20.bg-center.bg-no-repeat.bg-scroll.bg-cover
    {:style {;; :backgroundImage "url(\"/images/bg-2.png\")"
             :marginTop "-7rem"}}
    [letter]]
   [:section.section-3.w-full.flex.flex-col.items-center.pt-20
    {:style {:paddingBottom "6.125rem"}}
    [h2]
    [card {:left? true :last? false}
     [card-h1 "2022 Q2 - Pre-Launch"]
     [card-h2 {:className "mb-2"} "March - May"]
     ;; [card-h2 "Launch of Social Media"]
     [card-ul
      [:li "Launch of Social Media"]
      [:li "Incentivized Rinkeby Testnet"]
      [:li "Bug testing and Rewarding Early Contributor"]]]
    [card {:left? false :last? false}
     [card-h1 "2022 Q2 - Glory Game"]
     [card-h2 {:className "mb-2"} "May - June"]
     ;; [card-h2 "Genesis Collection Minted Out"]
     [card-ul
      [:li "Genesis Collection Minted Out"]
      [:li "Public Launch of For Glory Dapp on Ethereum Mainnet"]
      [:li "Cleaning the BattleField Feature Open"]]]
    [card {:left? true :last? false}
     [card-h1 "2022 Q3"]
     [card-h2 {:className "mb-2"} "July - Sept"]
     ;; [card-h2 "Refinement of Graphics and Gameplay Interface"]
     [card-ul
      [:li "Refinement of Graphics and Gameplay Interface"]
      [:li "Clash of Kingdoms"]
      [:li "Weapons Feature"]
      [:li "Kingdom Construction Feature"]]]
    [card {:left? false :last? true}
     [card-h1 "2022 Q4"]
     [card-h2 {:className "mb-2"} "Oct - Dec"]
     ;; [card-h2 "Partnership"]
     [card-ul
      [:li "Partnership"]
      [:li "Explore Gloryverse"]
      [:li "Lost Chapters Features"]]]]])
