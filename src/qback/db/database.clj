(ns qback.db.database
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as pool]
            [qback.utils.properties :refer [props]]))


(defonce datasource
  (delay (pool/make-datasource (:hikari-settings props))))

(defn create-table 
  [table & fields]
  (try
    (jdbc/with-db-connection [conn {:datasource @datasource}]
      (jdbc/db-do-commands
       conn
       (apply jdbc/create-table-ddl table (concat fields))))
    (catch Exception e (println "table" (name table) "already exists"))))

(defn insert! [table options]
  (jdbc/with-db-connection [conn {:datasource @datasource}]
    (jdbc/insert! conn table options)))

(defn select [query]
  (jdbc/with-db-connection [conn {:datasource @datasource}]
    (jdbc/query conn query)))

(comment
  (jdbc/with-db-connection [conn {:datasource @datasource}]
    (jdbc/query
     conn
     ["select * from images"])))

;; (pool/close-datasource @datasource)
