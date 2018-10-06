(defproject analemma "0.1.0-SNAPSHOT"
  :description "Projeto didático para ensinar clojurescript via analemma"
  :url "https://souenzzo.github.com"
  :license {:name "GPLv3"
            :url  "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :min-lein-version "2.8.1"
  :dependencies [[org.clojure/clojure "1.10.0-beta1"]
                 [org.clojure/clojurescript "1.10.339"]
                 [reagent/reagent "0.8.1"]]
  :plugins [[lein-figwheel/lein-figwheel "0.5.10"]
            [lein-cljsbuild/lein-cljsbuild "1.1.7"]]
  :source-paths ["src"]
  :cljsbuild {:builds [{:id           "dev"
                        :source-paths ["src"]
                        :figwheel     {:on-jsload "analemma.core/on-jsload"
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
  :profiles {:dev {:dependencies  [[binaryage/devtools "0.9.10"]
                                   [figwheel-sidecar/figwheel-sidecar "0.5.16"]
                                   [com.cemerick/piggieback "0.2.2"]]
                   :source-paths  ["src"]
                   :repl-options  {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
