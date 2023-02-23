(defproject qback "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring "1.9.6"]
                 [metosin/reitit "0.5.18"]
                 [com.talanlabs/avatar-generator "1.1.0"]
                 [com.talanlabs/avatar-generator-cat "1.1.0"]
                 [com.talanlabs/avatar-generator-8bit "1.1.0"]]
  :main ^:skip-aot qback.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev"]}}
  :ring {:handler qback.controller.handlers/handler
         :port 3001}
  :plugins [[lein-ring "0.12.5"]])
