(ns qback.files.image-processing
  (:import (javax.imageio ImageIO))
  (:require [clojure.java.io :as io]))

(defn image? [file]
  (with-open [stream (io/input-stream file)] 
    (try 
      (when (.toString (ImageIO/read stream)) true)
      (catch Exception e false))))




