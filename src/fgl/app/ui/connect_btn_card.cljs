(ns fgl.app.ui.connect-btn-card
  (:require
   [reagent.core :as reagent]
   [nubank.workspaces.core :as ws]
   [nubank.workspaces.card-types.react :as ct.react]
   [fgl.app.ui.connect-btn]))

(ws/defcard ^:dev/before-load connect-btn
  (ct.react/react-card
   (reagent/as-element
    [fgl.app.ui.connect-btn/ui])))
