(ns fgl.app.ui.header-tag)

(defn ui [children]
  [:div.relative.py-px
   [:div.absolute.h-full.w-full.top-0.left-0
    {:style {:backgroundColor "rgba(0,0,0,0.4)"
             :transform       "skew(-10deg)"
             :borderRadius    "0.2rem"
             :outline         "2px inset #3561703b"}}]
   [:div.absolute
    {:style {:backgroundColor "rgba(255,255,255,.051)"
             :transform       "skew(-4deg)"
             :borderRadius    "0.2rem 0.2rem 0 0"

             :top    "2%"
             :left   "2%"
             :height "40%"
             :width  "96%"}}]
   [:div.relative children]])
