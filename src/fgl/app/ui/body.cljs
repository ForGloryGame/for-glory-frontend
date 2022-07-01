(ns fgl.app.ui.body
  (:require
   [fgl.app.ui.dialog :as dialog]
   [fgl.app.ui.guild-panel :as gpanel]
   [fgl.app.ui.merchant-panel :as mpanel]
   [fgl.app.ui.panel :as panel]
   [fgl.app.ui.rank-panel :as rpanel]
   [fgl.app.ui.toast :as toast]
   [lambdaisland.glogi :as log]
   [re-frame.core :as rf]
   [taoensso.encore :as enc]))

(defn- back [guild?]
  (if guild?
    #(rf/dispatch [:navigate :route/kingdom-map])
    #(rf/dispatch [:navigate :route/map])))

(defn wrapper [children]
  [:div.cs2.ce3 {:style {:minWidth "64vw"}} children])

(defn ui [cur-route children]
  (let [route-data                   (:data cur-route)
        {:keys [panel-name] n :name} route-data

        global?   (-> n (or :_) name (.startsWith "the-map"))
        global?   (or global? (-> n (or :_) name (.startsWith "start")))
        guild?    (-> n (or :_) name (.startsWith "guild"))
        merchant? (-> n (or :_) name (.startsWith "merchant"))
        rank?     (-> n (or :_) name (.startsWith "rank"))

        sub-wrapper
        (cond guild?
              #(vector gpanel/ui cur-route %)
              merchant?
              #(vector mpanel/ui cur-route %)
              rank?
              #(vector rpanel/ui cur-route %)
              :else identity)

        panel-wrapper
        (if panel-name
          #(vector panel/ui cur-route panel-name 80 (back guild?) %)
          identity)

        outer-wrapper (if global? identity wrapper)

        body [outer-wrapper [panel-wrapper [sub-wrapper children]]]]
    [:main.grid-area-main.grid.justify-strech.mt-30
     {:style {:gridTemplateColumns "1fr auto 1fr"
              :justifyContent      "strech"}}
     body
     [toast/ui]
     [dialog/ui]]))
