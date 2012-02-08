(defproject auto-reload "1.0.3"
  :description "Reload code in a background thread on file modification."
  :author "Naitik Shah <n@daaku.org>"
  :url "https://github.com/nshah/auto-reload.clj"
  :repl-init auto-reload.repl
  :checksum-deps true
  :exclusions [org.clojure/clojure]
  :dependencies
    [[ns-tracker "0.1.1"]
     [org.clojure/clojure "1.3.0"]]
  :dev-dependencies
    [[lein-marginalia "0.7.0-20111019.122151-1"]
     [org.clojure/tools.logging "0.2.3"]
     [vimclojure/server "2.3.1"]])
