(ns test
  (:require [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.client :as client]
            [google-clj-workspace.keep :as keep-api]
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
            (is (m/validate full-schema generated-note) "Generated Note should be valid"))

        ;; Test Enum conversion
        (let [perm-schema (get registry "Permission")
              role-schema (get-in perm-schema [:map :role])]
             ;; properties -> role -> enum
             ;; In malli: [:map [:role {:optional true} [:enum "ROLE_UNSPECIFIED" ...]]]
             ;; Wait, my converter puts optional in props map.
             ;; [:map [:role {:optional true} ...]]
             ;; The value at key :role is the schema.

             ;; Let's inspect schema structure for Permission.
             ;; It has a role which is an enum.
             (is (some? perm-schema))
             ;; We can't easily inspect the nested vector structure without knowing exact position.
             ;; But we can check if generating Permission works and role is one of the enums.

             (let [full-perm-schema [:schema {:registry registry} "Permission"]
                   gen-perm (mg/generate full-perm-schema {:size 1})]
               (is (m/validate full-perm-schema gen-perm)))))))))

(deftest test-client-generation
  (testing "Client generation in keep namespace"
    ;; Check if functions are defined in google-clj-workspace.keep
    (is (resolve 'google-clj-workspace.keep/notes-list))
    (is (resolve 'google-clj-workspace.keep/notes-get))
    (is (resolve 'google-clj-workspace.keep/notes-create))

    ;; Verify name conversion (camelCase -> kebab-case)
    (is (resolve 'google-clj-workspace.keep/notes-permissions-batch-create))

    ;; Verify aliases
    (is (resolve 'google-clj-workspace.keep/find-notes))
    (is (resolve 'google-clj-workspace.keep/read-note))
    (is (resolve 'google-clj-workspace.keep/create-note))))

(defn -main []
  (run-tests 'test))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
