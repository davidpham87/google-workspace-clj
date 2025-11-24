(ns
 google-clj-workspace.forms
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 forms
 [params & [opts]]
 (case
  (:op opts)
  :create
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path "v1/forms" params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  :get
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  :batch-update
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}:batchUpdate"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  :set-publish-settings
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}:setPublishSettings"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 responses
 [params & [opts]]
 (case
  (:op opts)
  :get
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}/responses/{responseId}"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  :list
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}/responses"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 watches
 [params & [opts]]
 (case
  (:op opts)
  :create
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}/watches"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  :list
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}/watches"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  :renew
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}/watches/{watchId}:renew"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  :delete
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/forms/{formId}/watches/{watchId}"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://forms.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "DELETE"
    full-url
    query-params
    (:body opts)
    opts))
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(deftest
 test-example-forms-create
 (testing
  "Example: forms create mock"
  (with-redefs
   [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
   (is (= 200 (:status (forms {} {:op :create})))))))
