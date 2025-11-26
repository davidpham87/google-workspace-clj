(ns google-clj-workspace.gemini
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.impl.gemini]))

(defn permissions
  "Manages permissions.
  - op: :create, :get, :list, :patch, :delete

  Examples:
  ;; create a permission
  (permissions {:parent \"tunedModels/123\"}
               {:op :create
                :body {:granteeType \"EVERYONE\"
                       :role \"reader\"}})
  ;; get a permission
  (permissions {:name \"tunedModels/123/permissions/456\"} {:op :get})

  ;; list permissions
  (permissions {:parent \"tunedModels/123\"} {:op :list})

  ;; patch a permission
  (permissions {:name \"tunedModels/123/permissions/456\"}
               {:op :patch
                :body {:role \"writer\"}})

  ;; delete a permission
  (permissions {:name \"tunedModels/123/permissions/456\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:gemini :permissions (:op opts)] params opts))

(defn dynamic
  "Manages dynamic content.
  - op: :generate-content, :stream-generate-content

  Examples:
  ;; generate content
  (dynamic {:model \"gemini-pro\"}
           {:op :generate-content
            :body {:contents [{:parts [{:text \"Hello\"}]}]}})

  ;; stream generate content
  (dynamic {:model \"gemini-pro\"}
           {:op :stream-generate-content
            :body {:contents [{:parts [{:text \"Hello\"}]}]}})"
  [params & [opts]]
  (core/dispatch [:gemini :dynamic (:op opts)] params opts))

(defn file-search-stores
  "Manages file search stores.
  - op: :create, :get, :delete, :list, :import-file

  Examples:
  ;; create a file search store
  (file-search-stores {}
                      {:op :create
                       :body {:displayName \"My Store\"}})

  ;; get a file search store
  (file-search-stores {:name \"fileSearchStores/123\"} {:op :get})

  ;; list file search stores
  (file-search-stores {} {:op :list})

  ;; delete a file search store
  (file-search-stores {:name \"fileSearchStores/123\"} {:op :delete})

  ;; import a file
  (file-search-stores {:fileSearchStoreName \"fileSearchStores/123\"}
                      {:op :import-file
                       :body {:fileName \"my-file.txt\"}})"
  [params & [opts]]
  (core/dispatch [:gemini :file-search-stores (:op opts)] params opts))

(defn media
  "Manages media.
  - op: :upload, :upload-to-file-search-store

  Examples:
  ;; upload media
  (media {} {:op :upload
             :body {:file {:displayName \"My File\"}}})

  ;; upload to file search store
  (media {:fileSearchStoreName \"fileSearchStores/123\"}
         {:op :upload-to-file-search-store
          :body {:fileName \"my-file.txt\"}})"
  [params & [opts]]
  (core/dispatch [:gemini :media (:op opts)] params opts))

(defn batches
  "Manages batches.
  - op: :list, :get, :delete, :cancel, :update-generate-content-batch, :update-embed-content-batch

  Examples:
  ;; list batches
  (batches {:name \"tunedModels/123\"} {:op :list})

  ;; get a batch
  (batches {:name \"tunedModels/123/batches/456\"} {:op :get})

  ;; delete a batch
  (batches {:name \"tunedModels/123/batches/456\"} {:op :delete})

  ;; cancel a batch
  (batches {:name \"tunedModels/123/batches/456\"} {:op :cancel})"
  [params & [opts]]
  (core/dispatch [:gemini :batches (:op opts)] params opts))

(defn cached-contents
  "Manages cached contents.
  - op: :list, :create, :get, :patch, :delete

  Examples:
  ;; create cached content
  (cached-contents {}
                   {:op :create
                    :body {:model \"gemini-pro\"
                           :contents [{:parts [{:text \"Hello\"}]}]}})

  ;; get cached content
  (cached-contents {:name \"cachedContents/123\"} {:op :get})

  ;; list cached contents
  (cached-contents {} {:op :list})

  ;; patch cached content
  (cached-contents {:name \"cachedContents/123\"}
                   {:op :patch
                    :body {:ttl \"3600s\"}})

  ;; delete cached content
  (cached-contents {:name \"cachedContents/123\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:gemini :cached-contents (:op opts)] params opts))

(defn files
  "Manages files.
  - op: :list, :get, :delete

  Examples:
  ;; list files
  (files {} {:op :list})

  ;; get a file
  (files {:name \"files/123\"} {:op :get})

  ;; delete a file
  (files {:name \"files/123\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:gemini :files (:op opts)] params opts))

(defn corpora
  "Manages corpora.
  - op: :create, :get, :delete, :list

  Examples:
  ;; create a corpora
  (corpora {} {:op :create :body {:displayName \"My Corpora\"}})

  ;; get a corpora
  (corpora {:name \"corpora/123\"} {:op :get})

  ;; list corpora
  (corpora {} {:op :list})

  ;; delete a corpora
  (corpora {:name \"corpora/123\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:gemini :corpora (:op opts)] params opts))

(defn tuned-models
  "Manages tuned models.
  - op: :get, :stream-generate-content, :patch, :async-batch-embed-content, :create, :generate-content, :generate-text, :delete, :batch-generate-content, :list, :transfer-ownership

  Examples:
  ;; create a tuned model
  (tuned-models {} {:op :create
                    :body {:sourceModel \"gemini-1.0-pro-001\"
                           :trainingData {:examples {:examples [{:textInput \"example input\"
                                                                 :output \"example output\"}]}}}})

  ;; get a tuned model
  (tuned-models {:name \"tunedModels/123\"} {:op :get})

  ;; list tuned models
  (tuned-models {} {:op :list})

  ;; patch a tuned model
  (tuned-models {:name \"tunedModels/123\"}
                {:op :patch
                 :body {:snapshots [{:step 1
                                     :metrics [{:value 0.9}]}]}})

  ;; delete a tuned model
  (tuned-models {:name \"tunedModels/123\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:gemini :tuned-models (:op opts)] params opts))

(defn documents
  "Manages documents.
  - op: :get, :delete, :list

  Examples:
  ;; get a document
  (documents {:name \"corpora/123/documents/456\"} {:op :get})

  ;; list documents
  (documents {:parent \"corpora/123\"} {:op :list})

  ;; delete a document
  (documents {:name \"corpora/123/documents/456\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:gemini :documents (:op opts)] params opts))

(defn generated-files
  "Manages generated files.
  - op: :list

  Examples:
  ;; list generated files
  (generated-files {} {:op :list})"
  [params & [opts]]
  (core/dispatch [:gemini :generated-files (:op opts)] params opts))

(defn models
  "Manages models.
  - op: :batch-embed-text, :get, :embed-text, :stream-generate-content, :async-batch-embed-content, :generate-content, :generate-text, :batch-generate-content, :predict-long-running, :generate-message, :count-message-tokens, :list, :count-text-tokens, :generate-answer, :predict, :batch-embed-contents, :embed-content, :count-tokens

  Examples:
  ;; generate content from a model
  (models {:model \"gemini-pro\"}
          {:op :generate-content
           :body {:contents [{:parts [{:text \"Hello\"}]}]}})

  ;; list models
  (models {} {:op :list})

  ;; get a model
  (models {:name \"models/gemini-pro\"} {:op :get})

  ;; count tokens
  (models {:model \"gemini-pro\"}
          {:op :count-tokens
           :body {:contents [{:parts [{:text \"Hello\"}]}]}})

  ;; embed content
  (models {:model \"embedding-001\"}
          {:op :embed-content
           :body {:content {:parts [{:text \"Hello\"}]}}})

  ;; batch embed contents
  (models {:model \"embedding-001\"}
          {:op :batch-embed-contents
           :body {:requests [{:model \"models/embedding-001\"
                              :content {:parts [{:text \"Hello\"}]}}]}})"
  [params & [opts]]
  (core/dispatch [:gemini :models (:op opts)] params opts))

(defn operations
  "Manages operations.
  - op: :get, :list

  Examples:
  ;; get an operation
  (operations {:name \"operations/123\"} {:op :get})

  ;; list operations
  (operations {:name \"tunedModels/123\"} {:op :list})"
  [params & [opts]]
  (core/dispatch [:gemini :operations (:op opts)] params opts))
