(ns xxx.app.ui.connect-btn-card
  (:require
   [reagent.core :as reagent]
   [clojure.string :as str]
   [nubank.workspaces.core :as ws]
   [nubank.workspaces.card-types.react :as ct.react]
   [xxx.app.ui.connect-btn]))

(enable-console-print!)

(ws/defcard ^:dev/before-load connect-btn
  (ct.react/react-card
   (reagent/as-element
    [xxx.app.ui.connect-btn/ui])))
