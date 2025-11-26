(ns google-clj-workspace.keep
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.keep]))

(defn notes
  "Manages notes.
   - op: :create, :get, :list, :delete"
  [params & [opts]]
  (core/dispatch [:keep :notes (:op opts)] params opts))

(defn permissions
  "Manages permissions.
   - op: :batch-create, :batch-delete"
  [params & [opts]]
  (core/dispatch [:keep :permissions (:op opts)] params opts))

(defn media
  "Manages media.
   - op: :download"
  [params & [opts]]
  (core/dispatch [:keep :media (:op opts)] params opts))
