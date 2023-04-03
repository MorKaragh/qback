(ns qback.avatar.avatar-generator
  (:import (com.talanlabs.avatargenerator.eightbit EightBitAvatar))
  (:import (com.talanlabs.avatargenerator.cat CatAvatar)))

(def ^{:private true} cat-factory (.build (CatAvatar/newAvatarBuilder)))
(def ^{:private true} pixel-factory-m (.build (EightBitAvatar/newMaleAvatarBuilder)))
(def ^{:private true} pixel-factory-f (.build (EightBitAvatar/newFemaleAvatarBuilder)))

(defn cat-avatar [seed]
  (.createAsPngBytes cat-factory seed))

(defn pixel-avatar [seed]
  (.createAsPngBytes pixel-factory-m seed))

(defn pixel-avatar-f [seed]
  (.createAsPngBytes pixel-factory-f seed))

