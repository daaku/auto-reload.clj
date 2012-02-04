(ns auto-reload.core
  "Automatically reload code in a background thread on file modification."
  {:author "Naitik Shah"}
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
      (let [key (.take watcher)
            events (.pollEvents key)]
        (if (not-every? #(= (.kind %) StandardWatchEventKinds/OVERFLOW) events)
          (doseq [ns-sym (modified-namespaces)]
            (require ns-sym :reload)))
        (.reset key)))))

(defn auto-reload
  "Enables auto-reload for the namespaces loaded from the given seq of
  directories in a background thread. Returns immediately."
  [dirs]
  (.start (Thread. (partial auto-reload* dirs))))
