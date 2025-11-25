(ns google-clj-workspace.imp.forms
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.client :as client]))

;;; Forms
(defmethod core/dispatch [:forms :forms :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/forms"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :forms :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/forms/{formId}"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :forms :batch-update]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/forms/{formId}:batchUpdate"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :forms :set-publish-settings]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/forms/{formId}:setPublishSettings"
   params
   opts
   "https://forms.googleapis.com/"))

;;; Responses
(defmethod core/dispatch [:forms :responses :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/forms/{formId}/responses/{responseId}"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :responses :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/forms/{formId}/responses"
   params
   opts
   "https://forms.googleapis.com/"))

;;; Watches
(defmethod core/dispatch [:forms :watches :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/forms/{formId}/watches"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :watches :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1/forms/{formId}/watches"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :watches :renew]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1/forms/{formId}/watches/{watchId}:renew"
   params
   opts
   "https://forms.googleapis.com/"))

(defmethod core/dispatch [:forms :watches :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1/forms/{formId}/watches/{watchId}"
   params
   opts
   "https://forms.googleapis.com/"))
