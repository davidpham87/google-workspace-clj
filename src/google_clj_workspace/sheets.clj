(ns google-clj-workspace.sheets
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.sheets]))

(defn spreadsheets
  "Manages spreadsheets.
  - op: :create, :get, :get-by-data-filter, :batch-update"
  [params & [opts]]
  (core/dispatch [:sheets :spreadsheets (:op opts)] params opts))

(defn values
  "Manages values.
  - op: :get, :append, :update, :batch-clear-by-data-filter, :batch-update, :batch-get, :batch-get-by-data-filter, :batch-update-by-data-filter, :batch-clear, :clear"
  [params & [opts]]
  (core/dispatch [:sheets :values (:op opts)] params opts))

(defn developer-metadata
  "Manages developer metadata.
  - op: :get, :search"
  [params & [opts]]
  (core/dispatch [:sheets :developer-metadata (:op opts)] params opts))

(defn sheets
  "Manages sheets.
  - op: :copy-to"
  [params & [opts]]
  (core/dispatch [:sheets :sheets (:op opts)] params opts))
