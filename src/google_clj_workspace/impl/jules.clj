(ns google-clj-workspace.impl.jules
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://jules.googleapis.com/")

(def ops
  {[:sources :get] {:method "GET" :path "v1alpha/{+name}"}
   [:sources :list] {:method "GET" :path "v1alpha/sources"}
   [:sessions :list] {:method "GET" :path "v1alpha/sessions"}
   [:sessions :send-message] {:method "POST" :path "v1alpha/{+session}:sendMessage"}
   [:sessions :get] {:method "GET" :path "v1alpha/{+name}"}
   [:sessions :approve-plan] {:method "POST" :path "v1alpha/{+session}:approvePlan"}
   [:sessions :create] {:method "POST" :path "v1alpha/sessions"}
   [:sessions :delete] {:method "DELETE" :path "v1alpha/{+name}"}
   [:activities :get] {:method "GET" :path "v1alpha/{+name}"}
   [:activities :list] {:method "GET" :path "v1alpha/{+parent}/activities"}})

(defn invoke-jules-api
  [dispatch-val params opts]
  (let [[_service resource op] dispatch-val
        {:keys [method path]} (get ops [resource op])
        [interpolated-path used-params] (util/interpolate-path path params)]
    (client/invoke-endpoint
     method
     interpolated-path
     (apply dissoc params used-params)
     opts
     base-url)))

(defmacro ^:private def-jules-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:jules ~resource ~op]
              [d# p# o#]
              (invoke-jules-api d# p# o#))))))

(def-jules-dispatch-methods)
