(ns hooks.css
  (:require
   ;; [lambdaisland.ornament :as o]
   ;; [garden.compiler :as gc]
   ;; [girouette.tw.preflight :as girouette-preflight]
   [fgl.app.grammer]
   [clojure.string :as str]
   [fgl.app.css]
   ;; [fgl.app.styles]
   [girouette.processor :as css-processor]))

;; (defn write-styles-hook
;;   {:shadow.build/stage :flush}
;;   [build-state & args]
;;   ;; In case your global-styles is in a separate clj file you will have to
;;   ;; reload it yourself, shadow only reloads/recompiles cljs/cljc files
;;   (require 'fgl.app.styles :reload)
;;   ;; Just writing out the CSS is enough, shadow will pick it up (make sure you
;;   ;; have a <link href=styles.css rel=stylesheet>)
;;   (spit "resources/app/public/css/compiled/main.css"
;;         (str
;;          ;; `defined-styles` takes a :preflight? flag, but we like to have some
;;          ;; style rules between the preflight and the components. This whole bit
;;          ;; is optional.
;;          (gc/compile-css (concat
;;                           girouette-preflight/preflight
;;                           fgl.app.styles/global-styles))
;;          "\n"
;;          (o/defined-styles)))
;;   build-state)

(defn- css-resource-output-file [build-state]
  (-> build-state
      :shadow.build/config
      :output-dir
      (str/replace "/js/compiled" "/css/compiled/main.css")))

(defn build
  {:shadow.build/stage :configure}
  [build-state & _]
  (let [build-id (:shadow.build/build-id build-state)
        mode     (:shadow.build/mode build-state)]
    (prn "hahaha" (some #{build-id} [:cards :app]))
    (when (some #{build-id} [:cards :app])
      (css-processor/process {:css           {:output-file (css-resource-output-file build-state)}
                              :garden-fn     'fgl.app.grammer/class-name->garden
                              :apply-classes 'fgl.app.css/composed-classes
                              :watch?        (= mode :dev)
                              :verbose?      true}))
    build-state))
