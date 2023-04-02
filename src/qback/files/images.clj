(ns qback.files.images
  (:require [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [qback.utils.properties :refer [props]]))

(defn upload-handler [req]
  (let [file-data (get-in req [:multipart-params "uplfile"])
        file-name (:filename file-data)
        file (:tempfile file-data)
        dest (io/file (str (:img-path props) file-name))]
    (io/copy file dest)
    {:status 200
     :body "Hello, uploader!"})
  )