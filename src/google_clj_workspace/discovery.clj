(ns google-clj-workspace.discovery
  (:require [malli.core :as m]
            [cheshire.core :as json]
            [babashka.curl :as curl]))

;; -----------------------------------------------------------------------------
;; Malli Schema for Google Discovery Document
;; -----------------------------------------------------------------------------

(def parameter-schema
  [:map
   [:type {:optional true} :string]
   [:description {:optional true} :string]
   [:location {:optional true} :string]
   [:required {:optional true} :boolean]
   [:format {:optional true} :string]
   [:pattern {:optional true} :string]
   [:default {:optional true} :string]
   [:enum {:optional true} [:vector :string]]
   [:enumDescriptions {:optional true} [:vector :string]]])

(def ref-schema
  [:map
   [:$ref :string]])

(def schema-def-schema
  [:schema
   {:registry
    {::schema-definition
     [:map
      [:id {:optional true} :string]
      [:type {:optional true} :string]
      [:description {:optional true} :string]
      [:$ref {:optional true} :string]
      [:format {:optional true} :string]
      [:properties {:optional true} [:map-of :keyword [:ref ::schema-definition]]]
      [:items {:optional true} [:ref ::schema-definition]]
      [:readOnly {:optional true} :boolean]]}}
   ::schema-definition])

(def method-schema
  [:map
   [:id :string]
   [:path :string]
   [:httpMethod :string]
   [:description {:optional true} :string]
   [:parameters {:optional true} [:map-of :keyword parameter-schema]]
   [:parameterOrder {:optional true} [:vector :string]]
   [:request {:optional true} ref-schema]
   [:response {:optional true} ref-schema]
   [:scopes {:optional true} [:vector :string]]])

(def resource-schema
  [:schema
   {:registry
    {::resource
     [:map
      [:methods {:optional true} [:map-of :keyword method-schema]]
      [:resources {:optional true} [:map-of :keyword [:ref ::resource]]]]}}
   ::resource])

(def discovery-doc-schema
  [:map
   [:kind :string]
   [:discoveryVersion :string]
   [:id :string]
   [:name :string]
   [:version :string]
   [:title {:optional true} :string]
   [:description {:optional true} :string]
   [:protocol {:optional true} :string]
   [:baseUrl :string]
   [:basePath {:optional true} :string]
   [:rootUrl {:optional true} :string]
   [:servicePath {:optional true} :string]
   [:schemas {:optional true} [:map-of :keyword schema-def-schema]]
   [:resources {:optional true} [:map-of :keyword resource-schema]]
   [:auth {:optional true} :map]
   [:parameters {:optional true} [:map-of :keyword parameter-schema]]])

;; -----------------------------------------------------------------------------
;; Schema Conversion (JSON Schema -> Malli)
;; -----------------------------------------------------------------------------

(defn- json-schema-node->malli [node]
  (let [type (:type node)
        ref (:$ref node)
        enum (:enum node)]
    (cond
      ref [:ref ref]
      enum (into [:enum] enum)
      (= type "string") :string
      (= type "integer") :int
      (= type "boolean") :boolean
      (= type "number") :double
      (= type "array") [:vector (if-let [items (:items node)]
                                  (json-schema-node->malli items)
                                  :any)]
      (= type "object") (if (:properties node)
                          (into [:map {:closed false}]
                                (map (fn [[k v]]
                                       [k {:optional true} (json-schema-node->malli v)])
                                     (:properties node)))
                          ;; If object but no properties, maybe it's a map-of? Or just any map.
                          [:map-of :string :any])
      :else :any)))

(defn json-schemas->malli-registry
  "Converts the 'schemas' map from discovery doc into a Malli registry.
   Registry keys are Strings (original names)."
  [schemas]
  (reduce-kv (fn [acc k v]
               (assoc acc (name k) (json-schema-node->malli v)))
             {}
             schemas))

;; -----------------------------------------------------------------------------
;; Parse Function
;; -----------------------------------------------------------------------------

(defn parse-discovery-schema
  "Parses a discovery document from a URL or file (as string).
   Validates against discovery-doc-schema.
   Returns the parsed data."
  [source]
  (let [data (if (re-find #"^https?://" source)
               (-> (curl/get source) :body (json/parse-string true))
               (-> (slurp source) (json/parse-string true)))]
    (if (m/validate discovery-doc-schema data)
      data
      (throw (ex-info "Invalid Discovery Document"
                      {:errors (m/explain discovery-doc-schema data)})))))
