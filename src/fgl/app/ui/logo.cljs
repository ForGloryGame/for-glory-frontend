(ns fgl.app.ui.logo)

(defn ui
  ([] (ui {}))
  ([{:keys [style] :as opts}]
   [:img.mr-8
    (merge
     {:alt "link to home page"
      :srcSet "/images/logo-128.png 128w,
                /images/logo-256.png 256w,
                /images/logo-512.png 512w,
                /images/logo-1024.png 1024w,"
      :sizes  "(max-width: 1024px) 128px
                (max-width: 2048px) 256px
                (max-width: 2880px) 512px
                (min-width: 2880px) 1024px"}
     opts
     {:style (merge {:width "4.75rem" :minWidth "4.75rem" :minHeight "3.28375rem"} style)})]))
