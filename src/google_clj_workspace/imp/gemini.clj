(ns google-clj-workspace.imp.gemini
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.client :as client]))

;;; Permissions
(defmethod core/dispatch [:gemini :permissions :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+parent}/permissions"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :permissions :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :permissions :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+parent}/permissions"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :permissions :patch]
  [_ params opts]
  (client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :permissions :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Dynamic
(defmethod core/dispatch [:gemini :dynamic :generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :dynamic :stream-generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:streamGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; File Search Stores
(defmethod core/dispatch [:gemini :file-search-stores :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/fileSearchStores"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :file-search-stores :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :file-search-stores :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :file-search-stores :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/fileSearchStores"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :file-search-stores :import-file]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+fileSearchStoreName}:importFile"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Media
(defmethod core/dispatch [:gemini :media :upload]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/files"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :media :upload-to-file-search-store]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+fileSearchStoreName}:uploadToFileSearchStore"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Batches
(defmethod core/dispatch [:gemini :batches :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :batches :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :batches :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :batches :cancel]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+name}:cancel"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :batches :update-generate-content-batch]
  [_ params opts]
  (client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}:updateGenerateContentBatch"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :batches :update-embed-content-batch]
  [_ params opts]
  (client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}:updateEmbedContentBatch"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Cached Contents
(defmethod core/dispatch [:gemini :cached-contents :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/cachedContents"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :cached-contents :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/cachedContents"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :cached-contents :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :cached-contents :patch]
  [_ params opts]
  (client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :cached-contents :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Files
(defmethod core/dispatch [:gemini :files :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/files"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :files :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :files :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Corpora
(defmethod core/dispatch [:gemini :corpora :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/corpora"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :corpora :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :corpora :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :corpora :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/corpora"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Tuned Models
(defmethod core/dispatch [:gemini :tuned-models :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :stream-generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:streamGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :patch]
  [_ params opts]
  (client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :async-batch-embed-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:asyncBatchEmbedContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :create]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/tunedModels"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :generate-text]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateText"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :batch-generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/tunedModels"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :tuned-models :transfer-ownership]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+name}:transferOwnership"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Documents
(defmethod core/dispatch [:gemini :documents :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :documents :delete]
  [_ params opts]
  (client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :documents :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+parent}/documents"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Generated Files
(defmethod core/dispatch [:gemini :generated-files :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/generatedFiles"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Models
(defmethod core/dispatch [:gemini :models :batch-embed-text]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchEmbedText"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :embed-text]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:embedText"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :stream-generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:streamGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :async-batch-embed-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:asyncBatchEmbedContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :generate-text]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateText"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :batch-generate-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :predict-long-running]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:predictLongRunning"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :generate-message]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateMessage"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :count-message-tokens]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:countMessageTokens"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/models"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :count-text-tokens]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:countTextTokens"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :generate-answer]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateAnswer"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :predict]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:predict"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :batch-embed-contents]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchEmbedContents"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :embed-content]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:embedContent"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :models :count-tokens]
  [_ params opts]
  (client/invoke-endpoint
   "POST"
   "v1beta/{+model}:countTokens"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

;;; Operations
(defmethod core/dispatch [:gemini :operations :list]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}/operations"
   params
   opts
   "https://generativelanguage.googleapis.com/"))

(defmethod core/dispatch [:gemini :operations :get]
  [_ params opts]
  (client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/"))
