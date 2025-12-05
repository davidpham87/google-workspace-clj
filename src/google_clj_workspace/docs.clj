(ns
 google-clj-workspace.docs
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 documents
 "Available operations: :batch-update, :create, :get"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/documents/{documentId}"
   params
   opts
   "https://docs.googleapis.com/")
  :batch-update
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/documents/{documentId}:batchUpdate"
   params
   opts
   "https://docs.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/documents"
   params
   opts
   "https://docs.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(deftest
 test-example-documents-get
 (testing
  "Example: documents get mock"
  (with-redefs
   [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
   (is (= 200 (:status (documents {} {:op :get})))))))

