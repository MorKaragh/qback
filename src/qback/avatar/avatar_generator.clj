(ns qback.avatar.avatar-generator
  (:import (com.talanlabs.avatargenerator.eightbit EightBitAvatar))
  (:import (com.talanlabs.avatargenerator.cat CatAvatar)))

(def ^{:private true} cat-factory (.build (CatAvatar/newAvatarBuilder)))
(def ^{:private true} pixel-factory (.build (EightBitAvatar/newMaleAvatarBuilder)))

(defn cat-avatar [seed]
  (.createAsPngBytes cat-factory seed))

(defn pixel-avatar [seed]
  (.createAsPngBytes pixel-factory seed))