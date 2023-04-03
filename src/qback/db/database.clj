(ns qback.db.database
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as pool]))

(def datasource-options {:adapter "h2"
                         :url     "jdbc:h2:~/test"})

(def h2-settings
  {:classname   "org.h2.Driver"
   :subprotocol "h2:file:./data/qdata"
   :subname     "demo;DB_CLOSE_DELAY=-1"
   :user        "sa"
   :password    ""})

(jdbc/db-do-commands
 h2-settings
 (jdbc/create-table-ddl :filetable
                        [:name "varchar(3200)"]
                        [:path "varchar(3200)"]
                        [:origname "varchar(3200)"]))

(jdbc/insert!
 h2-settings
 :filetable {:name "superfile"
             :path "superpath"
             :origname "superorigin"})

(jdbc/query h2-settings ["select * from filetable limit 10"])

(defonce datasource
  (delay (pool/make-datasource datasource-options)))

(jdbc/with-db-connection [conn {:datasource @datasource}]
  (let [rows (jdbc/query conn "SELECT 0")]
    (println rows)))

(pool/close-datasource @datasource)
