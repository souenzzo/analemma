(defproject analemma "0.1.0-SNAPSHOT"
  :description "Projeto didático para ensinar clojurescript via analemma"
  :url "https://souenzzo.github.com"
  :license {:name "GPLv3"
            :url  "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :min-lein-version "2.7.1"
  :dependencies [[org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/clojurescript "1.9.521"]
                 [reagent "0.6.1"]]
  :plugins [[lein-figwheel "0.5.10"]
            [lein-cljsbuild "1.1.5"]]
  :source-paths ["src"]
  :cljsbuild {:builds [{:id           "dev"
                        :source-paths ["src"]
                        :figwheel     {:on-jsload "analemma.core/on-js-reload"
                                       :open-urls ["http://localhost:3449/index.html"]}
                        :compiler     {:main                 analemma.core
                                       :asset-path           "js/compiled/out"
                                       :output-to            "resources/public/js/compiled/analemma.js"
                                       :output-dir           "resources/public/js/compiled/out"
                                       :source-map-timestamp true
                                       :preloads             [devtools.preload]}}
                       {:id           "min"
                        :source-paths ["src"]
                        :compiler     {:output-to     "resources/public/js/compiled/analemma.js"
                                       :main          analemma.core
                                       :optimizations :advanced
                                       :pretty-print  false}}]}
  :profiles {:dev {:dependencies  [[binaryage/devtools "0.9.2"]
                                   [figwheel-sidecar "0.5.10"]
                                   [com.cemerick/piggieback "0.2.1"]]
                   :source-paths  ["src"]
                   :repl-options  {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
