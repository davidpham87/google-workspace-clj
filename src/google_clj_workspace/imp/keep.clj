(ns google-clj-workspace.imp.keep
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def ^:private base-url "https://keep.googleapis.com/")

(def ^:private ops
  {[:notes :create] {:method "POST" :path "v1/notes"}
   [:notes :get] {:method "GET" :path "v1/{+name}"}
   [:notes :list] {:method "GET" :path "v1/notes"}
   [:notes :delete] {:method "DELETE" :path "v1/{+name}"}
   [:permissions :batch-create] {:method "POST" :path "v1/{+parent}/permissions:batchCreate"}
   [:permissions :batch-delete] {:method "POST" :path "v1/{+parent}/permissions:batchDelete"}
   [:media :download] {:method "GET" :path "v1/{+name}"}})

(defn- invoke-keep-api
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

(defmacro ^:private def-keep-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:keep ~resource ~op]
              [d# p# o#]
              (invoke-keep-api d# p# o#))))))

(def-keep-dispatch-methods)
