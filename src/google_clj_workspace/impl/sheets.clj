(ns google-clj-workspace.impl.sheets
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://sheets.googleapis.com/")

(def ops
   ;; [:map
   ;;  {:closed false}
   ;;  [:spreadsheetId {:optional true} :string]
   ;;  [:dataSources {:optional true} [:vector [:ref "DataSource"]]]
   ;;  [:dataSourceSchedules
   ;;   {:optional true}
   ;;   [:vector [:ref "DataSourceRefreshSchedule"]]]
   ;;  [:sheets {:optional true} [:vector [:ref "Sheet"]]]
   ;;  [:developerMetadata
   ;;   {:optional true}
   ;;   [:vector [:ref "DeveloperMetadata"]]]
   ;;  [:namedRanges {:optional true} [:vector [:ref "NamedRange"]]]
   ;;  [:spreadsheetUrl {:optional true} :string]
   ;;  [:properties {:optional true} [:ref "SpreadsheetProperties"]]]
  {[:spreadsheets :create] {:method "POST" :path "v4/spreadsheets"}
   [:spreadsheets :get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
   ;;  [:includeGridData {:optional true} :boolean]
   ;;  [:excludeTablesInBandedRanges {:optional true} :boolean]]
   [:spreadsheets :get-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}:getByDataFilter"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:responseIncludeGridData {:optional true} :boolean]
   ;;  [:responseRanges {:optional true} [:vector :string]]
   ;;  [:requests {:optional true} [:vector [:ref "Request"]]]
   ;;  [:includeSpreadsheetInResponse {:optional true} :boolean]]
   [:spreadsheets :batch-update] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}:batchUpdate"}
   [:values :get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}/values/{range}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:values {:optional true} [:vector [:vector :any]]]
   ;;  [:range {:optional true} :string]
   ;;  [:majorDimension
   ;;   {:optional true}
   ;;   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]]
   [:values :append] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values/{range}:append"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:values {:optional true} [:vector [:vector :any]]]
   ;;  [:range {:optional true} :string]
   ;;  [:majorDimension
   ;;   {:optional true}
   ;;   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]]
   [:values :update] {:method "PUT" :path "v4/spreadsheets/{spreadsheetId}/values/{range}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]]
   [:values :batch-clear-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchClearByDataFilter"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:valueInputOption
   ;;   {:optional true}
   ;;   [:enum "INPUT_VALUE_OPTION_UNSPECIFIED" "RAW" "USER_ENTERED"]]
   ;;  [:includeValuesInResponse {:optional true} :boolean]
   ;;  [:responseValueRenderOption
   ;;   {:optional true}
   ;;   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
   ;;  [:responseDateTimeRenderOption
   ;;   {:optional true}
   ;;   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]
   ;;  [:data {:optional true} [:vector [:ref "ValueRange"]]]]
   [:values :batch-update] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchUpdate"}
   [:values :batch-get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}/values:batchGet"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:valueRenderOption
   ;;   {:optional true}
   ;;   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
   ;;  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
   ;;  [:dateTimeRenderOption
   ;;   {:optional true}
   ;;   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]
   ;;  [:majorDimension
   ;;   {:optional true}
   ;;   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]]
   [:values :batch-get-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchGetByDataFilter"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:responseValueRenderOption
   ;;   {:optional true}
   ;;   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
   ;;  [:valueInputOption
   ;;   {:optional true}
   ;;   [:enum "INPUT_VALUE_OPTION_UNSPECIFIED" "RAW" "USER_ENTERED"]]
   ;;  [:data {:optional true} [:vector [:ref "DataFilterValueRange"]]]
   ;;  [:includeValuesInResponse {:optional true} :boolean]
   ;;  [:responseDateTimeRenderOption
   ;;   {:optional true}
   ;;   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]]
   [:values :batch-update-by-data-filter] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchUpdateByDataFilter"}
   ;; [:map {:closed false} [:ranges {:optional true} [:vector :string]]]
   [:values :batch-clear] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values:batchClear"}
   ;; [:map {:closed false}]
   [:values :clear] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/values/{range}:clear"}
   [:developer-metadata :get] {:method "GET" :path "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]]
   [:developer-metadata :search] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/developerMetadata:search"}
   ;; [:map
   ;;  {:closed false}
   ;;  [:destinationSpreadsheetId {:optional true} :string]]
   [:sheets :copy-to] {:method "POST" :path "v4/spreadsheets/{spreadsheetId}/sheets/{sheetId}:copyTo"}})

(defn invoke-sheets-api
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