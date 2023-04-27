(ns qback.controller.main-controller
  (:require [qback.avatar.avatar-handlers :as avatar]
            [qback.blog.blog :as blg]
            [qback.middleware.utilitary-middleware :refer [cors-mw logger]]
            [qback.photohost.images-handler :refer [get-image-handler
                                                    get-image-hashes 
                                                    image-upl-handler]]
            [reitit.ring :as rering]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :as resp]))

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
   ["images"
    ["" {:get get-image-hashes
         :post image-upl-handler
         :middleware [wrap-multipart-params]}]
    ["/:hash" {:get get-image-handler}]]])

(def controller
  (rering/ring-handler
   (rering/router routes)))
