(ns google-clj-workspace.imp.sheets
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.client :as client]))

;;; Spreadsheets
(defmethod core/dispatch [:sheets :spreadsheets :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :spreadsheets :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :spreadsheets :get-by-data-filter]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}:getByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :spreadsheets :batch-update]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}:batchUpdate"
   params
   opts
   "https://sheets.googleapis.com/"))

;;; Values
(defmethod core/dispatch [:sheets :values :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}/values/{range}"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :append]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values/{range}:append"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :update]
  [_ params opts]
  (client/invoke-endpoint
   "PUT"
   "v4/spreadsheets/{spreadsheetId}/values/{range}"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :batch-clear-by-data-filter]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchClearByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :batch-update]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchUpdate"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :batch-get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}/values:batchGet"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :batch-get-by-data-filter]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchGetByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :batch-update-by-data-filter]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchUpdateByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :batch-clear]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchClear"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :values :clear]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values/{range}:clear"
   params
   opts
   "https://sheets.googleapis.com/"))

;;; Developer Metadata
(defmethod core/dispatch [:sheets :developer-metadata :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}"
   params
   opts
   "https://sheets.googleapis.com/"))

(defmethod core/dispatch [:sheets :developer-metadata :search]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/developerMetadata:search"
   params
   opts
   "https://sheets.googleapis.com/"))

;;; Sheets
(defmethod core/dispatch [:sheets :sheets :copy-to]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/sheets/{sheetId}:copyTo"
   params
   opts
   "https://sheets.googleapis.com/"))
