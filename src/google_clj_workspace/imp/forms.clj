(ns google-clj-workspace.imp.forms
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def ^:private base-url "https://forms.googleapis.com/")

(def ^:private ops
  {[:forms :create] {:method "POST" :path "v1/forms"}
   [:forms :get] {:method "GET" :path "v1/forms/{formId}"}
   [:forms :batch-update] {:method "POST" :path "v1/forms/{formId}:batchUpdate"}
   [:forms :set-publish-settings] {:method "POST" :path "v1/forms/{formId}:setPublishSettings"}
   [:responses :get] {:method "GET" :path "v1/forms/{formId}/responses/{responseId}"}
   [:responses :list] {:method "GET" :path "v1/forms/{formId}/responses"}
   [:watches :create] {:method "POST" :path "v1/forms/{formId}/watches"}
   [:watches :list] {:method "GET" :path "v1/forms/{formId}/watches"}
   [:watches :renew] {:method "POST" :path "v1/forms/{formId}/watches/{watchId}:renew"}
   [:watches :delete] {:method "DELETE" :path "v1/forms/{formId}/watches/{watchId}"}})

(defn- invoke-forms-api
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
