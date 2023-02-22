(ns qback.avatar.avatar-generator
  (:import (com.talanlabs.avatargenerator Avatar))
  (:import (com.talanlabs.avatargenerator.cat CatAvatar)))

(def ^{:private true} cat-factory (.build (CatAvatar/newAvatarBuilder)))

(defn cat-avatar [seed]
  (.createAsPngBytes cat-factory seed))
