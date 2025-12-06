(ns google-clj-workspace.impl.keep
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://keep.googleapis.com/")


(def registry
{"ListNotesResponse"
 [:map
  {:closed true}
  [:notes {:optional true} [:vector [:ref "Note"]]]
  [:nextPageToken {:optional true} :string]],
 "BatchCreatePermissionsRequest"
 [:map
  {:closed true}
  [:requests
   {:optional true}
   [:vector [:ref "CreatePermissionRequest"]]]],
 "Empty" [:map {:closed true}],
 "Attachment"
 [:map
  {:closed true}
  [:mimeType {:optional true} [:vector :string]]
  [:name {:optional true} :string]],
 "Permission"
 [:map
  {:closed true}
  [:family {:optional true} [:ref "Family"]]
  [:deleted {:optional true} :boolean]
  [:role {:optional true} [:enum "ROLE_UNSPECIFIED" "OWNER" "WRITER"]]
  [:user {:optional true} [:ref "User"]]
  [:group {:optional true} [:ref "Group"]]
  [:email {:optional true} :string]
  [:name {:optional true} :string]],
 "Note"
 [:map
  {:closed true}
  [:permissions {:optional true} [:vector [:ref "Permission"]]]
  [:trashTime {:optional true} :string]
  [:name {:optional true} :string]
  [:attachments {:optional true} [:vector [:ref "Attachment"]]]
  [:createTime {:optional true} :string]
  [:title {:optional true} :string]
  [:updateTime {:optional true} :string]
  [:trashed {:optional true} :boolean]
  [:body {:optional true} [:ref "Section"]]],
 "User" [:map {:closed true} [:email {:optional true} :string]],
 "Family" [:map {:closed true}],
 "Section"
 [:map
  {:closed true}
  [:list {:optional true} [:ref "ListContent"]]
  [:text {:optional true} [:ref "TextContent"]]],
 "ListContent"
 [:map
  {:closed true}
  [:listItems {:optional true} [:vector [:ref "ListItem"]]]],
 "Group" [:map {:closed true} [:email {:optional true} :string]],
 "BatchCreatePermissionsResponse"
 [:map
  {:closed true}
  [:permissions {:optional true} [:vector [:ref "Permission"]]]],
 "TextContent" [:map {:closed true} [:text {:optional true} :string]],
 "ListItem"
 [:map
  {:closed true}
  [:text {:optional true} [:ref "TextContent"]]
  [:childListItems {:optional true} [:vector [:ref "ListItem"]]]
  [:checked {:optional true} :boolean]],
 "CreatePermissionRequest"
 [:map
  {:closed true}
  [:parent {:optional true} :string]
  [:permission {:optional true} [:ref "Permission"]]],
 "BatchDeletePermissionsRequest"
 [:map {:closed true} [:names {:optional true} [:vector :string]]]}
)

(def ops
{[:notes :create]
 {:method "POST", :path "v1/notes", :schema [:ref "Note"]},
 [:notes :get] {:method "GET", :path "v1/{+name}"},
 [:notes :list] {:method "GET", :path "v1/notes"},
 [:notes :delete] {:method "DELETE", :path "v1/{+name}"},
 [:permissions :batch-create]
 {:method "POST",
  :path "v1/{+parent}/permissions:batchCreate",
  :schema [:ref "BatchCreatePermissionsRequest"]},
 [:permissions :batch-delete]
 {:method "POST",
  :path "v1/{+parent}/permissions:batchDelete",
  :schema [:ref "BatchDeletePermissionsRequest"]},
 [:media :download] {:method "GET", :path "v1/{+name}"}}
)

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
