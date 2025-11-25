(ns google-clj-workspace.gemini
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.gemini]))

(defn permissions
  "Manages permissions.
  - op: :create, :get, :list, :patch, :delete"
  [params & [opts]]
  (core/dispatch [:gemini :permissions (:op opts)] params opts))

(defn dynamic
  "Manages dynamic content.
  - op: :generate-content, :stream-generate-content"
  [params & [opts]]
  (core/dispatch [:gemini :dynamic (:op opts)] params opts))

(defn file-search-stores
  "Manages file search stores.
  - op: :create, :get, :delete, :list, :import-file"
  [params & [opts]]
  (core/dispatch [:gemini :file-search-stores (:op opts)] params opts))

(defn media
  "Manages media.
  - op: :upload, :upload-to-file-search-store"
  [params & [opts]]
  (core/dispatch [:gemini :media (:op opts)] params opts))

(defn batches
  "Manages batches.
  - op: :list, :get, :delete, :cancel, :update-generate-content-batch, :update-embed-content-batch"
  [params & [opts]]
  (core/dispatch [:gemini :batches (:op opts)] params opts))

(defn cached-contents
  "Manages cached contents.
  - op: :list, :create, :get, :patch, :delete"
  [params & [opts]]
  (core/dispatch [:gemini :cached-contents (:op opts)] params opts))

(defn files
  "Manages files.
  - op: :list, :get, :delete"
  [params & [opts]]
  (core/dispatch [:gemini :files (:op opts)] params opts))

(defn corpora
  "Manages corpora.
  - op: :create, :get, :delete, :list"
  [params & [opts]]
  (core/dispatch [:gemini :corpora (:op opts)] params opts))

(defn tuned-models
  "Manages tuned models.
  - op: :get, :stream-generate-content, :patch, :async-batch-embed-content, :create, :generate-content, :generate-text, :delete, :batch-generate-content, :list, :transfer-ownership"
  [params & [opts]]
  (core/dispatch [:gemini :tuned-models (:op opts)] params opts))

(defn documents
  "Manages documents.
  - op: :get, :delete, :list"
  [params & [opts]]
  (core/dispatch [:gemini :documents (:op opts)] params opts))

(defn generated-files
  "Manages generated files.
  - op: :list"
  [params & [opts]]
  (core/dispatch [:gemini :generated-files (:op opts)] params opts))

(defn models
  "Manages models.
  - op: :batch-embed-text, :get, :embed-text, :stream-generate-content, :async-batch-embed-content, :generate-content, :generate-text, :batch-generate-content, :predict-long-running, :generate-message, :count-message-tokens, :list, :count-text-tokens, :generate-answer, :predict, :batch-embed-contents, :embed-content, :count-tokens"
  [params & [opts]]
  (core/dispatch [:gemini :models (:op opts)] params opts))

(defn operations
  "Manages operations.
  - op: :list, :get"
  [params & [opts]]
  (core/dispatch [:gemini :operations (:op opts)] params opts))
