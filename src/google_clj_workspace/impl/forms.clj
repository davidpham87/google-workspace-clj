(ns google-clj-workspace.impl.forms
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://forms.googleapis.com/")


(def registry
{"RowQuestion" [:map {:closed true} [:title {:optional true} :string]],
 "QuestionItem"
 [:map
  {:closed true}
  [:image {:optional true} [:ref "Image"]]
  [:question {:optional true} [:ref "Question"]]],
 "FormResponse"
 [:map
  {:closed true}
  [:lastSubmittedTime {:optional true} :string]
  [:respondentEmail {:optional true} :string]
  [:totalScore {:optional true} :double]
  [:responseId {:optional true} :string]
  [:createTime {:optional true} :string]
  [:answers {:optional true} [:map-of :string :any]]
  [:formId {:optional true} :string]],
 "Empty" [:map {:closed true}],
 "DateQuestion"
 [:map
  {:closed true}
  [:includeTime {:optional true} :boolean]
  [:includeYear {:optional true} :boolean]],
 "MediaProperties"
 [:map
  {:closed true}
  [:width {:optional true} :int]
  [:alignment
   {:optional true}
   [:enum "ALIGNMENT_UNSPECIFIED" "LEFT" "RIGHT" "CENTER"]]],
 "PublishSettings"
 [:map
  {:closed true}
  [:publishState {:optional true} [:ref "PublishState"]]],
 "Request"
 [:map
  {:closed true}
  [:createItem {:optional true} [:ref "CreateItemRequest"]]
  [:updateFormInfo {:optional true} [:ref "UpdateFormInfoRequest"]]
  [:moveItem {:optional true} [:ref "MoveItemRequest"]]
  [:deleteItem {:optional true} [:ref "DeleteItemRequest"]]
  [:updateItem {:optional true} [:ref "UpdateItemRequest"]]
  [:updateSettings {:optional true} [:ref "UpdateSettingsRequest"]]],
 "BatchUpdateFormResponse"
 [:map
  {:closed true}
  [:form {:optional true} [:ref "Form"]]
  [:replies {:optional true} [:vector [:ref "Response"]]]
  [:writeControl {:optional true} [:ref "WriteControl"]]],
 "QuestionGroupItem"
 [:map
  {:closed true}
  [:image {:optional true} [:ref "Image"]]
  [:questions {:optional true} [:vector [:ref "Question"]]]
  [:grid {:optional true} [:ref "Grid"]]],
 "TextQuestion"
 [:map {:closed true} [:paragraph {:optional true} :boolean]],
 "VideoItem"
 [:map
  {:closed true}
  [:video {:optional true} [:ref "Video"]]
  [:caption {:optional true} :string]],
 "Option"
 [:map
  {:closed true}
  [:value {:optional true} :string]
  [:image {:optional true} [:ref "Image"]]
  [:goToAction
   {:optional true}
   [:enum
    "GO_TO_ACTION_UNSPECIFIED"
    "NEXT_SECTION"
    "RESTART_FORM"
    "SUBMIT_FORM"]]
  [:goToSectionId {:optional true} :string]
  [:isOther {:optional true} :boolean]],
 "QuizSettings"
 [:map {:closed true} [:isQuiz {:optional true} :boolean]],
 "BatchUpdateFormRequest"
 [:map
  {:closed true}
  [:includeFormInResponse {:optional true} :boolean]
  [:requests {:optional true} [:vector [:ref "Request"]]]
  [:writeControl {:optional true} [:ref "WriteControl"]]],
 "Grading"
 [:map
  {:closed true}
  [:generalFeedback {:optional true} [:ref "Feedback"]]
  [:pointValue {:optional true} :int]
  [:whenRight {:optional true} [:ref "Feedback"]]
  [:whenWrong {:optional true} [:ref "Feedback"]]
  [:correctAnswers {:optional true} [:ref "CorrectAnswers"]]],
 "UpdateFormInfoRequest"
 [:map
  {:closed true}
  [:info {:optional true} [:ref "Info"]]
  [:updateMask {:optional true} :string]],
 "Video"
 [:map
  {:closed true}
  [:youtubeUri {:optional true} :string]
  [:properties {:optional true} [:ref "MediaProperties"]]],
 "UpdateItemRequest"
 [:map
  {:closed true}
  [:item {:optional true} [:ref "Item"]]
  [:updateMask {:optional true} :string]
  [:location {:optional true} [:ref "Location"]]],
 "ListWatchesResponse"
 [:map
  {:closed true}
  [:watches {:optional true} [:vector [:ref "Watch"]]]],
 "Grade"
 [:map
  {:closed true}
  [:feedback {:optional true} [:ref "Feedback"]]
  [:correct {:optional true} :boolean]
  [:score {:optional true} :double]],
 "TextLink"
 [:map
  {:closed true}
  [:uri {:optional true} :string]
  [:displayText {:optional true} :string]],
 "TextItem" [:map {:closed true}],
 "Question"
 [:map
  {:closed true}
  [:fileUploadQuestion {:optional true} [:ref "FileUploadQuestion"]]
  [:choiceQuestion {:optional true} [:ref "ChoiceQuestion"]]
  [:ratingQuestion {:optional true} [:ref "RatingQuestion"]]
  [:timeQuestion {:optional true} [:ref "TimeQuestion"]]
  [:rowQuestion {:optional true} [:ref "RowQuestion"]]
  [:scaleQuestion {:optional true} [:ref "ScaleQuestion"]]
  [:textQuestion {:optional true} [:ref "TextQuestion"]]
  [:questionId {:optional true} :string]
  [:grading {:optional true} [:ref "Grading"]]
  [:required {:optional true} :boolean]
  [:dateQuestion {:optional true} [:ref "DateQuestion"]]],
 "Location" [:map {:closed true} [:index {:optional true} :int]],
 "ListFormResponsesResponse"
 [:map
  {:closed true}
  [:nextPageToken {:optional true} :string]
  [:responses {:optional true} [:vector [:ref "FormResponse"]]]],
 "TextAnswer" [:map {:closed true} [:value {:optional true} :string]],
 "RatingQuestion"
 [:map
  {:closed true}
  [:iconType
   {:optional true}
   [:enum "RATING_ICON_TYPE_UNSPECIFIED" "STAR" "HEART" "THUMB_UP"]]
  [:ratingScaleLevel {:optional true} :int]],
 "Info"
 [:map
  {:closed true}
  [:description {:optional true} :string]
  [:documentTitle {:optional true} :string]
  [:title {:optional true} :string]],
 "Watch"
 [:map
  {:closed true}
  [:expireTime {:optional true} :string]
  [:id {:optional true} :string]
  [:eventType
   {:optional true}
   [:enum "EVENT_TYPE_UNSPECIFIED" "SCHEMA" "RESPONSES"]]
  [:errorType
   {:optional true}
   [:enum
    "ERROR_TYPE_UNSPECIFIED"
    "PROJECT_NOT_AUTHORIZED"
    "NO_USER_ACCESS"
    "OTHER_ERRORS"]]
  [:target {:optional true} [:ref "WatchTarget"]]
  [:state
   {:optional true}
   [:enum "STATE_UNSPECIFIED" "ACTIVE" "SUSPENDED"]]
  [:createTime {:optional true} :string]],
 "Grid"
 [:map
  {:closed true}
  [:columns {:optional true} [:ref "ChoiceQuestion"]]
  [:shuffleQuestions {:optional true} :boolean]],
 "RenewWatchRequest" [:map {:closed true}],
 "ScaleQuestion"
 [:map
  {:closed true}
  [:highLabel {:optional true} :string]
  [:high {:optional true} :int]
  [:low {:optional true} :int]
  [:lowLabel {:optional true} :string]],
 "CreateWatchRequest"
 [:map
  {:closed true}
  [:watch {:optional true} [:ref "Watch"]]
  [:watchId {:optional true} :string]],
 "Response"
 [:map
  {:closed true}
  [:createItem {:optional true} [:ref "CreateItemResponse"]]],
 "CorrectAnswer"
 [:map {:closed true} [:value {:optional true} :string]],
 "ImageItem"
 [:map {:closed true} [:image {:optional true} [:ref "Image"]]],
 "PageBreakItem" [:map {:closed true}],
 "Form"
 [:map
  {:closed true}
  [:revisionId {:optional true} :string]
  [:responderUri {:optional true} :string]
  [:formId {:optional true} :string]
  [:settings {:optional true} [:ref "FormSettings"]]
  [:info {:optional true} [:ref "Info"]]
  [:publishSettings {:optional true} [:ref "PublishSettings"]]
  [:linkedSheetId {:optional true} :string]
  [:items {:optional true} [:vector [:ref "Item"]]]],
 "FileUploadQuestion"
 [:map
  {:closed true}
  [:folderId {:optional true} :string]
  [:types
   {:optional true}
   [:vector
    [:enum
     "FILE_TYPE_UNSPECIFIED"
     "ANY"
     "DOCUMENT"
     "PRESENTATION"
     "SPREADSHEET"
     "DRAWING"
     "PDF"
     "IMAGE"
     "VIDEO"
     "AUDIO"]]]
  [:maxFileSize {:optional true} :string]
  [:maxFiles {:optional true} :int]],
 "WriteControl"
 [:map
  {:closed true}
  [:requiredRevisionId {:optional true} :string]
  [:targetRevisionId {:optional true} :string]],
 "MoveItemRequest"
 [:map
  {:closed true}
  [:newLocation {:optional true} [:ref "Location"]]
  [:originalLocation {:optional true} [:ref "Location"]]],
 "SetPublishSettingsRequest"
 [:map
  {:closed true}
  [:publishSettings {:optional true} [:ref "PublishSettings"]]
  [:updateMask {:optional true} :string]],
 "PublishState"
 [:map
  {:closed true}
  [:isPublished {:optional true} :boolean]
  [:isAcceptingResponses {:optional true} :boolean]],
 "FileUploadAnswer"
 [:map
  {:closed true}
  [:fileName {:optional true} :string]
  [:fileId {:optional true} :string]
  [:mimeType {:optional true} :string]],
 "VideoLink"
 [:map
  {:closed true}
  [:youtubeUri {:optional true} :string]
  [:displayText {:optional true} :string]],
 "CreateItemRequest"
 [:map
  {:closed true}
  [:item {:optional true} [:ref "Item"]]
  [:location {:optional true} [:ref "Location"]]],
 "UpdateSettingsRequest"
 [:map
  {:closed true}
  [:updateMask {:optional true} :string]
  [:settings {:optional true} [:ref "FormSettings"]]],
 "FileUploadAnswers"
 [:map
  {:closed true}
  [:answers {:optional true} [:vector [:ref "FileUploadAnswer"]]]],
 "ChoiceQuestion"
 [:map
  {:closed true}
  [:shuffle {:optional true} :boolean]
  [:options {:optional true} [:vector [:ref "Option"]]]
  [:type
   {:optional true}
   [:enum "CHOICE_TYPE_UNSPECIFIED" "RADIO" "CHECKBOX" "DROP_DOWN"]]],
 "CloudPubsubTopic"
 [:map {:closed true} [:topicName {:optional true} :string]],
 "CreateItemResponse"
 [:map
  {:closed true}
  [:questionId {:optional true} [:vector :string]]
  [:itemId {:optional true} :string]],
 "ExtraMaterial"
 [:map
  {:closed true}
  [:link {:optional true} [:ref "TextLink"]]
  [:video {:optional true} [:ref "VideoLink"]]],
 "CorrectAnswers"
 [:map
  {:closed true}
  [:answers {:optional true} [:vector [:ref "CorrectAnswer"]]]],
 "TextAnswers"
 [:map
  {:closed true}
  [:answers {:optional true} [:vector [:ref "TextAnswer"]]]],
 "FormSettings"
 [:map
  {:closed true}
  [:quizSettings {:optional true} [:ref "QuizSettings"]]
  [:emailCollectionType
   {:optional true}
   [:enum
    "EMAIL_COLLECTION_TYPE_UNSPECIFIED"
    "DO_NOT_COLLECT"
    "VERIFIED"
    "RESPONDER_INPUT"]]],
 "SetPublishSettingsResponse"
 [:map
  {:closed true}
  [:publishSettings {:optional true} [:ref "PublishSettings"]]
  [:formId {:optional true} :string]],
 "Image"
 [:map
  {:closed true}
  [:sourceUri {:optional true} :string]
  [:altText {:optional true} :string]
  [:contentUri {:optional true} :string]
  [:properties {:optional true} [:ref "MediaProperties"]]],
 "Feedback"
 [:map
  {:closed true}
  [:text {:optional true} :string]
  [:material {:optional true} [:vector [:ref "ExtraMaterial"]]]],
 "WatchTarget"
 [:map
  {:closed true}
  [:topic {:optional true} [:ref "CloudPubsubTopic"]]],
 "DeleteItemRequest"
 [:map {:closed true} [:location {:optional true} [:ref "Location"]]],
 "Item"
 [:map
  {:closed true}
  [:description {:optional true} :string]
  [:imageItem {:optional true} [:ref "ImageItem"]]
  [:pageBreakItem {:optional true} [:ref "PageBreakItem"]]
  [:questionItem {:optional true} [:ref "QuestionItem"]]
  [:title {:optional true} :string]
  [:textItem {:optional true} [:ref "TextItem"]]
  [:questionGroupItem {:optional true} [:ref "QuestionGroupItem"]]
  [:videoItem {:optional true} [:ref "VideoItem"]]
  [:itemId {:optional true} :string]],
 "TimeQuestion"
 [:map {:closed true} [:duration {:optional true} :boolean]],
 "Answer"
 [:map
  {:closed true}
  [:questionId {:optional true} :string]
  [:textAnswers {:optional true} [:ref "TextAnswers"]]
  [:grade {:optional true} [:ref "Grade"]]
  [:fileUploadAnswers {:optional true} [:ref "FileUploadAnswers"]]]}
)

(def ops
{[:forms :set-publish-settings]
 {:method "POST",
  :path "v1/forms/{formId}:setPublishSettings",
  :schema [:ref "SetPublishSettingsRequest"]},
 [:watches :create]
 {:method "POST",
  :path "v1/forms/{formId}/watches",
  :schema [:ref "CreateWatchRequest"]},
 [:forms :get] {:method "GET", :path "v1/forms/{formId}"},
 [:forms :create]
 {:method "POST", :path "v1/forms", :schema [:ref "Form"]},
 [:watches :delete]
 {:method "DELETE", :path "v1/forms/{formId}/watches/{watchId}"},
 [:watches :renew]
 {:method "POST",
  :path "v1/forms/{formId}/watches/{watchId}:renew",
  :schema [:ref "RenewWatchRequest"]},
 [:watches :list] {:method "GET", :path "v1/forms/{formId}/watches"},
 [:forms :batch-update]
 {:method "POST",
  :path "v1/forms/{formId}:batchUpdate",
  :schema [:ref "BatchUpdateFormRequest"]},
 [:responses :list]
 {:method "GET", :path "v1/forms/{formId}/responses"},
 [:responses :get]
 {:method "GET", :path "v1/forms/{formId}/responses/{responseId}"}}
)

(defn invoke-forms-api
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

(defmacro ^:private def-forms-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:forms ~resource ~op]
              [d# p# o#]
              (invoke-forms-api d# p# o#))))))

(def-forms-dispatch-methods)
