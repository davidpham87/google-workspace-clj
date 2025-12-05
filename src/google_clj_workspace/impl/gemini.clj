(ns google-clj-workspace.impl.gemini
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://generativelanguage.googleapis.com/")

(def ops
   ;; [:map
   ;;  {:closed false}
   ;;  [:granteeType
   ;;   {:optional true}
   ;;   [:enum "GRANTEE_TYPE_UNSPECIFIED" "USER" "GROUP" "EVERYONE"]]
   ;;  [:role
   ;;   {:optional true}
   ;;   [:enum "ROLE_UNSPECIFIED" "OWNER" "WRITER" "READER"]]
   ;;  [:name {:optional true} :string]
   ;;  [:emailAddress {:optional true} :string]]
  {[:permissions :create] {:method "POST" :path "v1beta/{+parent}/permissions"}
   [:permissions :get] {:method "GET" :path "v1beta/{+name}"}
   [:permissions :list] {:method "GET" :path "v1beta/{+parent}/permissions"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]
   ;;  [:name {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:createTime {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:ttl {:optional true} :string]
   ;;  [:usageMetadata {:optional true} [:ref "CachedContentUsageMetadata"]]
   ;;  [:expireTime {:optional true} :string]
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:model {:optional true} :string]]
   [:permissions :patch] {:method "PATCH" :path "v1beta/{+name}"}
   [:permissions :delete] {:method "DELETE" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:model {:optional true} :string]
   ;;  [:cachedContent {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]]
   [:dynamic :generate-content] {:method "POST" :path "v1beta/{+model}:generateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:model {:optional true} :string]
   ;;  [:cachedContent {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]]
   [:dynamic :stream-generate-content] {:method "POST" :path "v1beta/{+model}:streamGenerateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:updateTime {:optional true} :string]
   ;;  [:sizeBytes {:optional true} :string]
   ;;  [:name {:optional true} :string]
   ;;  [:displayName {:optional true} :string]
   ;;  [:createTime {:optional true} :string]
   ;;  [:failedDocumentsCount {:optional true} :string]
   ;;  [:activeDocumentsCount {:optional true} :string]
   ;;  [:pendingDocumentsCount {:optional true} :string]]
   [:file-search-stores :create] {:method "POST" :path "v1beta/fileSearchStores"}
   [:file-search-stores :get] {:method "GET" :path "v1beta/{+name}"}
   [:file-search-stores :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:file-search-stores :list] {:method "GET" :path "v1beta/fileSearchStores"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
   ;;  [:fileName {:optional true} :string]
   ;;  [:chunkingConfig {:optional true} [:ref "ChunkingConfig"]]]
   [:file-search-stores :import-file] {:method "POST" :path "v1beta/{+fileSearchStoreName}:importFile"}
   ;; [:map {:closed false} [:file {:optional true} [:ref "File"]]]
   [:media :upload] {:method "POST" :path "v1beta/files"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:mimeType {:optional true} :string]
   ;;  [:displayName {:optional true} :string]
   ;;  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
   ;;  [:chunkingConfig {:optional true} [:ref "ChunkingConfig"]]]
   [:media :upload-to-file-search-store] {:method "POST" :path "v1beta/{+fileSearchStoreName}:uploadToFileSearchStore"}
   [:batches :list] {:method "GET" :path "v1beta/{+name}"}
   [:batches :get] {:method "GET" :path "v1beta/{+name}"}
   [:batches :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:batches :cancel] {:method "POST" :path "v1beta/{+name}:cancel"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:name {:optional true} :string]
   ;;  [:batchStats {:optional true} [:ref "BatchStats"]]
   ;;  [:endTime {:optional true} :string]
   ;;  [:createTime {:optional true} :string]
   ;;  [:state
   ;;   {:optional true}
   ;;   [:enum
   ;;    "BATCH_STATE_UNSPECIFIED"
   ;;    "BATCH_STATE_PENDING"
   ;;    "BATCH_STATE_RUNNING"
   ;;    "BATCH_STATE_SUCCEEDED"
   ;;    "BATCH_STATE_FAILED"
   ;;    "BATCH_STATE_CANCELLED"
   ;;    "BATCH_STATE_EXPIRED"]]
   ;;  [:output {:optional true} [:ref "GenerateContentBatchOutput"]]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:priority {:optional true} :string]
   ;;  [:inputConfig {:optional true} [:ref "InputConfig"]]
   ;;  [:model {:optional true} :string]]
   [:batches :update-generate-content-batch] {:method "PATCH" :path "v1beta/{+name}:updateGenerateContentBatch"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:name {:optional true} :string]
   ;;  [:batchStats {:optional true} [:ref "EmbedContentBatchStats"]]
   ;;  [:endTime {:optional true} :string]
   ;;  [:createTime {:optional true} :string]
   ;;  [:state
   ;;   {:optional true}
   ;;   [:enum
   ;;    "BATCH_STATE_UNSPECIFIED"
   ;;    "BATCH_STATE_PENDING"
   ;;    "BATCH_STATE_RUNNING"
   ;;    "BATCH_STATE_SUCCEEDED"
   ;;    "BATCH_STATE_FAILED"
   ;;    "BATCH_STATE_CANCELLED"
   ;;    "BATCH_STATE_EXPIRED"]]
   ;;  [:output {:optional true} [:ref "EmbedContentBatchOutput"]]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:priority {:optional true} :string]
   ;;  [:inputConfig {:optional true} [:ref "InputEmbedContentConfig"]]
   ;;  [:model {:optional true} :string]]
   [:batches :update-embed-content-batch] {:method "PATCH" :path "v1beta/{+name}:updateEmbedContentBatch"}
   [:cached-contents :list] {:method "GET" :path "v1beta/cachedContents"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]
   ;;  [:name {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:createTime {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:ttl {:optional true} :string]
   ;;  [:usageMetadata {:optional true} [:ref "CachedContentUsageMetadata"]]
   ;;  [:expireTime {:optional true} :string]
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:model {:optional true} :string]]
   [:cached-contents :create] {:method "POST" :path "v1beta/cachedContents"}
   [:cached-contents :get] {:method "GET" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]
   ;;  [:name {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:createTime {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:ttl {:optional true} :string]
   ;;  [:usageMetadata {:optional true} [:ref "CachedContentUsageMetadata"]]
   ;;  [:expireTime {:optional true} :string]
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:model {:optional true} :string]]
   [:cached-contents :patch] {:method "PATCH" :path "v1beta/{+name}"}
   [:cached-contents :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:files :list] {:method "GET" :path "v1beta/files"}
   [:files :get] {:method "GET" :path "v1beta/{+name}"}
   [:files :delete] {:method "DELETE" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:createTime {:optional true} :string]
   ;;  [:name {:optional true} :string]]
   [:corpora :create] {:method "POST" :path "v1beta/corpora"}
   [:corpora :get] {:method "GET" :path "v1beta/{+name}"}
   [:corpora :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:corpora :list] {:method "GET" :path "v1beta/corpora"}
   [:tuned-models :get] {:method "GET" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:model {:optional true} :string]
   ;;  [:cachedContent {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]]
   [:tuned-models :stream-generate-content] {:method "POST" :path "v1beta/{+model}:streamGenerateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:displayName {:optional true} :string]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]
   ;;  [:name {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:createTime {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:ttl {:optional true} :string]
   ;;  [:usageMetadata {:optional true} [:ref "CachedContentUsageMetadata"]]
   ;;  [:expireTime {:optional true} :string]
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:model {:optional true} :string]]
   [:tuned-models :patch] {:method "PATCH" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:batch {:optional true} [:ref "EmbedContentBatch"]]]
   [:tuned-models :async-batch-embed-content] {:method "POST" :path "v1beta/{+model}:asyncBatchEmbedContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:description {:optional true} :string]
   ;;  [:displayName {:optional true} :string]
   ;;  [:name {:optional true} :string]
   ;;  [:createTime {:optional true} :string]
   ;;  [:state
   ;;   {:optional true}
   ;;   [:enum "STATE_UNSPECIFIED" "CREATING" "ACTIVE" "FAILED"]]
   ;;  [:topP {:optional true} :double]
   ;;  [:baseModel {:optional true} :string]
   ;;  [:updateTime {:optional true} :string]
   ;;  [:tuningTask {:optional true} [:ref "TuningTask"]]
   ;;  [:readerProjectNumbers {:optional true} [:vector :string]]
   ;;  [:topK {:optional true} :int]
   ;;  [:tunedModelSource {:optional true} [:ref "TunedModelSource"]]
   ;;  [:temperature {:optional true} :double]]
   [:tuned-models :create] {:method "POST" :path "v1beta/tunedModels"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:model {:optional true} :string]
   ;;  [:cachedContent {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]]
   [:tuned-models :generate-content] {:method "POST" :path "v1beta/{+model}:generateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:temperature {:optional true} :double]
   ;;  [:stopSequences {:optional true} [:vector :string]]
   ;;  [:topP {:optional true} :double]
   ;;  [:topK {:optional true} :int]
   ;;  [:prompt {:optional true} [:ref "TextPrompt"]]
   ;;  [:maxOutputTokens {:optional true} :int]
   ;;  [:candidateCount {:optional true} :int]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]]
   [:tuned-models :generate-text] {:method "POST" :path "v1beta/{+model}:generateText"}
   [:tuned-models :delete] {:method "DELETE" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:batch {:optional true} [:ref "GenerateContentBatch"]]]
   [:tuned-models :batch-generate-content] {:method "POST" :path "v1beta/{+model}:batchGenerateContent"}
   [:tuned-models :list] {:method "GET" :path "v1beta/tunedModels"}
   ;; [:map {:closed false} [:emailAddress {:optional true} :string]]
   [:tuned-models :transfer-ownership] {:method "POST" :path "v1beta/{+name}:transferOwnership"}
   [:documents :get] {:method "GET" :path "v1beta/{+name}"}
   [:documents :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:documents :list] {:method "GET" :path "v1beta/{+parent}/documents"}
   [:generated-files :list] {:method "GET" :path "v1beta/generatedFiles"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:texts {:optional true} [:vector :string]]
   ;;  [:requests {:optional true} [:vector [:ref "EmbedTextRequest"]]]]
   [:models :batch-embed-text] {:method "POST" :path "v1beta/{+model}:batchEmbedText"}
   [:models :get] {:method "GET" :path "v1beta/{+name}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:text {:optional true} :string]
   ;;  [:model {:optional true} :string]]
   [:models :embed-text] {:method "POST" :path "v1beta/{+model}:embedText"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:model {:optional true} :string]
   ;;  [:cachedContent {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]]
   [:models :stream-generate-content] {:method "POST" :path "v1beta/{+model}:streamGenerateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:batch {:optional true} [:ref "EmbedContentBatch"]]]
   [:models :async-batch-embed-content] {:method "POST" :path "v1beta/{+model}:asyncBatchEmbedContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:model {:optional true} :string]
   ;;  [:cachedContent {:optional true} :string]
   ;;  [:toolConfig {:optional true} [:ref "ToolConfig"]]
   ;;  [:tools {:optional true} [:vector [:ref "Tool"]]]
   ;;  [:systemInstruction {:optional true} [:ref "Content"]]]
   [:models :generate-content] {:method "POST" :path "v1beta/{+model}:generateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:temperature {:optional true} :double]
   ;;  [:stopSequences {:optional true} [:vector :string]]
   ;;  [:topP {:optional true} :double]
   ;;  [:topK {:optional true} :int]
   ;;  [:prompt {:optional true} [:ref "TextPrompt"]]
   ;;  [:maxOutputTokens {:optional true} :int]
   ;;  [:candidateCount {:optional true} :int]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]]
   [:models :generate-text] {:method "POST" :path "v1beta/{+model}:generateText"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:batch {:optional true} [:ref "GenerateContentBatch"]]]
   [:models :batch-generate-content] {:method "POST" :path "v1beta/{+model}:batchGenerateContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:parameters {:optional true} :any]
   ;;  [:instances {:optional true} [:vector :any]]]
   [:models :predict-long-running] {:method "POST" :path "v1beta/{+model}:predictLongRunning"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:prompt {:optional true} [:ref "MessagePrompt"]]
   ;;  [:topP {:optional true} :double]
   ;;  [:temperature {:optional true} :double]
   ;;  [:topK {:optional true} :int]
   ;;  [:candidateCount {:optional true} :int]]
   [:models :generate-message] {:method "POST" :path "v1beta/{+model}:generateMessage"}
   ;; [:map {:closed false} [:prompt {:optional true} [:ref "MessagePrompt"]]]
   [:models :count-message-tokens] {:method "POST" :path "v1beta/{+model}:countMessageTokens"}
   [:models :list] {:method "GET" :path "v1beta/models"}
   ;; [:map {:closed false} [:prompt {:optional true} [:ref "TextPrompt"]]]
   [:models :count-text-tokens] {:method "POST" :path "v1beta/{+model}:countTextTokens"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:temperature {:optional true} :double]
   ;;  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
   ;;  [:answerStyle
   ;;   {:optional true}
   ;;   [:enum
   ;;    "ANSWER_STYLE_UNSPECIFIED"
   ;;    "ABSTRACTIVE"
   ;;    "EXTRACTIVE"
   ;;    "VERBOSE"]]
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]
   ;;  [:inlinePassages {:optional true} [:ref "GroundingPassages"]]
   ;;  [:semanticRetriever {:optional true} [:ref "SemanticRetrieverConfig"]]]
   [:models :generate-answer] {:method "POST" :path "v1beta/{+model}:generateAnswer"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:instances {:optional true} [:vector :any]]
   ;;  [:parameters {:optional true} :any]]
   [:models :predict] {:method "POST" :path "v1beta/{+model}:predict"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:requests {:optional true} [:vector [:ref "EmbedContentRequest"]]]]
   [:models :batch-embed-contents] {:method "POST" :path "v1beta/{+model}:batchEmbedContents"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:content {:optional true} [:ref "Content"]]
   ;;  [:outputDimensionality {:optional true} :int]
   ;;  [:title {:optional true} :string]
   ;;  [:taskType
   ;;   {:optional true}
   ;;   [:enum
   ;;    "TASK_TYPE_UNSPECIFIED"
   ;;    "RETRIEVAL_QUERY"
   ;;    "RETRIEVAL_DOCUMENT"
   ;;    "SEMANTIC_SIMILARITY"
   ;;    "CLASSIFICATION"
   ;;    "CLUSTERING"
   ;;    "QUESTION_ANSWERING"
   ;;    "FACT_VERIFICATION"
   ;;    "CODE_RETRIEVAL_QUERY"]]
   ;;  [:model {:optional true} :string]]
   [:models :embed-content] {:method "POST" :path "v1beta/{+model}:embedContent"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:generateContentRequest
   ;;   {:optional true}
   ;;   [:ref "GenerateContentRequest"]]
   ;;  [:contents {:optional true} [:vector [:ref "Content"]]]]
   [:models :count-tokens] {:method "POST" :path "v1beta/{+model}:countTokens"}
   [:operations :list] {:method "GET" :path "v1beta/{+name}/operations"}
   [:operations :get] {:method "GET" :path "v1beta/{+name}"}})

(defn invoke-gemini-api
  [dispatch-val params opts]
  (let [[_service resource op] dispatch-val
        {:keys [method path]} (get ops [resource op])
        [interpolated-path used-params] (util/interpolate-path path params)]
    (client/invoke-endpoint
     method
     interpolated-path
     (apply dissoc params used-params)
     opts
     base-url)))

(defmacro ^:private def-gemini-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:gemini ~resource ~op]
              [d# p# o#]
              (invoke-gemini-api d# p# o#))))))

(def-gemini-dispatch-methods)