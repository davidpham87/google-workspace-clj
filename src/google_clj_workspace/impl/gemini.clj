(ns google-clj-workspace.impl.gemini
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://generativelanguage.googleapis.com/")

(def ops
  {[:permissions :create] {:method "POST" :path "v1beta/{+parent}/permissions"}
   [:permissions :get] {:method "GET" :path "v1beta/{+name}"}
   [:permissions :list] {:method "GET" :path "v1beta/{+parent}/permissions"}
   [:permissions :patch] {:method "PATCH" :path "v1beta/{+name}"}
   [:permissions :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:dynamic :generate-content] {:method "POST" :path "v1beta/{+model}:generateContent"}
   [:dynamic :stream-generate-content] {:method "POST" :path "v1beta/{+model}:streamGenerateContent"}
   [:file-search-stores :create] {:method "POST" :path "v1beta/fileSearchStores"}
   [:file-search-stores :get] {:method "GET" :path "v1beta/{+name}"}
   [:file-search-stores :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:file-search-stores :list] {:method "GET" :path "v1beta/fileSearchStores"}
   [:file-search-stores :import-file] {:method "POST" :path "v1beta/{+fileSearchStoreName}:importFile"}
   [:media :upload] {:method "POST" :path "v1beta/files"}
   [:media :upload-to-file-search-store] {:method "POST" :path "v1beta/{+fileSearchStoreName}:uploadToFileSearchStore"}
   [:batches :list] {:method "GET" :path "v1beta/{+name}"}
   [:batches :get] {:method "GET" :path "v1beta/{+name}"}
   [:batches :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:batches :cancel] {:method "POST" :path "v1beta/{+name}:cancel"}
   [:batches :update-generate-content-batch] {:method "PATCH" :path "v1beta/{+name}:updateGenerateContentBatch"}
   [:batches :update-embed-content-batch] {:method "PATCH" :path "v1beta/{+name}:updateEmbedContentBatch"}
   [:cached-contents :list] {:method "GET" :path "v1beta/cachedContents"}
   [:cached-contents :create] {:method "POST" :path "v1beta/cachedContents"}
   [:cached-contents :get] {:method "GET" :path "v1beta/{+name}"}
   [:cached-contents :patch] {:method "PATCH" :path "v1beta/{+name}"}
   [:cached-contents :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:files :list] {:method "GET" :path "v1beta/files"}
   [:files :get] {:method "GET" :path "v1beta/{+name}"}
   [:files :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:corpora :create] {:method "POST" :path "v1beta/corpora"}
   [:corpora :get] {:method "GET" :path "v1beta/{+name}"}
   [:corpora :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:corpora :list] {:method "GET" :path "v1beta/corpora"}
   [:tuned-models :get] {:method "GET" :path "v1beta/{+name}"}
   [:tuned-models :stream-generate-content] {:method "POST" :path "v1beta/{+model}:streamGenerateContent"}
   [:tuned-models :patch] {:method "PATCH" :path "v1beta/{+name}"}
   [:tuned-models :async-batch-embed-content] {:method "POST" :path "v1beta/{+model}:asyncBatchEmbedContent"}
   [:tuned-models :create] {:method "POST" :path "v1beta/tunedModels"}
   [:tuned-models :generate-content] {:method "POST" :path "v1beta/{+model}:generateContent"}
   [:tuned-models :generate-text] {:method "POST" :path "v1beta/{+model}:generateText"}
   [:tuned-models :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:tuned-models :batch-generate-content] {:method "POST" :path "v1beta/{+model}:batchGenerateContent"}
   [:tuned-models :list] {:method "GET" :path "v1beta/tunedModels"}
   [:tuned-models :transfer-ownership] {:method "POST" :path "v1beta/{+name}:transferOwnership"}
   [:documents :get] {:method "GET" :path "v1beta/{+name}"}
   [:documents :delete] {:method "DELETE" :path "v1beta/{+name}"}
   [:documents :list] {:method "GET" :path "v1beta/{+parent}/documents"}
   [:generated-files :list] {:method "GET" :path "v1beta/generatedFiles"}
   [:models :batch-embed-text] {:method "POST" :path "v1beta/{+model}:batchEmbedText"}
   [:models :get] {:method "GET" :path "v1beta/{+name}"}
   [:models :embed-text] {:method "POST" :path "v1beta/{+model}:embedText"}
   [:models :stream-generate-content] {:method "POST" :path "v1beta/{+model}:streamGenerateContent"}
   [:models :async-batch-embed-content] {:method "POST" :path "v1beta/{+model}:asyncBatchEmbedContent"}
   [:models :generate-content] {:method "POST" :path "v1beta/{+model}:generateContent"}
   [:models :generate-text] {:method "POST" :path "v1beta/{+model}:generateText"}
   [:models :batch-generate-content] {:method "POST" :path "v1beta/{+model}:batchGenerateContent"}
   [:models :predict-long-running] {:method "POST" :path "v1beta/{+model}:predictLongRunning"}
   [:models :generate-message] {:method "POST" :path "v1beta/{+model}:generateMessage"}
   [:models :count-message-tokens] {:method "POST" :path "v1beta/{+model}:countMessageTokens"}
   [:models :list] {:method "GET" :path "v1beta/models"}
   [:models :count-text-tokens] {:method "POST" :path "v1beta/{+model}:countTextTokens"}
   [:models :generate-answer] {:method "POST" :path "v1beta/{+model}:generateAnswer"}
   [:models :predict] {:method "POST" :path "v1beta/{+model}:predict"}
   [:models :batch-embed-contents] {:method "POST" :path "v1beta/{+model}:batchEmbedContents"}
   [:models :embed-content] {:method "POST" :path "v1beta/{+model}:embedContent"}
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
