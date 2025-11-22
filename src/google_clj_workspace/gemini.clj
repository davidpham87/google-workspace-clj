(ns google-clj-workspace.gemini
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]))

(def discovery-url "https://generativelanguage.googleapis.com/$discovery/rest?version=v1beta")

(def discovery-data (discovery/parse-discovery-schema discovery-url))

(def registry
{"BatchEmbedTextRequest"
 [:map
  {:closed false}
  [:texts {:optional true} [:vector :string]]
  [:requests {:optional true} [:vector [:ref "EmbedTextRequest"]]]],
 "PredictLongRunningRequest"
 [:map
  {:closed false}
  [:instances {:optional true} [:vector :any]]
  [:parameters {:optional true} :any]],
 "InlinedResponse"
 [:map
  {:closed false}
  [:error {:optional true} [:ref "Status"]]
  [:response {:optional true} [:ref "GenerateContentResponse"]]
  [:metadata {:optional true} [:map-of :string :any]]],
 "StringList"
 [:map {:closed false} [:values {:optional true} [:vector :string]]],
 "Empty" [:map {:closed false}],
 "ListDocumentsResponse"
 [:map
  {:closed false}
  [:documents {:optional true} [:vector [:ref "Document"]]]
  [:nextPageToken {:optional true} :string]],
 "Schema"
 [:map
  {:closed false}
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
  {:closed false}
  [:error {:optional true} [:ref "Status"]]
  [:response {:optional true} [:map-of :string :any]]
  [:name {:optional true} :string]
  [:metadata {:optional true} [:map-of :string :any]]
  [:done {:optional true} :boolean]],
 "GoogleSearchRetrieval"
 [:map
  {:closed false}
  [:dynamicRetrievalConfig
   {:optional true}
   [:ref "DynamicRetrievalConfig"]]],
 "SafetyFeedback"
 [:map
  {:closed false}
  [:rating {:optional true} [:ref "SafetyRating"]]
  [:setting {:optional true} [:ref "SafetySetting"]]],
 "GenerateContentBatch"
 [:map
  {:closed false}
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
  {:closed false}
  [:embedding {:optional true} [:ref "Embedding"]]],
 "Candidate"
 [:map
  {:closed false}
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
 "TextPrompt" [:map {:closed false} [:text {:optional true} :string]],
 "CountMessageTokensRequest"
 [:map
  {:closed false}
  [:prompt {:optional true} [:ref "MessagePrompt"]]],
 "ImportFileRequest"
 [:map
  {:closed false}
  [:fileName {:optional true} :string]
  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
  [:chunkingConfig {:optional true} [:ref "ChunkingConfig"]]],
 "Dataset"
 [:map
  {:closed false}
  [:examples {:optional true} [:ref "TuningExamples"]]],
 "GeneratedFile"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:mimeType {:optional true} :string]
  [:state
   {:optional true}
   [:enum "STATE_UNSPECIFIED" "GENERATING" "GENERATED" "FAILED"]]
  [:error {:optional true} [:ref "Status"]]],
 "UploadToFileSearchStoreRequest"
 [:map
  {:closed false}
  [:displayName {:optional true} :string]
  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
  [:chunkingConfig {:optional true} [:ref "ChunkingConfig"]]
  [:mimeType {:optional true} :string]],
 "SpeakerVoiceConfig"
 [:map
  {:closed false}
  [:speaker {:optional true} :string]
  [:voiceConfig {:optional true} [:ref "VoiceConfig"]]],
 "RetrievalConfig"
 [:map
  {:closed false}
  [:latLng {:optional true} [:ref "LatLng"]]
  [:languageCode {:optional true} :string]],
 "FunctionResponse"
 [:map
  {:closed false}
  [:id {:optional true} :string]
  [:name {:optional true} :string]
  [:response {:optional true} [:map-of :string :any]]
  [:parts {:optional true} [:vector [:ref "FunctionResponsePart"]]]
  [:willContinue {:optional true} :boolean]
  [:scheduling
   {:optional true}
   [:enum "SCHEDULING_UNSPECIFIED" "SILENT" "WHEN_IDLE" "INTERRUPT"]]],
 "CountTextTokensRequest"
 [:map {:closed false} [:prompt {:optional true} [:ref "TextPrompt"]]],
 "EmbedContentBatchOutput"
 [:map
  {:closed false}
  [:responsesFile {:optional true} :string]
  [:inlinedResponses
   {:optional true}
   [:ref "InlinedEmbedContentResponses"]]],
 "FileSearchStore"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:displayName {:optional true} :string]
  [:createTime {:optional true} :string]
  [:updateTime {:optional true} :string]
  [:activeDocumentsCount {:optional true} :string]
  [:pendingDocumentsCount {:optional true} :string]
  [:failedDocumentsCount {:optional true} :string]
  [:sizeBytes {:optional true} :string]],
 "MetadataFilter"
 [:map
  {:closed false}
  [:key {:optional true} :string]
  [:conditions {:optional true} [:vector [:ref "Condition"]]]],
 "SearchEntryPoint"
 [:map
  {:closed false}
  [:renderedContent {:optional true} :string]
  [:sdkBlob {:optional true} :string]],
 "InputEmbedContentConfig"
 [:map
  {:closed false}
  [:fileName {:optional true} :string]
  [:requests {:optional true} [:ref "InlinedEmbedContentRequests"]]],
 "Example"
 [:map
  {:closed false}
  [:input {:optional true} [:ref "Message"]]
  [:output {:optional true} [:ref "Message"]]],
 "ListOperationsResponse"
 [:map
  {:closed false}
  [:operations {:optional true} [:vector [:ref "Operation"]]]
  [:nextPageToken {:optional true} :string]
  [:unreachable {:optional true} [:vector :string]]],
 "CountTokensResponse"
 [:map
  {:closed false}
  [:totalTokens {:optional true} :int]
  [:cachedContentTokenCount {:optional true} :int]
  [:promptTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]
  [:cacheTokensDetails
   {:optional true}
   [:vector [:ref "ModalityTokenCount"]]]],
 "Permission"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:granteeType
   {:optional true}
   [:enum "GRANTEE_TYPE_UNSPECIFIED" "USER" "GROUP" "EVERYONE"]]
  [:emailAddress {:optional true} :string]
  [:role
   {:optional true}
   [:enum "ROLE_UNSPECIFIED" "OWNER" "WRITER" "READER"]]],
 "GenerateTextRequest"
 [:map
  {:closed false}
  [:prompt {:optional true} [:ref "TextPrompt"]]
  [:temperature {:optional true} :double]
  [:candidateCount {:optional true} :int]
  [:maxOutputTokens {:optional true} :int]
  [:topP {:optional true} :double]
  [:topK {:optional true} :int]
  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
  [:stopSequences {:optional true} [:vector :string]]],
 "Tool"
 [:map
  {:closed false}
  [:functionDeclarations
   {:optional true}
   [:vector [:ref "FunctionDeclaration"]]]
  [:googleSearchRetrieval
   {:optional true}
   [:ref "GoogleSearchRetrieval"]]
  [:codeExecution {:optional true} [:ref "CodeExecution"]]
  [:googleSearch {:optional true} [:ref "GoogleSearch"]]
  [:computerUse {:optional true} [:ref "ComputerUse"]]
  [:urlContext {:optional true} [:ref "UrlContext"]]
  [:fileSearch {:optional true} [:ref "FileSearch"]]
  [:googleMaps {:optional true} [:ref "GoogleMaps"]]],
 "TransferOwnershipRequest"
 [:map {:closed false} [:emailAddress {:optional true} :string]],
 "GroundingPassage"
 [:map
  {:closed false}
  [:id {:optional true} :string]
  [:content {:optional true} [:ref "Content"]]],
 "CodeExecution" [:map {:closed false}],
 "InputConfig"
 [:map
  {:closed false}
  [:fileName {:optional true} :string]
  [:requests {:optional true} [:ref "InlinedRequests"]]],
 "BatchGenerateContentRequest"
 [:map
  {:closed false}
  [:batch {:optional true} [:ref "GenerateContentBatch"]]],
 "ComputerUse"
 [:map
  {:closed false}
  [:environment
   {:optional true}
   [:enum "ENVIRONMENT_UNSPECIFIED" "ENVIRONMENT_BROWSER"]]
  [:excludedPredefinedFunctions {:optional true} [:vector :string]]],
 "ContentEmbedding"
 [:map {:closed false} [:values {:optional true} [:vector :double]]],
 "CreateFileRequest"
 [:map {:closed false} [:file {:optional true} [:ref "File"]]],
 "UsageMetadata"
 [:map
  {:closed false}
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
  {:closed false}
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:uri {:optional true} :string]
  [:license {:optional true} :string]],
 "EmbedContentBatch"
 [:map
  {:closed false}
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
  {:closed false}
  [:name {:optional true} :string]
  [:description {:optional true} :string]
  [:parameters {:optional true} [:ref "Schema"]]
  [:parametersJsonSchema {:optional true} :any]
  [:response {:optional true} [:ref "Schema"]]
  [:responseJsonSchema {:optional true} :any]
  [:behavior
   {:optional true}
   [:enum "UNSPECIFIED" "BLOCKING" "NON_BLOCKING"]]],
 "ContentFilter"
 [:map
  {:closed false}
  [:reason
   {:optional true}
   [:enum "BLOCKED_REASON_UNSPECIFIED" "SAFETY" "OTHER"]]
  [:message {:optional true} :string]],
 "Hyperparameters"
 [:map
  {:closed false}
  [:learningRate {:optional true} :double]
  [:learningRateMultiplier {:optional true} :double]
  [:epochCount {:optional true} :int]
  [:batchSize {:optional true} :int]],
 "FileData"
 [:map
  {:closed false}
  [:mimeType {:optional true} :string]
  [:fileUri {:optional true} :string]],
 "AsyncBatchEmbedContentRequest"
 [:map
  {:closed false}
  [:batch {:optional true} [:ref "EmbedContentBatch"]]],
 "File"
 [:map
  {:closed false}
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
  {:closed false}
  [:name {:optional true} :string]
  [:displayName {:optional true} :string]
  [:createTime {:optional true} :string]
  [:updateTime {:optional true} :string]],
 "PredictResponse"
 [:map {:closed false} [:predictions {:optional true} [:vector :any]]],
 "RetrievalMetadata"
 [:map
  {:closed false}
  [:googleSearchDynamicRetrievalScore {:optional true} :double]],
 "ListTunedModelsResponse"
 [:map
  {:closed false}
  [:tunedModels {:optional true} [:vector [:ref "TunedModel"]]]
  [:nextPageToken {:optional true} :string]],
 "MessagePrompt"
 [:map
  {:closed false}
  [:context {:optional true} :string]
  [:examples {:optional true} [:vector [:ref "Example"]]]
  [:messages {:optional true} [:vector [:ref "Message"]]]],
 "TuningSnapshot"
 [:map
  {:closed false}
  [:step {:optional true} :int]
  [:epoch {:optional true} :int]
  [:meanLoss {:optional true} :double]
  [:computeTime {:optional true} :string]],
 "GroundingMetadata"
 [:map
  {:closed false}
  [:searchEntryPoint {:optional true} [:ref "SearchEntryPoint"]]
  [:groundingChunks {:optional true} [:vector [:ref "GroundingChunk"]]]
  [:groundingSupports
   {:optional true}
   [:vector [:ref "GroundingSupport"]]]
  [:retrievalMetadata {:optional true} [:ref "RetrievalMetadata"]]
  [:webSearchQueries {:optional true} [:vector :string]]
  [:googleMapsWidgetContextToken {:optional true} :string]],
 "ListFileSearchStoresResponse"
 [:map
  {:closed false}
  [:fileSearchStores
   {:optional true}
   [:vector [:ref "FileSearchStore"]]]
  [:nextPageToken {:optional true} :string]],
 "GenerateContentResponse"
 [:map
  {:closed false}
  [:candidates {:optional true} [:vector [:ref "Candidate"]]]
  [:promptFeedback {:optional true} [:ref "PromptFeedback"]]
  [:usageMetadata {:optional true} [:ref "UsageMetadata"]]
  [:modelVersion {:optional true} :string]
  [:responseId {:optional true} :string]],
 "MultiSpeakerVoiceConfig"
 [:map
  {:closed false}
  [:speakerVoiceConfigs
   {:optional true}
   [:vector [:ref "SpeakerVoiceConfig"]]]],
 "DownloadFileResponse" [:map {:closed false}],
 "Operation"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:metadata {:optional true} [:map-of :string :any]]
  [:done {:optional true} :boolean]
  [:error {:optional true} [:ref "Status"]]
  [:response {:optional true} [:map-of :string :any]]],
 "CountTokensRequest"
 [:map
  {:closed false}
  [:contents {:optional true} [:vector [:ref "Content"]]]
  [:generateContentRequest
   {:optional true}
   [:ref "GenerateContentRequest"]]],
 "GoogleMaps"
 [:map {:closed false} [:enableWidget {:optional true} :boolean]],
 "SemanticRetrieverChunk"
 [:map
  {:closed false}
  [:source {:optional true} :string]
  [:chunk {:optional true} :string]],
 "Status"
 [:map
  {:closed false}
  [:code {:optional true} :int]
  [:message {:optional true} :string]
  [:details {:optional true} [:vector [:map-of :string :any]]]],
 "GroundingChunk"
 [:map
  {:closed false}
  [:web {:optional true} [:ref "Web"]]
  [:retrievedContext {:optional true} [:ref "RetrievedContext"]]
  [:maps {:optional true} [:ref "Maps"]]],
 "ListModelsResponse"
 [:map
  {:closed false}
  [:models {:optional true} [:vector [:ref "Model"]]]
  [:nextPageToken {:optional true} :string]],
 "Interval"
 [:map
  {:closed false}
  [:startTime {:optional true} :string]
  [:endTime {:optional true} :string]],
 "GroundingPassageId"
 [:map
  {:closed false}
  [:passageId {:optional true} :string]
  [:partIndex {:optional true} :int]],
 "GroundingSupport"
 [:map
  {:closed false}
  [:segment {:optional true} [:ref "Segment"]]
  [:groundingChunkIndices {:optional true} [:vector :int]]
  [:confidenceScores {:optional true} [:vector :double]]],
 "GenerateAnswerResponse"
 [:map
  {:closed false}
  [:answer {:optional true} [:ref "Candidate"]]
  [:answerableProbability {:optional true} :double]
  [:inputFeedback {:optional true} [:ref "InputFeedback"]]],
 "ListFilesResponse"
 [:map
  {:closed false}
  [:files {:optional true} [:vector [:ref "File"]]]
  [:nextPageToken {:optional true} :string]],
 "TransferOwnershipResponse" [:map {:closed false}],
 "ReviewSnippet"
 [:map
  {:closed false}
  [:reviewId {:optional true} :string]
  [:googleMapsUri {:optional true} :string]
  [:title {:optional true} :string]],
 "GoogleSearch"
 [:map
  {:closed false}
  [:timeRangeFilter {:optional true} [:ref "Interval"]]],
 "ChunkingConfig"
 [:map
  {:closed false}
  [:whiteSpaceConfig {:optional true} [:ref "WhiteSpaceConfig"]]],
 "TuningTask"
 [:map
  {:closed false}
  [:startTime {:optional true} :string]
  [:completeTime {:optional true} :string]
  [:snapshots {:optional true} [:vector [:ref "TuningSnapshot"]]]
  [:trainingData {:optional true} [:ref "Dataset"]]
  [:hyperparameters {:optional true} [:ref "Hyperparameters"]]],
 "CachedContent"
 [:map
  {:closed false}
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
  {:closed false}
  [:inlinePassages {:optional true} [:ref "GroundingPassages"]]
  [:semanticRetriever
   {:optional true}
   [:ref "SemanticRetrieverConfig"]]
  [:contents {:optional true} [:vector [:ref "Content"]]]
  [:answerStyle
   {:optional true}
   [:enum
    "ANSWER_STYLE_UNSPECIFIED"
    "ABSTRACTIVE"
    "EXTRACTIVE"
    "VERBOSE"]]
  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
  [:temperature {:optional true} :double]],
 "Model"
 [:map
  {:closed false}
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
  {:closed false}
  [:generatedFiles {:optional true} [:vector [:ref "GeneratedFile"]]]
  [:nextPageToken {:optional true} :string]],
 "FunctionResponseBlob"
 [:map
  {:closed false}
  [:mimeType {:optional true} :string]
  [:data {:optional true} :string]],
 "Segment"
 [:map
  {:closed false}
  [:partIndex {:optional true} :int]
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:text {:optional true} :string]],
 "ToolConfig"
 [:map
  {:closed false}
  [:functionCallingConfig
   {:optional true}
   [:ref "FunctionCallingConfig"]]
  [:retrievalConfig {:optional true} [:ref "RetrievalConfig"]]],
 "GroundingAttribution"
 [:map
  {:closed false}
  [:sourceId {:optional true} [:ref "AttributionSourceId"]]
  [:content {:optional true} [:ref "Content"]]],
 "TopCandidates"
 [:map
  {:closed false}
  [:candidates
   {:optional true}
   [:vector [:ref "LogprobsResultCandidate"]]]],
 "GenerateMessageRequest"
 [:map
  {:closed false}
  [:prompt {:optional true} [:ref "MessagePrompt"]]
  [:temperature {:optional true} :double]
  [:candidateCount {:optional true} :int]
  [:topP {:optional true} :double]
  [:topK {:optional true} :int]],
 "FunctionResponsePart"
 [:map
  {:closed false}
  [:inlineData {:optional true} [:ref "FunctionResponseBlob"]]],
 "GenerateTextResponse"
 [:map
  {:closed false}
  [:candidates {:optional true} [:vector [:ref "TextCompletion"]]]
  [:filters {:optional true} [:vector [:ref "ContentFilter"]]]
  [:safetyFeedback
   {:optional true}
   [:vector [:ref "SafetyFeedback"]]]],
 "CreateFileResponse"
 [:map {:closed false} [:file {:optional true} [:ref "File"]]],
 "PrebuiltVoiceConfig"
 [:map {:closed false} [:voiceName {:optional true} :string]],
 "InlinedEmbedContentRequest"
 [:map
  {:closed false}
  [:request {:optional true} [:ref "EmbedContentRequest"]]
  [:metadata {:optional true} [:map-of :string :any]]],
 "GenerationConfig"
 [:map
  {:closed false}
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
  {:closed false}
  [:responsesFile {:optional true} :string]
  [:inlinedResponses {:optional true} [:ref "InlinedResponses"]]],
 "PromptFeedback"
 [:map
  {:closed false}
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
  {:closed false}
  [:author {:optional true} :string]
  [:content {:optional true} :string]
  [:citationMetadata {:optional true} [:ref "CitationMetadata"]]],
 "TextCompletion"
 [:map
  {:closed false}
  [:output {:optional true} :string]
  [:safetyRatings {:optional true} [:vector [:ref "SafetyRating"]]]
  [:citationMetadata {:optional true} [:ref "CitationMetadata"]]],
 "ImageConfig"
 [:map
  {:closed false}
  [:aspectRatio {:optional true} :string]
  [:imageSize {:optional true} :string]],
 "BatchEmbedContentsResponse"
 [:map
  {:closed false}
  [:embeddings {:optional true} [:vector [:ref "ContentEmbedding"]]]],
 "CachedContentUsageMetadata"
 [:map {:closed false} [:totalTokenCount {:optional true} :int]],
 "CountMessageTokensResponse"
 [:map {:closed false} [:tokenCount {:optional true} :int]],
 "InlinedResponses"
 [:map
  {:closed false}
  [:inlinedResponses
   {:optional true}
   [:vector [:ref "InlinedResponse"]]]],
 "CountTextTokensResponse"
 [:map {:closed false} [:tokenCount {:optional true} :int]],
 "RetrievedContext"
 [:map
  {:closed false}
  [:uri {:optional true} :string]
  [:title {:optional true} :string]
  [:text {:optional true} :string]
  [:fileSearchStore {:optional true} :string]],
 "EmbedTextRequest"
 [:map
  {:closed false}
  [:model {:optional true} :string]
  [:text {:optional true} :string]],
 "ExecutableCode"
 [:map
  {:closed false}
  [:language {:optional true} [:enum "LANGUAGE_UNSPECIFIED" "PYTHON"]]
  [:code {:optional true} :string]],
 "Part"
 [:map
  {:closed false}
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
  {:closed false}
  [:tunedModel {:optional true} :string]
  [:baseModel {:optional true} :string]],
 "Content"
 [:map
  {:closed false}
  [:parts {:optional true} [:vector [:ref "Part"]]]
  [:role {:optional true} :string]],
 "SafetySetting"
 [:map
  {:closed false}
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
  {:closed false}
  [:textInput {:optional true} :string]
  [:output {:optional true} :string]],
 "InlinedEmbedContentRequests"
 [:map
  {:closed false}
  [:requests
   {:optional true}
   [:vector [:ref "InlinedEmbedContentRequest"]]]],
 "GenerateMessageResponse"
 [:map
  {:closed false}
  [:candidates {:optional true} [:vector [:ref "Message"]]]
  [:messages {:optional true} [:vector [:ref "Message"]]]
  [:filters {:optional true} [:vector [:ref "ContentFilter"]]]],
 "CustomMetadata"
 [:map
  {:closed false}
  [:stringValue {:optional true} :string]
  [:stringListValue {:optional true} [:ref "StringList"]]
  [:numericValue {:optional true} :double]
  [:key {:optional true} :string]],
 "EmbedContentResponse"
 [:map
  {:closed false}
  [:embedding {:optional true} [:ref "ContentEmbedding"]]],
 "PredictRequest"
 [:map
  {:closed false}
  [:instances {:optional true} [:vector :any]]
  [:parameters {:optional true} :any]],
 "ListPermissionsResponse"
 [:map
  {:closed false}
  [:permissions {:optional true} [:vector [:ref "Permission"]]]
  [:nextPageToken {:optional true} :string]],
 "SemanticRetrieverConfig"
 [:map
  {:closed false}
  [:source {:optional true} :string]
  [:query {:optional true} [:ref "Content"]]
  [:metadataFilters {:optional true} [:vector [:ref "MetadataFilter"]]]
  [:maxChunksCount {:optional true} :int]
  [:minimumRelevanceScore {:optional true} :double]],
 "InlinedRequests"
 [:map
  {:closed false}
  [:requests {:optional true} [:vector [:ref "InlinedRequest"]]]],
 "GroundingPassages"
 [:map
  {:closed false}
  [:passages {:optional true} [:vector [:ref "GroundingPassage"]]]],
 "LogprobsResultCandidate"
 [:map
  {:closed false}
  [:token {:optional true} :string]
  [:tokenId {:optional true} :int]
  [:logProbability {:optional true} :double]],
 "GenerateContentRequest"
 [:map
  {:closed false}
  [:model {:optional true} :string]
  [:systemInstruction {:optional true} [:ref "Content"]]
  [:contents {:optional true} [:vector [:ref "Content"]]]
  [:tools {:optional true} [:vector [:ref "Tool"]]]
  [:toolConfig {:optional true} [:ref "ToolConfig"]]
  [:safetySettings {:optional true} [:vector [:ref "SafetySetting"]]]
  [:generationConfig {:optional true} [:ref "GenerationConfig"]]
  [:cachedContent {:optional true} :string]],
 "TuningExamples"
 [:map
  {:closed false}
  [:examples {:optional true} [:vector [:ref "TuningExample"]]]],
 "Web"
 [:map
  {:closed false}
  [:uri {:optional true} :string]
  [:title {:optional true} :string]],
 "PlaceAnswerSources"
 [:map
  {:closed false}
  [:reviewSnippets {:optional true} [:vector [:ref "ReviewSnippet"]]]],
 "ThinkingConfig"
 [:map
  {:closed false}
  [:includeThoughts {:optional true} :boolean]
  [:thinkingBudget {:optional true} :int]
  [:thinkingLevel
   {:optional true}
   [:enum "THINKING_LEVEL_UNSPECIFIED" "LOW" "HIGH"]]],
 "InputFeedback"
 [:map
  {:closed false}
  [:blockReason
   {:optional true}
   [:enum "BLOCK_REASON_UNSPECIFIED" "SAFETY" "OTHER"]]
  [:safetyRatings {:optional true} [:vector [:ref "SafetyRating"]]]],
 "FunctionCall"
 [:map
  {:closed false}
  [:id {:optional true} :string]
  [:name {:optional true} :string]
  [:args {:optional true} [:map-of :string :any]]],
 "AttributionSourceId"
 [:map
  {:closed false}
  [:groundingPassage {:optional true} [:ref "GroundingPassageId"]]
  [:semanticRetrieverChunk
   {:optional true}
   [:ref "SemanticRetrieverChunk"]]],
 "SafetyRating"
 [:map
  {:closed false}
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
  [:probability
   {:optional true}
   [:enum
    "HARM_PROBABILITY_UNSPECIFIED"
    "NEGLIGIBLE"
    "LOW"
    "MEDIUM"
    "HIGH"]]
  [:blocked {:optional true} :boolean]],
 "DynamicRetrievalConfig"
 [:map
  {:closed false}
  [:mode {:optional true} [:enum "MODE_UNSPECIFIED" "MODE_DYNAMIC"]]
  [:dynamicThreshold {:optional true} :double]],
 "TunedModel"
 [:map
  {:closed false}
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
  {:closed false}
  [:cachedContents {:optional true} [:vector [:ref "CachedContent"]]]
  [:nextPageToken {:optional true} :string]],
 "VoiceConfig"
 [:map
  {:closed false}
  [:prebuiltVoiceConfig
   {:optional true}
   [:ref "PrebuiltVoiceConfig"]]],
 "Condition"
 [:map
  {:closed false}
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
  {:closed false}
  [:urlMetadata {:optional true} [:vector [:ref "UrlMetadata"]]]],
 "FunctionCallingConfig"
 [:map
  {:closed false}
  [:mode
   {:optional true}
   [:enum "MODE_UNSPECIFIED" "AUTO" "ANY" "NONE" "VALIDATED"]]
  [:allowedFunctionNames {:optional true} [:vector :string]]],
 "InlinedRequest"
 [:map
  {:closed false}
  [:request {:optional true} [:ref "GenerateContentRequest"]]
  [:metadata {:optional true} [:map-of :string :any]]],
 "EmbedContentRequest"
 [:map
  {:closed false}
  [:model {:optional true} :string]
  [:content {:optional true} [:ref "Content"]]
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
    "CODE_RETRIEVAL_QUERY"]]
  [:title {:optional true} :string]
  [:outputDimensionality {:optional true} :int]],
 "InlinedEmbedContentResponse"
 [:map
  {:closed false}
  [:error {:optional true} [:ref "Status"]]
  [:response {:optional true} [:ref "EmbedContentResponse"]]
  [:metadata {:optional true} [:map-of :string :any]]],
 "VideoFileMetadata"
 [:map {:closed false} [:videoDuration {:optional true} :string]],
 "Blob"
 [:map
  {:closed false}
  [:mimeType {:optional true} :string]
  [:data {:optional true} :string]],
 "VideoMetadata"
 [:map
  {:closed false}
  [:startOffset {:optional true} :string]
  [:endOffset {:optional true} :string]
  [:fps {:optional true} :double]],
 "LogprobsResult"
 [:map
  {:closed false}
  [:logProbabilitySum {:optional true} :double]
  [:topCandidates {:optional true} [:vector [:ref "TopCandidates"]]]
  [:chosenCandidates
   {:optional true}
   [:vector [:ref "LogprobsResultCandidate"]]]],
 "BatchStats"
 [:map
  {:closed false}
  [:requestCount {:optional true} :string]
  [:successfulRequestCount {:optional true} :string]
  [:failedRequestCount {:optional true} :string]
  [:pendingRequestCount {:optional true} :string]],
 "BatchEmbedTextResponse"
 [:map
  {:closed false}
  [:embeddings {:optional true} [:vector [:ref "Embedding"]]]],
 "Maps"
 [:map
  {:closed false}
  [:uri {:optional true} :string]
  [:title {:optional true} :string]
  [:text {:optional true} :string]
  [:placeId {:optional true} :string]
  [:placeAnswerSources {:optional true} [:ref "PlaceAnswerSources"]]],
 "SpeechConfig"
 [:map
  {:closed false}
  [:voiceConfig {:optional true} [:ref "VoiceConfig"]]
  [:multiSpeakerVoiceConfig
   {:optional true}
   [:ref "MultiSpeakerVoiceConfig"]]
  [:languageCode {:optional true} :string]],
 "Document"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:displayName {:optional true} :string]
  [:customMetadata {:optional true} [:vector [:ref "CustomMetadata"]]]
  [:updateTime {:optional true} :string]
  [:createTime {:optional true} :string]
  [:state
   {:optional true}
   [:enum
    "STATE_UNSPECIFIED"
    "STATE_PENDING"
    "STATE_ACTIVE"
    "STATE_FAILED"]]
  [:sizeBytes {:optional true} :string]
  [:mimeType {:optional true} :string]],
 "WhiteSpaceConfig"
 [:map
  {:closed false}
  [:maxTokensPerChunk {:optional true} :int]
  [:maxOverlapTokens {:optional true} :int]],
 "BatchEmbedContentsRequest"
 [:map
  {:closed false}
  [:requests {:optional true} [:vector [:ref "EmbedContentRequest"]]]],
 "CodeExecutionResult"
 [:map
  {:closed false}
  [:outcome
   {:optional true}
   [:enum
    "OUTCOME_UNSPECIFIED"
    "OUTCOME_OK"
    "OUTCOME_FAILED"
    "OUTCOME_DEADLINE_EXCEEDED"]]
  [:output {:optional true} :string]],
 "ModalityTokenCount"
 [:map
  {:closed false}
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
  {:closed false}
  [:latitude {:optional true} :double]
  [:longitude {:optional true} :double]],
 "UrlMetadata"
 [:map
  {:closed false}
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
 [:map {:closed false} [:value {:optional true} [:vector :double]]],
 "InlinedEmbedContentResponses"
 [:map
  {:closed false}
  [:inlinedResponses
   {:optional true}
   [:vector [:ref "InlinedEmbedContentResponse"]]]],
 "EmbedContentBatchStats"
 [:map
  {:closed false}
  [:requestCount {:optional true} :string]
  [:successfulRequestCount {:optional true} :string]
  [:failedRequestCount {:optional true} :string]
  [:pendingRequestCount {:optional true} :string]],
 "ListCorporaResponse"
 [:map
  {:closed false}
  [:corpora {:optional true} [:vector [:ref "Corpus"]]]
  [:nextPageToken {:optional true} :string]],
 "FileSearch"
 [:map
  {:closed false}
  [:fileSearchStoreNames {:optional true} [:vector :string]]
  [:topK {:optional true} :int]
  [:metadataFilter {:optional true} :string]],
 "CitationMetadata"
 [:map
  {:closed false}
  [:citationSources
   {:optional true}
   [:vector [:ref "CitationSource"]]]],
 "UrlContext" [:map {:closed false}]}

)

(client/def-api discovery-data)

;; Aliases for user-friendly usage
(def list-models models-list)
(def get-model models-get)
(def generate-content models-generate-content)
