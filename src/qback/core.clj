(ns qback.core
  (:gen-class)
  (:require [qback.controller.main-controller :as controller]
            [ring.adapter.jetty :as jetty]
            [clojure.edn :as edn]))

(def properties
  (edn/read-string (slurp "resources/properties.edn")))

(defn -main 
  [& args])

