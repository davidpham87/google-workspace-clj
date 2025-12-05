(ns
 google-clj-workspace.gemini
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 permissions
 "Available operations: :create, :delete, :get, :list, :patch"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+parent}/permissions"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+parent}/permissions"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :patch
  (google-clj-workspace.client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 dynamic
 "Available operations: :generate-content, :stream-generate-content"
 [params & [opts]]
 (case
  (:op opts)
  :generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :stream-generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:streamGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 file-search-stores
 "Available operations: :create, :delete, :get, :import-file, :list"
 [params & [opts]]
 (case
  (:op opts)
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/fileSearchStores"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :import-file
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+fileSearchStoreName}:importFile"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/fileSearchStores"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 media
 "Available operations: :upload, :upload-to-file-search-store"
 [params & [opts]]
 (case
  (:op opts)
  :upload-to-file-search-store
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+fileSearchStoreName}:uploadToFileSearchStore"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :upload
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/files"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 batches
 "Available operations: :cancel, :delete, :get, :list, :update-embed-content-batch, :update-generate-content-batch"
 [params & [opts]]
 (case
  (:op opts)
  :cancel
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+name}:cancel"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :update-generate-content-batch
  (google-clj-workspace.client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}:updateGenerateContentBatch"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :update-embed-content-batch
  (google-clj-workspace.client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}:updateEmbedContentBatch"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 cached-contents
 "Available operations: :create, :delete, :get, :list, :patch"
 [params & [opts]]
 (case
  (:op opts)
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/cachedContents"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :patch
  (google-clj-workspace.client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/cachedContents"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 files
 "Available operations: :delete, :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/files"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 corpora
 "Available operations: :create, :delete, :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/corpora"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/corpora"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 tuned-models
 "Available operations: :async-batch-embed-content, :batch-generate-content, :create, :delete, :generate-content, :generate-text, :get, :list, :patch, :stream-generate-content, :transfer-ownership"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :stream-generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:streamGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :patch
  (google-clj-workspace.client/invoke-endpoint
   "PATCH"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :async-batch-embed-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:asyncBatchEmbedContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/tunedModels"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :generate-text
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateText"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :batch-generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/tunedModels"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :transfer-ownership
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+name}:transferOwnership"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 documents
 "Available operations: :delete, :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+parent}/documents"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 generated-files
 "Available operations: :list"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/generatedFiles"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 models
 "Available operations: :async-batch-embed-content, :batch-embed-contents, :batch-embed-text, :batch-generate-content, :count-message-tokens, :count-text-tokens, :count-tokens, :embed-content, :embed-text, :generate-answer, :generate-content, :generate-message, :generate-text, :get, :list, :predict, :predict-long-running, :stream-generate-content"
 [params & [opts]]
 (case
  (:op opts)
  :batch-embed-text
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchEmbedText"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :embed-text
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:embedText"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :stream-generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:streamGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :async-batch-embed-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:asyncBatchEmbedContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :generate-text
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateText"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :batch-generate-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchGenerateContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :predict-long-running
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:predictLongRunning"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :generate-message
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateMessage"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :count-message-tokens
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:countMessageTokens"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/models"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :count-text-tokens
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:countTextTokens"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :generate-answer
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:generateAnswer"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :predict
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:predict"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :batch-embed-contents
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:batchEmbedContents"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :embed-content
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:embedContent"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :count-tokens
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1beta/{+model}:countTokens"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 operations
 "Available operations: :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}/operations"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1beta/{+name}"
   params
   opts
   "https://generativelanguage.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

