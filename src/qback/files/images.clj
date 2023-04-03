(ns qback.files.images
  (:require [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [qback.utils.properties :refer [props]]))



(defn image-upl-handler [req]
  (let [file-data (get-in req [:multipart-params "uplfile"])
        file (:tempfile file-data)
        file-name (:filename file-data)
        dest-file-name (str (:img-path props) file-name)
        dest-file (io/file dest-file-name)]
    
    (io/copy file dest-file)
    
    {:status 200
     :body {:filename dest-file-name}})
  )