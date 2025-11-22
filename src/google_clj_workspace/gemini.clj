(ns google-clj-workspace.gemini
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]
            [google-clj-workspace.schema.gemini :as schema]))

(def discovery-url "https://generativelanguage.googleapis.com/$discovery/rest?version=v1beta")

(def discovery-data (discovery/parse-discovery-schema discovery-url))



(def registry schema/registry)

(client/def-api discovery-data)

;; Aliases for user-friendly usage
(def list-models models-list)
(def get-model models-get)
(def generate-content models-generate-content)
