(ns hooks.css
  (:require
   ;; [lambdaisland.ornament :as o]
   ;; [garden.compiler :as gc]
   ;; [girouette.tw.preflight :as girouette-preflight]
   [xxx.app.grammer]
   [xxx.app.css]
   ;; [xxx.app.styles]
   [girouette.processor :as css-processor]))

;; (defn write-styles-hook
;;   {:shadow.build/stage :flush}
;;   [build-state & args]
;;   ;; In case your global-styles is in a separate clj file you will have to
;;   ;; reload it yourself, shadow only reloads/recompiles cljs/cljc files
;;   (require 'xxx.app.styles :reload)
;;   ;; Just writing out the CSS is enough, shadow will pick it up (make sure you
;;   ;; have a <link href=styles.css rel=stylesheet>)
;;   (spit "resources/app/public/css/compiled/main.css"
;;         (str
;;          ;; `defined-styles` takes a :preflight? flag, but we like to have some
;;          ;; style rules between the preflight and the components. This whole bit
;;          ;; is optional.
;;          (gc/compile-css (concat
;;                           girouette-preflight/preflight
;;                           xxx.app.styles/global-styles))
;;          "\n"
;;          (o/defined-styles)))
;;   build-state)

(defn build
  {:shadow.build/stage :configure}
  [build-state & _]
  (let [mode (:shadow.build/mode build-state)]
    (css-processor/process {:css           {:output-file "resources/app/public/css/compiled/main.css"}
                            :garden-fn     'xxx.app.grammer/class-name->garden
                            :apply-classes 'xxx.app.css/composed-classes
                            :watch?        true
                            ;; :verbose?      false
                            ;; :dry-run? true
                            })
    build-state))
