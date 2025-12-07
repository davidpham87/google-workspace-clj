(ns test
  (:require [clojure.test :refer [run-tests deftest is testing]]
            [google-clj-workspace.keep :as keep]
            [google-clj-workspace.forms :as forms]
            [google-clj-workspace.docs :as docs]
            [google-clj-workspace.sheets :as sheets]
            [google-clj-workspace.gemini :as gemini]
            [google-clj-workspace.jules :as jules]
            [google-clj-workspace.client :as client]
            [google-clj-workspace.util :as util]
            [google-clj-workspace.jules-test] ;; Import the new test namespace
            [google-clj-workspace.generative-test]
            [clojure.string :as str]
            [babashka.curl]))

(deftest test-keep-client
  (testing "Keep services"
    (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
      (testing "notes"
        (is (= 200 (:status (keep/notes {:page-size 10} {:op :list})))))
      (testing "permissions"
        (is (= 200 (:status (keep/permissions {:parent "notes/123"} {:op :batch-create :body {:requests [{:permission {:grantee {:user {:emailAddress "user@example.com"}} :role "writer"}}]}}))))))))

(deftest test-forms-client
  (testing "Forms services"
    (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
      (testing "forms"
        (is (= 200 (:status (forms/forms {} {:op :create}))))
        (is (= 200 (:status (forms/forms {:formId "123"} {:op :get})))))
      (testing "responses"
        (is (= 200 (:status (forms/responses {:formId "123"} {:op :list})))))
      (testing "watches"
        (is (= 200 (:status (forms/watches {:formId "123"}
                                          {:op :create
                                           :body {:watch {:target {:topic {:topicName "projects/p/topics/t"}}
                                                          :eventType "RESPONSES"}}}))))))))

(deftest test-docs-client
  (testing "Docs documents get mock"
    (with-redefs
     [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
     (is (= 200 (:status (docs/documents {} {:op :get})))))))

(deftest test-sheets-client
  (testing "Sheets services"
    (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
      (testing "spreadsheets"
        (is (= 200 (:status (sheets/spreadsheets {} {:op :get})))))
      (testing "values"
        (is (= 200 (:status (sheets/values {:spreadsheetId "123" :range "A1:B2"} {:op :get})))))
      (testing "developer-metadata"
        (is (= 200 (:status (sheets/developer-metadata {:spreadsheetId "123" :metadataId 456} {:op :get})))))
      (testing "sheets"
        (is (= 200 (:status (sheets/sheets {:spreadsheetId "123" :sheetId 456}
                                          {:op :copy-to
                                           :body {:destinationSpreadsheetId "789"}}))))))))

(deftest test-gemini-client
  (testing "Gemini services"
    (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
      (testing "models"
        (is (= 200 (:status (gemini/models {} {:op :generate-content})))))
      (testing "permissions"
        (is (= 200 (:status (gemini/permissions {:parent "tunedModels/123"} {:op :create :body {:granteeType "EVERYONE" :role "reader"}}))))))))

(deftest test-gemini-request-construction
  (testing "Gemini request construction"
    (let [last-request (atom nil)]
      (with-redefs [babashka.curl/request (fn [opts] (reset! last-request opts) {:status 200 :body "{}"})]
        (gemini/models
         {:model "models/gemini-pro"}
         {:op :generate-content
          :body {:contents [{:parts [{:text "Hello"}]}]}})
        (let [req @last-request]
          (is (= :post (:method req)))
          (is (str/includes? (:url req) "/v1beta/models/gemini-pro:generateContent"))
          (is (str/includes? (:body req) "Hello")))))))

(deftest test-help-url
  (testing "Help URL construction"
    (is (= "https://developers.google.com/docs/api/reference/rest/v1/document.documents/create"
           (google-clj-workspace.util/help-url {:service :docs :resource :documents :op :create})))
    (is (= "https://ai.google.dev/api/rest/v1beta/models/generateContent"
           (google-clj-workspace.util/help-url {:service :gemini :resource :models :op :generateContent})))
    (is (= "https://developers.google.com/docs/api/reference/rest/v1/spreadsheet.values/get"
           (google-clj-workspace.util/help-url {:service :sheets :resource :values :op :get})))))

(deftest test-jules-client
  (testing "Jules services"
    (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
      (testing "sessions"
        (is (= 200 (:status (jules/sessions {} {:op :create}))))
        (is (= 200 (:status (jules/sessions {:session "sessions/123"} {:op :send-message :body {:message "Hello"}})))))
      (testing "activities"
        (is (= 200 (:status (jules/activities {:parent "sessions/123"} {:op :list})))))
      (testing "sources"
        (is (= 200 (:status (jules/sources {} {:op :list}))))))))

(defn -main []
  (let [test-results (run-tests 'test 'google-clj-workspace.jules-test 'google-clj-workspace.generative-test)]
    (when (pos? (+ (:fail test-results) (:error test-results)))
      (System/exit 1))))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
