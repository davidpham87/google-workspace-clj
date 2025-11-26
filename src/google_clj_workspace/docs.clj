(ns google-clj-workspace.docs
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.impl.docs]))

(defn documents
  "Manages documents.
  - op: :get, :create, :batch-update

  Examples:
  ;; get a document
  (documents {:documentId \"123\"} {:op :get})

  ;; create a document
  (documents {} {:op :create :body {:title \"New Doc\"}})

  ;; update a document
  (documents {:documentId \"123\"}
             {:op :batch-update
              :body {:requests [{:insertText {:location {:index 1}
                                               :text \"Hello\"}}]}})"
  [params & [opts]]
  (core/dispatch [:docs :documents (:op opts)] params opts))
