(ns middleware.utilitary-middleware
  (:require [clojure.pprint :as pprint]))

(defn logger [handler]
  (fn [request]
    (let [response (handler request)]
      (println "----------- REQUEST START -----------")
      (println (pprint/pprint request))
      (println "----------- REQUEST STOP -----------")
      response)))