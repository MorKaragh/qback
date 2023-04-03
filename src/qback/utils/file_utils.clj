(ns qback.utils.file-utils
  (:require [clojure.java.io :as io])
  (:import [java.security MessageDigest]
           [java.io ByteArrayOutputStream]))

(defn apply-to-every-line [path func]
  (with-open [fl (io/reader path)]
    (doseq [line (line-seq fl)]
           (apply func line))))

(defn md5 [file]
  (let [bytes'
        (with-open [xin (io/input-stream file)
                    xout (ByteArrayOutputStream.)]
          (io/copy xin xout)
          (.toByteArray xout))
        algorithm (MessageDigest/getInstance "MD5")
        raw (.digest algorithm bytes')]
    (format "%032x" (BigInteger. 1 raw))))