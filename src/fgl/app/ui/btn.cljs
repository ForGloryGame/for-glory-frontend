(ns fgl.app.ui.btn
  (:require
   [taoensso.encore :as enc]))

(defn ui [{:keys [c s] :as opt} text]
  [:button.relative.border.fb.text-center
   (enc/nested-merge
    {:style
     {:width           (case s :xs "12rem" :sm "8rem" "14rem")
      :height          (case s :xs "1.5rem" "1.8rem")
      :backgroundColor (case c :orange "rgb(237, 142, 40)" "rgb(37, 161, 183)")
      :textShadow      "0.0341rem 0.036575rem 0.025rem rgba(0, 0, 0, 0.64)"
      :borderColor     (case c :orange "rgb(255, 211, 134)" "rgb(94, 203, 231)")
      :borderRadius    "1rem"}}
    (-> opt
        (dissoc :c)
        (dissoc :s)))
   [:div.absolute
    {:style {:top                  "8%"
             :height               "30%"
             :left                 "50%"
             :transform            "translateX(-50%)"
             :width                "96%"
             :backgroundColor      "rgba(255, 255, 255, 0.2)"
             :borderTopLeftRadius  "1rem"
             :borderTopRightRadius "1rem"}}]
   text])
