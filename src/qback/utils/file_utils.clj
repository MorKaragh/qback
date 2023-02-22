(ns qback.utils.file-utils
  (:require [clojure.java.io :as io]))

(defn apply-to-every-line [path func]
  (with-open [fl (io/reader path)]
    (doseq [line (line-seq fl)]
           (apply func line))))