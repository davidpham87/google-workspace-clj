(ns google-clj-workspace.generative-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [malli.core :as m]
            [malli.generator :as mg]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [google-clj-workspace.client :as client]
            [google-clj-workspace.core :as core]
            ;; Import public namespaces
            [google-clj-workspace.keep :as keep]
            [google-clj-workspace.forms :as forms]
            [google-clj-workspace.docs :as docs]
            [google-clj-workspace.sheets :as sheets]
            [google-clj-workspace.gemini :as gemini]
            [google-clj-workspace.jules :as jules]
            ;; Explicitly require impl namespaces for ops
            [google-clj-workspace.impl.keep]
            [google-clj-workspace.impl.forms]
            [google-clj-workspace.impl.docs]
            [google-clj-workspace.impl.sheets]
            [google-clj-workspace.impl.gemini]
            [google-clj-workspace.impl.jules]))

(defn load-registry [service]
  (let [path (str "schema/" (name service) ".edn")]
    (if-let [res (io/resource path)]
      (edn/read-string (slurp res))
      (do
        (println "Warning: Schema resource not found for" service)
        {}))))

(defn get-impl-ops [service]
  (case service
    :keep google-clj-workspace.impl.keep/ops
    :forms google-clj-workspace.impl.forms/ops
    :docs google-clj-workspace.impl.docs/ops
    :sheets google-clj-workspace.impl.sheets/ops
    :gemini google-clj-workspace.impl.gemini/ops
    :jules google-clj-workspace.impl.jules/ops
    {}))

(defn extract-path-params [path]
  (map second (re-seq #"\{([^\}]+)\}" path)))

(defn op-generator [op-info]
  (let [{:keys [service resource op schema registry]} op-info
        impl-ops (get-impl-ops service)
        path (get-in impl-ops [[resource op] :path])
        path-keys (when path (extract-path-params path))

        body-schema [:schema {:registry registry} schema]
        ;; Use resize to keep generated structures small
        body-gen (gen/resize 3 (mg/generator body-schema))

        path-params-gen (if (seq path-keys)
                          (gen/bind (gen/vector gen/string-alphanumeric (count path-keys))
                                    (fn [vals]
                                      (gen/return (zipmap (map keyword path-keys) vals))))
                          (gen/return {}))]

    (gen/fmap (fn [[body path-params]]
                {:op-info op-info
                 :body body
                 :path-params path-params})
              (gen/tuple body-gen path-params-gen))))

(def all-ops
  (for [service [:keep :forms :docs :sheets :gemini :jules]
        :let [registry (load-registry service)]
        [k v] registry
        :when (vector? k)]
    {:service service
     :resource (first k)
     :op (second k)
     :schema v
     :registry registry}))

(def client-call-gen
  (if (empty? all-ops)
    (do
      (println "Warning: No operations found in schemas. Generative tests will be empty.")
      (gen/return nil))
    (gen/bind (gen/elements all-ops)
              op-generator)))

(defspec test-generated-client-calls
  10
  (prop/for-all [call-data client-call-gen]
    (if (nil? call-data)
      true
      (let [{:keys [op-info body path-params]} call-data
            {:keys [service resource op]} op-info]
        (with-redefs [client/make-request (fn [method url params opts base-url]
                                            {:status 200 :body "{}"})]
          (try
            (let [response (core/dispatch [service resource op] path-params {:op op :body body})]
              (= 200 (:status response)))
            (catch Exception e
              (println "Error in test for" service resource op ":" (.getMessage e))
              false)))))))
