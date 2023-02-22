(ns qback.core
  (:gen-class)
  (:require [qback.controller.handlers :as controller]
            [ring.adapter.jetty :as jetty]))

(defn -main 
  [& args]
  (jetty/run-jetty controller/my-ip {:port 3000
                                             :join? false}))

