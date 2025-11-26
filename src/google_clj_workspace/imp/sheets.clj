(ns google-clj-workspace.imp.sheets
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def ^:private base-url "https://sheets.googleapis.com/")

(def ^:private ops
  {[:spreadsheets :create] {:method "POST" :path "v4/spreadsheets"}
   [:spreadsheets :get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}"}
   [:spreadsheets :get-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}:getByDataFilter"}
   [:spreadsheets :batch-update] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}:batchUpdate"}
   [:values :get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}/values/{range}"}
   [:values :append] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values/{range}:append"}
   [:values :update] {:method "PUT" :path "v4/spreadsheets/{spreadsheetId}/values/{range}"}
   [:values :batch-clear-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchClearByDataFilter"}
   [:values :batch-update] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchUpdate"}
   [:values :batch-get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}/values:batchGet"}
   [:values :batch-get-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchGetByDataFilter"}
   [:values :batch-update-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchUpdateByDataFilter"}
   [:values :batch-clear] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchClear"}
   [:values :clear] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values/{range}:clear"}
   [:developer-metadata :get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}"}
   [:developer-metadata :search] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/developerMetadata:search"}
   [:sheets :copy-to] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/sheets/{sheetId}:copyTo"}})

(defn- invoke-sheets-api
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

(defmacro ^:private def-sheets-dispatch-methods []
  (let [dispatch-vals (keys ops)]
    `(do
       ~@(for [[resource op] dispatch-vals]
           `(defmethod core/dispatch [:sheets ~resource ~op]
              [d# p# o#]
              (invoke-sheets-api d# p# o#))))))

(def-sheets-dispatch-methods)
