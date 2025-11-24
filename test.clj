(ns test
  (:require [clojure.test :refer [run-tests]]
            [google-clj-workspace.keep]
            [google-clj-workspace.forms]))

(deftest test-docs-client
  (testing "Docs client generation"
    (is (resolve 'google-clj-workspace.docs/documents-get))
    (is (resolve 'google-clj-workspace.docs/documents-create))
    (is (resolve 'google-clj-workspace.docs/get-document))
    (is (resolve 'google-clj-workspace.docs/create-document))))

(deftest test-sheets-client
  (testing "Sheets client generation"
    (is (resolve 'google-clj-workspace.sheets/spreadsheets-get))
    (is (resolve 'google-clj-workspace.sheets/spreadsheets-create))
    (is (resolve 'google-clj-workspace.sheets/get-spreadsheet))
    (is (resolve 'google-clj-workspace.sheets/create-spreadsheet))))

(deftest test-gemini-client
  (testing "Gemini client generation"
    (is (resolve 'google-clj-workspace.gemini/models-list))
    (is (resolve 'google-clj-workspace.gemini/models-generate-content))
    (is (resolve 'google-clj-workspace.gemini/list-models))
    (is (resolve 'google-clj-workspace.gemini/generate-content)))

  (testing "Gemini request construction"
    (let [last-request (atom nil)]
      (with-redefs [babashka.curl/request (fn [opts] (reset! last-request opts) {:status 200 :body "{}"})]
        (gemini-api/generate-content {:model "models/gemini-pro"} {:body {:contents [{:parts [{:text "Hello"}]}]}})
        (let [req @last-request]
          (is (= :post (:method req)))
          (is (str/includes? (:url req) "/v1beta/models/gemini-pro:generateContent"))
          (is (str/includes? (:body req) "Hello")))))))

(defn -main []
  (run-tests 'google-clj-workspace.keep 'google-clj-workspace.forms))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
