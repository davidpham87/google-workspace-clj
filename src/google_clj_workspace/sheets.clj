(ns google-clj-workspace.sheets
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]
            [google-clj-workspace.schema.sheets :as schema]))

(def discovery-url "https://sheets.googleapis.com/$discovery/rest?version=v4")

(def discovery-data (discovery/parse-discovery-schema discovery-url))



(def registry schema/registry)

(client/def-api discovery-data)

;; Aliases for user-friendly usage
(def get-spreadsheet spreadsheets-get)
(def create-spreadsheet spreadsheets-create)
