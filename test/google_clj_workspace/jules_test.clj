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
  (testing "Stub server requires prompt for Jules"
    (let [req-valid {:uri "/jules" :body {:prompt "Hello"} :params {}}
          req-invalid {:uri "/jules" :body {} :params {}}]
      (is (= 200 (:status (stub/stub-server-handler req-valid))))
      (is (= 400 (:status (stub/stub-server-handler req-invalid)))))))

(deftest test-jules-client-with-stub
  (testing "Jules client integration with stub server"
    (with-redefs [client/make-request (fn [method url params body opts]
                                        ;; We simulate the behavior of the stub server here directly
                                        ;; or we could call the stub-handler if we constructed a full request map.
                                        ;; The stub handler expects {:uri ... :body ...}.
                                        ;; client/make-request receives parsed args.

                                        ;; Check if jules is in URL (simple check)
                                        (let [stub-req {:uri url :body body :params params}]
                                           (stub/stub-server-handler stub-req)))]

      (testing "sessions send-message with valid body"
        ;; The generated jules client should call make-request.
        ;; We need to know the op and expected path.
        ;; sessions send-message usually maps to something like .../sessions/{session}:sendMessage

        ;; Note: We are testing that the client *correctly propagates* the body to our stub.
        ;; And our stub enforces the schema (in this simple case, checking for :prompt).

        ;; Passing :prompt in body
        (let [resp (jules/sessions {:session "sessions/123"}
                                   {:op :send-message
                                    :body {:prompt "Hello Jules"}})]
          (is (= 200 (:status resp)))))

      (testing "sessions send-message missing required field"
        ;; Missing :prompt
        (let [resp (jules/sessions {:session "sessions/123"}
                                   {:op :send-message
                                    :body {}})]
          (is (= 400 (:status resp)) "Should fail because stub expects prompt"))))))
