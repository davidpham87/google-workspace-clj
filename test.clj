(ns test
  (:require [google-workspace.discovery :as discovery]
            [google-workspace.client :as client]
            [malli.core :as m]
            [malli.generator :as mg]
            [clojure.test :refer [deftest is testing run-tests]]))

(def discovery-url "https://keep.googleapis.com/$discovery/rest?version=v1")

(def parsed-discovery (discovery/parse-discovery-schema discovery-url))

(deftest test-discovery-schema-parsing
  (testing "Parse and validate Google Keep discovery document"
    (is (some? parsed-discovery))
    (is (= "keep" (:name parsed-discovery)))
    (is (= "v1" (:version parsed-discovery)))

    (testing "Validation against discovery-doc-schema"
      (is (m/validate discovery/discovery-doc-schema parsed-discovery)))

    (testing "Schema conversion and round-trip"
      (let [schemas (:schemas parsed-discovery)
            registry (discovery/json-schemas->malli-registry schemas)]

        ;; Pick a schema, e.g., Note
        (let [note-schema (get registry "Note")
              full-schema [:schema {:registry registry} "Note"]]

          (is (some? note-schema) "Note schema should be converted")

          ;; Generate data
          (let [generated-note (mg/generate full-schema {:size 3})]
            (is (some? generated-note) "Should generate a Note")

            ;; Validate data
            (is (m/validate full-schema generated-note) "Generated Note should be valid")))))))

;; Generate client functions
(client/def-api parsed-discovery)

(deftest test-client-generation
  (testing "Client generation"
    ;; Check if functions are defined
    (is (resolve 'notes-list))
    (is (resolve 'notes-get))
    (is (resolve 'notes-create))

    ;; Verify name conversion (camelCase -> kebab-case)
    ;; permissions.batchCreate -> notes-permissions-batch-create
    (is (resolve 'notes-permissions-batch-create))))

(defn -main []
  (run-tests 'test))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
