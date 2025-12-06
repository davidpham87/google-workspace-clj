(ns google-clj-workspace.jules-test
  (:require [clojure.test :refer :all]
            [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.stub-server :as stub]
            [google-clj-workspace.jules :as jules]
            [google-clj-workspace.client :as client]
            [malli.core :as m]
            [cheshire.core :as json]
            [babashka.fs :as fs]))

(deftest test-jules-schema-generation
  (testing "Jules schema generation and validation"
    (let [discovery-file "resources/schema/discovery/jules.json"]
      (if (fs/exists? discovery-file)
        (let [discovery-data (discovery/parse-discovery-schema discovery-file)
              registry (discovery/json-schemas->malli-registry (:schemas discovery-data))]

          (is (not (empty? registry)) "Registry should not be empty")

          ;; Test that the generated schemas are closed
          (let [some-schema (first (vals registry))]
            (when (vector? some-schema)
               ;; Verify structure of map options.
               ;; Since we used mu/closed-schema, it sets {:closed true}
               (is (true? (get (get some-schema 1) :closed)) "Schema should be closed map"))))
        (println "Skipping Jules schema test as discovery file not found (run download script first).")))))

(deftest test-stub-server-interaction
  (testing "Stub server requires valid body for Jules"
    ;; We use a real endpoint for testing the stub logic: sessions create
    ;; Op: [:sessions :create], Path: v1alpha/sessions, Method: POST
    ;; Request schema: Session (based on error output from previous run)

    (let [;; Need a valid URI that triggers :jules service identification
          uri "https://jules.googleapis.com/v1alpha/sessions"
          ;; Valid body for Session
          req-valid {:uri uri :method :post :body {:name "projects/p/locations/l/sessions/s"}}
          ;; Invalid: passing a field that doesn't exist (since schemas are closed)
          req-invalid {:uri uri :method :post :body {:invalidField "foo"}}]

      (is (= 200 (:status (stub/stub-server-handler req-valid)))
          (str "Valid request failed: " (:body (stub/stub-server-handler req-valid))))

      (is (= 400 (:status (stub/stub-server-handler req-invalid)))
          "Invalid request should fail"))))

(deftest test-jules-client-with-stub
  (testing "Jules client integration with stub server"
    (with-redefs [client/make-request (fn [method url params body opts]
                                        (let [stub-req {:uri url :method method :body body :params params}]
                                           (stub/stub-server-handler stub-req)))]

      (testing "sessions send-message with valid body"
        ;; [:sessions :send-message] -> v1alpha/{+session}:sendMessage
        ;; Schema: SendMessageRequest which has [:prompt :string]

        (let [resp (jules/sessions {:session "sessions/123"}
                                   {:op :send-message
                                    :body {:prompt "Hello Jules"}})]

          (if (= 400 (:status resp))
             (println "Schema validation failed:" (:body resp)))
          (is (= 200 (:status resp)))))

      (testing "sessions send-message missing required field / invalid field"
        (let [resp (jules/sessions {:session "sessions/123"}
                                   {:op :send-message
                                    :body {:invalidField "foo"}})]
          (is (= 400 (:status resp)) "Should fail because stub checks schema"))))))
