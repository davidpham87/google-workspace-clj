(ns google-clj-workspace.keep
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.impl.keep]))

(defn notes
  "Manages notes.
   - op: :create, :get, :list, :delete

  Examples:
  ;; list notes
  (notes {} {:op :list})

  ;; create a note
  (notes {} {:op :create
             :body {:title \"Foo\"
                    :body {:text {:text \"Bar\"}}}})
  ;; get a note
  (notes {:name \"notes/123\"} {:op :get})

  ;; delete a note
  (notes {:name \"notes/123\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:keep :notes (:op opts)] params opts))

(defn permissions
  "Manages permissions.
   - op: :batch-create, :batch-delete

  Examples:
  ;; batch create permissions
  (permissions {:parent \"notes/123\"}
               {:op :batch-create
                :body {:requests [{:permission {:grantee {:user {:emailAddress \"user@example.com\"}}
                                               :role \"writer\"}}]}})
  ;; batch delete permissions
  (permissions {:parent \"notes/123\"}
               {:op :batch-delete
                :body {:names [\"notes/123/permissions/456\"]}})"
  [params & [opts]]
  (core/dispatch [:keep :permissions (:op opts)] params opts))

(defn media
  "Manages media.
   - op: :download

  Examples:
  ;; download media
  (media {:name \"notes/123/attachments/456\"} {:op :download})"
  [params & [opts]]
  (core/dispatch [:keep :media (:op opts)] params opts))
