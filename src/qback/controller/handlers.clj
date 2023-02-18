(ns qback.controller.handlers)

(def last-request (atom {}))

(defn what-is-my-ip [request]
  (println "====================")
  (println (str request))
  (println "====================")
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "My IP is: " (:remote-addr request))})

