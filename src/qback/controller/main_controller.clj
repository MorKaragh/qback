(ns qback.controller.main-controller
  (:require [qback.avatar.avatar-handlers :as avatar]
            [qback.blog.blog :as blg]
            [qback.files.images :refer [upload-handler]]
            [qback.middleware.utilitary-middleware :refer [cors-mw logger]]
            [reitit.ring :as rering]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.util.response :as resp]))

(defn- opt-handler [arg1])

(def ^:private routes
  ["/" {:middleware [wrap-params
                     wrap-keyword-params
                     cors-mw
                    ;;  logger
                     ]}
   ["favicon.ico" (fn [_] (resp/resource-response "favicon.ico"))]
   ["avatar"
    ["/cat" avatar/cat-avatar-resp]
    ["/pixel-m" avatar/pixel-avatar-resp]
    ["/pixel-f" avatar/pixel-avatar-resp-f]]
   ["blog"
    ["/posts" {:get blg/get-handler}]]
   ["images" {:post upload-handler
              :middleware [wrap-multipart-params]}]])


(def controller
  (rering/ring-handler
   (rering/router routes)))
