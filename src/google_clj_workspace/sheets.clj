(ns
 google-clj-workspace.sheets
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 spreadsheets
 "Available operations: :batch-update, :create, :get, :get-by-data-filter"
 [params & [opts]]
 (case
  (:op opts)
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-update
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}:batchUpdate"
   params
   opts
   "https://sheets.googleapis.com/")
  :get-by-data-filter
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}:getByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}"
   params
   opts
   "https://sheets.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 developer-metadata
 "Available operations: :get, :search"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}"
   params
   opts
   "https://sheets.googleapis.com/")
  :search
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/developerMetadata:search"
   params
   opts
   "https://sheets.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 values
 "Available operations: :append, :batch-clear, :batch-clear-by-data-filter, :batch-get, :batch-get-by-data-filter, :batch-update, :batch-update-by-data-filter, :clear, :get, :update"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}/values/{range}"
   params
   opts
   "https://sheets.googleapis.com/")
  :append
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values/{range}:append"
   params
   opts
   "https://sheets.googleapis.com/")
  :update
  (google-clj-workspace.client/invoke-endpoint
   "PUT"
   "v4/spreadsheets/{spreadsheetId}/values/{range}"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-clear-by-data-filter
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchClearByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-update
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchUpdate"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v4/spreadsheets/{spreadsheetId}/values:batchGet"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-get-by-data-filter
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchGetByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-update-by-data-filter
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchUpdateByDataFilter"
   params
   opts
   "https://sheets.googleapis.com/")
  :batch-clear
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values:batchClear"
   params
   opts
   "https://sheets.googleapis.com/")
  :clear
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/values/{range}:clear"
   params
   opts
   "https://sheets.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 sheets
 "Available operations: :copy-to"
 [params & [opts]]
 (case
  (:op opts)
  :copy-to
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v4/spreadsheets/{spreadsheetId}/sheets/{sheetId}:copyTo"
   params
   opts
   "https://sheets.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

