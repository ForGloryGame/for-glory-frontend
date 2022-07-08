(ns fgl.app.preload
  (:require
   [babashka.fs :as fs]))

(defmacro images [on-load]
  (let [pwd    (fs/cwd)
        images (-> pwd
                   (fs/file "resources/app/public/images")
                   (fs/list-dir "*.{png,svg}"))
        images (map #(str "/images/" (fs/file-name %)) images)
        images-count (count images)]
    [images-count
     (into
      [:div {:style {:visibility "hidden" :position "fixed" :zIndex -100 :top 0 :left 0}}]
      (map #(vector :img {:onLoad on-load :src %}) images))]))
