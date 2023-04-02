(ns qback.files.files
  (:require [clojure.pprint :as pprint]))

(def cors-headers
  "Generic CORS headers"
  {"Access-Control-Allow-Origin"  "*"
   "Access-Control-Allow-Headers" "*"
   "Access-Control-Allow-Methods" "POST GET OPTIONS"})

(defn upload-handler [req]
  (pprint/pprint req)
  {:status 200
   :body "Hello, uploader!"})