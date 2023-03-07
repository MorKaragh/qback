(ns qback.controller.main-controller
  (:require [qback.middleware.utilitary-middleware :refer [logger]]
            [qback.avatar.avatar-handlers :as avatar]
            [reitit.ring :as rering]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :as resp]))

(def controller
  (rering/ring-handler
   (rering/router
    ["/" {:middleware [wrap-params wrap-keyword-params logger]}
     ["favicon.ico" (fn [_] (resp/resource-response "favicon.ico"))]
     ["cat" avatar/cat-avatar-resp]
     ["pixel-m" avatar/pixel-avatar-resp]
     ["pixel-f" avatar/pixel-avatar-resp-f]])))
