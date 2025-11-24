(ns
 google-clj-workspace.keep
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 notes
 [params & [opts]]
 (case
  (:op opts)
  :create
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path "v1/notes" params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  :get
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path "v1/{+name}" params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  :list
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path "v1/notes" params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  :delete
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path "v1/{+name}" params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "DELETE"
    full-url
    query-params
    (:body opts)
    opts))
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 permissions
 [params & [opts]]
 (case
  (:op opts)
  :batch-create
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/{+parent}/permissions:batchCreate"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  :batch-delete
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path
     "v1/{+parent}/permissions:batchDelete"
     params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "POST"
    full-url
    query-params
    (:body opts)
    opts))
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 media
 [params & [opts]]
 (case
  (:op opts)
  :download
  (let
   [[path-str used-keys]
    (google-clj-workspace.client/interpolate-path "v1/{+name}" params)
    query-params
    (apply dissoc params used-keys)
    full-url
    (str "https://keep.googleapis.com/" path-str)]
   (google-clj-workspace.client/make-request
    "GET"
    full-url
    query-params
    (:body opts)
    opts))
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(deftest
 test-example-notes-list
 (testing
  "Example: notes list mock"
  (with-redefs
   [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
   (is (= 200 (:status (notes {} {:op :list})))))))
