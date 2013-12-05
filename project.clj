(defproject alephing "0.1.0-SNAPSHOT"
  :description "A demo program for webscoket ping/pong using aleph"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [aleph "0.3.0"]
                 [quil "1.6.0"]]
  :main alephing.core
  :aot [alephing.core])
