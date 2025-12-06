(ns google-clj-workspace.impl.sheets
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://sheets.googleapis.com/")


(def registry
{"CellFormat"
 [:map
  {:closed true}
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:textRotation {:optional true} [:ref "TextRotation"]]
  [:textDirection
   {:optional true}
   [:enum
    "TEXT_DIRECTION_UNSPECIFIED"
    "LEFT_TO_RIGHT"
    "RIGHT_TO_LEFT"]]
  [:borders {:optional true} [:ref "Borders"]]
  [:numberFormat {:optional true} [:ref "NumberFormat"]]
  [:verticalAlignment
   {:optional true}
   [:enum "VERTICAL_ALIGN_UNSPECIFIED" "TOP" "MIDDLE" "BOTTOM"]]
  [:hyperlinkDisplayType
   {:optional true}
   [:enum "HYPERLINK_DISPLAY_TYPE_UNSPECIFIED" "LINKED" "PLAIN_TEXT"]]
  [:padding {:optional true} [:ref "Padding"]]
  [:wrapStrategy
   {:optional true}
   [:enum
    "WRAP_STRATEGY_UNSPECIFIED"
    "OVERFLOW_CELL"
    "LEGACY_WRAP"
    "CLIP"
    "WRAP"]]
  [:backgroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:backgroundColor {:optional true} [:ref "Color"]]
  [:horizontalAlignment
   {:optional true}
   [:enum "HORIZONTAL_ALIGN_UNSPECIFIED" "LEFT" "CENTER" "RIGHT"]]],
 "BatchUpdateValuesByDataFilterResponse"
 [:map
  {:closed true}
  [:totalUpdatedCells {:optional true} :int]
  [:spreadsheetId {:optional true} :string]
  [:totalUpdatedRows {:optional true} :int]
  [:totalUpdatedColumns {:optional true} :int]
  [:totalUpdatedSheets {:optional true} :int]
  [:responses
   {:optional true}
   [:vector [:ref "UpdateValuesByDataFilterResponse"]]]],
 "DeleteNamedRangeRequest"
 [:map {:closed true} [:namedRangeId {:optional true} :string]],
 "SpreadsheetTheme"
 [:map
  {:closed true}
  [:themeColors {:optional true} [:vector [:ref "ThemeColorPair"]]]
  [:primaryFontFamily {:optional true} :string]],
 "ChartSpec"
 [:map
  {:closed true}
  [:subtitleTextFormat {:optional true} [:ref "TextFormat"]]
  [:titleTextPosition {:optional true} [:ref "TextPosition"]]
  [:pieChart {:optional true} [:ref "PieChartSpec"]]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:candlestickChart {:optional true} [:ref "CandlestickChartSpec"]]
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]
  [:altText {:optional true} :string]
  [:dataSourceChartProperties
   {:optional true}
   [:ref "DataSourceChartProperties"]]
  [:basicChart {:optional true} [:ref "BasicChartSpec"]]
  [:subtitleTextPosition {:optional true} [:ref "TextPosition"]]
  [:scorecardChart {:optional true} [:ref "ScorecardChartSpec"]]
  [:waterfallChart {:optional true} [:ref "WaterfallChartSpec"]]
  [:maximized {:optional true} :boolean]
  [:title {:optional true} :string]
  [:histogramChart {:optional true} [:ref "HistogramChartSpec"]]
  [:subtitle {:optional true} :string]
  [:fontName {:optional true} :string]
  [:bubbleChart {:optional true} [:ref "BubbleChartSpec"]]
  [:hiddenDimensionStrategy
   {:optional true}
   [:enum
    "CHART_HIDDEN_DIMENSION_STRATEGY_UNSPECIFIED"
    "SKIP_HIDDEN_ROWS_AND_COLUMNS"
    "SKIP_HIDDEN_ROWS"
    "SKIP_HIDDEN_COLUMNS"
    "SHOW_ALL"]]
  [:titleTextFormat {:optional true} [:ref "TextFormat"]]
  [:backgroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:treemapChart {:optional true} [:ref "TreemapChartSpec"]]
  [:backgroundColor {:optional true} [:ref "Color"]]
  [:orgChart {:optional true} [:ref "OrgChartSpec"]]],
 "CreateDeveloperMetadataResponse"
 [:map
  {:closed true}
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]],
 "AddBandingRequest"
 [:map
  {:closed true}
  [:bandedRange {:optional true} [:ref "BandedRange"]]],
 "EmbeddedChart"
 [:map
  {:closed true}
  [:position {:optional true} [:ref "EmbeddedObjectPosition"]]
  [:spec {:optional true} [:ref "ChartSpec"]]
  [:border {:optional true} [:ref "EmbeddedObjectBorder"]]
  [:chartId {:optional true} :int]],
 "HistogramRule"
 [:map
  {:closed true}
  [:end {:optional true} :double]
  [:start {:optional true} :double]
  [:interval {:optional true} :double]],
 "BasicChartSpec"
 [:map
  {:closed true}
  [:lineSmoothing {:optional true} :boolean]
  [:totalDataLabel {:optional true} [:ref "DataLabel"]]
  [:chartType
   {:optional true}
   [:enum
    "BASIC_CHART_TYPE_UNSPECIFIED"
    "BAR"
    "LINE"
    "AREA"
    "COLUMN"
    "SCATTER"
    "COMBO"
    "STEPPED_AREA"]]
  [:series {:optional true} [:vector [:ref "BasicChartSeries"]]]
  [:domains {:optional true} [:vector [:ref "BasicChartDomain"]]]
  [:legendPosition
   {:optional true}
   [:enum
    "BASIC_CHART_LEGEND_POSITION_UNSPECIFIED"
    "BOTTOM_LEGEND"
    "LEFT_LEGEND"
    "RIGHT_LEGEND"
    "TOP_LEGEND"
    "NO_LEGEND"]]
  [:threeDimensional {:optional true} :boolean]
  [:compareMode
   {:optional true}
   [:enum "BASIC_CHART_COMPARE_MODE_UNSPECIFIED" "DATUM" "CATEGORY"]]
  [:interpolateNulls {:optional true} :boolean]
  [:axis {:optional true} [:vector [:ref "BasicChartAxis"]]]
  [:headerCount {:optional true} :int]
  [:stackedType
   {:optional true}
   [:enum
    "BASIC_CHART_STACKED_TYPE_UNSPECIFIED"
    "NOT_STACKED"
    "STACKED"
    "PERCENT_STACKED"]]],
 "GradientRule"
 [:map
  {:closed true}
  [:minpoint {:optional true} [:ref "InterpolationPoint"]]
  [:midpoint {:optional true} [:ref "InterpolationPoint"]]
  [:maxpoint {:optional true} [:ref "InterpolationPoint"]]],
 "DataSourceChartProperties"
 [:map
  {:closed true}
  [:dataExecutionStatus {:optional true} [:ref "DataExecutionStatus"]]
  [:dataSourceId {:optional true} :string]],
 "SourceAndDestination"
 [:map
  {:closed true}
  [:dimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:source {:optional true} [:ref "GridRange"]]
  [:fillLength {:optional true} :int]],
 "TextFormat"
 [:map
  {:closed true}
  [:bold {:optional true} :boolean]
  [:foregroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:underline {:optional true} :boolean]
  [:fontFamily {:optional true} :string]
  [:link {:optional true} [:ref "Link"]]
  [:strikethrough {:optional true} :boolean]
  [:fontSize {:optional true} :int]
  [:italic {:optional true} :boolean]
  [:foregroundColor {:optional true} [:ref "Color"]]],
 "DataFilter"
 [:map
  {:closed true}
  [:gridRange {:optional true} [:ref "GridRange"]]
  [:developerMetadataLookup
   {:optional true}
   [:ref "DeveloperMetadataLookup"]]
  [:a1Range {:optional true} :string]],
 "AddTableResponse"
 [:map {:closed true} [:table {:optional true} [:ref "Table"]]],
 "TableColumnDataValidationRule"
 [:map
  {:closed true}
  [:condition {:optional true} [:ref "BooleanCondition"]]],
 "GridData"
 [:map
  {:closed true}
  [:rowMetadata
   {:optional true}
   [:vector [:ref "DimensionProperties"]]]
  [:startColumn {:optional true} :int]
  [:columnMetadata
   {:optional true}
   [:vector [:ref "DimensionProperties"]]]
  [:startRow {:optional true} :int]
  [:rowData {:optional true} [:vector [:ref "RowData"]]]],
 "AddSlicerRequest"
 [:map {:closed true} [:slicer {:optional true} [:ref "Slicer"]]],
 "LineStyle"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum
    "LINE_DASH_TYPE_UNSPECIFIED"
    "INVISIBLE"
    "CUSTOM"
    "SOLID"
    "DOTTED"
    "MEDIUM_DASHED"
    "MEDIUM_DASHED_DOTTED"
    "LONG_DASHED"
    "LONG_DASHED_DOTTED"]]
  [:width {:optional true} :int]],
 "BatchUpdateValuesRequest"
 [:map
  {:closed true}
  [:valueInputOption
   {:optional true}
   [:enum "INPUT_VALUE_OPTION_UNSPECIFIED" "RAW" "USER_ENTERED"]]
  [:responseDateTimeRenderOption
   {:optional true}
   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]
  [:responseValueRenderOption
   {:optional true}
   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
  [:data {:optional true} [:vector [:ref "ValueRange"]]]
  [:includeValuesInResponse {:optional true} :boolean]],
 "Editors"
 [:map
  {:closed true}
  [:groups {:optional true} [:vector :string]]
  [:users {:optional true} [:vector :string]]
  [:domainUsersCanEdit {:optional true} :boolean]],
 "NumberFormat"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum
    "NUMBER_FORMAT_TYPE_UNSPECIFIED"
    "TEXT"
    "NUMBER"
    "PERCENT"
    "CURRENCY"
    "DATE"
    "TIME"
    "DATE_TIME"
    "SCIENTIFIC"]]
  [:pattern {:optional true} :string]],
 "AddSheetResponse"
 [:map
  {:closed true}
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "Request"
 [:map
  {:closed true}
  [:moveDimension {:optional true} [:ref "MoveDimensionRequest"]]
  [:repeatCell {:optional true} [:ref "RepeatCellRequest"]]
  [:updateFilterView {:optional true} [:ref "UpdateFilterViewRequest"]]
  [:addSlicer {:optional true} [:ref "AddSlicerRequest"]]
  [:deleteEmbeddedObject
   {:optional true}
   [:ref "DeleteEmbeddedObjectRequest"]]
  [:deleteBanding {:optional true} [:ref "DeleteBandingRequest"]]
  [:addDimensionGroup
   {:optional true}
   [:ref "AddDimensionGroupRequest"]]
  [:addConditionalFormatRule
   {:optional true}
   [:ref "AddConditionalFormatRuleRequest"]]
  [:updateDimensionGroup
   {:optional true}
   [:ref "UpdateDimensionGroupRequest"]]
  [:updateCells {:optional true} [:ref "UpdateCellsRequest"]]
  [:updateProtectedRange
   {:optional true}
   [:ref "UpdateProtectedRangeRequest"]]
  [:addFilterView {:optional true} [:ref "AddFilterViewRequest"]]
  [:autoResizeDimensions
   {:optional true}
   [:ref "AutoResizeDimensionsRequest"]]
  [:insertDimension {:optional true} [:ref "InsertDimensionRequest"]]
  [:addDataSource {:optional true} [:ref "AddDataSourceRequest"]]
  [:updateChartSpec {:optional true} [:ref "UpdateChartSpecRequest"]]
  [:duplicateSheet {:optional true} [:ref "DuplicateSheetRequest"]]
  [:addTable {:optional true} [:ref "AddTableRequest"]]
  [:trimWhitespace {:optional true} [:ref "TrimWhitespaceRequest"]]
  [:pasteData {:optional true} [:ref "PasteDataRequest"]]
  [:deleteDataSource {:optional true} [:ref "DeleteDataSourceRequest"]]
  [:updateSpreadsheetProperties
   {:optional true}
   [:ref "UpdateSpreadsheetPropertiesRequest"]]
  [:addBanding {:optional true} [:ref "AddBandingRequest"]]
  [:updateBanding {:optional true} [:ref "UpdateBandingRequest"]]
  [:deleteDuplicates {:optional true} [:ref "DeleteDuplicatesRequest"]]
  [:deleteSheet {:optional true} [:ref "DeleteSheetRequest"]]
  [:deleteProtectedRange
   {:optional true}
   [:ref "DeleteProtectedRangeRequest"]]
  [:cancelDataSourceRefresh
   {:optional true}
   [:ref "CancelDataSourceRefreshRequest"]]
  [:updateDeveloperMetadata
   {:optional true}
   [:ref "UpdateDeveloperMetadataRequest"]]
  [:updateSheetProperties
   {:optional true}
   [:ref "UpdateSheetPropertiesRequest"]]
  [:duplicateFilterView
   {:optional true}
   [:ref "DuplicateFilterViewRequest"]]
  [:updateDimensionProperties
   {:optional true}
   [:ref "UpdateDimensionPropertiesRequest"]]
  [:deleteDeveloperMetadata
   {:optional true}
   [:ref "DeleteDeveloperMetadataRequest"]]
  [:unmergeCells {:optional true} [:ref "UnmergeCellsRequest"]]
  [:updateTable {:optional true} [:ref "UpdateTableRequest"]]
  [:createDeveloperMetadata
   {:optional true}
   [:ref "CreateDeveloperMetadataRequest"]]
  [:updateDataSource {:optional true} [:ref "UpdateDataSourceRequest"]]
  [:updateEmbeddedObjectBorder
   {:optional true}
   [:ref "UpdateEmbeddedObjectBorderRequest"]]
  [:cutPaste {:optional true} [:ref "CutPasteRequest"]]
  [:appendDimension {:optional true} [:ref "AppendDimensionRequest"]]
  [:deleteFilterView {:optional true} [:ref "DeleteFilterViewRequest"]]
  [:deleteRange {:optional true} [:ref "DeleteRangeRequest"]]
  [:setDataValidation
   {:optional true}
   [:ref "SetDataValidationRequest"]]
  [:deleteConditionalFormatRule
   {:optional true}
   [:ref "DeleteConditionalFormatRuleRequest"]]
  [:deleteTable {:optional true} [:ref "DeleteTableRequest"]]
  [:refreshDataSource
   {:optional true}
   [:ref "RefreshDataSourceRequest"]]
  [:appendCells {:optional true} [:ref "AppendCellsRequest"]]
  [:textToColumns {:optional true} [:ref "TextToColumnsRequest"]]
  [:deleteDimensionGroup
   {:optional true}
   [:ref "DeleteDimensionGroupRequest"]]
  [:mergeCells {:optional true} [:ref "MergeCellsRequest"]]
  [:randomizeRange {:optional true} [:ref "RandomizeRangeRequest"]]
  [:insertRange {:optional true} [:ref "InsertRangeRequest"]]
  [:deleteNamedRange {:optional true} [:ref "DeleteNamedRangeRequest"]]
  [:updateNamedRange {:optional true} [:ref "UpdateNamedRangeRequest"]]
  [:findReplace {:optional true} [:ref "FindReplaceRequest"]]
  [:updateEmbeddedObjectPosition
   {:optional true}
   [:ref "UpdateEmbeddedObjectPositionRequest"]]
  [:addSheet {:optional true} [:ref "AddSheetRequest"]]
  [:updateBorders {:optional true} [:ref "UpdateBordersRequest"]]
  [:addNamedRange {:optional true} [:ref "AddNamedRangeRequest"]]
  [:addChart {:optional true} [:ref "AddChartRequest"]]
  [:addProtectedRange
   {:optional true}
   [:ref "AddProtectedRangeRequest"]]
  [:setBasicFilter {:optional true} [:ref "SetBasicFilterRequest"]]
  [:clearBasicFilter {:optional true} [:ref "ClearBasicFilterRequest"]]
  [:sortRange {:optional true} [:ref "SortRangeRequest"]]
  [:deleteDimension {:optional true} [:ref "DeleteDimensionRequest"]]
  [:updateSlicerSpec {:optional true} [:ref "UpdateSlicerSpecRequest"]]
  [:autoFill {:optional true} [:ref "AutoFillRequest"]]
  [:copyPaste {:optional true} [:ref "CopyPasteRequest"]]
  [:updateConditionalFormatRule
   {:optional true}
   [:ref "UpdateConditionalFormatRuleRequest"]]],
 "MatchedDeveloperMetadata"
 [:map
  {:closed true}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]],
 "Link" [:map {:closed true} [:uri {:optional true} :string]],
 "GridProperties"
 [:map
  {:closed true}
  [:rowGroupControlAfter {:optional true} :boolean]
  [:columnCount {:optional true} :int]
  [:frozenRowCount {:optional true} :int]
  [:hideGridlines {:optional true} :boolean]
  [:columnGroupControlAfter {:optional true} :boolean]
  [:rowCount {:optional true} :int]
  [:frozenColumnCount {:optional true} :int]],
 "DateTimeRule"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum
    "DATE_TIME_RULE_TYPE_UNSPECIFIED"
    "SECOND"
    "MINUTE"
    "HOUR"
    "HOUR_MINUTE"
    "HOUR_MINUTE_AMPM"
    "DAY_OF_WEEK"
    "DAY_OF_YEAR"
    "DAY_OF_MONTH"
    "DAY_MONTH"
    "MONTH"
    "QUARTER"
    "YEAR"
    "YEAR_MONTH"
    "YEAR_QUARTER"
    "YEAR_MONTH_DAY"]]],
 "UpdateEmbeddedObjectPositionRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:newPosition {:optional true} [:ref "EmbeddedObjectPosition"]]
  [:objectId {:optional true} :int]],
 "AddSlicerResponse"
 [:map {:closed true} [:slicer {:optional true} [:ref "Slicer"]]],
 "UpdateConditionalFormatRuleRequest"
 [:map
  {:closed true}
  [:rule {:optional true} [:ref "ConditionalFormatRule"]]
  [:index {:optional true} :int]
  [:newIndex {:optional true} :int]
  [:sheetId {:optional true} :int]],
 "UpdateDataSourceRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:dataSource {:optional true} [:ref "DataSource"]]],
 "ClearValuesResponse"
 [:map
  {:closed true}
  [:clearedRange {:optional true} :string]
  [:spreadsheetId {:optional true} :string]],
 "TimeOfDay"
 [:map
  {:closed true}
  [:minutes {:optional true} :int]
  [:hours {:optional true} :int]
  [:seconds {:optional true} :int]
  [:nanos {:optional true} :int]],
 "DimensionRange"
 [:map
  {:closed true}
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:sheetId {:optional true} :int]
  [:dimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "RandomizeRangeRequest"
 [:map {:closed true} [:range {:optional true} [:ref "GridRange"]]],
 "ChipRun"
 [:map
  {:closed true}
  [:startIndex {:optional true} :int]
  [:chip {:optional true} [:ref "Chip"]]],
 "BigQueryTableSpec"
 [:map
  {:closed true}
  [:tableId {:optional true} :string]
  [:datasetId {:optional true} :string]
  [:tableProjectId {:optional true} :string]],
 "DataSourceRefreshDailySchedule"
 [:map
  {:closed true}
  [:startTime {:optional true} [:ref "TimeOfDay"]]],
 "DeleteDimensionGroupResponse"
 [:map
  {:closed true}
  [:dimensionGroups
   {:optional true}
   [:vector [:ref "DimensionGroup"]]]],
 "UpdateNamedRangeRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:namedRange {:optional true} [:ref "NamedRange"]]],
 "RefreshCancellationStatus"
 [:map
  {:closed true}
  [:state
   {:optional true}
   [:enum
    "REFRESH_CANCELLATION_STATE_UNSPECIFIED"
    "CANCEL_SUCCEEDED"
    "CANCEL_FAILED"]]
  [:errorCode
   {:optional true}
   [:enum
    "REFRESH_CANCELLATION_ERROR_CODE_UNSPECIFIED"
    "EXECUTION_NOT_FOUND"
    "CANCEL_PERMISSION_DENIED"
    "QUERY_EXECUTION_COMPLETED"
    "CONCURRENT_CANCELLATION"
    "CANCEL_OTHER_ERROR"]]],
 "PivotGroupValueMetadata"
 [:map
  {:closed true}
  [:collapsed {:optional true} :boolean]
  [:value {:optional true} [:ref "ExtendedValue"]]],
 "TableRowsProperties"
 [:map
  {:closed true}
  [:headerColorStyle {:optional true} [:ref "ColorStyle"]]
  [:firstBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:secondBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:footerColorStyle {:optional true} [:ref "ColorStyle"]]],
 "Borders"
 [:map
  {:closed true}
  [:top {:optional true} [:ref "Border"]]
  [:right {:optional true} [:ref "Border"]]
  [:bottom {:optional true} [:ref "Border"]]
  [:left {:optional true} [:ref "Border"]]],
 "ConditionalFormatRule"
 [:map
  {:closed true}
  [:gradientRule {:optional true} [:ref "GradientRule"]]
  [:ranges {:optional true} [:vector [:ref "GridRange"]]]
  [:booleanRule {:optional true} [:ref "BooleanRule"]]],
 "DuplicateSheetRequest"
 [:map
  {:closed true}
  [:newSheetId {:optional true} :int]
  [:newSheetName {:optional true} :string]
  [:insertSheetIndex {:optional true} :int]
  [:sourceSheetId {:optional true} :int]],
 "DeleteEmbeddedObjectRequest"
 [:map {:closed true} [:objectId {:optional true} :int]],
 "CancelDataSourceRefreshStatus"
 [:map
  {:closed true}
  [:refreshCancellationStatus
   {:optional true}
   [:ref "RefreshCancellationStatus"]]
  [:reference {:optional true} [:ref "DataSourceObjectReference"]]],
 "WaterfallChartSeries"
 [:map
  {:closed true}
  [:hideTrailingSubtotal {:optional true} :boolean]
  [:subtotalColumnsStyle
   {:optional true}
   [:ref "WaterfallChartColumnStyle"]]
  [:positiveColumnsStyle
   {:optional true}
   [:ref "WaterfallChartColumnStyle"]]
  [:customSubtotals
   {:optional true}
   [:vector [:ref "WaterfallChartCustomSubtotal"]]]
  [:negativeColumnsStyle
   {:optional true}
   [:ref "WaterfallChartColumnStyle"]]
  [:data {:optional true} [:ref "ChartData"]]
  [:dataLabel {:optional true} [:ref "DataLabel"]]],
 "ColorStyle"
 [:map
  {:closed true}
  [:rgbColor {:optional true} [:ref "Color"]]
  [:themeColor
   {:optional true}
   [:enum
    "THEME_COLOR_TYPE_UNSPECIFIED"
    "TEXT"
    "BACKGROUND"
    "ACCENT1"
    "ACCENT2"
    "ACCENT3"
    "ACCENT4"
    "ACCENT5"
    "ACCENT6"
    "LINK"]]],
 "UpdateDeveloperMetadataResponse"
 [:map
  {:closed true}
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]],
 "BatchUpdateSpreadsheetResponse"
 [:map
  {:closed true}
  [:spreadsheetId {:optional true} :string]
  [:updatedSpreadsheet {:optional true} [:ref "Spreadsheet"]]
  [:replies {:optional true} [:vector [:ref "Response"]]]],
 "DataSourceObjectReferences"
 [:map
  {:closed true}
  [:references
   {:optional true}
   [:vector [:ref "DataSourceObjectReference"]]]],
 "NamedRange"
 [:map
  {:closed true}
  [:namedRangeId {:optional true} :string]
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]],
 "BatchClearValuesByDataFilterResponse"
 [:map
  {:closed true}
  [:clearedRanges {:optional true} [:vector :string]]
  [:spreadsheetId {:optional true} :string]],
 "AddTableRequest"
 [:map {:closed true} [:table {:optional true} [:ref "Table"]]],
 "SpreadsheetProperties"
 [:map
  {:closed true}
  [:locale {:optional true} :string]
  [:importFunctionsExternalUrlAccessAllowed {:optional true} :boolean]
  [:iterativeCalculationSettings
   {:optional true}
   [:ref "IterativeCalculationSettings"]]
  [:defaultFormat {:optional true} [:ref "CellFormat"]]
  [:timeZone {:optional true} :string]
  [:title {:optional true} :string]
  [:spreadsheetTheme {:optional true} [:ref "SpreadsheetTheme"]]
  [:autoRecalc
   {:optional true}
   [:enum
    "RECALCULATION_INTERVAL_UNSPECIFIED"
    "ON_CHANGE"
    "MINUTE"
    "HOUR"]]],
 "AppendCellsRequest"
 [:map
  {:closed true}
  [:rows {:optional true} [:vector [:ref "RowData"]]]
  [:fields {:optional true} :string]
  [:tableId {:optional true} :string]
  [:sheetId {:optional true} :int]],
 "SortSpec"
 [:map
  {:closed true}
  [:foregroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:dimensionIndex {:optional true} :int]
  [:backgroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:sortOrder
   {:optional true}
   [:enum "SORT_ORDER_UNSPECIFIED" "ASCENDING" "DESCENDING"]]
  [:backgroundColor {:optional true} [:ref "Color"]]
  [:foregroundColor {:optional true} [:ref "Color"]]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]],
 "BasicSeriesDataPointStyleOverride"
 [:map
  {:closed true}
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]
  [:pointStyle {:optional true} [:ref "PointStyle"]]
  [:index {:optional true} :int]],
 "UpdateEmbeddedObjectPositionResponse"
 [:map
  {:closed true}
  [:position {:optional true} [:ref "EmbeddedObjectPosition"]]],
 "TreemapChartSpec"
 [:map
  {:closed true}
  [:labels {:optional true} [:ref "ChartData"]]
  [:maxValue {:optional true} :double]
  [:headerColorStyle {:optional true} [:ref "ColorStyle"]]
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:parentLabels {:optional true} [:ref "ChartData"]]
  [:hideTooltips {:optional true} :boolean]
  [:minValue {:optional true} :double]
  [:levels {:optional true} :int]
  [:colorData {:optional true} [:ref "ChartData"]]
  [:sizeData {:optional true} [:ref "ChartData"]]
  [:colorScale {:optional true} [:ref "TreemapChartColorScale"]]
  [:hintedLevels {:optional true} :int]
  [:headerColor {:optional true} [:ref "Color"]]],
 "Chip"
 [:map
  {:closed true}
  [:personProperties {:optional true} [:ref "PersonProperties"]]
  [:richLinkProperties {:optional true} [:ref "RichLinkProperties"]]],
 "TableColumnProperties"
 [:map
  {:closed true}
  [:columnType
   {:optional true}
   [:enum
    "COLUMN_TYPE_UNSPECIFIED"
    "DOUBLE"
    "CURRENCY"
    "PERCENT"
    "DATE"
    "TIME"
    "DATE_TIME"
    "TEXT"
    "BOOLEAN"
    "DROPDOWN"
    "FILES_CHIP"
    "PEOPLE_CHIP"
    "FINANCE_CHIP"
    "PLACE_CHIP"
    "RATINGS_CHIP"]]
  [:columnIndex {:optional true} :int]
  [:dataValidationRule
   {:optional true}
   [:ref "TableColumnDataValidationRule"]]
  [:columnName {:optional true} :string]],
 "PivotTable"
 [:map
  {:closed true}
  [:filterSpecs {:optional true} [:vector [:ref "PivotFilterSpec"]]]
  [:columns {:optional true} [:vector [:ref "PivotGroup"]]]
  [:criteria {:optional true} [:map-of :string :any]]
  [:source {:optional true} [:ref "GridRange"]]
  [:rows {:optional true} [:vector [:ref "PivotGroup"]]]
  [:values {:optional true} [:vector [:ref "PivotValue"]]]
  [:dataSourceId {:optional true} :string]
  [:valueLayout {:optional true} [:enum "HORIZONTAL" "VERTICAL"]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "PivotGroupRule"
 [:map
  {:closed true}
  [:manualRule {:optional true} [:ref "ManualRule"]]
  [:dateTimeRule {:optional true} [:ref "DateTimeRule"]]
  [:histogramRule {:optional true} [:ref "HistogramRule"]]],
 "DataLabel"
 [:map
  {:closed true}
  [:placement
   {:optional true}
   [:enum
    "DATA_LABEL_PLACEMENT_UNSPECIFIED"
    "CENTER"
    "LEFT"
    "RIGHT"
    "ABOVE"
    "BELOW"
    "INSIDE_END"
    "INSIDE_BASE"
    "OUTSIDE_END"]]
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:type
   {:optional true}
   [:enum "DATA_LABEL_TYPE_UNSPECIFIED" "NONE" "DATA" "CUSTOM"]]
  [:customLabelData {:optional true} [:ref "ChartData"]]],
 "MoveDimensionRequest"
 [:map
  {:closed true}
  [:source {:optional true} [:ref "DimensionRange"]]
  [:destinationIndex {:optional true} :int]],
 "EmbeddedObjectPosition"
 [:map
  {:closed true}
  [:overlayPosition {:optional true} [:ref "OverlayPosition"]]
  [:sheetId {:optional true} :int]
  [:newSheet {:optional true} :boolean]],
 "BasicChartAxis"
 [:map
  {:closed true}
  [:format {:optional true} [:ref "TextFormat"]]
  [:titleTextPosition {:optional true} [:ref "TextPosition"]]
  [:title {:optional true} :string]
  [:position
   {:optional true}
   [:enum
    "BASIC_CHART_AXIS_POSITION_UNSPECIFIED"
    "BOTTOM_AXIS"
    "LEFT_AXIS"
    "RIGHT_AXIS"]]
  [:viewWindowOptions
   {:optional true}
   [:ref "ChartAxisViewWindowOptions"]]],
 "ExtendedValue"
 [:map
  {:closed true}
  [:stringValue {:optional true} :string]
  [:numberValue {:optional true} :double]
  [:boolValue {:optional true} :boolean]
  [:errorValue {:optional true} [:ref "ErrorValue"]]
  [:formulaValue {:optional true} :string]],
 "UpdateFilterViewRequest"
 [:map
  {:closed true}
  [:filter {:optional true} [:ref "FilterView"]]
  [:fields {:optional true} :string]],
 "PivotGroupLimit"
 [:map
  {:closed true}
  [:applyOrder {:optional true} :int]
  [:countLimit {:optional true} :int]],
 "AddFilterViewRequest"
 [:map {:closed true} [:filter {:optional true} [:ref "FilterView"]]],
 "AddNamedRangeRequest"
 [:map
  {:closed true}
  [:namedRange {:optional true} [:ref "NamedRange"]]],
 "AddNamedRangeResponse"
 [:map
  {:closed true}
  [:namedRange {:optional true} [:ref "NamedRange"]]],
 "ChartData"
 [:map
  {:closed true}
  [:columnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:sourceRange {:optional true} [:ref "ChartSourceRange"]]
  [:groupRule {:optional true} [:ref "ChartGroupRule"]]
  [:aggregateType
   {:optional true}
   [:enum
    "CHART_AGGREGATE_TYPE_UNSPECIFIED"
    "AVERAGE"
    "COUNT"
    "MAX"
    "MEDIAN"
    "MIN"
    "SUM"]]],
 "OrgChartSpec"
 [:map
  {:closed true}
  [:nodeSize
   {:optional true}
   [:enum "ORG_CHART_LABEL_SIZE_UNSPECIFIED" "SMALL" "MEDIUM" "LARGE"]]
  [:nodeColorStyle {:optional true} [:ref "ColorStyle"]]
  [:selectedNodeColor {:optional true} [:ref "Color"]]
  [:selectedNodeColorStyle {:optional true} [:ref "ColorStyle"]]
  [:nodeColor {:optional true} [:ref "Color"]]
  [:tooltips {:optional true} [:ref "ChartData"]]
  [:parentLabels {:optional true} [:ref "ChartData"]]
  [:labels {:optional true} [:ref "ChartData"]]],
 "IterativeCalculationSettings"
 [:map
  {:closed true}
  [:maxIterations {:optional true} :int]
  [:convergenceThreshold {:optional true} :double]],
 "PivotGroupSortValueBucket"
 [:map
  {:closed true}
  [:buckets {:optional true} [:vector [:ref "ExtendedValue"]]]
  [:valuesIndex {:optional true} :int]],
 "DeleteDuplicatesRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "GridRange"]]
  [:comparisonColumns
   {:optional true}
   [:vector [:ref "DimensionRange"]]]],
 "UpdateDimensionGroupRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:dimensionGroup {:optional true} [:ref "DimensionGroup"]]],
 "DeleteFilterViewRequest"
 [:map {:closed true} [:filterId {:optional true} :int]],
 "TrimWhitespaceRequest"
 [:map {:closed true} [:range {:optional true} [:ref "GridRange"]]],
 "CandlestickSeries"
 [:map {:closed true} [:data {:optional true} [:ref "ChartData"]]],
 "BubbleChartSpec"
 [:map
  {:closed true}
  [:bubbleBorderColor {:optional true} [:ref "Color"]]
  [:bubbleBorderColorStyle {:optional true} [:ref "ColorStyle"]]
  [:bubbleTextStyle {:optional true} [:ref "TextFormat"]]
  [:series {:optional true} [:ref "ChartData"]]
  [:legendPosition
   {:optional true}
   [:enum
    "BUBBLE_CHART_LEGEND_POSITION_UNSPECIFIED"
    "BOTTOM_LEGEND"
    "LEFT_LEGEND"
    "RIGHT_LEGEND"
    "TOP_LEGEND"
    "NO_LEGEND"
    "INSIDE_LEGEND"]]
  [:bubbleLabels {:optional true} [:ref "ChartData"]]
  [:groupIds {:optional true} [:ref "ChartData"]]
  [:bubbleMinRadiusSize {:optional true} :int]
  [:domain {:optional true} [:ref "ChartData"]]
  [:bubbleOpacity {:optional true} :double]
  [:bubbleMaxRadiusSize {:optional true} :int]
  [:bubbleSizes {:optional true} [:ref "ChartData"]]],
 "UpdateSlicerSpecRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:spec {:optional true} [:ref "SlicerSpec"]]
  [:slicerId {:optional true} :int]],
 "UpdateSheetPropertiesRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "BandingProperties"
 [:map
  {:closed true}
  [:footerColorStyle {:optional true} [:ref "ColorStyle"]]
  [:firstBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:headerColor {:optional true} [:ref "Color"]]
  [:secondBandColor {:optional true} [:ref "Color"]]
  [:footerColor {:optional true} [:ref "Color"]]
  [:firstBandColor {:optional true} [:ref "Color"]]
  [:headerColorStyle {:optional true} [:ref "ColorStyle"]]
  [:secondBandColorStyle {:optional true} [:ref "ColorStyle"]]],
 "GridRange"
 [:map
  {:closed true}
  [:endRowIndex {:optional true} :int]
  [:endColumnIndex {:optional true} :int]
  [:startColumnIndex {:optional true} :int]
  [:sheetId {:optional true} :int]
  [:startRowIndex {:optional true} :int]],
 "TextRotation"
 [:map
  {:closed true}
  [:angle {:optional true} :int]
  [:vertical {:optional true} :boolean]],
 "DeleteDeveloperMetadataRequest"
 [:map
  {:closed true}
  [:dataFilter {:optional true} [:ref "DataFilter"]]],
 "Padding"
 [:map
  {:closed true}
  [:right {:optional true} :int]
  [:bottom {:optional true} :int]
  [:left {:optional true} :int]
  [:top {:optional true} :int]],
 "UpdateChartSpecRequest"
 [:map
  {:closed true}
  [:spec {:optional true} [:ref "ChartSpec"]]
  [:chartId {:optional true} :int]],
 "DeleteProtectedRangeRequest"
 [:map {:closed true} [:protectedRangeId {:optional true} :int]],
 "KeyValueFormat"
 [:map
  {:closed true}
  [:position {:optional true} [:ref "TextPosition"]]
  [:textFormat {:optional true} [:ref "TextFormat"]]],
 "ValueRange"
 [:map
  {:closed true}
  [:range {:optional true} :string]
  [:values {:optional true} [:vector [:vector :any]]]
  [:majorDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "DimensionProperties"
 [:map
  {:closed true}
  [:pixelSize {:optional true} :int]
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:hiddenByFilter {:optional true} :boolean]
  [:hiddenByUser {:optional true} :boolean]],
 "DeleteConditionalFormatRuleRequest"
 [:map
  {:closed true}
  [:index {:optional true} :int]
  [:sheetId {:optional true} :int]],
 "DataSourceRefreshSchedule"
 [:map
  {:closed true}
  [:dailySchedule
   {:optional true}
   [:ref "DataSourceRefreshDailySchedule"]]
  [:refreshScope
   {:optional true}
   [:enum "DATA_SOURCE_REFRESH_SCOPE_UNSPECIFIED" "ALL_DATA_SOURCES"]]
  [:nextRun {:optional true} [:ref "Interval"]]
  [:monthlySchedule
   {:optional true}
   [:ref "DataSourceRefreshMonthlySchedule"]]
  [:enabled {:optional true} :boolean]
  [:weeklySchedule
   {:optional true}
   [:ref "DataSourceRefreshWeeklySchedule"]]],
 "Interval"
 [:map
  {:closed true}
  [:startTime {:optional true} :string]
  [:endTime {:optional true} :string]],
 "WaterfallChartDomain"
 [:map
  {:closed true}
  [:data {:optional true} [:ref "ChartData"]]
  [:reversed {:optional true} :boolean]],
 "BatchUpdateValuesResponse"
 [:map
  {:closed true}
  [:totalUpdatedCells {:optional true} :int]
  [:totalUpdatedRows {:optional true} :int]
  [:totalUpdatedColumns {:optional true} :int]
  [:responses {:optional true} [:vector [:ref "UpdateValuesResponse"]]]
  [:totalUpdatedSheets {:optional true} :int]
  [:spreadsheetId {:optional true} :string]],
 "DimensionGroup"
 [:map
  {:closed true}
  [:collapsed {:optional true} :boolean]
  [:depth {:optional true} :int]
  [:range {:optional true} [:ref "DimensionRange"]]],
 "BatchClearValuesByDataFilterRequest"
 [:map
  {:closed true}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "BigQueryDataSourceSpec"
 [:map
  {:closed true}
  [:tableSpec {:optional true} [:ref "BigQueryTableSpec"]]
  [:querySpec {:optional true} [:ref "BigQueryQuerySpec"]]
  [:projectId {:optional true} :string]],
 "AutoFillRequest"
 [:map
  {:closed true}
  [:sourceAndDestination
   {:optional true}
   [:ref "SourceAndDestination"]]
  [:useAlternateSeries {:optional true} :boolean]
  [:range {:optional true} [:ref "GridRange"]]],
 "LookerDataSourceSpec"
 [:map
  {:closed true}
  [:instanceUri {:optional true} :string]
  [:model {:optional true} :string]
  [:explore {:optional true} :string]],
 "TrimWhitespaceResponse"
 [:map {:closed true} [:cellsChangedCount {:optional true} :int]],
 "InsertDimensionRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "DimensionRange"]]
  [:inheritFromBefore {:optional true} :boolean]],
 "AutoResizeDimensionsRequest"
 [:map
  {:closed true}
  [:dimensions {:optional true} [:ref "DimensionRange"]]
  [:dataSourceSheetDimensions
   {:optional true}
   [:ref "DataSourceSheetDimensionRange"]]],
 "SortRangeRequest"
 [:map
  {:closed true}
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:range {:optional true} [:ref "GridRange"]]],
 "DuplicateFilterViewRequest"
 [:map {:closed true} [:filterId {:optional true} :int]],
 "UpdateValuesByDataFilterResponse"
 [:map
  {:closed true}
  [:updatedData {:optional true} [:ref "ValueRange"]]
  [:updatedCells {:optional true} :int]
  [:updatedRows {:optional true} :int]
  [:dataFilter {:optional true} [:ref "DataFilter"]]
  [:updatedRange {:optional true} :string]
  [:updatedColumns {:optional true} :int]],
 "Response"
 [:map
  {:closed true}
  [:addSlicer {:optional true} [:ref "AddSlicerResponse"]]
  [:addDimensionGroup
   {:optional true}
   [:ref "AddDimensionGroupResponse"]]
  [:addFilterView {:optional true} [:ref "AddFilterViewResponse"]]
  [:addDataSource {:optional true} [:ref "AddDataSourceResponse"]]
  [:duplicateSheet {:optional true} [:ref "DuplicateSheetResponse"]]
  [:addTable {:optional true} [:ref "AddTableResponse"]]
  [:trimWhitespace {:optional true} [:ref "TrimWhitespaceResponse"]]
  [:addBanding {:optional true} [:ref "AddBandingResponse"]]
  [:deleteDuplicates
   {:optional true}
   [:ref "DeleteDuplicatesResponse"]]
  [:cancelDataSourceRefresh
   {:optional true}
   [:ref "CancelDataSourceRefreshResponse"]]
  [:updateDeveloperMetadata
   {:optional true}
   [:ref "UpdateDeveloperMetadataResponse"]]
  [:duplicateFilterView
   {:optional true}
   [:ref "DuplicateFilterViewResponse"]]
  [:deleteDeveloperMetadata
   {:optional true}
   [:ref "DeleteDeveloperMetadataResponse"]]
  [:createDeveloperMetadata
   {:optional true}
   [:ref "CreateDeveloperMetadataResponse"]]
  [:updateDataSource
   {:optional true}
   [:ref "UpdateDataSourceResponse"]]
  [:deleteConditionalFormatRule
   {:optional true}
   [:ref "DeleteConditionalFormatRuleResponse"]]
  [:refreshDataSource
   {:optional true}
   [:ref "RefreshDataSourceResponse"]]
  [:deleteDimensionGroup
   {:optional true}
   [:ref "DeleteDimensionGroupResponse"]]
  [:findReplace {:optional true} [:ref "FindReplaceResponse"]]
  [:updateEmbeddedObjectPosition
   {:optional true}
   [:ref "UpdateEmbeddedObjectPositionResponse"]]
  [:addSheet {:optional true} [:ref "AddSheetResponse"]]
  [:addNamedRange {:optional true} [:ref "AddNamedRangeResponse"]]
  [:addChart {:optional true} [:ref "AddChartResponse"]]
  [:addProtectedRange
   {:optional true}
   [:ref "AddProtectedRangeResponse"]]
  [:updateConditionalFormatRule
   {:optional true}
   [:ref "UpdateConditionalFormatRuleResponse"]]],
 "BatchGetValuesByDataFilterResponse"
 [:map
  {:closed true}
  [:valueRanges {:optional true} [:vector [:ref "MatchedValueRange"]]]
  [:spreadsheetId {:optional true} :string]],
 "AddBandingResponse"
 [:map
  {:closed true}
  [:bandedRange {:optional true} [:ref "BandedRange"]]],
 "AddDataSourceResponse"
 [:map
  {:closed true}
  [:dataSource {:optional true} [:ref "DataSource"]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "DeleteRangeRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "GridRange"]]
  [:shiftDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "ClearValuesRequest" [:map {:closed true}],
 "PersonProperties"
 [:map
  {:closed true}
  [:email {:optional true} :string]
  [:displayFormat
   {:optional true}
   [:enum
    "DISPLAY_FORMAT_UNSPECIFIED"
    "DEFAULT"
    "LAST_NAME_COMMA_FIRST_NAME"
    "EMAIL"]]],
 "DataSourceSheetDimensionRange"
 [:map
  {:closed true}
  [:sheetId {:optional true} :int]
  [:columnReferences
   {:optional true}
   [:vector [:ref "DataSourceColumnReference"]]]],
 "AddConditionalFormatRuleRequest"
 [:map
  {:closed true}
  [:rule {:optional true} [:ref "ConditionalFormatRule"]]
  [:index {:optional true} :int]],
 "BasicChartSeries"
 [:map
  {:closed true}
  [:dataLabel {:optional true} [:ref "DataLabel"]]
  [:styleOverrides
   {:optional true}
   [:vector [:ref "BasicSeriesDataPointStyleOverride"]]]
  [:color {:optional true} [:ref "Color"]]
  [:series {:optional true} [:ref "ChartData"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]
  [:type
   {:optional true}
   [:enum
    "BASIC_CHART_TYPE_UNSPECIFIED"
    "BAR"
    "LINE"
    "AREA"
    "COLUMN"
    "SCATTER"
    "COMBO"
    "STEPPED_AREA"]]
  [:targetAxis
   {:optional true}
   [:enum
    "BASIC_CHART_AXIS_POSITION_UNSPECIFIED"
    "BOTTOM_AXIS"
    "LEFT_AXIS"
    "RIGHT_AXIS"]]
  [:pointStyle {:optional true} [:ref "PointStyle"]]
  [:lineStyle {:optional true} [:ref "LineStyle"]]],
 "WaterfallChartColumnStyle"
 [:map
  {:closed true}
  [:color {:optional true} [:ref "Color"]]
  [:label {:optional true} :string]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]],
 "UpdateTableRequest"
 [:map
  {:closed true}
  [:table {:optional true} [:ref "Table"]]
  [:fields {:optional true} :string]],
 "WaterfallChartSpec"
 [:map
  {:closed true}
  [:series {:optional true} [:vector [:ref "WaterfallChartSeries"]]]
  [:hideConnectorLines {:optional true} :boolean]
  [:totalDataLabel {:optional true} [:ref "DataLabel"]]
  [:stackedType
   {:optional true}
   [:enum "WATERFALL_STACKED_TYPE_UNSPECIFIED" "STACKED" "SEQUENTIAL"]]
  [:domain {:optional true} [:ref "WaterfallChartDomain"]]
  [:firstValueIsTotal {:optional true} :boolean]
  [:connectorLineStyle {:optional true} [:ref "LineStyle"]]],
 "TextToColumnsRequest"
 [:map
  {:closed true}
  [:delimiterType
   {:optional true}
   [:enum
    "DELIMITER_TYPE_UNSPECIFIED"
    "COMMA"
    "SEMICOLON"
    "PERIOD"
    "SPACE"
    "CUSTOM"
    "AUTODETECT"]]
  [:source {:optional true} [:ref "GridRange"]]
  [:delimiter {:optional true} :string]],
 "CancelDataSourceRefreshResponse"
 [:map
  {:closed true}
  [:statuses
   {:optional true}
   [:vector [:ref "CancelDataSourceRefreshStatus"]]]],
 "AddFilterViewResponse"
 [:map {:closed true} [:filter {:optional true} [:ref "FilterView"]]],
 "DeveloperMetadata"
 [:map
  {:closed true}
  [:metadataValue {:optional true} :string]
  [:visibility
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_VISIBILITY_UNSPECIFIED"
    "DOCUMENT"
    "PROJECT"]]
  [:metadataId {:optional true} :int]
  [:location {:optional true} [:ref "DeveloperMetadataLocation"]]
  [:metadataKey {:optional true} :string]],
 "SlicerSpec"
 [:map
  {:closed true}
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:dataRange {:optional true} [:ref "GridRange"]]
  [:filterCriteria {:optional true} [:ref "FilterCriteria"]]
  [:title {:optional true} :string]
  [:applyToPivotTables {:optional true} :boolean]
  [:columnIndex {:optional true} :int]
  [:backgroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:backgroundColor {:optional true} [:ref "Color"]]
  [:horizontalAlignment
   {:optional true}
   [:enum "HORIZONTAL_ALIGN_UNSPECIFIED" "LEFT" "CENTER" "RIGHT"]]],
 "RichLinkProperties"
 [:map
  {:closed true}
  [:uri {:optional true} :string]
  [:mimeType {:optional true} :string]],
 "UpdateBandingRequest"
 [:map
  {:closed true}
  [:bandedRange {:optional true} [:ref "BandedRange"]]
  [:fields {:optional true} :string]],
 "Slicer"
 [:map
  {:closed true}
  [:slicerId {:optional true} :int]
  [:spec {:optional true} [:ref "SlicerSpec"]]
  [:position {:optional true} [:ref "EmbeddedObjectPosition"]]],
 "RefreshDataSourceResponse"
 [:map
  {:closed true}
  [:statuses
   {:optional true}
   [:vector [:ref "RefreshDataSourceObjectExecutionStatus"]]]],
 "UpdateDataSourceResponse"
 [:map
  {:closed true}
  [:dataSource {:optional true} [:ref "DataSource"]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "ChartSourceRange"
 [:map
  {:closed true}
  [:sources {:optional true} [:vector [:ref "GridRange"]]]],
 "GetSpreadsheetByDataFilterRequest"
 [:map
  {:closed true}
  [:excludeTablesInBandedRanges {:optional true} :boolean]
  [:includeGridData {:optional true} :boolean]
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "ManualRuleGroup"
 [:map
  {:closed true}
  [:items {:optional true} [:vector [:ref "ExtendedValue"]]]
  [:groupName {:optional true} [:ref "ExtendedValue"]]],
 "RefreshDataSourceRequest"
 [:map
  {:closed true}
  [:references {:optional true} [:ref "DataSourceObjectReferences"]]
  [:force {:optional true} :boolean]
  [:isAll {:optional true} :boolean]
  [:dataSourceId {:optional true} :string]],
 "BooleanCondition"
 [:map
  {:closed true}
  [:values {:optional true} [:vector [:ref "ConditionValue"]]]
  [:type
   {:optional true}
   [:enum
    "CONDITION_TYPE_UNSPECIFIED"
    "NUMBER_GREATER"
    "NUMBER_GREATER_THAN_EQ"
    "NUMBER_LESS"
    "NUMBER_LESS_THAN_EQ"
    "NUMBER_EQ"
    "NUMBER_NOT_EQ"
    "NUMBER_BETWEEN"
    "NUMBER_NOT_BETWEEN"
    "TEXT_CONTAINS"
    "TEXT_NOT_CONTAINS"
    "TEXT_STARTS_WITH"
    "TEXT_ENDS_WITH"
    "TEXT_EQ"
    "TEXT_IS_EMAIL"
    "TEXT_IS_URL"
    "DATE_EQ"
    "DATE_BEFORE"
    "DATE_AFTER"
    "DATE_ON_OR_BEFORE"
    "DATE_ON_OR_AFTER"
    "DATE_BETWEEN"
    "DATE_NOT_BETWEEN"
    "DATE_IS_VALID"
    "ONE_OF_RANGE"
    "ONE_OF_LIST"
    "BLANK"
    "NOT_BLANK"
    "CUSTOM_FORMULA"
    "BOOLEAN"
    "TEXT_NOT_EQ"
    "DATE_NOT_EQ"
    "FILTER_EXPRESSION"]]],
 "FindReplaceRequest"
 [:map
  {:closed true}
  [:find {:optional true} :string]
  [:includeFormulas {:optional true} :boolean]
  [:matchEntireCell {:optional true} :boolean]
  [:sheetId {:optional true} :int]
  [:replacement {:optional true} :string]
  [:matchCase {:optional true} :boolean]
  [:allSheets {:optional true} :boolean]
  [:searchByRegex {:optional true} :boolean]
  [:range {:optional true} [:ref "GridRange"]]],
 "DataFilterValueRange"
 [:map
  {:closed true}
  [:dataFilter {:optional true} [:ref "DataFilter"]]
  [:majorDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:values {:optional true} [:vector [:vector :any]]]],
 "BatchClearValuesRequest"
 [:map {:closed true} [:ranges {:optional true} [:vector :string]]],
 "PivotValue"
 [:map
  {:closed true}
  [:calculatedDisplayType
   {:optional true}
   [:enum
    "PIVOT_VALUE_CALCULATED_DISPLAY_TYPE_UNSPECIFIED"
    "PERCENT_OF_ROW_TOTAL"
    "PERCENT_OF_COLUMN_TOTAL"
    "PERCENT_OF_GRAND_TOTAL"]]
  [:formula {:optional true} :string]
  [:sourceColumnOffset {:optional true} :int]
  [:name {:optional true} :string]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:summarizeFunction
   {:optional true}
   [:enum
    "PIVOT_STANDARD_VALUE_FUNCTION_UNSPECIFIED"
    "SUM"
    "COUNTA"
    "COUNT"
    "COUNTUNIQUE"
    "AVERAGE"
    "MAX"
    "MIN"
    "MEDIAN"
    "PRODUCT"
    "STDEV"
    "STDEVP"
    "VAR"
    "VARP"
    "CUSTOM"
    "NONE"]]],
 "DeleteTableRequest"
 [:map {:closed true} [:tableId {:optional true} :string]],
 "PasteDataRequest"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum
    "PASTE_NORMAL"
    "PASTE_VALUES"
    "PASTE_FORMAT"
    "PASTE_NO_BORDERS"
    "PASTE_FORMULA"
    "PASTE_DATA_VALIDATION"
    "PASTE_CONDITIONAL_FORMATTING"]]
  [:coordinate {:optional true} [:ref "GridCoordinate"]]
  [:data {:optional true} :string]
  [:delimiter {:optional true} :string]
  [:html {:optional true} :boolean]],
 "CutPasteRequest"
 [:map
  {:closed true}
  [:destination {:optional true} [:ref "GridCoordinate"]]
  [:source {:optional true} [:ref "GridRange"]]
  [:pasteType
   {:optional true}
   [:enum
    "PASTE_NORMAL"
    "PASTE_VALUES"
    "PASTE_FORMAT"
    "PASTE_NO_BORDERS"
    "PASTE_FORMULA"
    "PASTE_DATA_VALIDATION"
    "PASTE_CONDITIONAL_FORMATTING"]]],
 "UpdateDimensionPropertiesRequest"
 [:map
  {:closed true}
  [:dataSourceSheetRange
   {:optional true}
   [:ref "DataSourceSheetDimensionRange"]]
  [:properties {:optional true} [:ref "DimensionProperties"]]
  [:range {:optional true} [:ref "DimensionRange"]]
  [:fields {:optional true} :string]],
 "TextFormatRun"
 [:map
  {:closed true}
  [:startIndex {:optional true} :int]
  [:format {:optional true} [:ref "TextFormat"]]],
 "MatchedValueRange"
 [:map
  {:closed true}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:valueRange {:optional true} [:ref "ValueRange"]]],
 "ConditionValue"
 [:map
  {:closed true}
  [:relativeDate
   {:optional true}
   [:enum
    "RELATIVE_DATE_UNSPECIFIED"
    "PAST_YEAR"
    "PAST_MONTH"
    "PAST_WEEK"
    "YESTERDAY"
    "TODAY"
    "TOMORROW"]]
  [:userEnteredValue {:optional true} :string]],
 "BasicChartDomain"
 [:map
  {:closed true}
  [:reversed {:optional true} :boolean]
  [:domain {:optional true} [:ref "ChartData"]]],
 "Spreadsheet"
 [:map
  {:closed true}
  [:dataSourceSchedules
   {:optional true}
   [:vector [:ref "DataSourceRefreshSchedule"]]]
  [:spreadsheetUrl {:optional true} :string]
  [:dataSources {:optional true} [:vector [:ref "DataSource"]]]
  [:properties {:optional true} [:ref "SpreadsheetProperties"]]
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]
  [:sheets {:optional true} [:vector [:ref "Sheet"]]]
  [:namedRanges {:optional true} [:vector [:ref "NamedRange"]]]
  [:spreadsheetId {:optional true} :string]],
 "SetBasicFilterRequest"
 [:map {:closed true} [:filter {:optional true} [:ref "BasicFilter"]]],
 "BatchGetValuesByDataFilterRequest"
 [:map
  {:closed true}
  [:valueRenderOption
   {:optional true}
   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:dateTimeRenderOption
   {:optional true}
   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]
  [:majorDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "CandlestickChartSpec"
 [:map
  {:closed true}
  [:data {:optional true} [:vector [:ref "CandlestickData"]]]
  [:domain {:optional true} [:ref "CandlestickDomain"]]],
 "FilterView"
 [:map
  {:closed true}
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]
  [:range {:optional true} [:ref "GridRange"]]
  [:namedRangeId {:optional true} :string]
  [:tableId {:optional true} :string]
  [:title {:optional true} :string]
  [:criteria {:optional true} [:map-of :string :any]]
  [:filterViewId {:optional true} :int]],
 "DuplicateSheetResponse"
 [:map
  {:closed true}
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "PivotFilterCriteria"
 [:map
  {:closed true}
  [:visibleByDefault {:optional true} :boolean]
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:visibleValues {:optional true} [:vector :string]]],
 "DataSourceColumnReference"
 [:map {:closed true} [:name {:optional true} :string]],
 "SearchDeveloperMetadataRequest"
 [:map
  {:closed true}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "RowData"
 [:map
  {:closed true}
  [:values {:optional true} [:vector [:ref "CellData"]]]],
 "DataSource"
 [:map
  {:closed true}
  [:spec {:optional true} [:ref "DataSourceSpec"]]
  [:sheetId {:optional true} :int]
  [:dataSourceId {:optional true} :string]
  [:calculatedColumns
   {:optional true}
   [:vector [:ref "DataSourceColumn"]]]],
 "DeveloperMetadataLocation"
 [:map
  {:closed true}
  [:sheetId {:optional true} :int]
  [:dimensionRange {:optional true} [:ref "DimensionRange"]]
  [:spreadsheet {:optional true} :boolean]
  [:locationType
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_LOCATION_TYPE_UNSPECIFIED"
    "ROW"
    "COLUMN"
    "SHEET"
    "SPREADSHEET"]]],
 "DataSourceObjectReference"
 [:map
  {:closed true}
  [:sheetId {:optional true} :string]
  [:chartId {:optional true} :int]
  [:dataSourceFormulaCell {:optional true} [:ref "GridCoordinate"]]
  [:dataSourceTableAnchorCell {:optional true} [:ref "GridCoordinate"]]
  [:dataSourcePivotTableAnchorCell
   {:optional true}
   [:ref "GridCoordinate"]]],
 "BatchGetValuesResponse"
 [:map
  {:closed true}
  [:valueRanges {:optional true} [:vector [:ref "ValueRange"]]]
  [:spreadsheetId {:optional true} :string]],
 "Sheet"
 [:map
  {:closed true}
  [:properties {:optional true} [:ref "SheetProperties"]]
  [:filterViews {:optional true} [:vector [:ref "FilterView"]]]
  [:slicers {:optional true} [:vector [:ref "Slicer"]]]
  [:columnGroups {:optional true} [:vector [:ref "DimensionGroup"]]]
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]
  [:protectedRanges {:optional true} [:vector [:ref "ProtectedRange"]]]
  [:tables {:optional true} [:vector [:ref "Table"]]]
  [:rowGroups {:optional true} [:vector [:ref "DimensionGroup"]]]
  [:bandedRanges {:optional true} [:vector [:ref "BandedRange"]]]
  [:basicFilter {:optional true} [:ref "BasicFilter"]]
  [:merges {:optional true} [:vector [:ref "GridRange"]]]
  [:charts {:optional true} [:vector [:ref "EmbeddedChart"]]]
  [:conditionalFormats
   {:optional true}
   [:vector [:ref "ConditionalFormatRule"]]]
  [:data {:optional true} [:vector [:ref "GridData"]]]],
 "DuplicateFilterViewResponse"
 [:map {:closed true} [:filter {:optional true} [:ref "FilterView"]]],
 "ChartAxisViewWindowOptions"
 [:map
  {:closed true}
  [:viewWindowMode
   {:optional true}
   [:enum
    "DEFAULT_VIEW_WINDOW_MODE"
    "VIEW_WINDOW_MODE_UNSUPPORTED"
    "EXPLICIT"
    "PRETTY"]]
  [:viewWindowMin {:optional true} :double]
  [:viewWindowMax {:optional true} :double]],
 "UnmergeCellsRequest"
 [:map {:closed true} [:range {:optional true} [:ref "GridRange"]]],
 "CopySheetToAnotherSpreadsheetRequest"
 [:map
  {:closed true}
  [:destinationSpreadsheetId {:optional true} :string]],
 "BatchUpdateValuesByDataFilterRequest"
 [:map
  {:closed true}
  [:data {:optional true} [:vector [:ref "DataFilterValueRange"]]]
  [:responseValueRenderOption
   {:optional true}
   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
  [:includeValuesInResponse {:optional true} :boolean]
  [:valueInputOption
   {:optional true}
   [:enum "INPUT_VALUE_OPTION_UNSPECIFIED" "RAW" "USER_ENTERED"]]
  [:responseDateTimeRenderOption
   {:optional true}
   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]],
 "FilterSpec"
 [:map
  {:closed true}
  [:columnIndex {:optional true} :int]
  [:filterCriteria {:optional true} [:ref "FilterCriteria"]]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]],
 "ThemeColorPair"
 [:map
  {:closed true}
  [:colorType
   {:optional true}
   [:enum
    "THEME_COLOR_TYPE_UNSPECIFIED"
    "TEXT"
    "BACKGROUND"
    "ACCENT1"
    "ACCENT2"
    "ACCENT3"
    "ACCENT4"
    "ACCENT5"
    "ACCENT6"
    "LINK"]]
  [:color {:optional true} [:ref "ColorStyle"]]],
 "DataSourceRefreshMonthlySchedule"
 [:map
  {:closed true}
  [:daysOfMonth {:optional true} [:vector :int]]
  [:startTime {:optional true} [:ref "TimeOfDay"]]],
 "AppendDimensionRequest"
 [:map
  {:closed true}
  [:length {:optional true} :int]
  [:sheetId {:optional true} :int]
  [:dimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "FilterCriteria"
 [:map
  {:closed true}
  [:hiddenValues {:optional true} [:vector :string]]
  [:visibleForegroundColor {:optional true} [:ref "Color"]]
  [:visibleForegroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:visibleBackgroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:visibleBackgroundColor {:optional true} [:ref "Color"]]],
 "Border"
 [:map
  {:closed true}
  [:colorStyle {:optional true} [:ref "ColorStyle"]]
  [:width {:optional true} :int]
  [:color {:optional true} [:ref "Color"]]
  [:style
   {:optional true}
   [:enum
    "STYLE_UNSPECIFIED"
    "DOTTED"
    "DASHED"
    "SOLID"
    "SOLID_MEDIUM"
    "SOLID_THICK"
    "NONE"
    "DOUBLE"]]],
 "DeleteDimensionGroupRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "DimensionRange"]]],
 "DeleteConditionalFormatRuleResponse"
 [:map
  {:closed true}
  [:rule {:optional true} [:ref "ConditionalFormatRule"]]],
 "UpdateSpreadsheetPropertiesRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:properties {:optional true} [:ref "SpreadsheetProperties"]]],
 "FindReplaceResponse"
 [:map
  {:closed true}
  [:rowsChanged {:optional true} :int]
  [:formulasChanged {:optional true} :int]
  [:valuesChanged {:optional true} :int]
  [:sheetsChanged {:optional true} :int]
  [:occurrencesChanged {:optional true} :int]],
 "SheetProperties"
 [:map
  {:closed true}
  [:tabColor {:optional true} [:ref "Color"]]
  [:sheetId {:optional true} :int]
  [:index {:optional true} :int]
  [:tabColorStyle {:optional true} [:ref "ColorStyle"]]
  [:dataSourceSheetProperties
   {:optional true}
   [:ref "DataSourceSheetProperties"]]
  [:rightToLeft {:optional true} :boolean]
  [:title {:optional true} :string]
  [:hidden {:optional true} :boolean]
  [:sheetType
   {:optional true}
   [:enum "SHEET_TYPE_UNSPECIFIED" "GRID" "OBJECT" "DATA_SOURCE"]]
  [:gridProperties {:optional true} [:ref "GridProperties"]]],
 "PointStyle"
 [:map
  {:closed true}
  [:size {:optional true} :double]
  [:shape
   {:optional true}
   [:enum
    "POINT_SHAPE_UNSPECIFIED"
    "CIRCLE"
    "DIAMOND"
    "HEXAGON"
    "PENTAGON"
    "SQUARE"
    "STAR"
    "TRIANGLE"
    "X_MARK"]]],
 "CreateDeveloperMetadataRequest"
 [:map
  {:closed true}
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]],
 "CopyPasteRequest"
 [:map
  {:closed true}
  [:pasteOrientation {:optional true} [:enum "NORMAL" "TRANSPOSE"]]
  [:source {:optional true} [:ref "GridRange"]]
  [:destination {:optional true} [:ref "GridRange"]]
  [:pasteType
   {:optional true}
   [:enum
    "PASTE_NORMAL"
    "PASTE_VALUES"
    "PASTE_FORMAT"
    "PASTE_NO_BORDERS"
    "PASTE_FORMULA"
    "PASTE_DATA_VALIDATION"
    "PASTE_CONDITIONAL_FORMATTING"]]],
 "GridCoordinate"
 [:map
  {:closed true}
  [:columnIndex {:optional true} :int]
  [:rowIndex {:optional true} :int]
  [:sheetId {:optional true} :int]],
 "ProtectedRange"
 [:map
  {:closed true}
  [:unprotectedRanges {:optional true} [:vector [:ref "GridRange"]]]
  [:description {:optional true} :string]
  [:requestingUserCanEdit {:optional true} :boolean]
  [:editors {:optional true} [:ref "Editors"]]
  [:tableId {:optional true} :string]
  [:namedRangeId {:optional true} :string]
  [:protectedRangeId {:optional true} :int]
  [:warningOnly {:optional true} :boolean]
  [:range {:optional true} [:ref "GridRange"]]],
 "CandlestickDomain"
 [:map
  {:closed true}
  [:reversed {:optional true} :boolean]
  [:data {:optional true} [:ref "ChartData"]]],
 "ManualRule"
 [:map
  {:closed true}
  [:groups {:optional true} [:vector [:ref "ManualRuleGroup"]]]],
 "EmbeddedObjectBorder"
 [:map
  {:closed true}
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]],
 "UpdateEmbeddedObjectBorderRequest"
 [:map
  {:closed true}
  [:border {:optional true} [:ref "EmbeddedObjectBorder"]]
  [:fields {:optional true} :string]
  [:objectId {:optional true} :int]],
 "TreemapChartColorScale"
 [:map
  {:closed true}
  [:midValueColor {:optional true} [:ref "Color"]]
  [:noDataColorStyle {:optional true} [:ref "ColorStyle"]]
  [:noDataColor {:optional true} [:ref "Color"]]
  [:midValueColorStyle {:optional true} [:ref "ColorStyle"]]
  [:maxValueColorStyle {:optional true} [:ref "ColorStyle"]]
  [:maxValueColor {:optional true} [:ref "Color"]]
  [:minValueColorStyle {:optional true} [:ref "ColorStyle"]]
  [:minValueColor {:optional true} [:ref "Color"]]],
 "TextPosition"
 [:map
  {:closed true}
  [:horizontalAlignment
   {:optional true}
   [:enum "HORIZONTAL_ALIGN_UNSPECIFIED" "LEFT" "CENTER" "RIGHT"]]],
 "ChartDateTimeRule"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum
    "CHART_DATE_TIME_RULE_TYPE_UNSPECIFIED"
    "SECOND"
    "MINUTE"
    "HOUR"
    "HOUR_MINUTE"
    "HOUR_MINUTE_AMPM"
    "DAY_OF_WEEK"
    "DAY_OF_YEAR"
    "DAY_OF_MONTH"
    "DAY_MONTH"
    "MONTH"
    "QUARTER"
    "YEAR"
    "YEAR_MONTH"
    "YEAR_QUARTER"
    "YEAR_MONTH_DAY"]]],
 "AddDimensionGroupRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "DimensionRange"]]],
 "AddProtectedRangeResponse"
 [:map
  {:closed true}
  [:protectedRange {:optional true} [:ref "ProtectedRange"]]],
 "PieChartSpec"
 [:map
  {:closed true}
  [:legendPosition
   {:optional true}
   [:enum
    "PIE_CHART_LEGEND_POSITION_UNSPECIFIED"
    "BOTTOM_LEGEND"
    "LEFT_LEGEND"
    "RIGHT_LEGEND"
    "TOP_LEGEND"
    "NO_LEGEND"
    "LABELED_LEGEND"]]
  [:domain {:optional true} [:ref "ChartData"]]
  [:pieHole {:optional true} :double]
  [:series {:optional true} [:ref "ChartData"]]
  [:threeDimensional {:optional true} :boolean]],
 "DataSourceRefreshWeeklySchedule"
 [:map
  {:closed true}
  [:daysOfWeek
   {:optional true}
   [:vector
    [:enum
     "DAY_OF_WEEK_UNSPECIFIED"
     "MONDAY"
     "TUESDAY"
     "WEDNESDAY"
     "THURSDAY"
     "FRIDAY"
     "SATURDAY"
     "SUNDAY"]]]
  [:startTime {:optional true} [:ref "TimeOfDay"]]],
 "OverlayPosition"
 [:map
  {:closed true}
  [:widthPixels {:optional true} :int]
  [:anchorCell {:optional true} [:ref "GridCoordinate"]]
  [:heightPixels {:optional true} :int]
  [:offsetYPixels {:optional true} :int]
  [:offsetXPixels {:optional true} :int]],
 "DeleteDeveloperMetadataResponse"
 [:map
  {:closed true}
  [:deletedDeveloperMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]],
 "SetDataValidationRequest"
 [:map
  {:closed true}
  [:rule {:optional true} [:ref "DataValidationRule"]]
  [:range {:optional true} [:ref "GridRange"]]
  [:filteredRowsIncluded {:optional true} :boolean]],
 "SearchDeveloperMetadataResponse"
 [:map
  {:closed true}
  [:matchedDeveloperMetadata
   {:optional true}
   [:vector [:ref "MatchedDeveloperMetadata"]]]],
 "AddDataSourceRequest"
 [:map
  {:closed true}
  [:dataSource {:optional true} [:ref "DataSource"]]],
 "CellData"
 [:map
  {:closed true}
  [:effectiveFormat {:optional true} [:ref "CellFormat"]]
  [:dataValidation {:optional true} [:ref "DataValidationRule"]]
  [:chipRuns {:optional true} [:vector [:ref "ChipRun"]]]
  [:textFormatRuns {:optional true} [:vector [:ref "TextFormatRun"]]]
  [:effectiveValue {:optional true} [:ref "ExtendedValue"]]
  [:note {:optional true} :string]
  [:dataSourceFormula {:optional true} [:ref "DataSourceFormula"]]
  [:dataSourceTable {:optional true} [:ref "DataSourceTable"]]
  [:formattedValue {:optional true} :string]
  [:hyperlink {:optional true} :string]
  [:userEnteredValue {:optional true} [:ref "ExtendedValue"]]
  [:pivotTable {:optional true} [:ref "PivotTable"]]
  [:userEnteredFormat {:optional true} [:ref "CellFormat"]]],
 "PivotGroup"
 [:map
  {:closed true}
  [:sourceColumnOffset {:optional true} :int]
  [:showTotals {:optional true} :boolean]
  [:groupRule {:optional true} [:ref "PivotGroupRule"]]
  [:label {:optional true} :string]
  [:repeatHeadings {:optional true} :boolean]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:sortOrder
   {:optional true}
   [:enum "SORT_ORDER_UNSPECIFIED" "ASCENDING" "DESCENDING"]]
  [:valueMetadata
   {:optional true}
   [:vector [:ref "PivotGroupValueMetadata"]]]
  [:groupLimit {:optional true} [:ref "PivotGroupLimit"]]
  [:valueBucket {:optional true} [:ref "PivotGroupSortValueBucket"]]],
 "BooleanRule"
 [:map
  {:closed true}
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:format {:optional true} [:ref "CellFormat"]]],
 "DataSourceParameter"
 [:map
  {:closed true}
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]
  [:namedRangeId {:optional true} :string]],
 "RepeatCellRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:cell {:optional true} [:ref "CellData"]]
  [:range {:optional true} [:ref "GridRange"]]],
 "ChartHistogramRule"
 [:map
  {:closed true}
  [:maxValue {:optional true} :double]
  [:intervalSize {:optional true} :double]
  [:minValue {:optional true} :double]],
 "DataSourceTable"
 [:map
  {:closed true}
  [:dataSourceId {:optional true} :string]
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]
  [:rowLimit {:optional true} :int]
  [:columnSelectionType
   {:optional true}
   [:enum
    "DATA_SOURCE_TABLE_COLUMN_SELECTION_TYPE_UNSPECIFIED"
    "SELECTED"
    "SYNC_ALL"]]
  [:columns
   {:optional true}
   [:vector [:ref "DataSourceColumnReference"]]]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "RefreshDataSourceObjectExecutionStatus"
 [:map
  {:closed true}
  [:dataExecutionStatus {:optional true} [:ref "DataExecutionStatus"]]
  [:reference {:optional true} [:ref "DataSourceObjectReference"]]],
 "BatchClearValuesResponse"
 [:map
  {:closed true}
  [:clearedRanges {:optional true} [:vector :string]]
  [:spreadsheetId {:optional true} :string]],
 "DeleteDataSourceRequest"
 [:map {:closed true} [:dataSourceId {:optional true} :string]],
 "InsertRangeRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "GridRange"]]
  [:shiftDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "BasicFilter"
 [:map
  {:closed true}
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]
  [:range {:optional true} [:ref "GridRange"]]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:tableId {:optional true} :string]
  [:criteria {:optional true} [:map-of :string :any]]],
 "BandedRange"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "GridRange"]]
  [:columnProperties {:optional true} [:ref "BandingProperties"]]
  [:bandedRangeId {:optional true} :int]
  [:bandedRangeReference {:optional true} :string]
  [:rowProperties {:optional true} [:ref "BandingProperties"]]],
 "DataValidationRule"
 [:map
  {:closed true}
  [:inputMessage {:optional true} :string]
  [:strict {:optional true} :boolean]
  [:showCustomUi {:optional true} :boolean]
  [:condition {:optional true} [:ref "BooleanCondition"]]],
 "AddProtectedRangeRequest"
 [:map
  {:closed true}
  [:protectedRange {:optional true} [:ref "ProtectedRange"]]],
 "DataSourceSpec"
 [:map
  {:closed true}
  [:bigQuery {:optional true} [:ref "BigQueryDataSourceSpec"]]
  [:parameters {:optional true} [:vector [:ref "DataSourceParameter"]]]
  [:looker {:optional true} [:ref "LookerDataSourceSpec"]]],
 "BatchUpdateSpreadsheetRequest"
 [:map
  {:closed true}
  [:includeSpreadsheetInResponse {:optional true} :boolean]
  [:responseIncludeGridData {:optional true} :boolean]
  [:responseRanges {:optional true} [:vector :string]]
  [:requests {:optional true} [:vector [:ref "Request"]]]],
 "UpdateConditionalFormatRuleResponse"
 [:map
  {:closed true}
  [:oldRule {:optional true} [:ref "ConditionalFormatRule"]]
  [:oldIndex {:optional true} :int]
  [:newRule {:optional true} [:ref "ConditionalFormatRule"]]
  [:newIndex {:optional true} :int]],
 "CancelDataSourceRefreshRequest"
 [:map
  {:closed true}
  [:isAll {:optional true} :boolean]
  [:dataSourceId {:optional true} :string]
  [:references {:optional true} [:ref "DataSourceObjectReferences"]]],
 "UpdateProtectedRangeRequest"
 [:map
  {:closed true}
  [:protectedRange {:optional true} [:ref "ProtectedRange"]]
  [:fields {:optional true} :string]],
 "PivotFilterSpec"
 [:map
  {:closed true}
  [:columnOffsetIndex {:optional true} :int]
  [:filterCriteria {:optional true} [:ref "PivotFilterCriteria"]]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]],
 "UpdateCellsRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]
  [:start {:optional true} [:ref "GridCoordinate"]]
  [:rows {:optional true} [:vector [:ref "RowData"]]]],
 "AppendValuesResponse"
 [:map
  {:closed true}
  [:updates {:optional true} [:ref "UpdateValuesResponse"]]
  [:tableRange {:optional true} :string]
  [:spreadsheetId {:optional true} :string]],
 "DataSourceSheetProperties"
 [:map
  {:closed true}
  [:dataSourceId {:optional true} :string]
  [:dataExecutionStatus {:optional true} [:ref "DataExecutionStatus"]]
  [:columns {:optional true} [:vector [:ref "DataSourceColumn"]]]],
 "DataExecutionStatus"
 [:map
  {:closed true}
  [:errorMessage {:optional true} :string]
  [:errorCode
   {:optional true}
   [:enum
    "DATA_EXECUTION_ERROR_CODE_UNSPECIFIED"
    "TIMED_OUT"
    "TOO_MANY_ROWS"
    "TOO_MANY_COLUMNS"
    "TOO_MANY_CELLS"
    "ENGINE"
    "PARAMETER_INVALID"
    "UNSUPPORTED_DATA_TYPE"
    "DUPLICATE_COLUMN_NAMES"
    "INTERRUPTED"
    "CONCURRENT_QUERY"
    "OTHER"
    "TOO_MANY_CHARS_PER_CELL"
    "DATA_NOT_FOUND"
    "PERMISSION_DENIED"
    "MISSING_COLUMN_ALIAS"
    "OBJECT_NOT_FOUND"
    "OBJECT_IN_ERROR_STATE"
    "OBJECT_SPEC_INVALID"
    "DATA_EXECUTION_CANCELLED"]]
  [:state
   {:optional true}
   [:enum
    "DATA_EXECUTION_STATE_UNSPECIFIED"
    "NOT_STARTED"
    "RUNNING"
    "CANCELLING"
    "SUCCEEDED"
    "FAILED"]]
  [:lastRefreshTime {:optional true} :string]],
 "MergeCellsRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "GridRange"]]
  [:mergeType
   {:optional true}
   [:enum "MERGE_ALL" "MERGE_COLUMNS" "MERGE_ROWS"]]],
 "Table"
 [:map
  {:closed true}
  [:tableId {:optional true} :string]
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]
  [:columnProperties
   {:optional true}
   [:vector [:ref "TableColumnProperties"]]]
  [:rowsProperties {:optional true} [:ref "TableRowsProperties"]]],
 "Color"
 [:map
  {:closed true}
  [:green {:optional true} :double]
  [:blue {:optional true} :double]
  [:alpha {:optional true} :double]
  [:red {:optional true} :double]],
 "AddChartResponse"
 [:map
  {:closed true}
  [:chart {:optional true} [:ref "EmbeddedChart"]]],
 "InterpolationPoint"
 [:map
  {:closed true}
  [:colorStyle {:optional true} [:ref "ColorStyle"]]
  [:color {:optional true} [:ref "Color"]]
  [:value {:optional true} :string]
  [:type
   {:optional true}
   [:enum
    "INTERPOLATION_POINT_TYPE_UNSPECIFIED"
    "MIN"
    "MAX"
    "NUMBER"
    "PERCENT"
    "PERCENTILE"]]],
 "BaselineValueFormat"
 [:map
  {:closed true}
  [:description {:optional true} :string]
  [:negativeColorStyle {:optional true} [:ref "ColorStyle"]]
  [:positiveColor {:optional true} [:ref "Color"]]
  [:negativeColor {:optional true} [:ref "Color"]]
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:position {:optional true} [:ref "TextPosition"]]
  [:positiveColorStyle {:optional true} [:ref "ColorStyle"]]
  [:comparisonType
   {:optional true}
   [:enum
    "COMPARISON_TYPE_UNDEFINED"
    "ABSOLUTE_DIFFERENCE"
    "PERCENTAGE_DIFFERENCE"]]],
 "AddSheetRequest"
 [:map
  {:closed true}
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "DeleteBandingRequest"
 [:map {:closed true} [:bandedRangeId {:optional true} :int]],
 "HistogramChartSpec"
 [:map
  {:closed true}
  [:showItemDividers {:optional true} :boolean]
  [:legendPosition
   {:optional true}
   [:enum
    "HISTOGRAM_CHART_LEGEND_POSITION_UNSPECIFIED"
    "BOTTOM_LEGEND"
    "LEFT_LEGEND"
    "RIGHT_LEGEND"
    "TOP_LEGEND"
    "NO_LEGEND"
    "INSIDE_LEGEND"]]
  [:bucketSize {:optional true} :double]
  [:series {:optional true} [:vector [:ref "HistogramSeries"]]]
  [:outlierPercentile {:optional true} :double]],
 "ChartGroupRule"
 [:map
  {:closed true}
  [:histogramRule {:optional true} [:ref "ChartHistogramRule"]]
  [:dateTimeRule {:optional true} [:ref "ChartDateTimeRule"]]],
 "DeleteDuplicatesResponse"
 [:map {:closed true} [:duplicatesRemovedCount {:optional true} :int]],
 "DeleteDimensionRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "DimensionRange"]]],
 "UpdateValuesResponse"
 [:map
  {:closed true}
  [:updatedData {:optional true} [:ref "ValueRange"]]
  [:updatedColumns {:optional true} :int]
  [:updatedCells {:optional true} :int]
  [:updatedRange {:optional true} :string]
  [:spreadsheetId {:optional true} :string]
  [:updatedRows {:optional true} :int]],
 "ScorecardChartSpec"
 [:map
  {:closed true}
  [:customFormatOptions
   {:optional true}
   [:ref "ChartCustomNumberFormatOptions"]]
  [:numberFormatSource
   {:optional true}
   [:enum "CHART_NUMBER_FORMAT_SOURCE_UNDEFINED" "FROM_DATA" "CUSTOM"]]
  [:baselineValueData {:optional true} [:ref "ChartData"]]
  [:aggregateType
   {:optional true}
   [:enum
    "CHART_AGGREGATE_TYPE_UNSPECIFIED"
    "AVERAGE"
    "COUNT"
    "MAX"
    "MEDIAN"
    "MIN"
    "SUM"]]
  [:keyValueFormat {:optional true} [:ref "KeyValueFormat"]]
  [:baselineValueFormat {:optional true} [:ref "BaselineValueFormat"]]
  [:scaleFactor {:optional true} :double]
  [:keyValueData {:optional true} [:ref "ChartData"]]],
 "DeleteSheetRequest"
 [:map {:closed true} [:sheetId {:optional true} :int]],
 "UpdateDeveloperMetadataRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]],
 "ChartCustomNumberFormatOptions"
 [:map
  {:closed true}
  [:prefix {:optional true} :string]
  [:suffix {:optional true} :string]],
 "HistogramSeries"
 [:map
  {:closed true}
  [:barColorStyle {:optional true} [:ref "ColorStyle"]]
  [:barColor {:optional true} [:ref "Color"]]
  [:data {:optional true} [:ref "ChartData"]]],
 "ErrorValue"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum
    "ERROR_TYPE_UNSPECIFIED"
    "ERROR"
    "NULL_VALUE"
    "DIVIDE_BY_ZERO"
    "VALUE"
    "REF"
    "NAME"
    "NUM"
    "N_A"
    "LOADING"]]
  [:message {:optional true} :string]],
 "ClearBasicFilterRequest"
 [:map {:closed true} [:sheetId {:optional true} :int]],
 "AddChartRequest"
 [:map
  {:closed true}
  [:chart {:optional true} [:ref "EmbeddedChart"]]],
 "AddDimensionGroupResponse"
 [:map
  {:closed true}
  [:dimensionGroups
   {:optional true}
   [:vector [:ref "DimensionGroup"]]]],
 "DataSourceColumn"
 [:map
  {:closed true}
  [:reference {:optional true} [:ref "DataSourceColumnReference"]]
  [:formula {:optional true} :string]],
 "DeveloperMetadataLookup"
 [:map
  {:closed true}
  [:metadataId {:optional true} :int]
  [:metadataKey {:optional true} :string]
  [:metadataValue {:optional true} :string]
  [:locationMatchingStrategy
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_LOCATION_MATCHING_STRATEGY_UNSPECIFIED"
    "EXACT_LOCATION"
    "INTERSECTING_LOCATION"]]
  [:metadataLocation
   {:optional true}
   [:ref "DeveloperMetadataLocation"]]
  [:locationType
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_LOCATION_TYPE_UNSPECIFIED"
    "ROW"
    "COLUMN"
    "SHEET"
    "SPREADSHEET"]]
  [:visibility
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_VISIBILITY_UNSPECIFIED"
    "DOCUMENT"
    "PROJECT"]]],
 "WaterfallChartCustomSubtotal"
 [:map
  {:closed true}
  [:label {:optional true} :string]
  [:dataIsSubtotal {:optional true} :boolean]
  [:subtotalIndex {:optional true} :int]],
 "DataSourceFormula"
 [:map
  {:closed true}
  [:dataExecutionStatus {:optional true} [:ref "DataExecutionStatus"]]
  [:dataSourceId {:optional true} :string]],
 "BigQueryQuerySpec"
 [:map {:closed true} [:rawQuery {:optional true} :string]],
 "UpdateBordersRequest"
 [:map
  {:closed true}
  [:innerVertical {:optional true} [:ref "Border"]]
  [:top {:optional true} [:ref "Border"]]
  [:right {:optional true} [:ref "Border"]]
  [:left {:optional true} [:ref "Border"]]
  [:range {:optional true} [:ref "GridRange"]]
  [:innerHorizontal {:optional true} [:ref "Border"]]
  [:bottom {:optional true} [:ref "Border"]]],
 "CandlestickData"
 [:map
  {:closed true}
  [:closeSeries {:optional true} [:ref "CandlestickSeries"]]
  [:highSeries {:optional true} [:ref "CandlestickSeries"]]
  [:openSeries {:optional true} [:ref "CandlestickSeries"]]
  [:lowSeries {:optional true} [:ref "CandlestickSeries"]]]}
)

(def ops
{[:spreadsheets :get-by-data-filter]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}:getByDataFilter",
  :schema [:ref "GetSpreadsheetByDataFilterRequest"]},
 [:values :update]
 {:method "PUT",
  :path "v4/spreadsheets/{spreadsheetId}/values/{range}",
  :schema [:ref "ValueRange"]},
 [:spreadsheets :batch-update]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}:batchUpdate",
  :schema [:ref "BatchUpdateSpreadsheetRequest"]},
 [:spreadsheets :get]
 {:method "GET", :path "v4/spreadsheets/{spreadsheetId}"},
 [:values :batch-clear]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/values:batchClear",
  :schema [:ref "BatchClearValuesRequest"]},
 [:sheets :copy-to]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/sheets/{sheetId}:copyTo",
  :schema [:ref "CopySheetToAnotherSpreadsheetRequest"]},
 [:values :batch-clear-by-data-filter]
 {:method "POST",
  :path
  "v4/spreadsheets/{spreadsheetId}/values:batchClearByDataFilter",
  :schema [:ref "BatchClearValuesByDataFilterRequest"]},
 [:spreadsheets :create]
 {:method "POST",
  :path "v4/spreadsheets",
  :schema [:ref "Spreadsheet"]},
 [:developer-metadata :search]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/developerMetadata:search",
  :schema [:ref "SearchDeveloperMetadataRequest"]},
 [:values :batch-get]
 {:method "GET",
  :path "v4/spreadsheets/{spreadsheetId}/values:batchGet"},
 [:values :clear]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/values/{range}:clear",
  :schema [:ref "ClearValuesRequest"]},
 [:values :batch-update-by-data-filter]
 {:method "POST",
  :path
  "v4/spreadsheets/{spreadsheetId}/values:batchUpdateByDataFilter",
  :schema [:ref "BatchUpdateValuesByDataFilterRequest"]},
 [:values :batch-update]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/values:batchUpdate",
  :schema [:ref "BatchUpdateValuesRequest"]},
 [:developer-metadata :get]
 {:method "GET",
  :path
  "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}"},
 [:values :batch-get-by-data-filter]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/values:batchGetByDataFilter",
  :schema [:ref "BatchGetValuesByDataFilterRequest"]},
 [:values :append]
 {:method "POST",
  :path "v4/spreadsheets/{spreadsheetId}/values/{range}:append",
  :schema [:ref "ValueRange"]},
 [:values :get]
 {:method "GET",
  :path "v4/spreadsheets/{spreadsheetId}/values/{range}"}}
)

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
