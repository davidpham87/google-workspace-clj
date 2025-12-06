(ns google-clj-workspace.stub-server
  (:require [clojure.test :refer :all]
            [clojure.edn :as edn]
            [clojure.string :as str]
            [cheshire.core :as json]
            [malli.core :as m]
            [google-clj-workspace.impl.keep :as keep-impl]
            [google-clj-workspace.impl.docs :as docs-impl]
            [google-clj-workspace.impl.forms :as forms-impl]
            [google-clj-workspace.impl.sheets :as sheets-impl]
            [google-clj-workspace.impl.gemini :as gemini-impl]
            [google-clj-workspace.impl.jules :as jules-impl]))

(defn- load-registry [service]
  (let [path (str "resources/schema/" (name service) ".edn")]
    (edn/read-string (slurp path))))

(def registries
  {:keep (load-registry :keep)
   :docs (load-registry :docs)
   :forms (load-registry :forms)
   :sheets (load-registry :sheets)
   :gemini (load-registry :gemini)
   :jules (load-registry :jules)})

(def service-ops
  {:keep keep-impl/ops
   :docs docs-impl/ops
   :forms forms-impl/ops
   :sheets sheets-impl/ops
   :gemini gemini-impl/ops
   :jules jules-impl/ops})

(defn- match-path? [template path]
  ;; path might be full URL or just path.
  ;; template is like "v1/notes".
  ;; We want to match if path ENDS with the regex generated from template.
  (let [regex-str (-> template
                      ;; Escape dots
                      (str/replace "." "\\.")
                      ;; Handle {+name} (greedy) - matches rest of path usually, or until next literal?
                      ;; In google apis, + means it can contain slashes.
                      (str/replace #"/\{\+[^}]+\}" "/.+")
                      ;; Handle {name} (segment) - matches non-slashes
                      (str/replace #"/\{[^}]+\}" "/[^/]+")
                      ;; Handle :customVerb at end
                      (str/replace ":" "\\:"))]
    (re-find (re-pattern (str regex-str "$")) path)))

(defn- find-op [service method uri]
  (let [ops (get service-ops service)]
    (reduce-kv (fn [found [resource op-name] op-def]
                 (if found found
                     (if (and (= (:method op-def) method)
                              (match-path? (:path op-def) uri))
                       [resource op-name]
                       nil)))
               nil
               ops)))

(defn- identify-service [uri]
  (cond
    (str/includes? uri "keep.googleapis.com") :keep
    (str/includes? uri "docs.googleapis.com") :docs
    (str/includes? uri "forms.googleapis.com") :forms
    (str/includes? uri "sheets.googleapis.com") :sheets
    (str/includes? uri "generativelanguage.googleapis.com") :gemini
    (str/includes? uri "jules.googleapis.com") :jules
    :else nil))

(defn stub-server-handler
  "A stub handler that simulates a server.
   It validates inputs using stored Malli schemas."
  [request]
  (let [uri (:uri request)
        method-val (:method request)
        method (when method-val (str/upper-case (name method-val)))
        service (identify-service uri)]

    (if-not service
      {:status 404 :body {:error "Service not found" :uri uri}}
      (let [[resource op-name] (find-op service method uri)]
        (if-not (and resource op-name)
          {:status 404 :body {:error (str "Operation not found for " method " " uri)}}

          (let [registry (get registries service)
                op-schema-ref (get registry [resource op-name])]
            (if op-schema-ref
              (let [body (:body request)
                    ;; Parse body if it's a string (it might be json stringified in client)
                    body-data (if (string? body) (json/parse-string body true) body)
                    schema [:schema {:registry registry} op-schema-ref]]
                (if (m/validate schema body-data)
                  {:status 200 :body {:message "Valid request"}}
                  {:status 400 :body {:error "Invalid schema" :details (m/explain schema body-data)}}))

              ;; No schema defined for this op (e.g. GET request with no body), just return success
              {:status 200 :body {:message "Success (no schema validation needed)"}})))))))
