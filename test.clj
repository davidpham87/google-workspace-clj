(ns test
  (:require [clojure.test :refer [run-tests deftest is testing]]
            [google-clj-workspace.keep :as keep]
            [google-clj-workspace.forms :as forms]
            [google-clj-workspace.docs :as docs]
            [google-clj-workspace.sheets :as sheets]
            [google-clj-workspace.gemini :as gemini]
            [google-clj-workspace.client :as client]
            [clojure.string :as str]
            [babashka.curl]))

(deftest test-keep-client
  (testing "Keep notes list mock with kebab-case params"
    (with-redefs
     [client/make-request
      (fn
       [_ _ params _ _]
       (if
        (= 10 (:pageSize params))
        {:status 200, :body "{}"}
        {:status 400, :error "Params not converted"}))]
     (is (= 200 (:status (keep/notes {:page-size 10} {:op :list})))))))

(deftest test-forms-client
  (testing "Forms create mock"
    (with-redefs
     [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
     (is (= 200 (:status (forms/forms {} {:op :create})))))))

(deftest test-docs-client
  (testing "Docs documents get mock"
    (with-redefs
     [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
     (is (= 200 (:status (docs/documents {} {:op :get})))))))

(deftest test-sheets-client
  (testing "Sheets spreadsheets get mock"
    (with-redefs
     [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
     (is (= 200 (:status (sheets/spreadsheets {} {:op :get})))))))

(deftest test-gemini-client
  (testing "Gemini models generateContent mock"
    (with-redefs
     [client/make-request (fn [_ _ _ _ _] {:status 200, :body "{}"})]
     (is (= 200 (:status (gemini/models {} {:op :generate-content})))))))

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

(defn -main []
  (let [test-results (run-tests 'test)]
    (when (pos? (+ (:fail test-results) (:error test-results)))
      (System/exit 1))))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
