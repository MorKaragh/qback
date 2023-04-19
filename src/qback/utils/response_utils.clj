(ns qback.utils.response-utils 
  (:require [cheshire.core :as json]))

(defn png [pic-bytes]
  {:status 200
   :headers {"Content-Type" "image/png"}
   :body pic-bytes})

(defn json [body]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string body)})