(ns google-clj-workspace.keep
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]))

(def discovery-url "https://keep.googleapis.com/$discovery/rest?version=v1")

(def discovery-data (discovery/parse-discovery-schema discovery-url))

(def registry
  {"ListNotesResponse"
   [:map
    {:closed false}
    [:notes {:optional true} [:vector [:ref "Note"]]]
    [:nextPageToken {:optional true} :string]],
   "BatchCreatePermissionsRequest"
   [:map
    {:closed false}
    [:requests
     {:optional true}
     [:vector [:ref "CreatePermissionRequest"]]]],
   "Empty" [:map {:closed false}],
   "Attachment"
   [:map
    {:closed false}
    [:name {:optional true} :string]
    [:mimeType {:optional true} [:vector :string]]],
   "Permission"
   [:map
    {:closed false}
    [:name {:optional true} :string]
    [:role {:optional true} [:enum "ROLE_UNSPECIFIED" "OWNER" "WRITER"]]
    [:email {:optional true} :string]
    [:user {:optional true} [:ref "User"]]
    [:group {:optional true} [:ref "Group"]]
    [:family {:optional true} [:ref "Family"]]
    [:deleted {:optional true} :boolean]],
   "Note"
   [:map
    {:closed false}
    [:permissions {:optional true} [:vector [:ref "Permission"]]]
    [:trashTime {:optional true} :string]
    [:name {:optional true} :string]
    [:attachments {:optional true} [:vector [:ref "Attachment"]]]
    [:createTime {:optional true} :string]
    [:title {:optional true} :string]
    [:updateTime {:optional true} :string]
    [:trashed {:optional true} :boolean]
    [:body {:optional true} [:ref "Section"]]],
   "User" [:map {:closed false} [:email {:optional true} :string]],
   "Family" [:map {:closed false}],
   "Section"
   [:map
    {:closed false}
    [:text {:optional true} [:ref "TextContent"]]
    [:list {:optional true} [:ref "ListContent"]]],
   "ListContent"
   [:map
    {:closed false}
    [:listItems {:optional true} [:vector [:ref "ListItem"]]]],
   "Group" [:map {:closed false} [:email {:optional true} :string]],
   "BatchCreatePermissionsResponse"
   [:map
    {:closed false}
    [:permissions {:optional true} [:vector [:ref "Permission"]]]],
   "TextContent" [:map {:closed false} [:text {:optional true} :string]],
   "ListItem"
   [:map
    {:closed false}
    [:childListItems {:optional true} [:vector [:ref "ListItem"]]]
    [:text {:optional true} [:ref "TextContent"]]
    [:checked {:optional true} :boolean]],
   "CreatePermissionRequest"
   [:map
    {:closed false}
    [:parent {:optional true} :string]
    [:permission {:optional true} [:ref "Permission"]]],
   "BatchDeletePermissionsRequest"
   [:map {:closed false} [:names {:optional true} [:vector :string]]]})

(client/def-api discovery-data)

;; Aliases for user-friendly usage
(def find-notes notes-list)
(def read-note notes-get)
(def create-note notes-create)
