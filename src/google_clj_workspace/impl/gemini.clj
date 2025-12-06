(ns google-clj-workspace.impl.gemini
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://generativelanguage.googleapis.com/")


(def registry
{"BatchEmbedTextRequest"
 [:map
  {:closed true}
  [:texts {:optional true} [:vector :string]]
  [:requests {:optional true} [:vector [:ref "EmbedTextRequest"]]]],
 "PredictLongRunningRequest"
 [:map
  {:closed true}
  [:parameters {:optional true} :any]
  [:instances {:optional true} [:vector :any]]],
 "InlinedResponse"
 [:map
  {:closed true}
  [:response {:optional true} [:ref "GenerateContentResponse"]]
  [:metadata {:optional true} [:map-of :string :any]]
  [:error {:optional true} [:ref "Status"]]],
 "StringList"
 [:map {:closed true} [:values {:optional true} [:vector :string]]],
 "Empty" [:map {:closed true}],
 "ListDocumentsResponse"
 [:map
  {:closed true}
  [:documents {:optional true} [:vector [:ref "Document"]]]
  [:nextPageToken {:optional true} :string]],
 "Schema"
 [:map
  {:closed true}
  [:propertyOrdering {:optional true} [:vector :string]]
  [:description {:optional true} :string]
  [:enum {:optional true} [:vector :string]]
  [:properties {:optional true} [:map-of :string :any]]
  [:format {:optional true} :string]
  [:maxProperties {:optional true} :string]
  [:anyOf {:optional true} [:vector [:ref "Schema"]]]
  [:default {:optional true} :any]
  [:maximum {:optional true} :double]
  [:type
   {:optional true}
   [:enum
    "TYPE_UNSPECIFIED"
    "STRING"
    "NUMBER"
    "INTEGER"
    "BOOLEAN"
    "ARRAY"
    "OBJECT"
    "NULL"]]
  [:minLength {:optional true} :string]
  [:title {:optional true} :string]
  [:minProperties {:optional true} :string]
  [:minimum {:optional true} :double]
  [:minItems {:optional true} :string]
  [:maxLength {:optional true} :string]
  [:example {:optional true} :any]
  [:items {:optional true} [:ref "Schema"]]
  [:nullable {:optional true} :boolean]
  [:required {:optional true} [:vector :string]]
  [:maxItems {:optional true} :string]
  [:pattern {:optional true} :string]],
 "CustomLongRunningOperation"
 [:map
  {:closed true}
  [:done {:optional true} :boolean]
  [:metadata {:optional true} [:map-of :string :any]]
  [:error {:optional true} [:ref "Status"]]
  [:name {:optional true} :string]
  [:response {:optional true} [:map-of :string :any]]],
 "GoogleSearchRetrieval"
 [:map
  {:closed true}
  [:dynamicRetrievalConfig
   {:optional true}
   [:ref "DynamicRetrievalConfig"]]],
 "SafetyFeedback"
 [:map
  {:closed true}
  [:rating {:optional true} [:ref "SafetyRating"]]
  [:setting {:optional true} [:ref "SafetySetting"]]],
 "GenerateContentBatch"
 [:map
  {:closed true}
  [:displayName {:optional true} :string]
  [:name {:optional true} :string]
  [:batchStats {:optional true} [:ref "BatchStats"]]
  [:endTime {:optional true} :string]
  [:createTime {:optional true} :string]
  [:state
   {:optional true}
   [:enum
    "BATCH_STATE_UNSPECIFIED"
    "BATCH_STATE_PENDING"
    "BATCH_STATE_RUNNING"
    "BATCH_STATE_SUCCEEDED"
    "BATCH_STATE_FAILED"
    "BATCH_STATE_CANCELLED"
    "BATCH_STATE_EXPIRED"]]
  [:output {:optional true} [:ref "GenerateContentBatchOutput"]]
  [:updateTime {:optional true} :string]
  [:priority {:optional true} :string]
  [:inputConfig {:optional true} [:ref "InputConfig"]]
  [:model {:optional true} :string]],
 "EmbedTextResponse"
 [:map
  {:closed true}
  [:embedding {:optional true} [:ref "Embedding"]]],
 "Candidate"
 [:map
  {:closed true}
  [:logprobsResult {:optional true} [:ref "LogprobsResult"]]
  [:index {:optional true} :int]
  [:content {:optional true} [:ref "Content"]]
  [:tokenCount {:optional true} :int]
  [:safetyRatings {:optional true} [:vector [:ref "SafetyRating"]]]
  [:finishReason
   {:optional true}
   [:enum
    "FINISH_REASON_UNSPECIFIED"
    "STOP"
    "MAX_TOKENS"
    "SAFETY"
    "RECITATION"
    "LANGUAGE"
    "OTHER"
    "BLOCKLIST"
    "PROHIBITED_CONTENT"
    "SPII"
    "MALFORMED_FUNCTION_CALL"
    "IMAGE_SAFETY"
    "IMAGE_PROHIBITED_CONTENT"
    "IMAGE_OTHER"
    "NO_IMAGE"
    "IMAGE_RECITATION"
    "UNEXPECTED_TOOL_CALL"
    "TOO_MANY_TOOL_CALLS"
    "MISSING_THOUGHT_SIGNATURE"]]
  [:finishMessage {:optional true} :string]
  [:urlContextMetadata {:optional true} [:ref "UrlContextMetadata"]]
  [:citationMetadata {:optional true} [:ref "CitationMetadata"]]
  [:groundingMetadata {:optional true} [:ref "GroundingMetadata"]]
  [:avgLogprobs {:optional true} :double]
  [:groundingAttributions
   {:optional true}
   [:vector [:ref "GroundingAttribution"]]]],
 "TextPrompt" [:map {:closed true} [:text {:optional true} :string]],
 "CountMessageTokensRequest"
 [:map
  {:closed true}
  [:prompt {:optional true} [:ref "MessagePrompt"]]],
 "ImportFileRequest"
 [:map
  {:closed true}
  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
  [:chunkingConfig {:optional true} [:ref "ChunkingConfig"]]
  [:fileName {:optional true} :string]],
 "Dataset"
 [:map
  {:closed true}
  [:examples {:optional true} [:ref "TuningExamples"]]],
 "GeneratedFile"
 [:map
  {:closed true}
  [:state
   {:optional true}
   [:enum "STATE_UNSPECIFIED" "GENERATING" "GENERATED" "FAILED"]]
  [:error {:optional true} [:ref "Status"]]
  [:mimeType {:optional true} :string]
  [:name {:optional true} :string]],
 "UploadToFileSearchStoreRequest"
 [:map
  {:closed true}
  [:mimeType {:optional true} :string]
  [:displayName {:optional true} :string]
  [:chunkingConfig {:optional true} [:ref "ChunkingConfig"]]
  [:customMetadata
   {:optional true}
   [:vector [:ref "CustomMetadata"]]]],
 "SpeakerVoiceConfig"
 [:map
  {:closed true}
  [:voiceConfig {:optional true} [:ref "VoiceConfig"]]
  [:speaker {:optional true} :string]],
 "RetrievalConfig"
 [:map
  {:closed true}
  [:latLng {:optional true} [:ref "LatLng"]]
  [:languageCode {:optional true} :string]],
 "FunctionResponse"
 [:map
  {:closed true}
  [:parts {:optional true} [:vector [:ref "FunctionResponsePart"]]]
  [:willContinue {:optional true} :boolean]
  [:id {:optional true} :string]
  [:name {:optional true} :string]
  [:scheduling
   {:optional true}
   [:enum "SCHEDULING_UNSPECIFIED" "SILENT" "WHEN_IDLE" "INTERRUPT"]]
  [:response {:optional true} [:map-of :string :any]]],
 "CountTextTokensRequest"
 [:map {:closed true} [:prompt {:optional true} [:ref "TextPrompt"]]],
 "EmbedContentBatchOutput"
 [:map
  {:closed true}
  [:responsesFile {:optional true} :string]
  [:inlinedResponses
   {:optional true}
   [:ref "InlinedEmbedContentResponses"]]],
 "FileSearchStore"
 [:map
  {:closed true}
  [:pendingDocumentsCount {:optional true} :string]
  [:sizeBytes {:optional true} :string]
  [:createTime {:optional true} :string]
  [:name {:optional true} :string]
  [:activeDocumentsCount {:optional true} :string]
  [:displayName {:optional true} :string]
  [:updateTime {:optional true} :string]
  [:failedDocumentsCount {:optional true} :string]],
 "MetadataFilter"
 [:map
  {:closed true}
  [:conditions {:optional true} [:vector [:ref "Condition"]]]
  [:key {:optional true} :string]],
 "SearchEntryPoint"
 [:map
  {:closed true}
  [:renderedContent {:optional true} :string]
  [:sdkBlob {:optional true} :string]],
 "InputEmbedContentConfig"
 [:map
  {:closed true}
  [:fileName {:optional true} :string]
  [:requests {:optional true} [:ref "InlinedEmbedContentRequests"]]],
 "Example"
 [:map
  {:closed true}
  [:output {:optional true} [:ref "Message"]]
  [:input {:optional true} [:ref "Message"]]],
 "ListOperationsResponse"
 [:map
  {:closed true}
  [:nextPageToken {:optional true} :string]
  [:unreachable {:optional true} [:vector :string]]
  [:operations {:optional true} [:vector [:ref "Operation"]]]],
 "CountTokensResponse"
 [:map
  {:closed true}
  [:totalTokens {:optional true} :int]
  [:cacheTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:promptTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:cachedContentTokenCount {:optional true} :int]],
 "Permission"
 [:map
  {:closed true}
  [:role
   {:optional true}
   [:enum "ROLE_UNSPECIFIED" "OWNER" "WRITER" "READER"]]
  [:granteeType
   {:optional true}
   [:enum "GRANTEE_TYPE_UNSPECIFIED" "USER" "GROUP" "EVERYONE"]]
  [:emailAddress {:optional true} :string]
  [:name {:optional true} :string]],
 "GenerateTextRequest"
 [:map
  {:closed true}
  [:stopSequences {:optional true} [:vector :string]]
  [:prompt {:optional true} [:ref "TextPrompt"]]
  [:topP {:optional true} :double]
  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
  [:temperature {:optional true} :double]
  [:topK {:optional true} :int]
  [:maxOutputTokens {:optional true} :int]
  [:candidateCount {:optional true} :int]],
 "Tool"
 [:map
  {:closed true}
  [:googleSearch {:optional true} [:ref "GoogleSearch"]]
  [:googleMaps {:optional true} [:ref "GoogleMaps"]]
  [:computerUse {:optional true} [:ref "ComputerUse"]]
  [:googleSearchRetrieval
   {:optional true}
   [:ref "GoogleSearchRetrieval"]]
  [:functionDeclarations
   {:optional true}
   [:vector [:ref "FunctionDeclaration"]]]
  [:urlContext {:optional true} [:ref "UrlContext"]]
  [:codeExecution {:optional true} [:ref "CodeExecution"]]
  [:fileSearch {:optional true} [:ref "FileSearch"]]],
 "TransferOwnershipRequest"
 [:map {:closed true} [:emailAddress {:optional true} :string]],
 "GroundingPassage"
 [:map
  {:closed true}
  [:content {:optional true} [:ref "Content"]]
  [:id {:optional true} :string]],
 "CodeExecution" [:map {:closed true}],
 "InputConfig"
 [:map
  {:closed true}
  [:fileName {:optional true} :string]
  [:requests {:optional true} [:ref "InlinedRequests"]]],
 "BatchGenerateContentRequest"
 [:map
  {:closed true}
  [:batch {:optional true} [:ref "GenerateContentBatch"]]],
 "ComputerUse"
 [:map
  {:closed true}
  [:environment
   {:optional true}
   [:enum "ENVIRONMENT_UNSPECIFIED" "ENVIRONMENT_BROWSER"]]
  [:excludedPredefinedFunctions {:optional true} [:vector :string]]],
 "ContentEmbedding"
 [:map
  {:closed true}
  [:shape {:optional true} [:vector :int]]
  [:values {:optional true} [:vector :double]]],
 "CreateFileRequest"
 [:map {:closed true} [:file {:optional true} [:ref "File"]]],
 "UsageMetadata"
 [:map
  {:closed true}
  [:candidatesTokenCount {:optional true} :int]
  [:toolUsePromptTokenCount {:optional true} :int]
  [:toolUsePromptTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:cachedContentTokenCount {:optional true} :int]
  [:candidatesTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:promptTokenCount {:optional true} :int]
  [:promptTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:cacheTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:totalTokenCount {:optional true} :int]
  [:thoughtsTokenCount {:optional true} :int]],
 "CitationSource"
 [:map
  {:closed true}
  [:endIndex {:optional true} :int]
  [:license {:optional true} :string]
  [:uri {:optional true} :string]
  [:startIndex {:optional true} :int]],
 "EmbedContentBatch"
 [:map
  {:closed true}
  [:displayName {:optional true} :string]
  [:name {:optional true} :string]
  [:batchStats {:optional true} [:ref "EmbedContentBatchStats"]]
  [:endTime {:optional true} :string]
  [:createTime {:optional true} :string]
  [:state
   {:optional true}
   [:enum
    "BATCH_STATE_UNSPECIFIED"
    "BATCH_STATE_PENDING"
    "BATCH_STATE_RUNNING"
    "BATCH_STATE_SUCCEEDED"
    "BATCH_STATE_FAILED"
    "BATCH_STATE_CANCELLED"
    "BATCH_STATE_EXPIRED"]]
  [:output {:optional true} [:ref "EmbedContentBatchOutput"]]
  [:updateTime {:optional true} :string]
  [:priority {:optional true} :string]
  [:inputConfig {:optional true} [:ref "InputEmbedContentConfig"]]
  [:model {:optional true} :string]],
 "FunctionDeclaration"
 [:map
  {:closed true}
  [:parametersJsonSchema {:optional true} :any]
  [:behavior
   {:optional true}
   [:enum "UNSPECIFIED" "BLOCKING" "NON_BLOCKING"]]
  [:name {:optional true} :string]
  [:responseJsonSchema {:optional true} :any]
  [:parameters {:optional true} [:ref "Schema"]]
  [:description {:optional true} :string]
  [:response {:optional true} [:ref "Schema"]]],
 "ContentFilter"
 [:map
  {:closed true}
  [:reason
   {:optional true}
   [:enum "BLOCKED_REASON_UNSPECIFIED" "SAFETY" "OTHER"]]
  [:message {:optional true} :string]],
 "Hyperparameters"
 [:map
  {:closed true}
  [:batchSize {:optional true} :int]
  [:learningRateMultiplier {:optional true} :double]
  [:learningRate {:optional true} :double]
  [:epochCount {:optional true} :int]],
 "FileData"
 [:map
  {:closed true}
  [:mimeType {:optional true} :string]
  [:fileUri {:optional true} :string]],
 "AsyncBatchEmbedContentRequest"
 [:map
  {:closed true}
  [:batch {:optional true} [:ref "EmbedContentBatch"]]],
 "File"
 [:map
  {:closed true}
  [:sha256Hash {:optional true} :string]
  [:expirationTime {:optional true} :string]
  [:displayName {:optional true} :string]
  [:name {:optional true} :string]
  [:sizeBytes {:optional true} :string]
  [:createTime {:optional true} :string]
  [:state
   {:optional true}
   [:enum "STATE_UNSPECIFIED" "PROCESSING" "ACTIVE" "FAILED"]]
  [:source
   {:optional true}
   [:enum "SOURCE_UNSPECIFIED" "UPLOADED" "GENERATED" "REGISTERED"]]
  [:updateTime {:optional true} :string]
  [:mimeType {:optional true} :string]
  [:error {:optional true} [:ref "Status"]]
  [:uri {:optional true} :string]
  [:videoMetadata {:optional true} [:ref "VideoFileMetadata"]]
  [:downloadUri {:optional true} :string]],
 "Corpus"
 [:map
  {:closed true}
  [:createTime {:optional true} :string]
  [:name {:optional true} :string]
  [:displayName {:optional true} :string]
  [:updateTime {:optional true} :string]],
 "PredictResponse"
 [:map {:closed true} [:predictions {:optional true} [:vector :any]]],
 "RetrievalMetadata"
 [:map
  {:closed true}
  [:googleSearchDynamicRetrievalScore {:optional true} :double]],
 "ListTunedModelsResponse"
 [:map
  {:closed true}
  [:tunedModels {:optional true} [:vector [:ref "TunedModel"]]]
  [:nextPageToken {:optional true} :string]],
 "MessagePrompt"
 [:map
  {:closed true}
  [:messages {:optional true} [:vector [:ref "Message"]]]
  [:context {:optional true} :string]
  [:examples {:optional true} [:vector [:ref "Example"]]]],
 "TuningSnapshot"
 [:map
  {:closed true}
  [:epoch {:optional true} :int]
  [:step {:optional true} :int]
  [:computeTime {:optional true} :string]
  [:meanLoss {:optional true} :double]],
 "GroundingMetadata"
 [:map
  {:closed true}
  [:retrievalMetadata {:optional true} [:ref "RetrievalMetadata"]]
  [:groundingSupports
   {:optional true}
   [:vector [:ref "GroundingSupport"]]]
  [:googleMapsWidgetContextToken {:optional true} :string]
  [:groundingChunks {:optional true} [:vector [:ref "GroundingChunk"]]]
  [:webSearchQueries {:optional true} [:vector :string]]
  [:searchEntryPoint {:optional true} [:ref "SearchEntryPoint"]]],
 "ListFileSearchStoresResponse"
 [:map
  {:closed true}
  [:nextPageToken {:optional true} :string]
  [:fileSearchStores
   {:optional true}
   [:vector [:ref "FileSearchStore"]]]],
 "GenerateContentResponse"
 [:map
  {:closed true}
  [:modelVersion {:optional true} :string]
  [:responseId {:optional true} :string]
  [:usageMetadata {:optional true} [:ref "UsageMetadata"]]
  [:candidates {:optional true} [:vector [:ref "Candidate"]]]
  [:promptFeedback {:optional true} [:ref "PromptFeedback"]]],
 "MultiSpeakerVoiceConfig"
 [:map
  {:closed true}
  [:speakerVoiceConfigs
   {:optional true}
   [:vector [:ref "SpeakerVoiceConfig"]]]],
 "DownloadFileResponse" [:map {:closed true}],
 "Operation"
 [:map
  {:closed true}
  [:response {:optional true} [:map-of :string :any]]
  [:done {:optional true} :boolean]
  [:error {:optional true} [:ref "Status"]]
  [:metadata {:optional true} [:map-of :string :any]]
  [:name {:optional true} :string]],
 "CountTokensRequest"
 [:map
  {:closed true}
  [:contents {:optional true} [:vector [:ref "Content"]]]
  [:generateContentRequest
   {:optional true}
   [:ref "GenerateContentRequest"]]],
 "GoogleMaps"
 [:map {:closed true} [:enableWidget {:optional true} :boolean]],
 "SemanticRetrieverChunk"
 [:map
  {:closed true}
  [:source {:optional true} :string]
  [:chunk {:optional true} :string]],
 "Status"
 [:map
  {:closed true}
  [:message {:optional true} :string]
  [:code {:optional true} :int]
  [:details {:optional true} [:vector [:map-of :string :any]]]],
 "GroundingChunk"
 [:map
  {:closed true}
  [:retrievedContext {:optional true} [:ref "RetrievedContext"]]
  [:maps {:optional true} [:ref "Maps"]]
  [:web {:optional true} [:ref "Web"]]],
 "ListModelsResponse"
 [:map
  {:closed true}
  [:models {:optional true} [:vector [:ref "Model"]]]
  [:nextPageToken {:optional true} :string]],
 "Interval"
 [:map
  {:closed true}
  [:endTime {:optional true} :string]
  [:startTime {:optional true} :string]],
 "GroundingPassageId"
 [:map
  {:closed true}
  [:passageId {:optional true} :string]
  [:partIndex {:optional true} :int]],
 "GroundingSupport"
 [:map
  {:closed true}
  [:confidenceScores {:optional true} [:vector :double]]
  [:segment {:optional true} [:ref "Segment"]]
  [:groundingChunkIndices {:optional true} [:vector :int]]],
 "GenerateAnswerResponse"
 [:map
  {:closed true}
  [:answerableProbability {:optional true} :double]
  [:inputFeedback {:optional true} [:ref "InputFeedback"]]
  [:answer {:optional true} [:ref "Candidate"]]],
 "ListFilesResponse"
 [:map
  {:closed true}
  [:files {:optional true} [:vector [:ref "File"]]]
  [:nextPageToken {:optional true} :string]],
 "TransferOwnershipResponse" [:map {:closed true}],
 "ReviewSnippet"
 [:map
  {:closed true}
  [:googleMapsUri {:optional true} :string]
  [:reviewId {:optional true} :string]
  [:title {:optional true} :string]],
 "GoogleSearch"
 [:map
  {:closed true}
  [:timeRangeFilter {:optional true} [:ref "Interval"]]],
 "ChunkingConfig"
 [:map
  {:closed true}
  [:whiteSpaceConfig {:optional true} [:ref "WhiteSpaceConfig"]]],
 "TuningTask"
 [:map
  {:closed true}
  [:snapshots {:optional true} [:vector [:ref "TuningSnapshot"]]]
  [:hyperparameters {:optional true} [:ref "Hyperparameters"]]
  [:completeTime {:optional true} :string]
  [:trainingData {:optional true} [:ref "Dataset"]]
  [:startTime {:optional true} :string]],
 "CachedContent"
 [:map
  {:closed true}
  [:displayName {:optional true} :string]
  [:systemInstruction {:optional true} [:ref "Content"]]
  [:name {:optional true} :string]
  [:toolConfig {:optional true} [:ref "ToolConfig"]]
  [:tools {:optional true} [:vector [:ref "Tool"]]]
  [:createTime {:optional true} :string]
  [:updateTime {:optional true} :string]
  [:ttl {:optional true} :string]
  [:usageMetadata {:optional true} [:ref "CachedContentUsageMetadata"]]
  [:expireTime {:optional true} :string]
  [:contents {:optional true} [:vector [:ref "Content"]]]
  [:model {:optional true} :string]],
 "GenerateAnswerRequest"
 [:map
  {:closed true}
  [:temperature {:optional true} :double]
  [:answerStyle
   {:optional true}
   [:enum
    "ANSWER_STYLE_UNSPECIFIED"
    "ABSTRACTIVE"
    "EXTRACTIVE"
    "VERBOSE"]]
  [:semanticRetriever
   {:optional true}
   [:ref "SemanticRetrieverConfig"]]
  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
  [:inlinePassages {:optional true} [:ref "GroundingPassages"]]
  [:contents {:optional true} [:vector [:ref "Content"]]]],
 "Model"
 [:map
  {:closed true}
  [:description {:optional true} :string]
  [:displayName {:optional true} :string]
  [:name {:optional true} :string]
  [:maxTemperature {:optional true} :double]
  [:thinking {:optional true} :boolean]
  [:inputTokenLimit {:optional true} :int]
  [:topP {:optional true} :double]
  [:outputTokenLimit {:optional true} :int]
  [:supportedGenerationMethods {:optional true} [:vector :string]]
  [:baseModelId {:optional true} :string]
  [:topK {:optional true} :int]
  [:version {:optional true} :string]
  [:temperature {:optional true} :double]],
 "ListGeneratedFilesResponse"
 [:map
  {:closed true}
  [:generatedFiles {:optional true} [:vector [:ref "GeneratedFile"]]]
  [:nextPageToken {:optional true} :string]],
 "FunctionResponseBlob"
 [:map
  {:closed true}
  [:data {:optional true} :string]
  [:mimeType {:optional true} :string]],
 "Segment"
 [:map
  {:closed true}
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:partIndex {:optional true} :int]
  [:text {:optional true} :string]],
 "ToolConfig"
 [:map
  {:closed true}
  [:retrievalConfig {:optional true} [:ref "RetrievalConfig"]]
  [:functionCallingConfig
   {:optional true}
   [:ref "FunctionCallingConfig"]]],
 "GroundingAttribution"
 [:map
  {:closed true}
  [:sourceId {:optional true} [:ref "AttributionSourceId"]]
  [:content {:optional true} [:ref "Content"]]],
 "TopCandidates"
 [:map
  {:closed true}
  [:candidates
   {:optional true}
   [:vector [:ref "LogprobsResultCandidate"]]]],
 "GenerateMessageRequest"
 [:map
  {:closed true}
  [:topP {:optional true} :double]
  [:topK {:optional true} :int]
  [:candidateCount {:optional true} :int]
  [:temperature {:optional true} :double]
  [:prompt {:optional true} [:ref "MessagePrompt"]]],
 "FunctionResponsePart"
 [:map
  {:closed true}
  [:inlineData {:optional true} [:ref "FunctionResponseBlob"]]],
 "GenerateTextResponse"
 [:map
  {:closed true}
  [:safetyFeedback {:optional true} [:vector [:ref "SafetyFeedback"]]]
  [:candidates {:optional true} [:vector [:ref "TextCompletion"]]]
  [:filters {:optional true} [:vector [:ref "ContentFilter"]]]],
 "CreateFileResponse"
 [:map {:closed true} [:file {:optional true} [:ref "File"]]],
 "PrebuiltVoiceConfig"
 [:map {:closed true} [:voiceName {:optional true} :string]],
 "InlinedEmbedContentRequest"
 [:map
  {:closed true}
  [:metadata {:optional true} [:map-of :string :any]]
  [:request {:optional true} [:ref "EmbedContentRequest"]]],
 "GenerationConfig"
 [:map
  {:closed true}
  [:responseMimeType {:optional true} :string]
  [:responseSchema {:optional true} [:ref "Schema"]]
  [:responseJsonSchema {:optional true} :any]
  [:presencePenalty {:optional true} :double]
  [:stopSequences {:optional true} [:vector :string]]
  [:thinkingConfig {:optional true} [:ref "ThinkingConfig"]]
  [:enableEnhancedCivicAnswers {:optional true} :boolean]
  [:topP {:optional true} :double]
  [:maxOutputTokens {:optional true} :int]
  [:seed {:optional true} :int]
  [:logprobs {:optional true} :int]
  [:responseLogprobs {:optional true} :boolean]
  [:speechConfig {:optional true} [:ref "SpeechConfig"]]
  [:responseModalities
   {:optional true}
   [:vector [:enum "MODALITY_UNSPECIFIED" "TEXT" "IMAGE" "AUDIO"]]]
  [:frequencyPenalty {:optional true} :double]
  [:imageConfig {:optional true} [:ref "ImageConfig"]]
  [:topK {:optional true} :int]
  [:candidateCount {:optional true} :int]
  [:mediaResolution
   {:optional true}
   [:enum
    "MEDIA_RESOLUTION_UNSPECIFIED"
    "MEDIA_RESOLUTION_LOW"
    "MEDIA_RESOLUTION_MEDIUM"
    "MEDIA_RESOLUTION_HIGH"]]
  [:_responseJsonSchema {:optional true} :any]
  [:temperature {:optional true} :double]],
 "GenerateContentBatchOutput"
 [:map
  {:closed true}
  [:inlinedResponses {:optional true} [:ref "InlinedResponses"]]
  [:responsesFile {:optional true} :string]],
 "PromptFeedback"
 [:map
  {:closed true}
  [:blockReason
   {:optional true}
   [:enum
    "BLOCK_REASON_UNSPECIFIED"
    "SAFETY"
    "OTHER"
    "BLOCKLIST"
    "PROHIBITED_CONTENT"
    "IMAGE_SAFETY"]]
  [:safetyRatings {:optional true} [:vector [:ref "SafetyRating"]]]],
 "Message"
 [:map
  {:closed true}
  [:author {:optional true} :string]
  [:content {:optional true} :string]
  [:citationMetadata {:optional true} [:ref "CitationMetadata"]]],
 "TextCompletion"
 [:map
  {:closed true}
  [:output {:optional true} :string]
  [:citationMetadata {:optional true} [:ref "CitationMetadata"]]
  [:safetyRatings {:optional true} [:vector [:ref "SafetyRating"]]]],
 "ImageConfig"
 [:map
  {:closed true}
  [:aspectRatio {:optional true} :string]
  [:imageSize {:optional true} :string]],
 "BatchEmbedContentsResponse"
 [:map
  {:closed true}
  [:embeddings {:optional true} [:vector [:ref "ContentEmbedding"]]]],
 "CachedContentUsageMetadata"
 [:map {:closed true} [:totalTokenCount {:optional true} :int]],
 "CountMessageTokensResponse"
 [:map {:closed true} [:tokenCount {:optional true} :int]],
 "InlinedResponses"
 [:map
  {:closed true}
  [:inlinedResponses
   {:optional true}
   [:vector [:ref "InlinedResponse"]]]],
 "CountTextTokensResponse"
 [:map {:closed true} [:tokenCount {:optional true} :int]],
 "RetrievedContext"
 [:map
  {:closed true}
  [:fileSearchStore {:optional true} :string]
  [:uri {:optional true} :string]
  [:title {:optional true} :string]
  [:text {:optional true} :string]],
 "EmbedTextRequest"
 [:map
  {:closed true}
  [:text {:optional true} :string]
  [:model {:optional true} :string]],
 "ExecutableCode"
 [:map
  {:closed true}
  [:code {:optional true} :string]
  [:language
   {:optional true}
   [:enum "LANGUAGE_UNSPECIFIED" "PYTHON"]]],
 "Part"
 [:map
  {:closed true}
  [:inlineData {:optional true} [:ref "Blob"]]
  [:codeExecutionResult {:optional true} [:ref "CodeExecutionResult"]]
  [:thought {:optional true} :boolean]
  [:partMetadata {:optional true} [:map-of :string :any]]
  [:executableCode {:optional true} [:ref "ExecutableCode"]]
  [:functionCall {:optional true} [:ref "FunctionCall"]]
  [:fileData {:optional true} [:ref "FileData"]]
  [:videoMetadata {:optional true} [:ref "VideoMetadata"]]
  [:thoughtSignature {:optional true} :string]
  [:functionResponse {:optional true} [:ref "FunctionResponse"]]
  [:text {:optional true} :string]],
 "TunedModelSource"
 [:map
  {:closed true}
  [:tunedModel {:optional true} :string]
  [:baseModel {:optional true} :string]],
 "Content"
 [:map
  {:closed true}
  [:role {:optional true} :string]
  [:parts {:optional true} [:vector [:ref "Part"]]]],
 "SafetySetting"
 [:map
  {:closed true}
  [:category
   {:optional true}
   [:enum
    "HARM_CATEGORY_UNSPECIFIED"
    "HARM_CATEGORY_DEROGATORY"
    "HARM_CATEGORY_TOXICITY"
    "HARM_CATEGORY_VIOLENCE"
    "HARM_CATEGORY_SEXUAL"
    "HARM_CATEGORY_MEDICAL"
    "HARM_CATEGORY_DANGEROUS"
    "HARM_CATEGORY_HARASSMENT"
    "HARM_CATEGORY_HATE_SPEECH"
    "HARM_CATEGORY_SEXUALLY_EXPLICIT"
    "HARM_CATEGORY_DANGEROUS_CONTENT"
    "HARM_CATEGORY_CIVIC_INTEGRITY"]]
  [:threshold
   {:optional true}
   [:enum
    "HARM_BLOCK_THRESHOLD_UNSPECIFIED"
    "BLOCK_LOW_AND_ABOVE"
    "BLOCK_MEDIUM_AND_ABOVE"
    "BLOCK_ONLY_HIGH"
    "BLOCK_NONE"
    "OFF"]]],
 "TuningExample"
 [:map
  {:closed true}
  [:textInput {:optional true} :string]
  [:output {:optional true} :string]],
 "InlinedEmbedContentRequests"
 [:map
  {:closed true}
  [:requests
   {:optional true}
   [:vector [:ref "InlinedEmbedContentRequest"]]]],
 "GenerateMessageResponse"
 [:map
  {:closed true}
  [:filters {:optional true} [:vector [:ref "ContentFilter"]]]
  [:candidates {:optional true} [:vector [:ref "Message"]]]
  [:messages {:optional true} [:vector [:ref "Message"]]]],
 "CustomMetadata"
 [:map
  {:closed true}
  [:stringListValue {:optional true} [:ref "StringList"]]
  [:key {:optional true} :string]
  [:numericValue {:optional true} :double]
  [:stringValue {:optional true} :string]],
 "EmbedContentResponse"
 [:map
  {:closed true}
  [:embedding {:optional true} [:ref "ContentEmbedding"]]],
 "PredictRequest"
 [:map
  {:closed true}
  [:instances {:optional true} [:vector :any]]
  [:parameters {:optional true} :any]],
 "ListPermissionsResponse"
 [:map
  {:closed true}
  [:permissions {:optional true} [:vector [:ref "Permission"]]]
  [:nextPageToken {:optional true} :string]],
 "SemanticRetrieverConfig"
 [:map
  {:closed true}
  [:maxChunksCount {:optional true} :int]
  [:query {:optional true} [:ref "Content"]]
  [:metadataFilters {:optional true} [:vector [:ref "MetadataFilter"]]]
  [:minimumRelevanceScore {:optional true} :double]
  [:source {:optional true} :string]],
 "InlinedRequests"
 [:map
  {:closed true}
  [:requests {:optional true} [:vector [:ref "InlinedRequest"]]]],
 "GroundingPassages"
 [:map
  {:closed true}
  [:passages {:optional true} [:vector [:ref "GroundingPassage"]]]],
 "LogprobsResultCandidate"
 [:map
  {:closed true}
  [:token {:optional true} :string]
  [:logProbability {:optional true} :double]
  [:tokenId {:optional true} :int]],
 "GenerateContentRequest"
 [:map
  {:closed true}
  [:cachedContent {:optional true} :string]
  [:contents {:optional true} [:vector [:ref "Content"]]]
  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
  [:toolConfig {:optional true} [:ref "ToolConfig"]]
  [:systemInstruction {:optional true} [:ref "Content"]]
  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
  [:tools {:optional true} [:vector [:ref "Tool"]]]
  [:model {:optional true} :string]],
 "TuningExamples"
 [:map
  {:closed true}
  [:examples {:optional true} [:vector [:ref "TuningExample"]]]],
 "Web"
 [:map
  {:closed true}
  [:uri {:optional true} :string]
  [:title {:optional true} :string]],
 "PlaceAnswerSources"
 [:map
  {:closed true}
  [:reviewSnippets {:optional true} [:vector [:ref "ReviewSnippet"]]]],
 "ThinkingConfig"
 [:map
  {:closed true}
  [:thinkingLevel
   {:optional true}
   [:enum "THINKING_LEVEL_UNSPECIFIED" "LOW" "HIGH"]]
  [:includeThoughts {:optional true} :boolean]
  [:thinkingBudget {:optional true} :int]],
 "InputFeedback"
 [:map
  {:closed true}
  [:safetyRatings {:optional true} [:vector [:ref "SafetyRating"]]]
  [:blockReason
   {:optional true}
   [:enum "BLOCK_REASON_UNSPECIFIED" "SAFETY" "OTHER"]]],
 "FunctionCall"
 [:map
  {:closed true}
  [:id {:optional true} :string]
  [:args {:optional true} [:map-of :string :any]]
  [:name {:optional true} :string]],
 "AttributionSourceId"
 [:map
  {:closed true}
  [:semanticRetrieverChunk
   {:optional true}
   [:ref "SemanticRetrieverChunk"]]
  [:groundingPassage {:optional true} [:ref "GroundingPassageId"]]],
 "SafetyRating"
 [:map
  {:closed true}
  [:probability
   {:optional true}
   [:enum
    "HARM_PROBABILITY_UNSPECIFIED"
    "NEGLIGIBLE"
    "LOW"
    "MEDIUM"
    "HIGH"]]
  [:blocked {:optional true} :boolean]
  [:category
   {:optional true}
   [:enum
    "HARM_CATEGORY_UNSPECIFIED"
    "HARM_CATEGORY_DEROGATORY"
    "HARM_CATEGORY_TOXICITY"
    "HARM_CATEGORY_VIOLENCE"
    "HARM_CATEGORY_SEXUAL"
    "HARM_CATEGORY_MEDICAL"
    "HARM_CATEGORY_DANGEROUS"
    "HARM_CATEGORY_HARASSMENT"
    "HARM_CATEGORY_HATE_SPEECH"
    "HARM_CATEGORY_SEXUALLY_EXPLICIT"
    "HARM_CATEGORY_DANGEROUS_CONTENT"
    "HARM_CATEGORY_CIVIC_INTEGRITY"]]],
 "DynamicRetrievalConfig"
 [:map
  {:closed true}
  [:mode {:optional true} [:enum "MODE_UNSPECIFIED" "MODE_DYNAMIC"]]
  [:dynamicThreshold {:optional true} :double]],
 "TunedModel"
 [:map
  {:closed true}
  [:description {:optional true} :string]
  [:displayName {:optional true} :string]
  [:name {:optional true} :string]
  [:createTime {:optional true} :string]
  [:state
   {:optional true}
   [:enum "STATE_UNSPECIFIED" "CREATING" "ACTIVE" "FAILED"]]
  [:topP {:optional true} :double]
  [:baseModel {:optional true} :string]
  [:updateTime {:optional true} :string]
  [:tuningTask {:optional true} [:ref "TuningTask"]]
  [:readerProjectNumbers {:optional true} [:vector :string]]
  [:topK {:optional true} :int]
  [:tunedModelSource {:optional true} [:ref "TunedModelSource"]]
  [:temperature {:optional true} :double]],
 "ListCachedContentsResponse"
 [:map
  {:closed true}
  [:cachedContents {:optional true} [:vector [:ref "CachedContent"]]]
  [:nextPageToken {:optional true} :string]],
 "VoiceConfig"
 [:map
  {:closed true}
  [:prebuiltVoiceConfig
   {:optional true}
   [:ref "PrebuiltVoiceConfig"]]],
 "Condition"
 [:map
  {:closed true}
  [:stringValue {:optional true} :string]
  [:numericValue {:optional true} :double]
  [:operation
   {:optional true}
   [:enum
    "OPERATOR_UNSPECIFIED"
    "LESS"
    "LESS_EQUAL"
    "EQUAL"
    "GREATER_EQUAL"
    "GREATER"
    "NOT_EQUAL"
    "INCLUDES"
    "EXCLUDES"]]],
 "UrlContextMetadata"
 [:map
  {:closed true}
  [:urlMetadata {:optional true} [:vector [:ref "UrlMetadata"]]]],
 "FunctionCallingConfig"
 [:map
  {:closed true}
  [:mode
   {:optional true}
   [:enum "MODE_UNSPECIFIED" "AUTO" "ANY" "NONE" "VALIDATED"]]
  [:allowedFunctionNames {:optional true} [:vector :string]]],
 "InlinedRequest"
 [:map
  {:closed true}
  [:request {:optional true} [:ref "GenerateContentRequest"]]
  [:metadata {:optional true} [:map-of :string :any]]],
 "EmbedContentRequest"
 [:map
  {:closed true}
  [:model {:optional true} :string]
  [:outputDimensionality {:optional true} :int]
  [:content {:optional true} [:ref "Content"]]
  [:title {:optional true} :string]
  [:taskType
   {:optional true}
   [:enum
    "TASK_TYPE_UNSPECIFIED"
    "RETRIEVAL_QUERY"
    "RETRIEVAL_DOCUMENT"
    "SEMANTIC_SIMILARITY"
    "CLASSIFICATION"
    "CLUSTERING"
    "QUESTION_ANSWERING"
    "FACT_VERIFICATION"
    "CODE_RETRIEVAL_QUERY"]]],
 "InlinedEmbedContentResponse"
 [:map
  {:closed true}
  [:error {:optional true} [:ref "Status"]]
  [:metadata {:optional true} [:map-of :string :any]]
  [:response {:optional true} [:ref "EmbedContentResponse"]]],
 "VideoFileMetadata"
 [:map {:closed true} [:videoDuration {:optional true} :string]],
 "Blob"
 [:map
  {:closed true}
  [:mimeType {:optional true} :string]
  [:data {:optional true} :string]],
 "VideoMetadata"
 [:map
  {:closed true}
  [:fps {:optional true} :double]
  [:endOffset {:optional true} :string]
  [:startOffset {:optional true} :string]],
 "LogprobsResult"
 [:map
  {:closed true}
  [:topCandidates {:optional true} [:vector [:ref "TopCandidates"]]]
  [:chosenCandidates
   {:optional true}
   [:vector [:ref "LogprobsResultCandidate"]]]
  [:logProbabilitySum {:optional true} :double]],
 "BatchStats"
 [:map
  {:closed true}
  [:failedRequestCount {:optional true} :string]
  [:pendingRequestCount {:optional true} :string]
  [:successfulRequestCount {:optional true} :string]
  [:requestCount {:optional true} :string]],
 "BatchEmbedTextResponse"
 [:map
  {:closed true}
  [:embeddings {:optional true} [:vector [:ref "Embedding"]]]],
 "Maps"
 [:map
  {:closed true}
  [:placeId {:optional true} :string]
  [:uri {:optional true} :string]
  [:placeAnswerSources {:optional true} [:ref "PlaceAnswerSources"]]
  [:text {:optional true} :string]
  [:title {:optional true} :string]],
 "SpeechConfig"
 [:map
  {:closed true}
  [:voiceConfig {:optional true} [:ref "VoiceConfig"]]
  [:multiSpeakerVoiceConfig
   {:optional true}
   [:ref "MultiSpeakerVoiceConfig"]]
  [:languageCode {:optional true} :string]],
 "Document"
 [:map
  {:closed true}
  [:name {:optional true} :string]
  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
  [:displayName {:optional true} :string]
  [:updateTime {:optional true} :string]
  [:sizeBytes {:optional true} :string]
  [:mimeType {:optional true} :string]
  [:createTime {:optional true} :string]
  [:state
   {:optional true}
   [:enum
    "STATE_UNSPECIFIED"
    "STATE_PENDING"
    "STATE_ACTIVE"
    "STATE_FAILED"]]],
 "WhiteSpaceConfig"
 [:map
  {:closed true}
  [:maxOverlapTokens {:optional true} :int]
  [:maxTokensPerChunk {:optional true} :int]],
 "BatchEmbedContentsRequest"
 [:map
  {:closed true}
  [:requests {:optional true} [:vector [:ref "EmbedContentRequest"]]]],
 "CodeExecutionResult"
 [:map
  {:closed true}
  [:output {:optional true} :string]
  [:outcome
   {:optional true}
   [:enum
    "OUTCOME_UNSPECIFIED"
    "OUTCOME_OK"
    "OUTCOME_FAILED"
    "OUTCOME_DEADLINE_EXCEEDED"]]],
 "ModalityTokenCount"
 [:map
  {:closed true}
  [:modality
   {:optional true}
   [:enum
    "MODALITY_UNSPECIFIED"
    "TEXT"
    "IMAGE"
    "VIDEO"
    "AUDIO"
    "DOCUMENT"]]
  [:tokenCount {:optional true} :int]],
 "LatLng"
 [:map
  {:closed true}
  [:latitude {:optional true} :double]
  [:longitude {:optional true} :double]],
 "UrlMetadata"
 [:map
  {:closed true}
  [:retrievedUrl {:optional true} :string]
  [:urlRetrievalStatus
   {:optional true}
   [:enum
    "URL_RETRIEVAL_STATUS_UNSPECIFIED"
    "URL_RETRIEVAL_STATUS_SUCCESS"
    "URL_RETRIEVAL_STATUS_ERROR"
    "URL_RETRIEVAL_STATUS_PAYWALL"
    "URL_RETRIEVAL_STATUS_UNSAFE"]]],
 "Embedding"
 [:map {:closed true} [:value {:optional true} [:vector :double]]],
 "InlinedEmbedContentResponses"
 [:map
  {:closed true}
  [:inlinedResponses
   {:optional true}
   [:vector [:ref "InlinedEmbedContentResponse"]]]],
 "EmbedContentBatchStats"
 [:map
  {:closed true}
  [:pendingRequestCount {:optional true} :string]
  [:successfulRequestCount {:optional true} :string]
  [:requestCount {:optional true} :string]
  [:failedRequestCount {:optional true} :string]],
 "ListCorporaResponse"
 [:map
  {:closed true}
  [:corpora {:optional true} [:vector [:ref "Corpus"]]]
  [:nextPageToken {:optional true} :string]],
 "FileSearch"
 [:map
  {:closed true}
  [:fileSearchStoreNames {:optional true} [:vector :string]]
  [:topK {:optional true} :int]
  [:metadataFilter {:optional true} :string]],
 "CitationMetadata"
 [:map
  {:closed true}
  [:citationSources
   {:optional true}
   [:vector [:ref "CitationSource"]]]],
 "UrlContext" [:map {:closed true}]}
)

(def ops
{[:files :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:tuned-models :generate-text]
 {:method "POST",
  :path "v1beta/{+model}:generateText",
  :schema [:ref "GenerateTextRequest"]},
 [:models :async-batch-embed-content]
 {:method "POST",
  :path "v1beta/{+model}:asyncBatchEmbedContent",
  :schema [:ref "AsyncBatchEmbedContentRequest"]},
 [:models :list] {:method "GET", :path "v1beta/models"},
 [:permissions :patch]
 {:method "PATCH",
  :path "v1beta/{+name}",
  :schema [:ref "CachedContent"]},
 [:batches :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:models :batch-embed-text]
 {:method "POST",
  :path "v1beta/{+model}:batchEmbedText",
  :schema [:ref "BatchEmbedTextRequest"]},
 [:models :predict]
 {:method "POST",
  :path "v1beta/{+model}:predict",
  :schema [:ref "PredictRequest"]},
 [:models :count-tokens]
 {:method "POST",
  :path "v1beta/{+model}:countTokens",
  :schema [:ref "CountTokensRequest"]},
 [:tuned-models :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:file-search-stores :get] {:method "GET", :path "v1beta/{+name}"},
 [:corpora :get] {:method "GET", :path "v1beta/{+name}"},
 [:models :embed-content]
 {:method "POST",
  :path "v1beta/{+model}:embedContent",
  :schema [:ref "EmbedContentRequest"]},
 [:tuned-models :generate-content]
 {:method "POST",
  :path "v1beta/{+model}:generateContent",
  :schema [:ref "GenerateContentRequest"]},
 [:batches :get] {:method "GET", :path "v1beta/{+name}"},
 [:cached-contents :patch]
 {:method "PATCH",
  :path "v1beta/{+name}",
  :schema [:ref "CachedContent"]},
 [:models :stream-generate-content]
 {:method "POST",
  :path "v1beta/{+model}:streamGenerateContent",
  :schema [:ref "GenerateContentRequest"]},
 [:media :upload-to-file-search-store]
 {:method "POST",
  :path "v1beta/{+fileSearchStoreName}:uploadToFileSearchStore",
  :schema [:ref "UploadToFileSearchStoreRequest"]},
 [:file-search-stores :delete]
 {:method "DELETE", :path "v1beta/{+name}"},
 [:media :upload]
 {:method "POST",
  :path "v1beta/files",
  :schema [:ref "CreateFileRequest"]},
 [:models :count-text-tokens]
 {:method "POST",
  :path "v1beta/{+model}:countTextTokens",
  :schema [:ref "CountTextTokensRequest"]},
 [:models :get] {:method "GET", :path "v1beta/{+name}"},
 [:permissions :get] {:method "GET", :path "v1beta/{+name}"},
 [:file-search-stores :import-file]
 {:method "POST",
  :path "v1beta/{+fileSearchStoreName}:importFile",
  :schema [:ref "ImportFileRequest"]},
 [:models :predict-long-running]
 {:method "POST",
  :path "v1beta/{+model}:predictLongRunning",
  :schema [:ref "PredictLongRunningRequest"]},
 [:corpora :create]
 {:method "POST", :path "v1beta/corpora", :schema [:ref "Corpus"]},
 [:batches :cancel] {:method "POST", :path "v1beta/{+name}:cancel"},
 [:tuned-models :patch]
 {:method "PATCH",
  :path "v1beta/{+name}",
  :schema [:ref "CachedContent"]},
 [:cached-contents :get] {:method "GET", :path "v1beta/{+name}"},
 [:cached-contents :create]
 {:method "POST",
  :path "v1beta/cachedContents",
  :schema [:ref "CachedContent"]},
 [:corpora :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:dynamic :stream-generate-content]
 {:method "POST",
  :path "v1beta/{+model}:streamGenerateContent",
  :schema [:ref "GenerateContentRequest"]},
 [:models :generate-content]
 {:method "POST",
  :path "v1beta/{+model}:generateContent",
  :schema [:ref "GenerateContentRequest"]},
 [:tuned-models :get] {:method "GET", :path "v1beta/{+name}"},
 [:documents :list]
 {:method "GET", :path "v1beta/{+parent}/documents"},
 [:models :batch-embed-contents]
 {:method "POST",
  :path "v1beta/{+model}:batchEmbedContents",
  :schema [:ref "BatchEmbedContentsRequest"]},
 [:batches :update-embed-content-batch]
 {:method "PATCH",
  :path "v1beta/{+name}:updateEmbedContentBatch",
  :schema [:ref "EmbedContentBatch"]},
 [:tuned-models :batch-generate-content]
 {:method "POST",
  :path "v1beta/{+model}:batchGenerateContent",
  :schema [:ref "BatchGenerateContentRequest"]},
 [:batches :update-generate-content-batch]
 {:method "PATCH",
  :path "v1beta/{+name}:updateGenerateContentBatch",
  :schema [:ref "GenerateContentBatch"]},
 [:file-search-stores :create]
 {:method "POST",
  :path "v1beta/fileSearchStores",
  :schema [:ref "FileSearchStore"]},
 [:corpora :list] {:method "GET", :path "v1beta/corpora"},
 [:cached-contents :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:operations :get] {:method "GET", :path "v1beta/{+name}"},
 [:tuned-models :create]
 {:method "POST",
  :path "v1beta/tunedModels",
  :schema [:ref "TunedModel"]},
 [:models :generate-answer]
 {:method "POST",
  :path "v1beta/{+model}:generateAnswer",
  :schema [:ref "GenerateAnswerRequest"]},
 [:tuned-models :stream-generate-content]
 {:method "POST",
  :path "v1beta/{+model}:streamGenerateContent",
  :schema [:ref "GenerateContentRequest"]},
 [:tuned-models :async-batch-embed-content]
 {:method "POST",
  :path "v1beta/{+model}:asyncBatchEmbedContent",
  :schema [:ref "AsyncBatchEmbedContentRequest"]},
 [:tuned-models :transfer-ownership]
 {:method "POST",
  :path "v1beta/{+name}:transferOwnership",
  :schema [:ref "TransferOwnershipRequest"]},
 [:file-search-stores :list]
 {:method "GET", :path "v1beta/fileSearchStores"},
 [:permissions :create]
 {:method "POST",
  :path "v1beta/{+parent}/permissions",
  :schema [:ref "Permission"]},
 [:files :get] {:method "GET", :path "v1beta/{+name}"},
 [:models :batch-generate-content]
 {:method "POST",
  :path "v1beta/{+model}:batchGenerateContent",
  :schema [:ref "BatchGenerateContentRequest"]},
 [:permissions :list]
 {:method "GET", :path "v1beta/{+parent}/permissions"},
 [:documents :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:permissions :delete] {:method "DELETE", :path "v1beta/{+name}"},
 [:files :list] {:method "GET", :path "v1beta/files"},
 [:operations :list]
 {:method "GET", :path "v1beta/{+name}/operations"},
 [:cached-contents :list]
 {:method "GET", :path "v1beta/cachedContents"},
 [:models :count-message-tokens]
 {:method "POST",
  :path "v1beta/{+model}:countMessageTokens",
  :schema [:ref "CountMessageTokensRequest"]},
 [:batches :list] {:method "GET", :path "v1beta/{+name}"},
 [:models :embed-text]
 {:method "POST",
  :path "v1beta/{+model}:embedText",
  :schema [:ref "EmbedTextRequest"]},
 [:dynamic :generate-content]
 {:method "POST",
  :path "v1beta/{+model}:generateContent",
  :schema [:ref "GenerateContentRequest"]},
 [:documents :get] {:method "GET", :path "v1beta/{+name}"},
 [:generated-files :list]
 {:method "GET", :path "v1beta/generatedFiles"},
 [:tuned-models :list] {:method "GET", :path "v1beta/tunedModels"},
 [:models :generate-message]
 {:method "POST",
  :path "v1beta/{+model}:generateMessage",
  :schema [:ref "GenerateMessageRequest"]},
 [:models :generate-text]
 {:method "POST",
  :path "v1beta/{+model}:generateText",
  :schema [:ref "GenerateTextRequest"]}}
)

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
