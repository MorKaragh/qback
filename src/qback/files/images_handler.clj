(ns qback.files.images-handler
  (:require [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [qback.files.images-db :as db]
            [qback.utils.properties :refer [props]]
            [qback.utils.file-utils :as futils]))


(defn get-dirs [s]
  (str (subs s 0 2) "/" (subs s 2 4) "/"))

(defn image-upl-handler [req]
  (let [file-data (get-in req [:multipart-params "uplfile"])
        file (:tempfile file-data)
        hash (futils/md5 file)
        file-name (:filename file-data)
        dest-file-path (str (:img-path props) (get-dirs hash) hash)
        dest-file (io/file dest-file-path)] 
    (when (futils/ensure-file-exists dest-file)
      (io/copy file dest-file)
      (db/save-file-info {:name file-name :path dest-file-path}))
    {:status 200
     :body {:filename dest-file-path}}))


(defn get-image-handler [{{:keys [hash]} :path-params}] 
  {:status 200 :body hash})


