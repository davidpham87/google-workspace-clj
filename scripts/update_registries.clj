(ns scripts.update-registries
  (:require [cheshire.core :as json]
            [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [google-clj-workspace.discovery :as discovery]
            [google-clj-workspace.impl.keep]
            [google-clj-workspace.impl.docs]
            [google-clj-workspace.impl.forms]
            [google-clj-workspace.impl.sheets]
            [google-clj-workspace.impl.gemini]
            [google-clj-workspace.impl.jules]))

;; Helper to recursively find methods in discovery doc
(defn find-methods [resources]
  (reduce-kv (fn [acc res-key res-val]
               (let [methods (reduce-kv (fn [m-acc m-key m-val]
                                          (assoc m-acc [(:httpMethod m-val) (:path m-val)] m-val))
                                        {}
                                        (:methods res-val))
                     sub-methods (if (:resources res-val)
                                   (find-methods (:resources res-val))
                                   {})]
                 (merge acc methods sub-methods)))
             {}
             resources))

(defn normalize-path [path]
  ;; Remove leading slash if present
  (let [p (if (str/starts-with? path "/") (subs path 1) path)]
    ;; Replace {+param} with {param} to normalize differences
    (str/replace p #"\{\+?([^}]+)\}" "{$1}")))

(defn get-ops [service-ns]
  (let [ops-var (ns-resolve service-ns 'ops)]
    (if ops-var
      @ops-var
      (println "Warning: ops not found for" service-ns))))

(defn update-registry [service service-ns-sym]
  (println "Updating registry for" service)
  (let [discovery-file (str "resources/schema/discovery/" (name service) ".json")
        output-file (str "resources/schema/" (name service) ".edn")
        discovery-json (json/parse-string (slurp discovery-file) true)

        ;; Base schema registry
        base-registry (discovery/json-schemas->malli-registry (:schemas discovery-json))

        ;; Map of [method path] -> method-definition
        discovery-methods (find-methods (:resources discovery-json))
        ;; Normalize keys for easier matching
        discovery-lookup (reduce-kv (fn [acc [method path] val]
                                      (assoc acc [method (normalize-path path)] val))
                                    {}
                                    discovery-methods)

        ops (get-ops service-ns-sym)]

    (if ops
      (let [op-entries (reduce-kv (fn [acc op-key op-details]
                                    (let [method (:method op-details)
                                          path (normalize-path (:path op-details))
                                          lookup-key [method path]
                                          matched-method (get discovery-lookup lookup-key)]
                                      (if matched-method
                                        (if-let [req-ref (-> matched-method :request :$ref)]
                                          (assoc acc op-key [:ref req-ref])
                                          (do
                                            ;; (println "No request body for" op-key)
                                            acc))
                                        (do
                                          (println "Warning: No matching method found in discovery for" op-key "Lookup:" lookup-key)
                                          acc))))
                                  {}
                                  ops)
            final-registry (merge base-registry op-entries)]
        (spit output-file (with-out-str (clojure.pprint/pprint final-registry)))
        (println "Updated" output-file))
      (println "Skipping" service "due to missing ops"))))

(defn -main []
  (update-registry :keep 'google-clj-workspace.impl.keep)
  (update-registry :docs 'google-clj-workspace.impl.docs)
  (update-registry :forms 'google-clj-workspace.impl.forms)
  (update-registry :sheets 'google-clj-workspace.impl.sheets)
  (update-registry :gemini 'google-clj-workspace.impl.gemini)
  (update-registry :jules 'google-clj-workspace.impl.jules))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
