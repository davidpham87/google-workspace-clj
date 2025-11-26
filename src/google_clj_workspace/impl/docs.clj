(ns google-clj-workspace.impl.docs
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://docs.googleapis.com/")

(def ops
  {[:documents :get] {:method "GET" :path "v1/documents/{documentId}"}
   [:documents :create] {:method "POST" :path "v1/documents"}
   [:documents :batch-update] {:method "POST" :path "v1/documents/{documentId}:batchUpdate"}})

(defn invoke-docs-api
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

(defmacro ^:private def-docs-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:docs ~resource ~op]
              [d# p# o#]
              (invoke-docs-api d# p# o#))))))

(def-docs-dispatch-methods)
