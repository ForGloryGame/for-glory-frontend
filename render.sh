#!/usr/bin/env bash

classpath="$(clojure -A:nbb -Spath -Sdeps '{:aliases {:nbb {:replace-deps {io.github.reagent-project/reagent {:git/tag "v1.0.0" :git/sha "02f2098"} io.github.day8/re-frame {:git/tag "v1.2.0" :git/sha "7e58848"}}}}}')"
nbb --classpath "$classpath" src/xxx/app/core_ssr.cljs
