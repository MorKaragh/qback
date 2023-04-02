(ns qback.utils.properties
  (:require [clojure.edn :as edn]))

(def props
  (edn/read-string (slurp "resources/properties.edn")))
