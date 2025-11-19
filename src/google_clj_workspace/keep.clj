(ns google-clj-workspace.keep
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]))

(def discovery-url "https://keep.googleapis.com/$discovery/rest?version=v1")

(def discovery-data (discovery/parse-discovery-schema discovery-url))

(client/def-api discovery-data)

;; Aliases for user-friendly usage
(def find-notes notes-list)
(def read-note notes-get)
(def create-note notes-create)
