(ns auto-reload.core
  "Automatically reload code in a background thread on file modification."
  {:author "Naitik Shah"}
  (:require
    [clojure.tools.logging])
  (:use
    [ns-tracker.core :only [ns-tracker]])
  (:import
    [java.nio.file FileSystems StandardWatchEventKinds]))

(defn- auto-reload* [dirs]
  (let [modified-namespaces (ns-tracker dirs)
        fs (FileSystems/getDefault)
        watcher (.newWatchService fs)
        events (into-array [StandardWatchEventKinds/ENTRY_MODIFY])
        strs (into-array String [])]
    (doseq [dir dirs]
      (.register (.getPath fs dir strs) watcher events))
    (while true
      (let [key (.take watcher)]
        (doseq [ns-sym (modified-namespaces)]
          (require ns-sym :reload))
        (.reset key)))))

(defn auto-reload [dirs]
  (.start (Thread. (partial auto-reload* dirs))))
