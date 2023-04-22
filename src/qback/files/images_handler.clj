(ns qback.files.images-handler
  (:require [clojure.java.io :as io]
            [qback.files.image-processing :as img]
            [qback.files.images-db :as db]
            [qback.utils.file-utils :as futils]
            [qback.utils.properties :refer [props]]
            [qback.utils.response-utils :as resp]
            [clojure.string :as str]))


(defn- get-dirs [s]
  (str (subs s 0 2) "/" (subs s 2 4) "/"))

(defn- save-image [dest-file dest-file-path file file-name img-type hash]
  (when (futils/ensure-file-exists dest-file)
    (io/copy file dest-file)
    (db/save-file-info
     {:name file-name
      :path dest-file-path
      :type (name img-type)}))
  (resp/json {:hash hash}))

(def testr "test.jpg")
(subs testr (str/index-of testr "." ))

(defn image-upl-handler [req]
  (let [file-data (get-in req [:multipart-params "uplfile"])
        file (:tempfile file-data)
        hash (futils/md5 file)
        file-name (:filename file-data)
        dest-file-path (str (:img-path props) (get-dirs hash) hash)
        dest-file (io/file dest-file-path)
        img-type (img/image-type file)
        is-image (not (nil? img-type))]
    (if is-image
      (save-image dest-file dest-file-path file file-name img-type hash)
      (resp/json {:error "bad image"}))))


(defn get-image-handler [{{:keys [hash]} :path-params}] 
  {:status 200 :body hash})


