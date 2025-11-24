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
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/notes"
   params
   opts
   "https://keep.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/{+name}"
   params
   opts
   "https://keep.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/notes"
   params
   opts
   "https://keep.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1/{+name}"
   params
   opts
   "https://keep.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 permissions
 [params & [opts]]
 (case
  (:op opts)
  :batch-create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/{+parent}/permissions:batchCreate"
   params
   opts
   "https://keep.googleapis.com/")
  :batch-delete
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1/{+parent}/permissions:batchDelete"
   params
   opts
   "https://keep.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 media
 [params & [opts]]
 (case
  (:op opts)
  :download
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1/{+name}"
   params
   opts
   "https://keep.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))


(client/def-api discovery-data)

(deftest
 test-example-notes-list
 (testing
  "Example: notes list mock with kebab-case params"
  (with-redefs
   [client/make-request
    (fn
     [_ _ params _ _]
     (if
      (= 10 (:pageSize params))
      {:status 200, :body "{}"}
      {:status 400, :error "Params not converted"}))]
   (is (= 200 (:status (notes {:page-size 10} {:op :list})))))))
