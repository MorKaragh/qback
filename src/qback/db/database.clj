(ns qback.db.database
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as pool]
            [qback.utils.properties :refer [props]]))


(defonce datasource
  (delay (pool/make-datasource (:hikari-settings props))))

(try 
  (jdbc/with-db-connection [conn {:datasource @datasource}]
   (jdbc/db-do-commands
    conn
    (jdbc/create-table-ddl :images
                           [:id "BIGINT AUTO_INCREMENT PRIMARY KEY"]
                           [:name "VARCHAR(100)"]
                           [:path "VARCHAR(200)"])))
  (catch Exception e (println "table already exists")))

(defn insert! [table options]
  (println options)
  (jdbc/with-db-connection [conn {:datasource @datasource}]
    (jdbc/insert! conn table options)))

(jdbc/with-db-connection [conn {:datasource @datasource}]
  (jdbc/query
   conn
   ["select * from images"]))

;; (pool/close-datasource @datasource)
