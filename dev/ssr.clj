(ns ssr
  (:require
   [selmer.parser :as selmer]
   [selmer.util]
   [babashka.process :as p]
   [babashka.fs :as fs]))

(defn- get-pwd []
  (System/getProperty "user.dir"))

(let [html          (-> ['node (-> (get-pwd)
                                   (fs/file "./resources/app/ssr.js"))]
                        p/process
                        :out
                        slurp)
      template-file (fs/file (get-pwd) "./resources/app/public/index.html")

      template (slurp template-file)

      rendered (selmer.util/without-escaping
                (selmer/render
                 template
                 {:main html}))]
  (spit template-file rendered))
