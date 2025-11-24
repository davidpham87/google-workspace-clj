(ns google-clj-workspace.docs
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]
            [google-clj-workspace.schema.docs :as schema]))

(def discovery-url "https://docs.googleapis.com/$discovery/rest?version=v1")

(def discovery-data (discovery/parse-discovery-schema discovery-url))

(def registry schema/registry)

(client/def-api discovery-data)

;; Aliases for user-friendly usage
(def get-document documents-get)
(def create-document documents-create)
