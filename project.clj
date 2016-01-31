(defproject automat "0.2.0-alpha2"
  :description ""
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[rhizome "0.2.1"]
                 [primitive-math "0.1.4"]
                 [potemkin "0.3.9"]
                 [proteus "0.1.4"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.7.0"]
                                  [org.clojure/clojurescript "1.7.228"]
                                  [org.clojure/test.check "0.5.9"]
                                  [criterium "0.4.3"]
                                  [codox-md "0.2.0" :exclusions [org.clojure/clojure]]]
                   :prep-tasks ["javac" "compile"]
                   :auto-clean false
                   :aliases {"clean-test" ["do" "clean," "test," "cljsbuild" "test"]
                             "clean-build" ["do" "clean," "cljsbuild" "once"]}}}
  :test-selectors {:default #(every? (complement #{:stress :benchmark}) (keys %))
                   :stress :stress
                   :benchmark :benchmark}
  :global-vars {*warn-on-reflection* true}
  :jvm-opts ^:replace ["-server" "-Xmx2g"]
  :java-source-paths ["src"]
  :javac-options ["-target" "1.5" "-source" "1.5"]
  :source-paths ["src" "target/src" "target/classes"]
  :test-paths ["test" "target/test"]
  :plugins [[codox "0.6.4"]
            [com.cemerick/clojurescript.test "0.3.3"]
            [lein-cljsbuild "1.1.2"]]
  :cljsbuild {:builds [{:source-paths ["src" "test"]
                        :compiler {:output-to "target/test.js"
                                   :source-map "target/test.js.map"
                                   :output-dir "target/js"
                                   :optimizations :advanced}}]
              :test-commands {"phantom" ["phantomjs" :runner "target/test.js"]}}
  :codox {:writer codox-md.writer/write-docs
          :include [automat.core automat.viz]})
