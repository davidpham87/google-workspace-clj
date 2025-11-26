(ns google-clj-workspace.forms
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.forms]))

(defn forms
  "Manages forms.
  - op: :create, :get, :batch-update, :set-publish-settings

  Examples:
  ;; create a form
  (forms {} {:op :create :body {:info {:title \"New Form\"}}})

  ;; get a form
  (forms {:formId \"123\"} {:op :get})

  ;; update a form
  (forms {:formId \"123\"}
         {:op :batch-update
          :body {:requests [{:updateFormInfo {:info {:title \"New Title\"}
                                               :updateMask \"title\"}}]}})

  ;; set publish settings
  (forms {:formId \"123\"}
         {:op :set-publish-settings
          :body {:settings {:isPublishing \"on\"}}})"
  [params & [opts]]
  (core/dispatch [:forms :forms (:op opts)] params opts))

(defn responses
  "Manages responses.
  - op: :get, :list

  Examples:
  ;; get a response
  (responses {:formId \"123\" :responseId \"456\"} {:op :get})

  ;; list responses
  (responses {:formId \"123\"} {:op :list})"
  [params & [opts]]
  (core/dispatch [:forms :responses (:op opts)] params opts))

(defn watches
  "Manages watches.
  - op: :create, :list, :renew, :delete

  Examples:
  ;; create a watch
  (watches {:formId \"123\"}
           {:op :create
            :body {:watch {:target {:topic {:topicName \"projects/p/topics/t\"}}
                           :eventType \"RESPONSES\"}}})

  ;; list watches
  (watches {:formId \"123\"} {:op :list})

  ;; renew a watch
  (watches {:formId \"123\" :watchId \"456\"} {:op :renew})

  ;; delete a watch
  (watches {:formId \"123\" :watchId \"456\"} {:op :delete})"
  [params & [opts]]
  (core/dispatch [:forms :watches (:op opts)] params opts))
