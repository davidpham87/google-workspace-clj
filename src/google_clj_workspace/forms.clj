(ns
 google-clj-workspace.forms
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 forms
 "Available operations: :batch-update, :create, :get, :set-publish-settings"
 [params & [opts]]
 (case
  (:op opts)
  :batch-update
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/forms/{formId}:batchUpdate"
   params
   opts
   "https://forms.googleapis.com/")
  :set-publish-settings
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/forms/{formId}:setPublishSettings"
   params
   opts
   "https://forms.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/forms"
   params
   opts
   "https://forms.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/forms/{formId}"
   params
   opts
   "https://forms.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 responses
 "Available operations: :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/forms/{formId}/responses/{responseId}"
   params
   opts
   "https://forms.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/forms/{formId}/responses"
   params
   opts
   "https://forms.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 watches
 "Available operations: :create, :delete, :list, :renew"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/forms/{formId}/watches"
   params
   opts
   "https://forms.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1/forms/{formId}/watches/{watchId}"
   params
   opts
   "https://forms.googleapis.com/")
  :renew
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/forms/{formId}/watches/{watchId}:renew"
   params
   opts
   "https://forms.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/forms/{formId}/watches"
   params
   opts
   "https://forms.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(deftest
 test-example-forms-create
 (testing
  "Example: forms create mock"
  (with-redefs
   [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
   (is (= 200 (:status (forms {} {:op :create})))))))

