(ns fgl.app.views.start
  (:require
   [re-frame.core :as rf]))

(defn controllers []
  [{:start identity :stop identity}])

(defn main []
  [:div
   {:className "fixed start w-screen h-screen bg-cover bg-center top-0 left-0"
    :style     {:backgroundImage "url(/images/start-bg-lg.png)"
                :zIndex          "100000"}}
   [:style
    " .start-reflection {
        transform: scale(1, -1);
      }
      .start-mask {
        background-image: radial-gradient(
          farthest-corner at 50% 0,
          #00000025,
          transparent 35%
        );
      }
"]
   [:div.absolute.right-10%.-translate-x-50%.bottom-4
    [:button.block.w-56.bg-center
     {:on-click #(rf/dispatch [:navigate :route/map])
      :style    {:backgroundImage "url(/images/start-btn-bg.png)"
                 :backgroundSize  "100% 100%"
                 :paddingLeft     "2rem"
                 :paddingRight    "2rem"}}
     [:svg.start-default-svg
      {:viewBox "0 0 418 135", :width "100%", :height "100%"}
      [:style
       {:type "text/css"}
       ".start-default-svg g {filter: url(#Filter_1);}.start-default-svg:hover g {filter: url(#Filter_0);}"]
      [:defs
       [:filter#Filter_0
        {:height      "135px",
         :width       "418px",
         :y           "0px",
         :x           "0px",
         :filterUnits "userSpaceOnUse"}
        [:feOffset {:dy "6.536", :dx "10.064", :in "SourceAlpha"}]
        [:feGaussianBlur {:stdDeviation "1.414", :result "blurOut"}]
        [:feFlood {:result "floodOut", :flood-color "rgb(6, 42, 53)"}]
        [:feComposite
         {:in2 "blurOut", :in "floodOut", :operator "atop"}]
        [:feComponentTransfer
         [:feFuncA {:slope "0.3", :type "linear"}]]
        [:feMerge
         [:feMergeNode]
         [:feMergeNode {:in "SourceGraphic"}]]]
       [:filter#Filter_1
        {:height      "135px",
         :width       "418px",
         :y           "0px",
         :x           "0px",
         :filterUnits "userSpaceOnUse"}
        [:feOffset {:dy "6.536", :dx "10.064", :in "SourceAlpha"}]
        [:feGaussianBlur {:stdDeviation "1.414", :result "blurOut"}]
        [:feFlood {:result "floodOut", :flood-color "rgb(6, 42, 53)"}]
        [:feComposite
         {:in2 "blurOut", :in "floodOut", :operator "atop"}]
        [:feMerge
         [:feMergeNode]
         [:feMergeNode {:in "SourceGraphic"}]]]]
      [:g
       [:path
        {:d
         "M384.735,19.454 L384.735,132.108 L362.722,132.108 L362.722,19.454 L344.185,19.454 L346.573,0.623 L400.883,0.623 L403.272,19.454 L384.735,19.454 ZM313.171,70.901 L313.166,70.901 L325.469,132.105 L303.457,132.105 L292.434,78.580 L279.96,86.260 L279.96,132.108 L257.84,132.108 L257.84,0.623 L314.451,0.623 C319.116,5.287 321.729,7.903 326.393,12.565 L325.114,64.78 L313.171,70.901 ZM304.381,19.454 L279.96,19.454 L279.96,67.429 L304.381,54.629 L304.381,19.454 ZM208.759,79.978 L189.901,93.87 L183.775,132.108 L161.762,132.108 L184.792,0.623 L215.755,0.623 L238.789,132.108 L216.777,132.108 L208.759,79.978 ZM200.275,27.4 L193.355,71.74 L206.199,63.339 L200.275,27.4 ZM128.358,132.108 L106.345,132.108 L106.345,19.454 L87.808,19.454 L90.196,0.623 L144.506,0.623 L146.895,19.454 L128.358,19.454 L128.358,132.108 ZM48.4,19.456 L22.720,19.456 L22.720,34.148 L48.4,63.201 L67.81,85.123 C68.973,87.297 70.14,90.82 70.14,92.962 L70.14,122.86 C66.102,125.998 63.907,128.193 59.992,132.108 L10.731,132.108 C6.817,128.195 4.624,126.0 0.709,122.86 L0.709,96.466 L22.721,83.666 L22.721,113.277 L48.7,113.277 L48.7,96.732 L16.5,59.958 L3.642,45.756 C1.751,43.582 0.709,40.797 0.709,37.917 L0.709,10.645 C4.622,6.732 6.817,4.537 10.731,0.623 L59.994,0.623 C63.907,4.535 66.102,6.730 70.17,10.645 L70.17,34.401 L48.4,47.201 L48.4,19.456 Z",
         :fill      "rgb(255, 255, 255)",
         :fill-rule "evenodd"}]]]]
    [:div.start-reflection.w-56
     {:style {:paddingLeft  "2rem"
              :paddingRight "2rem"}}
     [:svg
      {:viewBox "0 0 418 135", :width "100%", :height "100%"}
      [:style
       {:type "text/css"}
       ".start-reflection-path {fill: url(#MyGradient);}"]
      [:defs
       [:linearGradient#MyGradient
        {:gradientTransform "rotate(90)"}
        [:stop {:stop-color "#ffffff00", :offset "35%"}]
        [:stop {:stop-color "#f0f0f020", :offset "100%"}]]
       [:filter#Filter_0
        {:height      "135px",
         :width       "418px",
         :y           "0px",
         :x           "0px",
         :filterUnits "userSpaceOnUse"}
        [:feOffset {:dy "6.536", :dx "10.064", :in "SourceAlpha"}]
        [:feGaussianBlur {:stdDeviation "1.414", :result "blurOut"}]
        [:feFlood {:result "floodOut", :flood-color "#e0e2e3"}]
        [:feComposite
         {:in2 "blurOut", :in "floodOut", :operator "atop"}]
        [:feComponentTransfer
         [:feFuncA {:slope "0.3", :type "linear"}]]
        [:feMerge
         [:feMergeNode]
         [:feMergeNode {:in "SourceGraphic"}]]]]
      [:g
       {:filter "url(#Filter_0)"}
       [:path.start-reflection-path
        {:d
         "M384.735,19.454 L384.735,132.108 L362.722,132.108 L362.722,19.454 L344.185,19.454 L346.573,0.623 L400.883,0.623 L403.272,19.454 L384.735,19.454 ZM313.171,70.901 L313.166,70.901 L325.469,132.105 L303.457,132.105 L292.434,78.580 L279.96,86.260 L279.96,132.108 L257.84,132.108 L257.84,0.623 L314.451,0.623 C319.116,5.287 321.729,7.903 326.393,12.565 L325.114,64.78 L313.171,70.901 ZM304.381,19.454 L279.96,19.454 L279.96,67.429 L304.381,54.629 L304.381,19.454 ZM208.759,79.978 L189.901,93.87 L183.775,132.108 L161.762,132.108 L184.792,0.623 L215.755,0.623 L238.789,132.108 L216.777,132.108 L208.759,79.978 ZM200.275,27.4 L193.355,71.74 L206.199,63.339 L200.275,27.4 ZM128.358,132.108 L106.345,132.108 L106.345,19.454 L87.808,19.454 L90.196,0.623 L144.506,0.623 L146.895,19.454 L128.358,19.454 L128.358,132.108 ZM48.4,19.456 L22.720,19.456 L22.720,34.148 L48.4,63.201 L67.81,85.123 C68.973,87.297 70.14,90.82 70.14,92.962 L70.14,122.86 C66.102,125.998 63.907,128.193 59.992,132.108 L10.731,132.108 C6.817,128.195 4.624,126.0 0.709,122.86 L0.709,96.466 L22.721,83.666 L22.721,113.277 L48.7,113.277 L48.7,96.732 L16.5,59.958 L3.642,45.756 C1.751,43.582 0.709,40.797 0.709,37.917 L0.709,10.645 C4.622,6.732 6.817,4.537 10.731,0.623 L59.994,0.623 C63.907,4.535 66.102,6.730 70.17,10.645 L70.17,34.401 L48.4,47.201 L48.4,19.456 Z",
         :fill      "currentColor",
         :fill-rule "evenodd"}]]]]
    [:div.start-mask.absolute.left-50%.-translate-x-50%.w-96.h-32
     {:className "z-[-1]] top-1/3"}]]

   [:div.start-sound.absolute.left-12.bottom-10.rounded-sm.bg-cover.p-1.text-0px
    {:style {:backgroundImage "url(/images/start-sound-bg.png)"
             :backgroundSize  "100% 100%"}}
    [:style
     {:type "text/css"}
     "
.start-page-sound {
  background-image: url(/images/start-sound.png);
}
.start-page-sound:hover {
  background-image: url(/images/start-nosound.png);
}
"]
    [:button.start-page-sound.inline-block.w-6.h-6.bg-cover]]])
