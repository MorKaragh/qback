(ns qback.photohost.image-processing-test
  (:require
   [clojure.test :refer [deftest is]]
   [qback.photohost.image-processing :as subject]
   [clojure.java.io :as io]))

(deftest image?-test
  (is (= false
         (subject/image? (io/file "./resources/favicon.ico"))))
  (is (= true
         (subject/image? (io/file "./resources/test.jpg")))))

(deftest image-type-test
  (is (= :jpeg
         (subject/image-type (io/file "./resources/test.jpg"))))
  (is (nil? (subject/image-type (io/file "./resources/favicon.ico")))))