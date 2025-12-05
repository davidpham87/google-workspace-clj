(ns google-clj-workspace.impl.forms
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://forms.googleapis.com/")

(def ops
   ;; [:map
   ;;  {:closed false}
   ;;  [:info {:optional true} [:ref "Info"]]
   ;;  [:items {:optional true} [:vector [:ref "Item"]]]
   ;;  [:responderUri {:optional true} :string]
   ;;  [:formId {:optional true} :string]
   ;;  [:publishSettings {:optional true} [:ref "PublishSettings"]]
   ;;  [:revisionId {:optional true} :string]
   ;;  [:settings {:optional true} [:ref "FormSettings"]]
   ;;  [:linkedSheetId {:optional true} :string]]
  {[:forms :create] {:method "POST" :path "v1/forms"}
   [:forms :get] {:method "GET" :path "v1/forms/{formId}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:writeControl {:optional true} [:ref "WriteControl"]]
   ;;  [:includeFormInResponse {:optional true} :boolean]
   ;;  [:requests {:optional true} [:vector [:ref "Request"]]]]
   [:forms :batch-update] {:method "POST" :path "v1/forms/{formId}:batchUpdate"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:updateMask {:optional true} :string]
   ;;  [:publishSettings {:optional true} [:ref "PublishSettings"]]]
   [:forms :set-publish-settings] {:method "POST" :path "v1/forms/{formId}:setPublishSettings"}
   [:responses :get] {:method "GET" :path "v1/forms/{formId}/responses/{responseId}"}
   [:responses :list] {:method "GET" :path "v1/forms/{formId}/responses"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:watchId {:optional true} :string]
   ;;  [:watch {:optional true} [:ref "Watch"]]]
   [:watches :create] {:method "POST" :path "v1/forms/{formId}/watches"}
   [:watches :list] {:method "GET" :path "v1/forms/{formId}/watches"}
   ;; [:map {:closed false}]
   [:watches :renew] {:method "POST" :path "v1/forms/{formId}/watches/{watchId}:renew"}
   [:watches :delete] {:method "DELETE" :path "v1/forms/{formId}/watches/{watchId}"}})

(defn invoke-forms-api
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

(defmacro ^:private def-forms-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:forms ~resource ~op]
              [d# p# o#]
              (invoke-forms-api d# p# o#))))))

(def-forms-dispatch-methods)