(ns fgl.app.ui.toast-card
  (:require
   [reagent.core :as reagent]
   [nubank.workspaces.core :as ws]
   [nubank.workspaces.card-types.react :as ct.react]
   [fgl.app.ui.toast :as toast]))

(ws/defcard ^:dev/before-load toast
  (ct.react/react-card
   (reagent/as-element
    [:div [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]
     [toast/ui]])))
