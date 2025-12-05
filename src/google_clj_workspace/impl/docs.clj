(ns google-clj-workspace.impl.docs
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://docs.googleapis.com/")

(def ops
  {[:documents :get] {:method "GET" :path "v1/documents/{documentId}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:suggestedDocumentStyleChanges
   ;;   {:optional true}
   ;;   [:map-of :string :any]]
   ;;  [:footnotes {:optional true} [:map-of :string :any]]
   ;;  [:lists {:optional true} [:map-of :string :any]]
   ;;  [:positionedObjects {:optional true} [:map-of :string :any]]
   ;;  [:footers {:optional true} [:map-of :string :any]]
   ;;  [:tabs {:optional true} [:vector [:ref "Tab"]]]
   ;;  [:inlineObjects {:optional true} [:map-of :string :any]]
   ;;  [:namedRanges {:optional true} [:map-of :string :any]]
   ;;  [:revisionId {:optional true} :string]
   ;;  [:namedStyles {:optional true} [:ref "NamedStyles"]]
   ;;  [:suggestedNamedStylesChanges {:optional true} [:map-of :string :any]]
   ;;  [:documentStyle {:optional true} [:ref "DocumentStyle"]]
   ;;  [:title {:optional true} :string]
   ;;  [:suggestionsViewMode
   ;;   {:optional true}
   ;;   [:enum
   ;;    "DEFAULT_FOR_CURRENT_ACCESS"
   ;;    "SUGGESTIONS_INLINE"
   ;;    "PREVIEW_SUGGESTIONS_ACCEPTED"
   ;;    "PREVIEW_WITHOUT_SUGGESTIONS"]]
   ;;  [:headers {:optional true} [:map-of :string :any]]
   ;;  [:documentId {:optional true} :string]
   ;;  [:body {:optional true} [:ref "Body"]]]
   [:documents :create] {:method "POST" :path "v1/documents"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:requests {:optional true} [:vector [:ref "Request"]]]
   ;;  [:writeControl {:optional true} [:ref "WriteControl"]]]
   [:documents :batch-update] {:method "POST" :path "v1/documents/{documentId}:batchUpdate"}})

(defn invoke-docs-api
  [dispatch-val params opts]
  (let [[_service resource op] dispatch-val
        {:keys [method path]} (get ops [resource op])
        [interpolated-path used-params] (util/interpolate-path path params)]
    (client/invoke-endpoint
     method
     interpolated-path
     (apply dissoc params used-params)
     opts
     base-url)))

(defmacro ^:private def-docs-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:docs ~resource ~op]
              [d# p# o#]
              (invoke-docs-api d# p# o#))))))

(def-docs-dispatch-methods)