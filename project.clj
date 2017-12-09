(defproject lacinia-reagent "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-RC1"]
                 [duct/core "0.6.1"]
                 [duct/module.logging "0.3.1"]
                 [duct/module.web "0.6.3"]
                 [duct/module.ataraxy "0.2.0"]
                 [duct/module.cljs "0.3.1"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.2"]
                 [cljs-ajax "0.7.3"]
                 [kibu/pushy "0.3.8"]
                 [bidi "2.1.2"]
                 [vincit/venia "0.2.4"]]
  :plugins [[duct/lein-duct "0.10.5"]]
  :main ^:skip-aot lacinia-reagent.main
  :uberjar-name  "lacinia-reagent-standalone.jar"
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user
                         :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.2.0"]
                                   [eftest "0.4.0"]
                                   [kerodon "0.9.0"]]}})
