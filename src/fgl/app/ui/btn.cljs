(ns fgl.app.ui.btn
  (:require
   [lambdaisland.glogi :as log]
   [taoensso.encore :as enc]))

(defn- light []
  [:div.absolute
   {:style {:top                  "8%"
            :height               "30%"
            :left                 "50%"
            :transform            "translateX(-50%)"
            :width                "96%"
            :backgroundColor      "rgba(255, 255, 255, 0.2)"
            :borderTopLeftRadius  "1rem"
            :borderTopRightRadius "1rem"}}])

(def preset
  {:olg "bg-Ced8e28 border-Cffd386 w-56 h-1_8rem active:bg-Cf8b133 active:border-Cfeeca9 disabled:bg-Ca8978e disabled:border-Cddd2c6"
   :blg "bg-C25a1b7 border-C5ecbe7 w-56 h-1_8rem active:bg-C29cde1 active:border-C78e7f7"
   :osm "bg-Ced8e28 border-Cffd386 w-32 h-1_8rem active:bg-Cf8b133 active:border-Cfeeca9 disabled:bg-Ca8978e disabled:border-Cddd2c6"
   :bsm "bg-C25a1b7 border-C5ecbe7 w-32 h-1_8rem active:bg-C29cde1 active:border-C78e7f7"
   :oxs "bg-Ced8e28 border-Cffd386 w-48 h-6 active:bg-Cf8b133 active:border-Cfeeca9 disabled:bg-Ca8978e disabled:border-Cddd2c6"
   :bxs "bg-C25a1b7 border-C5ecbe7 w-48 h-6 active:bg-C29cde1 active:border-C78e7f7"})

(defn ui [{:keys [t className] :as opt} text]
  [:button.relative.border.fb.text-center.rounded-2xl.disabled:bg-C758184.disabled:border-C96a3a7
   (enc/nested-merge
    {:style
     {:boxShadow  "
0.1609375rem 0.10715rem 0px 0px rgba(0, 0, 0, 0.5),
0.1705rem 0.18285rem 0.2rem 0px rgba(0, 0, 0, 0.17),
0.1705rem 0.18285rem 0.2rem 0px rgba(0, 0, 0, 0.17)"
      :textShadow "0.0341rem 0.036575rem 0.025rem rgba(0, 0, 0, 0.64)"}}
    (-> opt
        (assoc :className (str (or className " ") " " (get preset t "")))
        (dissoc :t)))
   [light]
   text])
