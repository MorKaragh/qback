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


(defn ensure-file-exists [file]
  (when-not (.exists file)
    (.mkdirs (.getParentFile file))
    (.createNewFile file)))

(defn image-file? [file]
  (let [magic-numbers {"xFFxD8" :jpeg
                       "x89x50x4Ex47x0Dx0Ax1Ax0A" :png
                       "x47x49x46x38x37x61" :gif
                       "x42x4D" :bmp}
        magic-number (with-open [r (io/input-stream file)]
                       (->> (byte-array 8)
                            (doto (fn [bytes] (.read r bytes)))
                            (apply str)))
        file-extension (-> file .getName (.lastIndexOf ".") (inc) (-> file .getName (.substring)))
        format (magic-numbers magic-number)]
    (and format (or (= format (keyword file-extension))
                    (= :jpeg format)))))

;; (image-file? (io/file "/home/sergei/Pictures/door-books.jpg"))
