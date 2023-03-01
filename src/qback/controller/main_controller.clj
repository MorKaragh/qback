(ns qback.controller.main-controller
  (:require [qback.avatar.avatar-handlers :as avatar]
            [reitit.ring :as rering]
            [ring.util.response :as resp]
            [ring.middleware.params :refer [wrap-params]]))

(def controller
  (rering/ring-handler
   (rering/router
    ["/" {:middleware [wrap-params]}
     ["cat" avatar/cat-avatar-resp]
     ["pixel" avatar/pixel-avatar-resp]])))
