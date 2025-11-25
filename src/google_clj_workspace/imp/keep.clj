(ns google-clj-workspace.imp.keep
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.client :as client]))

;;; Notes
(defmethod core/dispatch [:keep :notes :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/notes"
   params
   opts
   "https://keep.googleapis.com/"))

(defmethod core/dispatch [:keep :notes :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/{+name}"
   params
   opts
   "https://keep.googleapis.com/"))

(defmethod core/dispatch [:keep :notes :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/notes"
   params
   opts
   "https://keep.googleapis.com/"))

(defmethod core/dispatch [:keep :notes :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1/{+name}"
   params
   opts
   "https://keep.googleapis.com/"))

;;; Permissions
(defmethod core/dispatch [:keep :permissions :batch-create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/{+parent}/permissions:batchCreate"
   params
   opts
   "https://keep.googleapis.com/"))

(defmethod core/dispatch [:keep :permissions :batch-delete]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/{+parent}/permissions:batchDelete"
   params
   opts
   "https://keep.googleapis.com/"))

;;; Media
(defmethod core/dispatch [:keep :media :download]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/{+name}"
   params
   opts
   "https://keep.googleapis.com/"))
