(ns qback.middleware.utilitary-middleware
  (:require [clojure.pprint :as pprint]
            [ring.util.response :as response]))

(defn logger [handler]
  (fn [request]
    (let [response (handler request)]
      (println "----------- REQUEST START -----------")
      (println (pprint/pprint request))
      (println "----------- REQUEST STOP -----------")
      (println "----------- RESPONSE START -----------")
      (println (pprint/pprint response))
      (println "----------- RESPONNSE STOP -----------") 
      response)))


(def hdr {"Access-Control-Allow-Origin" "*"
          "Access-Control-Allow-Headers" "*"
          "Access-Control-Allow-Methods" "POST, GET, OPTIONS"})

(defn cors-mw [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc response :headers (merge (:headers response) hdr)))))
