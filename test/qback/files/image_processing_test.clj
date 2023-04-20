(ns qback.files.image-processing-test
  (:require
   [clojure.test :refer [deftest is]]
   [qback.files.image-processing :as subject]
   [clojure.java.io :as io]))

(deftest image?-test
  (is (= false
         (subject/image? (io/file "./resources/favicon.ico"))))
  (is (= true
         (subject/image? (io/file "./resources/test.jpg")))))
