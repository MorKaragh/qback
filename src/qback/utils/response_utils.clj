(ns qback.utils.response-utils)

(defn png [pic-bytes]
  {:status 200
   :headers {"Content-Type" "image/png"}
   :body pic-bytes})