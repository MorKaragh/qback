(ns user
  (:require [clojure.java.io :as io]
            [qback.utils.file-utils :as fu]))

(fu/apply-to-every-line (io/resource "startlogo.txt") println)