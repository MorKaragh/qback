(ns qback.db.database
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as pool]))

(def datasource-options {:adapter "h2"
                         :url     "jdbc:h2:~/test"})

(def demo-settings
  {:classname   "org.h2.Driver"
   :subprotocol "h2:mem"
   :subname     "demo;DB_CLOSE_DELAY=-1"
   :user        "sa"
   :password    ""})


(jdbc/db-do-commands
 demo-settings
 (jdbc/create-table-ddl :filetable
                        [:name "varchar(3200)"]
                        [:path "varchar(3200)"]
                        [:origname "varchar(3200)"]))

(jdbc/insert!
 demo-settings
 :filetable {:name "superfile"
             :path "superpath"
             :origname "superorigin"})

(jdbc/query demo-settings ["select * from filetable limit 10"])

(defonce datasource
  (delay (pool/make-datasource datasource-options)))

(jdbc/with-db-connection [conn {:datasource @datasource}]
  (let [rows (jdbc/query conn "SELECT 0")]
    (println rows)))

(pool/close-datasource @datasource)
