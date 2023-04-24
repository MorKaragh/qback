(ns qback.photohost.image-processing
  (:import (javax.imageio ImageIO))
  (:import (java.nio.file Files))
  (:require [clojure.java.io :as io]))

(def ^{:private true}
  signatures {:jpeg ["ff" "d8" "ff" "e0"]
              :png ["89" "50" "4e" "47"]
              :gif ["47" "49" "46" "38"]
              :tiff ["49" "49" "2a" "00"]})

(defn- stream-bytes [is]
  (let [baos (java.io.ByteArrayOutputStream.)]
    (io/copy is baos)
    (.toByteArray baos)))

(defn- to-hex [v]
  (format "%02x" v))

(defn- starts-with [src tst]
  (= tst (subvec src 0 (count tst))))

(defn- get-file-type [file-bytes]
  (some #(when (starts-with (vec (take 10 file-bytes)) (val %)) (key %))
        signatures))

(defn image-type [file]
  (with-open [stream (io/input-stream file)]
    (get-file-type (map to-hex (stream-bytes stream)))))

(defn bytes [path]
  (Files/readAllBytes (.toPath (io/file path))))

(defn image? [file]
  (with-open [stream (io/input-stream file)]
    (try
      (when (.toString (ImageIO/read stream)) true)
      (catch Exception e false))))

