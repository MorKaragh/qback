(ns qback.photohost.images-db
  (:require [qback.db.database :as db]))

(db/create-table
 :images
 [:id "BIGINT AUTO_INCREMENT PRIMARY KEY"]
 [:name "VARCHAR(100)"]
 [:path "VARCHAR(200)"]
 [:type "VARCHAR(10)"]
 [:hash "VARCHAR(50)"])

(defn save-img-metadata 
  "example: (save-file-info {:name \"super_file\" :path \"super_path\"})"
  [file-info] 
  (db/insert! :images file-info))

(defn get-img-metadata 
  [hash]
  (println "search by hash " hash)
  (first (db/select ["select * from images where hash = ?" hash])))

(defn get-all-images-metadata
  []
  (db/select ["select * from images"]))


