(ns qback.files.images-db
  (:require [qback.db.database :as db]))

(defn save-file-info 
  "example: (save-file-info {:name \"super_file\" :path \"super_path\"})"
  [file-info]
  (db/insert! :images file-info))

