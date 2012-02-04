(defproject auto-reload "1.0.2"
  :description "Reload code in a background thread on file modification."
  :author "Naitik Shah <n@daaku.org>"
  :url "https://github.com/nshah/auto-reload.clj"
  :repl-init auto-reload.repl
  :dependencies
    [[ns-tracker "0.1.1"]
     [org.clojure/clojure "1.3.0"]
     [org.clojure/tools.logging "0.2.3"]]
  :dev-dependencies
    [[org.clojure/clojure "1.3.0"]
     [vimclojure/server "2.3.0-SNAPSHOT"]])
