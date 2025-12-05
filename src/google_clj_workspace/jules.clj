(ns
 google-clj-workspace.jules
 (:require
  [google-clj-workspace.client :as client]
  [clojure.test :refer [deftest is testing]]))

(defn
 sources
 "Available operations: :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1alpha/{+name}"
   params
   opts
   "https://jules.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1alpha/sources"
   params
   opts
   "https://jules.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 sessions
 "Available operations: :approve-plan, :create, :delete, :get, :list, :send-message"
 [params & [opts]]
 (case
  (:op opts)
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1alpha/sessions"
   params
   opts
   "https://jules.googleapis.com/")
  :send-message
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1alpha/{+session}:sendMessage"
   params
   opts
   "https://jules.googleapis.com/")
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1alpha/{+name}"
   params
   opts
   "https://jules.googleapis.com/")
  :approve-plan
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1alpha/{+session}:approvePlan"
   params
   opts
   "https://jules.googleapis.com/")
  :create
  (google-clj-workspace.client/invoke-endpoint
   "POST"
   "v1alpha/sessions"
   params
   opts
   "https://jules.googleapis.com/")
  :delete
  (google-clj-workspace.client/invoke-endpoint
   "DELETE"
   "v1alpha/{+name}"
   params
   opts
   "https://jules.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))

(defn
 activities
 "Available operations: :get, :list"
 [params & [opts]]
 (case
  (:op opts)
  :get
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1alpha/{+name}"
   params
   opts
   "https://jules.googleapis.com/")
  :list
  (google-clj-workspace.client/invoke-endpoint
   "GET"
   "v1alpha/{+parent}/activities"
   params
   opts
   "https://jules.googleapis.com/")
  (throw (ex-info "Unknown op" {:op (:op opts)}))))
