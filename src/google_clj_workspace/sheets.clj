(ns google-clj-workspace.sheets
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.sheets]))

(defn spreadsheets
  "Manages spreadsheets.
  - op: :create, :get, :get-by-data-filter, :batch-update

  Examples:
  ;; create a spreadsheet
  (spreadsheets {} {:op :create :body {:properties {:title \"New Sheet\"}}})

  ;; get a spreadsheet
  (spreadsheets {:spreadsheetId \"123\"} {:op :get})

  ;; get a spreadsheet by data filter
  (spreadsheets {:spreadsheetId \"123\"}
                {:op :get-by-data-filter
                 :body {:dataFilters [{:a1Range \"A1:B2\"}]}})

  ;; update a spreadsheet
  (spreadsheets {:spreadsheetId \"123\"}
                {:op :batch-update
                 :body {:requests [{:updateSpreadsheetProperties
                                     {:properties {:title \"New Title\"}
                                      :fields \"title\"}}]}})"
  [params & [opts]]
  (core/dispatch [:sheets :spreadsheets (:op opts)] params opts))

(defn values
  "Manages values.
  - op: :get, :append, :update, :batch-clear-by-data-filter, :batch-update, :batch-get, :batch-get-by-data-filter, :batch-update-by-data-filter, :batch-clear, :clear

  Examples:
  ;; get values
  (values {:spreadsheetId \"123\" :range \"A1:B2\"} {:op :get})

  ;; batch get values
  (values {:spreadsheetId \"123\"} {:op :batch-get :query-params {:ranges [\"A1:B2\"]}})

  ;; append values
  (values {:spreadsheetId \"123\" :range \"A1\"}
          {:op :append
           :query-params {:valueInputOption \"USER_ENTERED\"}
           :body {:values [[\"a\" \"b\"]]}})

  ;; update values
  (values {:spreadsheetId \"123\" :range \"A1\"}
          {:op :update
           :query-params {:valueInputOption \"USER_ENTERED\"}
           :body {:values [[\"c\" \"d\"]]}})

  ;; batch update values
  (values {:spreadsheetId \"123\"}
          {:op :batch-update
           :body {:valueInputOption \"USER_ENTERED\"
                  :data [{:range \"A1\"
                          :values [[\"e\" \"f\"]]}]}})

  ;; clear values
  (values {:spreadsheetId \"123\" :range \"A1:B2\"} {:op :clear})

  ;; batch clear values
  (values {:spreadsheetId \"123\"}
          {:op :batch-clear
           :body {:ranges [\"A1:B2\"]}})"
  [params & [opts]]
  (core/dispatch [:sheets :values (:op opts)] params opts))

(defn developer-metadata
  "Manages developer metadata.
  - op: :get, :search

  Examples:
  ;; get developer metadata
  (developer-metadata {:spreadsheetId \"123\" :metadataId 456} {:op :get})

  ;; search developer metadata
  (developer-metadata {:spreadsheetId \"123\"}
                      {:op :search
                       :body {:dataFilters [{:developerMetadataLookup
                                             {:metadataKey \"foo\"}}]}})"
  [params & [opts]]
  (core/dispatch [:sheets :developer-metadata (:op opts)] params opts))

(defn sheets
  "Manages sheets.
  - op: :copy-to

  Examples:
  ;; copy a sheet
  (sheets {:spreadsheetId \"123\" :sheetId 456}
          {:op :copy-to
           :body {:destinationSpreadsheetId \"789\"}})"
  [params & [opts]]
  (core/dispatch [:sheets :sheets (:op opts)] params opts))
