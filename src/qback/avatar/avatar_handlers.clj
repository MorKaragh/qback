(ns qback.avatar.avatar-handlers 
  (:require [qback.avatar.avatar-generator :as avatar]
            [qback.utils.response-utils :as resp]))

(defn avatar-resp [factory]
  (fn [{{:keys [seed]} :params}]
    (println "getting avatar")
    (let [image-data (factory (Long/valueOf seed))]
      (resp/png image-data))))

(def pixel-avatar-resp
  (avatar-resp avatar/pixel-avatar))

(def pixel-avatar-resp-f
  (avatar-resp avatar/pixel-avatar-f))

(def cat-avatar-resp
  (avatar-resp avatar/cat-avatar))

