(ns qback.controller.handlers
  (:require [clojure.pprint :as pprint]
            [ring.middleware.params :refer [wrap-params]]
            [qback.avatar.avatar-generator :as avatar]))


(defn avatar-resp [request]
  (pprint/pprint request)
  (println "===========================================")
  (let [image-data (avatar/cat-avatar (Long/valueOf (get-in request [:params "seed"])))]
    {:status 200
     :headers {"Content-Type" "image/png"}
     :body image-data}))

(def handler
  (-> avatar-resp
      (wrap-params)))
