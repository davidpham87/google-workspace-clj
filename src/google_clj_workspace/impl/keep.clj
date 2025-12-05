(ns google-clj-workspace.impl.keep
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://keep.googleapis.com/")

(def ops
   ;; [:map
   ;;  {:closed false}
   ;;  [:permissions {:optional true} [:vector [:ref "Permission"]]]
   ;;  [:trashTime {:optional true} :string]
   ;;  [:name {:optional true} :string]
   ;;  [:attachments {:optional true} [:vector [:ref "Attachment"]]]
   ;;  [:createTime {:optional true} :string]
   ;;  [:title {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:trashed {:optional true} :boolean]
   ;;  [:body {:optional true} [:ref "Section"]]]
  {[:notes :create] {:method "POST" :path "v1/notes"}
   [:notes :get] {:method "GET" :path "v1/{+name}"}
   [:notes :list] {:method "GET" :path "v1/notes"}
   [:notes :delete] {:method "DELETE" :path "v1/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:requests
   ;;   {:optional true}
   ;;   [:vector [:ref "CreatePermissionRequest"]]]]
   [:permissions :batch-create] {:method "POST" :path "v1/{+parent}/permissions:batchCreate"}
   ;; [:map {:closed false} [:names {:optional true} [:vector :string]]]
   [:permissions :batch-delete] {:method "POST" :path "v1/{+parent}/permissions:batchDelete"}
   [:media :download] {:method "GET" :path "v1/{+name}"}})

(defn invoke-keep-api
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