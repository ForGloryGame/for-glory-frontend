(ns fgl.app.views.choose-kingdom
  (:require
   [fgl.contracts.kingdoms :as kingdom]
   [fgl.re-frame]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]))

(defn controllers []
  [{:start
    #(do
       (rf/dispatch [::kingdom/init-all]))
    :stop identity}])

(defn main []
  [:div "choose kingdom"])
