(ns fgl.app.views.front.body
  (:require
   ["@radix-ui/react-avatar" :as Avatar]))

(defn h1 []
  [:h1.font-bold.text-center {:style {:fontSize "3.125rem"}} "Explore Middle Ages in Metaverse"])

(defn h3 []
  [:h3.font-bold.text-lg.mb-5 "A brand new experience"])

(defn p1 []
  [:p.text-sm.text-center
   {:style {:maxWidth "50rem"}}
   "A continent in the metaverse is going through its Middle Ages. Noble Knights are determined to go to the Battlefield, slaying enemies together in every cycle, and earning their $GLORY. With enough $GLORY in hand, they are able to recruit more Knights to strengthen themselves. They even have chances to become a Lord themselves."])

(defn app-btn []
  [:a.block.font-bold.text-white.text-center.py-1
   {:className "px-3.25rem mr-2.5 w-11.25rem"
    :style     {:backgroundImage "linear-gradient(180deg, #E8B239 0%, #EC7917 100%)"
                :boxShadow       "0 2px 4px 0 rgba(255,197,90,0.24)"
                :borderRadius    "1.25rem"
                :cursor          :pointer
                :minWidth        "2.5rem"}
    :on-click  identity}
   "Start App"])

(defn whitepaper-btn []
  [:a.block.font-bold.text-white.text-center.py-1.border.border-white
   {:className "px-3.25rem w-11.25rem"
    :style     {:boxShadow    "0 2px 4px 0 rgba(255,197,90,0.24)"
                :borderRadius "1.25rem"
                :cursor       :pointer
                :minWidth     "2.5rem"}
    :on-click  identity}
   "Whitepaper"])

(defn avatar [src]
  [:> Avatar/Root
   {:className "block border-0.125rem border-rgb-8f816a"
    :style     {:borderRadius "50%"}}
   [:> Avatar/Image
    {:src       src
     :alt       "avatar"
     :className "border-0.125rem border-rgb-4f3926 w-6.75rem"
     :style     {:borderRadius "50%"
                 :boxShadow    "inset 0 0 3px 0 rgba(0,0,0,0.50)"}}]])

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
   [letter-p "The greedy Lords in power certainly need Knights to guard their wealth, while they always afraid of too many $GLORYs in the hand of the Knights. They established a Council and claimed to support every Knights on the Battlefield. However, the shady main purpose behind the Council is to tax every Knights for their hard-earned $GLORYs. They sometimes even overuse their power to expropriate the newly recruited Knights & Lords. Some Knights may decide to act against the authorities and leave the Battlefield with all $GLORY in hand, but they might lose all in this risky move..."]
   [letter-br]
   [letter-p "However, the relationship between Lord & Knight can not be simply summarized as rivalry. Lords & Knights with mutual objective send their peasants as messengers to form a Guild. As a powerful organization with maximum of 50 members, a Guild is entitled with organic benefits: by locking up $GLORY for certain period"]
   [letter-br]
   [letter-p "Opportunistic Civilians always aim to earn more $GLORY in every battle cycle. They lock their $GLORY at the Pawn for a Ballot. Ballots are extremely limited in every cycle. With Ballots in hand, Civilians get to vote for one Lord in each cycle. A Lord with more votes has higher discourse of power at the Council, and he might have chance to charge an extra tax randomly on some unlucky Knights. The extra taxed $GLORY will be fifty-fifty split to the Lord and those Civilians who voted for him. Civilians get to recruit their own Knights or even become a Lord from few smart choices."]
   [letter-br]
   [letter-p "And of course, to defend their own benefit, Lords & Knights may secretly hide among Civilians, manipulate Ballots or vote for themselves. Teaming up, cooperating or betraying... Different voting strategy are open for you to explore."]
   [letter-br]
   [letter-h1 "The Team"]
   [:br]
   [:ul.avatars.flex.flex-row.justify-between
    [:li [ava "/images/avatar-1.png" "Michael Scott" "Founder"]]
    [:li [ava "/images/avatar-2.png" "Jim Helpert" "Co-Founder"]]
    [:li [ava "/images/avatar-3.png" "Pam Beesly" "Head of Marketing"]]
    [:li [ava "/images/avatar-4.png" "Angella Philip" "Head of Operations"]]]])

(defn h2 []
  [:h2.font-bold {:id "roadmap"
                  :style {:fontSize     "2.375rem"
                          :marginBottom "2.375rem"}}
   "Roadmap"])

(defn card-h1 [x]
  [:h1.font-semibold.text-3xl x])
(defn card-h2 [x]
  [:h2.font-semibold.text-xl x])
(defn card-ul [& x]
  [into [:ul.font-medium.text-sm] x])

(defn raw-card [{:keys [style]} & children]
  [into
   [:div.bg-rgb-f4e8d5.px-5.pb-5
    {:style (merge  {:padding-top   "1.125rem"
                     :margin-left   "1.8125rem"
                     :margin-right  "1.875rem"
                     :max-width     "25rem"
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
   [:section.section-1.w-full.flex.flex-col.items-center.pt-45vh.pb-60.mb-7rem.bg-no-repeat.bg-scroll
    {:style {:backgroundImage    "radial-gradient(circle, transparent 10%, transparent 75%),linear-gradient(to right, rgba(0,0,0,0.9), rgba(0,0,0,0.9)),url(\"/images/bg-new.jpg\")"
             :backgroundSize     "cover" ;; "100%"
             :backgroundBlendMode "normal, darken"
             :backgroundPosition "center center"
             :marginTop          "-7rem"
             :height "calc(100vh + 7rem)"}}
    [h1]
    [h3]
    [p1]
    [:div.flex.flex-row.mt-10
     [app-btn]
     [whitepaper-btn]]]
   [:section.section-2.w-full.flex.flex-col.items-center.py-20.bg-center.bg-no-repeat.bg-scroll.bg-cover
    {:style {:backgroundImage "url(\"/images/bg-2.png\")"
             :marginTop       "-7rem"}}
    [letter]]
   [:section.section-3.w-full.flex.flex-col.items-center.pt-20
    {:style {:paddingBottom "6.125rem"}}
    [h2]
    [card {:left? true :last? false}
     [card-h2 "2021 Q3 COMPLETE"]
     [card-h1 "Sept."]
     [card-h2 "First Launch"]
     [card-ul
      [:li "First of the the game FORGLORY via NFT staking."]
      [:li "80% user signed up with and made trades within the first 72 hours."]
      [:li "Introduced and edcated a new wave of users."]]]
    [card {:left? false :last? false}
     [card-h2 "2021 Q4 COMPLETE"]
     [card-h1 "Dec."]
     [card-h2 "First Launch"]
     [card-ul
      [:li "First of the the game FORGLORY via NFT staking."]
      [:li "80% user signed up with and made trades within the first 72 hours."]
      [:li "Introduced and edcated a new wave of users."]
      [:li "First of the the game FORGLORY via NFT staking."]
      [:li "80% user signed up with and made trades within the first 72 hours."]
      [:li "Introduced and edcated a new wave of users."]]]
    [card {:left? true :last? false}
     [card-h2 "2021 Q3 COMPLETE"]
     [card-h1 "Sept."]
     [card-h2 "First Launch"]
     [card-ul
      [:li "First of the the game FORGLORY via NFT staking."]
      [:li "80% user signed up with and made trades within the first 72 hours."]
      [:li "Introduced and edcated a new wave of users."]]]
    [card {:left? false :last? true}
     [card-h2 "2021 Q4 COMPLETE"]
     [card-h1 "Dec."]
     [card-h2 "First Launch"]
     [card-ul
      [:li "First of the the game FORGLORY via NFT staking."]
      [:li "80% user signed up with and made trades within the first 72 hours."]
      [:li "Introduced and edcated a new wave of users."]
      [:li "First of the the game FORGLORY via NFT staking."]
      [:li "80% user signed up with and made trades within the first 72 hours."]
      [:li "Introduced and edcated a new wave of users."]]]]])
