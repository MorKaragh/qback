(ns qback.utils.response-utils 
  (:require [cheshire.core :as json]))

(defn png [pic-bytes]
  {:status 200
   :headers {"Content-Type" "image/png"}
   :body pic-bytes})

(defn json
  ([status body]
   {:status status
    :headers {"Content-Type" "application/json"}
    :body (json/generate-string body)})
  ([body]
   (json 200 body)))


