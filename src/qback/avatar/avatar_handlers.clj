(ns qback.avatar.avatar-handlers 
  (:require [qback.avatar.avatar-generator :as avatar]
            [qback.utils.response-utils :as resp]))


(defn cat-avatar-resp [{:keys [params]}]
  (let [image-data (avatar/cat-avatar (Long/valueOf (get params "seed")))]
    (resp/png image-data)))

(defn pixel-avatar-resp [{:keys [params]}]
  (let [image-data (avatar/pixel-avatar (Long/valueOf (get params "seed")))]
    (resp/png image-data)))
