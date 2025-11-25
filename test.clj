(ns test
  (:require [clojure.test :refer [run-tests deftest is testing]]
            [google-clj-workspace.keep]
            [google-clj-workspace.forms]
            [google-clj-workspace.docs]
            [google-clj-workspace.sheets]
            [google-clj-workspace.gemini]
            [clojure.string :as str]
            [babashka.curl]))

(deftest test-docs-client
  (testing "Docs client generation"
    (is (resolve 'google-clj-workspace.docs/documents))))

(deftest test-sheets-client
  (testing "Sheets client generation"
    (is (resolve 'google-clj-workspace.sheets/spreadsheets))))

(deftest test-gemini-client
  (testing "Gemini client generation"
    (is (resolve 'google-clj-workspace.gemini/models)))

  (testing "Gemini request construction"
    (let [last-request (atom nil)]
      (with-redefs [babashka.curl/request (fn [opts] (reset! last-request opts) {:status 200 :body "{}"})]
        (google-clj-workspace.gemini/models
         {:model "models/gemini-pro"}
         {:op :generate-content
          :body {:contents [{:parts [{:text "Hello"}]}]}})
        (let [req @last-request]
          (is (= :post (:method req)))
          (is (str/includes? (:url req) "/v1beta/models/gemini-pro:generateContent"))
          (is (str/includes? (:body req) "Hello")))))))

(defn -main []
  (run-tests 'google-clj-workspace.keep
             'google-clj-workspace.forms
             'google-clj-workspace.docs
             'google-clj-workspace.sheets
             'google-clj-workspace.gemini
             'test))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
