(ns qback.controller.routes
  (:require [muuntaja.core :as m]
            [reitit.coercion.spec]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]))


(def routes
  (ring/ring-handler
   (ring/router
    [["/test"
      {:get {:summary "test method"
             :parameters {:query {:x int?
                                  :y int?}}
             :response {200 {:body {:total int?}}}
             :handler (fn [{{{:keys [x y]} :query} :parameters}]
                        {:status 200
                         :body {:total (+ x y)}})}}]])))
(def app
  (ring/ring-handler
   (ring/router
    ["/api"
     ["/math" {:get {:parameters {:query {:x int?, :y int?}}
                     :responses  {200 {:body {:total int?}}}
                     :handler    (fn [{{{:keys [x y]} :query} :parameters}]
                                   {:status 200
                                    :body   {:total (+ x y)}})}}]]
      ;; router data affecting all routes
    {:data {:coercion   reitit.coercion.spec/coercion
            :muuntaja   m/instance
            :middleware [parameters/parameters-middleware
                         rrc/coerce-request-middleware
                         muuntaja/format-response-middleware
                         rrc/coerce-response-middleware]}})))

(app {:request-method :get
      :uri "/api/math"
      :query-params {:x "1", :y "2"}})
