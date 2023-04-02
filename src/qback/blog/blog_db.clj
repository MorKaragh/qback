(ns qback.blog.blog-db
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as pool]))

(jdbc/create-table-ddl
 :blog-posts
 [:id "bigint auto_increment"]
 [:title "varchar"]
 [:body "varchar"])


