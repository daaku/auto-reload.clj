(ns auto-reload.repl
  "repl helpers"
  {:author "Naitik Shah"}
  (:require
    [auto-reload.core]
    [clojure.tools.logging]))

(auto-reload.core/auto-reload ["src"])
