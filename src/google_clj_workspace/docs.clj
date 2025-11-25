(ns google-clj-workspace.docs
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.docs]))

(defn documents
  "Manages documents.
  - op: :get, :create, :batch-update"
  [params & [opts]]
  (core/dispatch [:docs :documents (:op opts)] params opts))
