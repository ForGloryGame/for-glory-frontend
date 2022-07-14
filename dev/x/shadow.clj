(ns x.shadow
  (:require
   [babashka.fs :as fs]
   ;; [babashka.process :refer [process]]
   [babashka.tasks :refer [shell]]
   ;; [taoensso.timbre :refer [warn]]
   [clojure.java.shell :refer [sh]]
   [clojure.string :as s]))

(defonce project-dir (-> *file* fs/parent fs/parent fs/parent))

(defn- shadow-edn []
  (-> project-dir
      (fs/path "shadow-cljs.edn")
      fs/file
      slurp
      read-string))

(defn- get-shadow-cljs-command [args]
  (let [command "bun run shadow-cljs"]
    (if (= (first args) "server")
      (let [command
            (str
             command
             " -d cider/cider-nrepl:"
             (-> (sh "emacsclient" "--eval" "(progn (require 'cider) cider-injected-middleware-version)")
                 :out
                 s/trim-newline
                 read-string)
             " -d nrepl/nrepl:"
             (-> (sh "emacsclient" "--eval" "(progn (require 'cider) cider-injected-nrepl-version)")
                 :out
                 s/trim-newline
                 read-string)

             ;; " -d refactor-nrepl/refactor-nrepl:"
             ;; (-> (sh "emacsclient" "--eval" "cljr-injected-middleware-version")
             ;;     :out
             ;;     s/trim-newline
             ;;     read-string)
             )
            jack-in-deps

            (-> (sh "emacsclient" "--eval" "(progn (require 'cider) cider-jack-in-dependencies)")
                :out
                s/trim-newline
                read-string)
            deps
            jack-in-deps
            ;; => "bun run shadow-cljs -d nrepl/nrepl:0.9.0-beta5
            ;;                     -d refactor-nrepl/refactor-nrepl:3.0.0-alpha13
            ;;                     -d cider/cider-nrepl:0.27.2 "
            command
            (reduce (fn [acc [library version]]
                      (str acc " -d " library ":" version))
                    command deps)]
        command)
      command)))

(defn run [& args]
  (let [args                (or args ["server"])
        shadow-cljs-command (get-shadow-cljs-command args)]
    (println (s/join " " (concat [">" shadow-cljs-command] args)))
    (apply shell shadow-cljs-command args)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn release-all []
  (let [{:keys [builds]} (shadow-edn)
        ids              (remove #{:cards :browser-test :karma-test} (keys builds))]
    (apply run "release" ids)))
