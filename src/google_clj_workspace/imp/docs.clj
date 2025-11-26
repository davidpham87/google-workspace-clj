(ns google-clj-workspace.imp.docs
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.client :as client]))

(defmethod core/dispatch [:docs :documents :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/documents/{documentId}"
   params
   opts
   "https://docs.googleapis.com/"))

(defmethod core/dispatch [:docs :documents :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/documents"
   params
   opts
   "https://docs.googleapis.com/"))

(defmethod core/dispatch [:docs :documents :batch-update]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/documents/{documentId}:batchUpdate"
   params
   opts
   "https://docs.googleapis.com/"))
