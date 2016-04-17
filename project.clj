(defproject clj-async-test "0.0.5"
  :description "clojure.test assertions for testing asynchronous code"
  :url "https://github.com/mhjort/clj-async-test"
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :source-paths ["src"]
  :test-paths ["test"]
  :profiles {:dev {:dependencies [[org.clojure/tools.trace "0.7.8"]]}}
  :plugins [[lein-ancient "0.6.6"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"})
