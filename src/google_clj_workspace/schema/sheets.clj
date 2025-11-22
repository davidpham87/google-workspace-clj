(ns google-clj-workspace.schema.sheets)

(def registry
  {"CellFormat"
 [:map
  {:closed false}
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
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:totalUpdatedRows {:optional true} :int]
  [:totalUpdatedColumns {:optional true} :int]
  [:totalUpdatedCells {:optional true} :int]
  [:totalUpdatedSheets {:optional true} :int]
  [:responses
   {:optional true}
   [:vector [:ref "UpdateValuesByDataFilterResponse"]]]],
 "DeleteNamedRangeRequest"
 [:map {:closed false} [:namedRangeId {:optional true} :string]],
 "SpreadsheetTheme"
 [:map
  {:closed false}
  [:primaryFontFamily {:optional true} :string]
  [:themeColors {:optional true} [:vector [:ref "ThemeColorPair"]]]],
 "ChartSpec"
 [:map
  {:closed false}
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
  {:closed false}
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]],
 "AddBandingRequest"
 [:map
  {:closed false}
  [:bandedRange {:optional true} [:ref "BandedRange"]]],
 "EmbeddedChart"
 [:map
  {:closed false}
  [:chartId {:optional true} :int]
  [:spec {:optional true} [:ref "ChartSpec"]]
  [:position {:optional true} [:ref "EmbeddedObjectPosition"]]
  [:border {:optional true} [:ref "EmbeddedObjectBorder"]]],
 "HistogramRule"
 [:map
  {:closed false}
  [:interval {:optional true} :double]
  [:start {:optional true} :double]
  [:end {:optional true} :double]],
 "BasicChartSpec"
 [:map
  {:closed false}
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
  {:closed false}
  [:minpoint {:optional true} [:ref "InterpolationPoint"]]
  [:midpoint {:optional true} [:ref "InterpolationPoint"]]
  [:maxpoint {:optional true} [:ref "InterpolationPoint"]]],
 "DataSourceChartProperties"
 [:map
  {:closed false}
  [:dataSourceId {:optional true} :string]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "SourceAndDestination"
 [:map
  {:closed false}
  [:source {:optional true} [:ref "GridRange"]]
  [:dimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:fillLength {:optional true} :int]],
 "TextFormat"
 [:map
  {:closed false}
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
  {:closed false}
  [:developerMetadataLookup
   {:optional true}
   [:ref "DeveloperMetadataLookup"]]
  [:a1Range {:optional true} :string]
  [:gridRange {:optional true} [:ref "GridRange"]]],
 "AddTableResponse"
 [:map {:closed false} [:table {:optional true} [:ref "Table"]]],
 "TableColumnDataValidationRule"
 [:map
  {:closed false}
  [:condition {:optional true} [:ref "BooleanCondition"]]],
 "GridData"
 [:map
  {:closed false}
  [:startRow {:optional true} :int]
  [:startColumn {:optional true} :int]
  [:rowData {:optional true} [:vector [:ref "RowData"]]]
  [:rowMetadata
   {:optional true}
   [:vector [:ref "DimensionProperties"]]]
  [:columnMetadata
   {:optional true}
   [:vector [:ref "DimensionProperties"]]]],
 "AddSlicerRequest"
 [:map {:closed false} [:slicer {:optional true} [:ref "Slicer"]]],
 "LineStyle"
 [:map
  {:closed false}
  [:width {:optional true} :int]
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
    "LONG_DASHED_DOTTED"]]],
 "BatchUpdateValuesRequest"
 [:map
  {:closed false}
  [:valueInputOption
   {:optional true}
   [:enum "INPUT_VALUE_OPTION_UNSPECIFIED" "RAW" "USER_ENTERED"]]
  [:data {:optional true} [:vector [:ref "ValueRange"]]]
  [:includeValuesInResponse {:optional true} :boolean]
  [:responseValueRenderOption
   {:optional true}
   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
  [:responseDateTimeRenderOption
   {:optional true}
   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]],
 "Editors"
 [:map
  {:closed false}
  [:users {:optional true} [:vector :string]]
  [:groups {:optional true} [:vector :string]]
  [:domainUsersCanEdit {:optional true} :boolean]],
 "NumberFormat"
 [:map
  {:closed false}
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
  {:closed false}
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "Request"
 [:map
  {:closed false}
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
  {:closed false}
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "Link" [:map {:closed false} [:uri {:optional true} :string]],
 "GridProperties"
 [:map
  {:closed false}
  [:rowCount {:optional true} :int]
  [:columnCount {:optional true} :int]
  [:frozenRowCount {:optional true} :int]
  [:frozenColumnCount {:optional true} :int]
  [:hideGridlines {:optional true} :boolean]
  [:rowGroupControlAfter {:optional true} :boolean]
  [:columnGroupControlAfter {:optional true} :boolean]],
 "DateTimeRule"
 [:map
  {:closed false}
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
  {:closed false}
  [:objectId {:optional true} :int]
  [:newPosition {:optional true} [:ref "EmbeddedObjectPosition"]]
  [:fields {:optional true} :string]],
 "AddSlicerResponse"
 [:map {:closed false} [:slicer {:optional true} [:ref "Slicer"]]],
 "UpdateConditionalFormatRuleRequest"
 [:map
  {:closed false}
  [:rule {:optional true} [:ref "ConditionalFormatRule"]]
  [:newIndex {:optional true} :int]
  [:index {:optional true} :int]
  [:sheetId {:optional true} :int]],
 "UpdateDataSourceRequest"
 [:map
  {:closed false}
  [:dataSource {:optional true} [:ref "DataSource"]]
  [:fields {:optional true} :string]],
 "ClearValuesResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:clearedRange {:optional true} :string]],
 "TimeOfDay"
 [:map
  {:closed false}
  [:hours {:optional true} :int]
  [:minutes {:optional true} :int]
  [:seconds {:optional true} :int]
  [:nanos {:optional true} :int]],
 "DimensionRange"
 [:map
  {:closed false}
  [:sheetId {:optional true} :int]
  [:dimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]],
 "RandomizeRangeRequest"
 [:map {:closed false} [:range {:optional true} [:ref "GridRange"]]],
 "ChipRun"
 [:map
  {:closed false}
  [:startIndex {:optional true} :int]
  [:chip {:optional true} [:ref "Chip"]]],
 "BigQueryTableSpec"
 [:map
  {:closed false}
  [:tableProjectId {:optional true} :string]
  [:tableId {:optional true} :string]
  [:datasetId {:optional true} :string]],
 "DataSourceRefreshDailySchedule"
 [:map
  {:closed false}
  [:startTime {:optional true} [:ref "TimeOfDay"]]],
 "DeleteDimensionGroupResponse"
 [:map
  {:closed false}
  [:dimensionGroups
   {:optional true}
   [:vector [:ref "DimensionGroup"]]]],
 "UpdateNamedRangeRequest"
 [:map
  {:closed false}
  [:namedRange {:optional true} [:ref "NamedRange"]]
  [:fields {:optional true} :string]],
 "RefreshCancellationStatus"
 [:map
  {:closed false}
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
  {:closed false}
  [:value {:optional true} [:ref "ExtendedValue"]]
  [:collapsed {:optional true} :boolean]],
 "TableRowsProperties"
 [:map
  {:closed false}
  [:headerColorStyle {:optional true} [:ref "ColorStyle"]]
  [:firstBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:secondBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:footerColorStyle {:optional true} [:ref "ColorStyle"]]],
 "Borders"
 [:map
  {:closed false}
  [:top {:optional true} [:ref "Border"]]
  [:bottom {:optional true} [:ref "Border"]]
  [:left {:optional true} [:ref "Border"]]
  [:right {:optional true} [:ref "Border"]]],
 "ConditionalFormatRule"
 [:map
  {:closed false}
  [:ranges {:optional true} [:vector [:ref "GridRange"]]]
  [:booleanRule {:optional true} [:ref "BooleanRule"]]
  [:gradientRule {:optional true} [:ref "GradientRule"]]],
 "DuplicateSheetRequest"
 [:map
  {:closed false}
  [:sourceSheetId {:optional true} :int]
  [:insertSheetIndex {:optional true} :int]
  [:newSheetId {:optional true} :int]
  [:newSheetName {:optional true} :string]],
 "DeleteEmbeddedObjectRequest"
 [:map {:closed false} [:objectId {:optional true} :int]],
 "CancelDataSourceRefreshStatus"
 [:map
  {:closed false}
  [:reference {:optional true} [:ref "DataSourceObjectReference"]]
  [:refreshCancellationStatus
   {:optional true}
   [:ref "RefreshCancellationStatus"]]],
 "WaterfallChartSeries"
 [:map
  {:closed false}
  [:data {:optional true} [:ref "ChartData"]]
  [:positiveColumnsStyle
   {:optional true}
   [:ref "WaterfallChartColumnStyle"]]
  [:negativeColumnsStyle
   {:optional true}
   [:ref "WaterfallChartColumnStyle"]]
  [:subtotalColumnsStyle
   {:optional true}
   [:ref "WaterfallChartColumnStyle"]]
  [:hideTrailingSubtotal {:optional true} :boolean]
  [:customSubtotals
   {:optional true}
   [:vector [:ref "WaterfallChartCustomSubtotal"]]]
  [:dataLabel {:optional true} [:ref "DataLabel"]]],
 "ColorStyle"
 [:map
  {:closed false}
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
  {:closed false}
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]],
 "BatchUpdateSpreadsheetResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:replies {:optional true} [:vector [:ref "Response"]]]
  [:updatedSpreadsheet {:optional true} [:ref "Spreadsheet"]]],
 "DataSourceObjectReferences"
 [:map
  {:closed false}
  [:references
   {:optional true}
   [:vector [:ref "DataSourceObjectReference"]]]],
 "NamedRange"
 [:map
  {:closed false}
  [:namedRangeId {:optional true} :string]
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]],
 "BatchClearValuesByDataFilterResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:clearedRanges {:optional true} [:vector :string]]],
 "AddTableRequest"
 [:map {:closed false} [:table {:optional true} [:ref "Table"]]],
 "SpreadsheetProperties"
 [:map
  {:closed false}
  [:title {:optional true} :string]
  [:locale {:optional true} :string]
  [:autoRecalc
   {:optional true}
   [:enum
    "RECALCULATION_INTERVAL_UNSPECIFIED"
    "ON_CHANGE"
    "MINUTE"
    "HOUR"]]
  [:timeZone {:optional true} :string]
  [:defaultFormat {:optional true} [:ref "CellFormat"]]
  [:iterativeCalculationSettings
   {:optional true}
   [:ref "IterativeCalculationSettings"]]
  [:spreadsheetTheme {:optional true} [:ref "SpreadsheetTheme"]]
  [:importFunctionsExternalUrlAccessAllowed
   {:optional true}
   :boolean]],
 "AppendCellsRequest"
 [:map
  {:closed false}
  [:sheetId {:optional true} :int]
  [:rows {:optional true} [:vector [:ref "RowData"]]]
  [:fields {:optional true} :string]
  [:tableId {:optional true} :string]],
 "SortSpec"
 [:map
  {:closed false}
  [:dimensionIndex {:optional true} :int]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:sortOrder
   {:optional true}
   [:enum "SORT_ORDER_UNSPECIFIED" "ASCENDING" "DESCENDING"]]
  [:foregroundColor {:optional true} [:ref "Color"]]
  [:foregroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:backgroundColor {:optional true} [:ref "Color"]]
  [:backgroundColorStyle {:optional true} [:ref "ColorStyle"]]],
 "BasicSeriesDataPointStyleOverride"
 [:map
  {:closed false}
  [:index {:optional true} :int]
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]
  [:pointStyle {:optional true} [:ref "PointStyle"]]],
 "UpdateEmbeddedObjectPositionResponse"
 [:map
  {:closed false}
  [:position {:optional true} [:ref "EmbeddedObjectPosition"]]],
 "TreemapChartSpec"
 [:map
  {:closed false}
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
  {:closed false}
  [:personProperties {:optional true} [:ref "PersonProperties"]]
  [:richLinkProperties {:optional true} [:ref "RichLinkProperties"]]],
 "TableColumnProperties"
 [:map
  {:closed false}
  [:columnIndex {:optional true} :int]
  [:columnName {:optional true} :string]
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
  [:dataValidationRule
   {:optional true}
   [:ref "TableColumnDataValidationRule"]]],
 "PivotTable"
 [:map
  {:closed false}
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
  {:closed false}
  [:manualRule {:optional true} [:ref "ManualRule"]]
  [:histogramRule {:optional true} [:ref "HistogramRule"]]
  [:dateTimeRule {:optional true} [:ref "DateTimeRule"]]],
 "DataLabel"
 [:map
  {:closed false}
  [:type
   {:optional true}
   [:enum "DATA_LABEL_TYPE_UNSPECIFIED" "NONE" "DATA" "CUSTOM"]]
  [:textFormat {:optional true} [:ref "TextFormat"]]
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
  [:customLabelData {:optional true} [:ref "ChartData"]]],
 "MoveDimensionRequest"
 [:map
  {:closed false}
  [:source {:optional true} [:ref "DimensionRange"]]
  [:destinationIndex {:optional true} :int]],
 "EmbeddedObjectPosition"
 [:map
  {:closed false}
  [:sheetId {:optional true} :int]
  [:overlayPosition {:optional true} [:ref "OverlayPosition"]]
  [:newSheet {:optional true} :boolean]],
 "BasicChartAxis"
 [:map
  {:closed false}
  [:position
   {:optional true}
   [:enum
    "BASIC_CHART_AXIS_POSITION_UNSPECIFIED"
    "BOTTOM_AXIS"
    "LEFT_AXIS"
    "RIGHT_AXIS"]]
  [:title {:optional true} :string]
  [:format {:optional true} [:ref "TextFormat"]]
  [:titleTextPosition {:optional true} [:ref "TextPosition"]]
  [:viewWindowOptions
   {:optional true}
   [:ref "ChartAxisViewWindowOptions"]]],
 "ExtendedValue"
 [:map
  {:closed false}
  [:numberValue {:optional true} :double]
  [:stringValue {:optional true} :string]
  [:boolValue {:optional true} :boolean]
  [:formulaValue {:optional true} :string]
  [:errorValue {:optional true} [:ref "ErrorValue"]]],
 "UpdateFilterViewRequest"
 [:map
  {:closed false}
  [:filter {:optional true} [:ref "FilterView"]]
  [:fields {:optional true} :string]],
 "PivotGroupLimit"
 [:map
  {:closed false}
  [:countLimit {:optional true} :int]
  [:applyOrder {:optional true} :int]],
 "AddFilterViewRequest"
 [:map {:closed false} [:filter {:optional true} [:ref "FilterView"]]],
 "AddNamedRangeRequest"
 [:map
  {:closed false}
  [:namedRange {:optional true} [:ref "NamedRange"]]],
 "AddNamedRangeResponse"
 [:map
  {:closed false}
  [:namedRange {:optional true} [:ref "NamedRange"]]],
 "ChartData"
 [:map
  {:closed false}
  [:sourceRange {:optional true} [:ref "ChartSourceRange"]]
  [:columnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
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
  {:closed false}
  [:nodeSize
   {:optional true}
   [:enum "ORG_CHART_LABEL_SIZE_UNSPECIFIED" "SMALL" "MEDIUM" "LARGE"]]
  [:nodeColor {:optional true} [:ref "Color"]]
  [:nodeColorStyle {:optional true} [:ref "ColorStyle"]]
  [:selectedNodeColor {:optional true} [:ref "Color"]]
  [:selectedNodeColorStyle {:optional true} [:ref "ColorStyle"]]
  [:labels {:optional true} [:ref "ChartData"]]
  [:parentLabels {:optional true} [:ref "ChartData"]]
  [:tooltips {:optional true} [:ref "ChartData"]]],
 "IterativeCalculationSettings"
 [:map
  {:closed false}
  [:maxIterations {:optional true} :int]
  [:convergenceThreshold {:optional true} :double]],
 "PivotGroupSortValueBucket"
 [:map
  {:closed false}
  [:valuesIndex {:optional true} :int]
  [:buckets {:optional true} [:vector [:ref "ExtendedValue"]]]],
 "DeleteDuplicatesRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:comparisonColumns
   {:optional true}
   [:vector [:ref "DimensionRange"]]]],
 "UpdateDimensionGroupRequest"
 [:map
  {:closed false}
  [:dimensionGroup {:optional true} [:ref "DimensionGroup"]]
  [:fields {:optional true} :string]],
 "DeleteFilterViewRequest"
 [:map {:closed false} [:filterId {:optional true} :int]],
 "TrimWhitespaceRequest"
 [:map {:closed false} [:range {:optional true} [:ref "GridRange"]]],
 "CandlestickSeries"
 [:map {:closed false} [:data {:optional true} [:ref "ChartData"]]],
 "BubbleChartSpec"
 [:map
  {:closed false}
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
  {:closed false}
  [:slicerId {:optional true} :int]
  [:spec {:optional true} [:ref "SlicerSpec"]]
  [:fields {:optional true} :string]],
 "UpdateSheetPropertiesRequest"
 [:map
  {:closed false}
  [:properties {:optional true} [:ref "SheetProperties"]]
  [:fields {:optional true} :string]],
 "BandingProperties"
 [:map
  {:closed false}
  [:headerColor {:optional true} [:ref "Color"]]
  [:headerColorStyle {:optional true} [:ref "ColorStyle"]]
  [:firstBandColor {:optional true} [:ref "Color"]]
  [:firstBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:secondBandColor {:optional true} [:ref "Color"]]
  [:secondBandColorStyle {:optional true} [:ref "ColorStyle"]]
  [:footerColor {:optional true} [:ref "Color"]]
  [:footerColorStyle {:optional true} [:ref "ColorStyle"]]],
 "GridRange"
 [:map
  {:closed false}
  [:sheetId {:optional true} :int]
  [:startRowIndex {:optional true} :int]
  [:endRowIndex {:optional true} :int]
  [:startColumnIndex {:optional true} :int]
  [:endColumnIndex {:optional true} :int]],
 "TextRotation"
 [:map
  {:closed false}
  [:angle {:optional true} :int]
  [:vertical {:optional true} :boolean]],
 "DeleteDeveloperMetadataRequest"
 [:map
  {:closed false}
  [:dataFilter {:optional true} [:ref "DataFilter"]]],
 "Padding"
 [:map
  {:closed false}
  [:top {:optional true} :int]
  [:right {:optional true} :int]
  [:bottom {:optional true} :int]
  [:left {:optional true} :int]],
 "UpdateChartSpecRequest"
 [:map
  {:closed false}
  [:chartId {:optional true} :int]
  [:spec {:optional true} [:ref "ChartSpec"]]],
 "DeleteProtectedRangeRequest"
 [:map {:closed false} [:protectedRangeId {:optional true} :int]],
 "KeyValueFormat"
 [:map
  {:closed false}
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:position {:optional true} [:ref "TextPosition"]]],
 "ValueRange"
 [:map
  {:closed false}
  [:range {:optional true} :string]
  [:majorDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:values {:optional true} [:vector [:vector :any]]]],
 "DimensionProperties"
 [:map
  {:closed false}
  [:hiddenByFilter {:optional true} :boolean]
  [:hiddenByUser {:optional true} :boolean]
  [:pixelSize {:optional true} :int]
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]],
 "DeleteConditionalFormatRuleRequest"
 [:map
  {:closed false}
  [:index {:optional true} :int]
  [:sheetId {:optional true} :int]],
 "DataSourceRefreshSchedule"
 [:map
  {:closed false}
  [:enabled {:optional true} :boolean]
  [:refreshScope
   {:optional true}
   [:enum "DATA_SOURCE_REFRESH_SCOPE_UNSPECIFIED" "ALL_DATA_SOURCES"]]
  [:dailySchedule
   {:optional true}
   [:ref "DataSourceRefreshDailySchedule"]]
  [:weeklySchedule
   {:optional true}
   [:ref "DataSourceRefreshWeeklySchedule"]]
  [:monthlySchedule
   {:optional true}
   [:ref "DataSourceRefreshMonthlySchedule"]]
  [:nextRun {:optional true} [:ref "Interval"]]],
 "Interval"
 [:map
  {:closed false}
  [:startTime {:optional true} :string]
  [:endTime {:optional true} :string]],
 "WaterfallChartDomain"
 [:map
  {:closed false}
  [:data {:optional true} [:ref "ChartData"]]
  [:reversed {:optional true} :boolean]],
 "BatchUpdateValuesResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:totalUpdatedRows {:optional true} :int]
  [:totalUpdatedColumns {:optional true} :int]
  [:totalUpdatedCells {:optional true} :int]
  [:totalUpdatedSheets {:optional true} :int]
  [:responses
   {:optional true}
   [:vector [:ref "UpdateValuesResponse"]]]],
 "DimensionGroup"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "DimensionRange"]]
  [:depth {:optional true} :int]
  [:collapsed {:optional true} :boolean]],
 "BatchClearValuesByDataFilterRequest"
 [:map
  {:closed false}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "BigQueryDataSourceSpec"
 [:map
  {:closed false}
  [:projectId {:optional true} :string]
  [:querySpec {:optional true} [:ref "BigQueryQuerySpec"]]
  [:tableSpec {:optional true} [:ref "BigQueryTableSpec"]]],
 "AutoFillRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:sourceAndDestination
   {:optional true}
   [:ref "SourceAndDestination"]]
  [:useAlternateSeries {:optional true} :boolean]],
 "LookerDataSourceSpec"
 [:map
  {:closed false}
  [:instanceUri {:optional true} :string]
  [:model {:optional true} :string]
  [:explore {:optional true} :string]],
 "TrimWhitespaceResponse"
 [:map {:closed false} [:cellsChangedCount {:optional true} :int]],
 "InsertDimensionRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "DimensionRange"]]
  [:inheritFromBefore {:optional true} :boolean]],
 "AutoResizeDimensionsRequest"
 [:map
  {:closed false}
  [:dimensions {:optional true} [:ref "DimensionRange"]]
  [:dataSourceSheetDimensions
   {:optional true}
   [:ref "DataSourceSheetDimensionRange"]]],
 "SortRangeRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]],
 "DuplicateFilterViewRequest"
 [:map {:closed false} [:filterId {:optional true} :int]],
 "UpdateValuesByDataFilterResponse"
 [:map
  {:closed false}
  [:updatedRange {:optional true} :string]
  [:updatedRows {:optional true} :int]
  [:updatedColumns {:optional true} :int]
  [:updatedCells {:optional true} :int]
  [:dataFilter {:optional true} [:ref "DataFilter"]]
  [:updatedData {:optional true} [:ref "ValueRange"]]],
 "Response"
 [:map
  {:closed false}
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
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:valueRanges
   {:optional true}
   [:vector [:ref "MatchedValueRange"]]]],
 "AddBandingResponse"
 [:map
  {:closed false}
  [:bandedRange {:optional true} [:ref "BandedRange"]]],
 "AddDataSourceResponse"
 [:map
  {:closed false}
  [:dataSource {:optional true} [:ref "DataSource"]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "DeleteRangeRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:shiftDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "ClearValuesRequest" [:map {:closed false}],
 "PersonProperties"
 [:map
  {:closed false}
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
  {:closed false}
  [:sheetId {:optional true} :int]
  [:columnReferences
   {:optional true}
   [:vector [:ref "DataSourceColumnReference"]]]],
 "AddConditionalFormatRuleRequest"
 [:map
  {:closed false}
  [:rule {:optional true} [:ref "ConditionalFormatRule"]]
  [:index {:optional true} :int]],
 "BasicChartSeries"
 [:map
  {:closed false}
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
  {:closed false}
  [:label {:optional true} :string]
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]],
 "UpdateTableRequest"
 [:map
  {:closed false}
  [:table {:optional true} [:ref "Table"]]
  [:fields {:optional true} :string]],
 "WaterfallChartSpec"
 [:map
  {:closed false}
  [:domain {:optional true} [:ref "WaterfallChartDomain"]]
  [:series {:optional true} [:vector [:ref "WaterfallChartSeries"]]]
  [:stackedType
   {:optional true}
   [:enum "WATERFALL_STACKED_TYPE_UNSPECIFIED" "STACKED" "SEQUENTIAL"]]
  [:firstValueIsTotal {:optional true} :boolean]
  [:hideConnectorLines {:optional true} :boolean]
  [:connectorLineStyle {:optional true} [:ref "LineStyle"]]
  [:totalDataLabel {:optional true} [:ref "DataLabel"]]],
 "TextToColumnsRequest"
 [:map
  {:closed false}
  [:source {:optional true} [:ref "GridRange"]]
  [:delimiter {:optional true} :string]
  [:delimiterType
   {:optional true}
   [:enum
    "DELIMITER_TYPE_UNSPECIFIED"
    "COMMA"
    "SEMICOLON"
    "PERIOD"
    "SPACE"
    "CUSTOM"
    "AUTODETECT"]]],
 "CancelDataSourceRefreshResponse"
 [:map
  {:closed false}
  [:statuses
   {:optional true}
   [:vector [:ref "CancelDataSourceRefreshStatus"]]]],
 "AddFilterViewResponse"
 [:map {:closed false} [:filter {:optional true} [:ref "FilterView"]]],
 "DeveloperMetadata"
 [:map
  {:closed false}
  [:metadataId {:optional true} :int]
  [:metadataKey {:optional true} :string]
  [:metadataValue {:optional true} :string]
  [:location {:optional true} [:ref "DeveloperMetadataLocation"]]
  [:visibility
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_VISIBILITY_UNSPECIFIED"
    "DOCUMENT"
    "PROJECT"]]],
 "SlicerSpec"
 [:map
  {:closed false}
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
  {:closed false}
  [:uri {:optional true} :string]
  [:mimeType {:optional true} :string]],
 "UpdateBandingRequest"
 [:map
  {:closed false}
  [:bandedRange {:optional true} [:ref "BandedRange"]]
  [:fields {:optional true} :string]],
 "Slicer"
 [:map
  {:closed false}
  [:slicerId {:optional true} :int]
  [:spec {:optional true} [:ref "SlicerSpec"]]
  [:position {:optional true} [:ref "EmbeddedObjectPosition"]]],
 "RefreshDataSourceResponse"
 [:map
  {:closed false}
  [:statuses
   {:optional true}
   [:vector [:ref "RefreshDataSourceObjectExecutionStatus"]]]],
 "UpdateDataSourceResponse"
 [:map
  {:closed false}
  [:dataSource {:optional true} [:ref "DataSource"]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "ChartSourceRange"
 [:map
  {:closed false}
  [:sources {:optional true} [:vector [:ref "GridRange"]]]],
 "GetSpreadsheetByDataFilterRequest"
 [:map
  {:closed false}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:includeGridData {:optional true} :boolean]
  [:excludeTablesInBandedRanges {:optional true} :boolean]],
 "ManualRuleGroup"
 [:map
  {:closed false}
  [:groupName {:optional true} [:ref "ExtendedValue"]]
  [:items {:optional true} [:vector [:ref "ExtendedValue"]]]],
 "RefreshDataSourceRequest"
 [:map
  {:closed false}
  [:references {:optional true} [:ref "DataSourceObjectReferences"]]
  [:dataSourceId {:optional true} :string]
  [:isAll {:optional true} :boolean]
  [:force {:optional true} :boolean]],
 "BooleanCondition"
 [:map
  {:closed false}
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
    "FILTER_EXPRESSION"]]
  [:values {:optional true} [:vector [:ref "ConditionValue"]]]],
 "FindReplaceRequest"
 [:map
  {:closed false}
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
  {:closed false}
  [:dataFilter {:optional true} [:ref "DataFilter"]]
  [:majorDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:values {:optional true} [:vector [:vector :any]]]],
 "BatchClearValuesRequest"
 [:map {:closed false} [:ranges {:optional true} [:vector :string]]],
 "PivotValue"
 [:map
  {:closed false}
  [:sourceColumnOffset {:optional true} :int]
  [:formula {:optional true} :string]
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
    "NONE"]]
  [:name {:optional true} :string]
  [:calculatedDisplayType
   {:optional true}
   [:enum
    "PIVOT_VALUE_CALCULATED_DISPLAY_TYPE_UNSPECIFIED"
    "PERCENT_OF_ROW_TOTAL"
    "PERCENT_OF_COLUMN_TOTAL"
    "PERCENT_OF_GRAND_TOTAL"]]],
 "DeleteTableRequest"
 [:map {:closed false} [:tableId {:optional true} :string]],
 "PasteDataRequest"
 [:map
  {:closed false}
  [:coordinate {:optional true} [:ref "GridCoordinate"]]
  [:data {:optional true} :string]
  [:delimiter {:optional true} :string]
  [:html {:optional true} :boolean]
  [:type
   {:optional true}
   [:enum
    "PASTE_NORMAL"
    "PASTE_VALUES"
    "PASTE_FORMAT"
    "PASTE_NO_BORDERS"
    "PASTE_FORMULA"
    "PASTE_DATA_VALIDATION"
    "PASTE_CONDITIONAL_FORMATTING"]]],
 "CutPasteRequest"
 [:map
  {:closed false}
  [:source {:optional true} [:ref "GridRange"]]
  [:destination {:optional true} [:ref "GridCoordinate"]]
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
  {:closed false}
  [:range {:optional true} [:ref "DimensionRange"]]
  [:dataSourceSheetRange
   {:optional true}
   [:ref "DataSourceSheetDimensionRange"]]
  [:properties {:optional true} [:ref "DimensionProperties"]]
  [:fields {:optional true} :string]],
 "TextFormatRun"
 [:map
  {:closed false}
  [:startIndex {:optional true} :int]
  [:format {:optional true} [:ref "TextFormat"]]],
 "MatchedValueRange"
 [:map
  {:closed false}
  [:valueRange {:optional true} [:ref "ValueRange"]]
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "ConditionValue"
 [:map
  {:closed false}
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
  {:closed false}
  [:domain {:optional true} [:ref "ChartData"]]
  [:reversed {:optional true} :boolean]],
 "Spreadsheet"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:properties {:optional true} [:ref "SpreadsheetProperties"]]
  [:sheets {:optional true} [:vector [:ref "Sheet"]]]
  [:namedRanges {:optional true} [:vector [:ref "NamedRange"]]]
  [:spreadsheetUrl {:optional true} :string]
  [:developerMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]
  [:dataSources {:optional true} [:vector [:ref "DataSource"]]]
  [:dataSourceSchedules
   {:optional true}
   [:vector [:ref "DataSourceRefreshSchedule"]]]],
 "SetBasicFilterRequest"
 [:map
  {:closed false}
  [:filter {:optional true} [:ref "BasicFilter"]]],
 "BatchGetValuesByDataFilterRequest"
 [:map
  {:closed false}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:majorDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:valueRenderOption
   {:optional true}
   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
  [:dateTimeRenderOption
   {:optional true}
   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]],
 "CandlestickChartSpec"
 [:map
  {:closed false}
  [:domain {:optional true} [:ref "CandlestickDomain"]]
  [:data {:optional true} [:vector [:ref "CandlestickData"]]]],
 "FilterView"
 [:map
  {:closed false}
  [:filterViewId {:optional true} :int]
  [:title {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]
  [:namedRangeId {:optional true} :string]
  [:tableId {:optional true} :string]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:criteria {:optional true} [:map-of :string :any]]
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]],
 "DuplicateSheetResponse"
 [:map
  {:closed false}
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "PivotFilterCriteria"
 [:map
  {:closed false}
  [:visibleValues {:optional true} [:vector :string]]
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:visibleByDefault {:optional true} :boolean]],
 "DataSourceColumnReference"
 [:map {:closed false} [:name {:optional true} :string]],
 "SearchDeveloperMetadataRequest"
 [:map
  {:closed false}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]],
 "RowData"
 [:map
  {:closed false}
  [:values {:optional true} [:vector [:ref "CellData"]]]],
 "DataSource"
 [:map
  {:closed false}
  [:dataSourceId {:optional true} :string]
  [:spec {:optional true} [:ref "DataSourceSpec"]]
  [:calculatedColumns
   {:optional true}
   [:vector [:ref "DataSourceColumn"]]]
  [:sheetId {:optional true} :int]],
 "DeveloperMetadataLocation"
 [:map
  {:closed false}
  [:locationType
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_LOCATION_TYPE_UNSPECIFIED"
    "ROW"
    "COLUMN"
    "SHEET"
    "SPREADSHEET"]]
  [:spreadsheet {:optional true} :boolean]
  [:sheetId {:optional true} :int]
  [:dimensionRange {:optional true} [:ref "DimensionRange"]]],
 "DataSourceObjectReference"
 [:map
  {:closed false}
  [:sheetId {:optional true} :string]
  [:chartId {:optional true} :int]
  [:dataSourceTableAnchorCell {:optional true} [:ref "GridCoordinate"]]
  [:dataSourcePivotTableAnchorCell
   {:optional true}
   [:ref "GridCoordinate"]]
  [:dataSourceFormulaCell {:optional true} [:ref "GridCoordinate"]]],
 "BatchGetValuesResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:valueRanges {:optional true} [:vector [:ref "ValueRange"]]]],
 "Sheet"
 [:map
  {:closed false}
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
 [:map {:closed false} [:filter {:optional true} [:ref "FilterView"]]],
 "ChartAxisViewWindowOptions"
 [:map
  {:closed false}
  [:viewWindowMin {:optional true} :double]
  [:viewWindowMax {:optional true} :double]
  [:viewWindowMode
   {:optional true}
   [:enum
    "DEFAULT_VIEW_WINDOW_MODE"
    "VIEW_WINDOW_MODE_UNSUPPORTED"
    "EXPLICIT"
    "PRETTY"]]],
 "UnmergeCellsRequest"
 [:map {:closed false} [:range {:optional true} [:ref "GridRange"]]],
 "CopySheetToAnotherSpreadsheetRequest"
 [:map
  {:closed false}
  [:destinationSpreadsheetId {:optional true} :string]],
 "BatchUpdateValuesByDataFilterRequest"
 [:map
  {:closed false}
  [:valueInputOption
   {:optional true}
   [:enum "INPUT_VALUE_OPTION_UNSPECIFIED" "RAW" "USER_ENTERED"]]
  [:data {:optional true} [:vector [:ref "DataFilterValueRange"]]]
  [:includeValuesInResponse {:optional true} :boolean]
  [:responseValueRenderOption
   {:optional true}
   [:enum "FORMATTED_VALUE" "UNFORMATTED_VALUE" "FORMULA"]]
  [:responseDateTimeRenderOption
   {:optional true}
   [:enum "SERIAL_NUMBER" "FORMATTED_STRING"]]],
 "FilterSpec"
 [:map
  {:closed false}
  [:columnIndex {:optional true} :int]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:filterCriteria {:optional true} [:ref "FilterCriteria"]]],
 "ThemeColorPair"
 [:map
  {:closed false}
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
  {:closed false}
  [:startTime {:optional true} [:ref "TimeOfDay"]]
  [:daysOfMonth {:optional true} [:vector :int]]],
 "AppendDimensionRequest"
 [:map
  {:closed false}
  [:sheetId {:optional true} :int]
  [:dimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]
  [:length {:optional true} :int]],
 "FilterCriteria"
 [:map
  {:closed false}
  [:hiddenValues {:optional true} [:vector :string]]
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:visibleBackgroundColor {:optional true} [:ref "Color"]]
  [:visibleBackgroundColorStyle {:optional true} [:ref "ColorStyle"]]
  [:visibleForegroundColor {:optional true} [:ref "Color"]]
  [:visibleForegroundColorStyle {:optional true} [:ref "ColorStyle"]]],
 "Border"
 [:map
  {:closed false}
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
    "DOUBLE"]]
  [:width {:optional true} :int]
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]],
 "DeleteDimensionGroupRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "DimensionRange"]]],
 "DeleteConditionalFormatRuleResponse"
 [:map
  {:closed false}
  [:rule {:optional true} [:ref "ConditionalFormatRule"]]],
 "UpdateSpreadsheetPropertiesRequest"
 [:map
  {:closed false}
  [:properties {:optional true} [:ref "SpreadsheetProperties"]]
  [:fields {:optional true} :string]],
 "FindReplaceResponse"
 [:map
  {:closed false}
  [:valuesChanged {:optional true} :int]
  [:formulasChanged {:optional true} :int]
  [:rowsChanged {:optional true} :int]
  [:sheetsChanged {:optional true} :int]
  [:occurrencesChanged {:optional true} :int]],
 "SheetProperties"
 [:map
  {:closed false}
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
  {:closed false}
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
  {:closed false}
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]],
 "CopyPasteRequest"
 [:map
  {:closed false}
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
    "PASTE_CONDITIONAL_FORMATTING"]]
  [:pasteOrientation {:optional true} [:enum "NORMAL" "TRANSPOSE"]]],
 "GridCoordinate"
 [:map
  {:closed false}
  [:sheetId {:optional true} :int]
  [:rowIndex {:optional true} :int]
  [:columnIndex {:optional true} :int]],
 "ProtectedRange"
 [:map
  {:closed false}
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
  {:closed false}
  [:data {:optional true} [:ref "ChartData"]]
  [:reversed {:optional true} :boolean]],
 "ManualRule"
 [:map
  {:closed false}
  [:groups {:optional true} [:vector [:ref "ManualRuleGroup"]]]],
 "EmbeddedObjectBorder"
 [:map
  {:closed false}
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]],
 "UpdateEmbeddedObjectBorderRequest"
 [:map
  {:closed false}
  [:objectId {:optional true} :int]
  [:border {:optional true} [:ref "EmbeddedObjectBorder"]]
  [:fields {:optional true} :string]],
 "TreemapChartColorScale"
 [:map
  {:closed false}
  [:minValueColor {:optional true} [:ref "Color"]]
  [:minValueColorStyle {:optional true} [:ref "ColorStyle"]]
  [:midValueColor {:optional true} [:ref "Color"]]
  [:midValueColorStyle {:optional true} [:ref "ColorStyle"]]
  [:maxValueColor {:optional true} [:ref "Color"]]
  [:maxValueColorStyle {:optional true} [:ref "ColorStyle"]]
  [:noDataColor {:optional true} [:ref "Color"]]
  [:noDataColorStyle {:optional true} [:ref "ColorStyle"]]],
 "TextPosition"
 [:map
  {:closed false}
  [:horizontalAlignment
   {:optional true}
   [:enum "HORIZONTAL_ALIGN_UNSPECIFIED" "LEFT" "CENTER" "RIGHT"]]],
 "ChartDateTimeRule"
 [:map
  {:closed false}
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
  {:closed false}
  [:range {:optional true} [:ref "DimensionRange"]]],
 "AddProtectedRangeResponse"
 [:map
  {:closed false}
  [:protectedRange {:optional true} [:ref "ProtectedRange"]]],
 "PieChartSpec"
 [:map
  {:closed false}
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
  [:series {:optional true} [:ref "ChartData"]]
  [:threeDimensional {:optional true} :boolean]
  [:pieHole {:optional true} :double]],
 "DataSourceRefreshWeeklySchedule"
 [:map
  {:closed false}
  [:startTime {:optional true} [:ref "TimeOfDay"]]
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
     "SUNDAY"]]]],
 "OverlayPosition"
 [:map
  {:closed false}
  [:anchorCell {:optional true} [:ref "GridCoordinate"]]
  [:offsetXPixels {:optional true} :int]
  [:offsetYPixels {:optional true} :int]
  [:widthPixels {:optional true} :int]
  [:heightPixels {:optional true} :int]],
 "DeleteDeveloperMetadataResponse"
 [:map
  {:closed false}
  [:deletedDeveloperMetadata
   {:optional true}
   [:vector [:ref "DeveloperMetadata"]]]],
 "SetDataValidationRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:rule {:optional true} [:ref "DataValidationRule"]]
  [:filteredRowsIncluded {:optional true} :boolean]],
 "SearchDeveloperMetadataResponse"
 [:map
  {:closed false}
  [:matchedDeveloperMetadata
   {:optional true}
   [:vector [:ref "MatchedDeveloperMetadata"]]]],
 "AddDataSourceRequest"
 [:map
  {:closed false}
  [:dataSource {:optional true} [:ref "DataSource"]]],
 "CellData"
 [:map
  {:closed false}
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
  {:closed false}
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
  {:closed false}
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:format {:optional true} [:ref "CellFormat"]]],
 "DataSourceParameter"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:namedRangeId {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]],
 "RepeatCellRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:cell {:optional true} [:ref "CellData"]]
  [:fields {:optional true} :string]],
 "ChartHistogramRule"
 [:map
  {:closed false}
  [:minValue {:optional true} :double]
  [:maxValue {:optional true} :double]
  [:intervalSize {:optional true} :double]],
 "DataSourceTable"
 [:map
  {:closed false}
  [:dataSourceId {:optional true} :string]
  [:columnSelectionType
   {:optional true}
   [:enum
    "DATA_SOURCE_TABLE_COLUMN_SELECTION_TYPE_UNSPECIFIED"
    "SELECTED"
    "SYNC_ALL"]]
  [:columns
   {:optional true}
   [:vector [:ref "DataSourceColumnReference"]]]
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:rowLimit {:optional true} :int]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "RefreshDataSourceObjectExecutionStatus"
 [:map
  {:closed false}
  [:reference {:optional true} [:ref "DataSourceObjectReference"]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "BatchClearValuesResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:clearedRanges {:optional true} [:vector :string]]],
 "DeleteDataSourceRequest"
 [:map {:closed false} [:dataSourceId {:optional true} :string]],
 "InsertRangeRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:shiftDimension
   {:optional true}
   [:enum "DIMENSION_UNSPECIFIED" "ROWS" "COLUMNS"]]],
 "BasicFilter"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:tableId {:optional true} :string]
  [:sortSpecs {:optional true} [:vector [:ref "SortSpec"]]]
  [:criteria {:optional true} [:map-of :string :any]]
  [:filterSpecs {:optional true} [:vector [:ref "FilterSpec"]]]],
 "BandedRange"
 [:map
  {:closed false}
  [:bandedRangeId {:optional true} :int]
  [:bandedRangeReference {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]
  [:rowProperties {:optional true} [:ref "BandingProperties"]]
  [:columnProperties {:optional true} [:ref "BandingProperties"]]],
 "DataValidationRule"
 [:map
  {:closed false}
  [:condition {:optional true} [:ref "BooleanCondition"]]
  [:inputMessage {:optional true} :string]
  [:strict {:optional true} :boolean]
  [:showCustomUi {:optional true} :boolean]],
 "AddProtectedRangeRequest"
 [:map
  {:closed false}
  [:protectedRange {:optional true} [:ref "ProtectedRange"]]],
 "DataSourceSpec"
 [:map
  {:closed false}
  [:bigQuery {:optional true} [:ref "BigQueryDataSourceSpec"]]
  [:looker {:optional true} [:ref "LookerDataSourceSpec"]]
  [:parameters
   {:optional true}
   [:vector [:ref "DataSourceParameter"]]]],
 "BatchUpdateSpreadsheetRequest"
 [:map
  {:closed false}
  [:requests {:optional true} [:vector [:ref "Request"]]]
  [:includeSpreadsheetInResponse {:optional true} :boolean]
  [:responseRanges {:optional true} [:vector :string]]
  [:responseIncludeGridData {:optional true} :boolean]],
 "UpdateConditionalFormatRuleResponse"
 [:map
  {:closed false}
  [:oldRule {:optional true} [:ref "ConditionalFormatRule"]]
  [:oldIndex {:optional true} :int]
  [:newRule {:optional true} [:ref "ConditionalFormatRule"]]
  [:newIndex {:optional true} :int]],
 "CancelDataSourceRefreshRequest"
 [:map
  {:closed false}
  [:references {:optional true} [:ref "DataSourceObjectReferences"]]
  [:dataSourceId {:optional true} :string]
  [:isAll {:optional true} :boolean]],
 "UpdateProtectedRangeRequest"
 [:map
  {:closed false}
  [:protectedRange {:optional true} [:ref "ProtectedRange"]]
  [:fields {:optional true} :string]],
 "PivotFilterSpec"
 [:map
  {:closed false}
  [:columnOffsetIndex {:optional true} :int]
  [:dataSourceColumnReference
   {:optional true}
   [:ref "DataSourceColumnReference"]]
  [:filterCriteria {:optional true} [:ref "PivotFilterCriteria"]]],
 "UpdateCellsRequest"
 [:map
  {:closed false}
  [:start {:optional true} [:ref "GridCoordinate"]]
  [:range {:optional true} [:ref "GridRange"]]
  [:rows {:optional true} [:vector [:ref "RowData"]]]
  [:fields {:optional true} :string]],
 "AppendValuesResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:tableRange {:optional true} :string]
  [:updates {:optional true} [:ref "UpdateValuesResponse"]]],
 "DataSourceSheetProperties"
 [:map
  {:closed false}
  [:dataSourceId {:optional true} :string]
  [:columns {:optional true} [:vector [:ref "DataSourceColumn"]]]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "DataExecutionStatus"
 [:map
  {:closed false}
  [:state
   {:optional true}
   [:enum
    "DATA_EXECUTION_STATE_UNSPECIFIED"
    "NOT_STARTED"
    "RUNNING"
    "CANCELLING"
    "SUCCEEDED"
    "FAILED"]]
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
  [:errorMessage {:optional true} :string]
  [:lastRefreshTime {:optional true} :string]],
 "MergeCellsRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:mergeType
   {:optional true}
   [:enum "MERGE_ALL" "MERGE_COLUMNS" "MERGE_ROWS"]]],
 "Table"
 [:map
  {:closed false}
  [:tableId {:optional true} :string]
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "GridRange"]]
  [:rowsProperties {:optional true} [:ref "TableRowsProperties"]]
  [:columnProperties
   {:optional true}
   [:vector [:ref "TableColumnProperties"]]]],
 "Color"
 [:map
  {:closed false}
  [:red {:optional true} :double]
  [:green {:optional true} :double]
  [:blue {:optional true} :double]
  [:alpha {:optional true} :double]],
 "AddChartResponse"
 [:map
  {:closed false}
  [:chart {:optional true} [:ref "EmbeddedChart"]]],
 "InterpolationPoint"
 [:map
  {:closed false}
  [:color {:optional true} [:ref "Color"]]
  [:colorStyle {:optional true} [:ref "ColorStyle"]]
  [:type
   {:optional true}
   [:enum
    "INTERPOLATION_POINT_TYPE_UNSPECIFIED"
    "MIN"
    "MAX"
    "NUMBER"
    "PERCENT"
    "PERCENTILE"]]
  [:value {:optional true} :string]],
 "BaselineValueFormat"
 [:map
  {:closed false}
  [:comparisonType
   {:optional true}
   [:enum
    "COMPARISON_TYPE_UNDEFINED"
    "ABSOLUTE_DIFFERENCE"
    "PERCENTAGE_DIFFERENCE"]]
  [:textFormat {:optional true} [:ref "TextFormat"]]
  [:position {:optional true} [:ref "TextPosition"]]
  [:description {:optional true} :string]
  [:positiveColor {:optional true} [:ref "Color"]]
  [:positiveColorStyle {:optional true} [:ref "ColorStyle"]]
  [:negativeColor {:optional true} [:ref "Color"]]
  [:negativeColorStyle {:optional true} [:ref "ColorStyle"]]],
 "AddSheetRequest"
 [:map
  {:closed false}
  [:properties {:optional true} [:ref "SheetProperties"]]],
 "DeleteBandingRequest"
 [:map {:closed false} [:bandedRangeId {:optional true} :int]],
 "HistogramChartSpec"
 [:map
  {:closed false}
  [:series {:optional true} [:vector [:ref "HistogramSeries"]]]
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
  [:showItemDividers {:optional true} :boolean]
  [:bucketSize {:optional true} :double]
  [:outlierPercentile {:optional true} :double]],
 "ChartGroupRule"
 [:map
  {:closed false}
  [:dateTimeRule {:optional true} [:ref "ChartDateTimeRule"]]
  [:histogramRule {:optional true} [:ref "ChartHistogramRule"]]],
 "DeleteDuplicatesResponse"
 [:map
  {:closed false}
  [:duplicatesRemovedCount {:optional true} :int]],
 "DeleteDimensionRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "DimensionRange"]]],
 "UpdateValuesResponse"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:updatedRange {:optional true} :string]
  [:updatedRows {:optional true} :int]
  [:updatedColumns {:optional true} :int]
  [:updatedCells {:optional true} :int]
  [:updatedData {:optional true} [:ref "ValueRange"]]],
 "ScorecardChartSpec"
 [:map
  {:closed false}
  [:keyValueData {:optional true} [:ref "ChartData"]]
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
  [:numberFormatSource
   {:optional true}
   [:enum "CHART_NUMBER_FORMAT_SOURCE_UNDEFINED" "FROM_DATA" "CUSTOM"]]
  [:customFormatOptions
   {:optional true}
   [:ref "ChartCustomNumberFormatOptions"]]],
 "DeleteSheetRequest"
 [:map {:closed false} [:sheetId {:optional true} :int]],
 "UpdateDeveloperMetadataRequest"
 [:map
  {:closed false}
  [:dataFilters {:optional true} [:vector [:ref "DataFilter"]]]
  [:developerMetadata {:optional true} [:ref "DeveloperMetadata"]]
  [:fields {:optional true} :string]],
 "ChartCustomNumberFormatOptions"
 [:map
  {:closed false}
  [:prefix {:optional true} :string]
  [:suffix {:optional true} :string]],
 "HistogramSeries"
 [:map
  {:closed false}
  [:barColor {:optional true} [:ref "Color"]]
  [:barColorStyle {:optional true} [:ref "ColorStyle"]]
  [:data {:optional true} [:ref "ChartData"]]],
 "ErrorValue"
 [:map
  {:closed false}
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
 [:map {:closed false} [:sheetId {:optional true} :int]],
 "AddChartRequest"
 [:map
  {:closed false}
  [:chart {:optional true} [:ref "EmbeddedChart"]]],
 "AddDimensionGroupResponse"
 [:map
  {:closed false}
  [:dimensionGroups
   {:optional true}
   [:vector [:ref "DimensionGroup"]]]],
 "DataSourceColumn"
 [:map
  {:closed false}
  [:reference {:optional true} [:ref "DataSourceColumnReference"]]
  [:formula {:optional true} :string]],
 "DeveloperMetadataLookup"
 [:map
  {:closed false}
  [:locationType
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_LOCATION_TYPE_UNSPECIFIED"
    "ROW"
    "COLUMN"
    "SHEET"
    "SPREADSHEET"]]
  [:metadataLocation
   {:optional true}
   [:ref "DeveloperMetadataLocation"]]
  [:locationMatchingStrategy
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_LOCATION_MATCHING_STRATEGY_UNSPECIFIED"
    "EXACT_LOCATION"
    "INTERSECTING_LOCATION"]]
  [:metadataId {:optional true} :int]
  [:metadataKey {:optional true} :string]
  [:metadataValue {:optional true} :string]
  [:visibility
   {:optional true}
   [:enum
    "DEVELOPER_METADATA_VISIBILITY_UNSPECIFIED"
    "DOCUMENT"
    "PROJECT"]]],
 "WaterfallChartCustomSubtotal"
 [:map
  {:closed false}
  [:subtotalIndex {:optional true} :int]
  [:label {:optional true} :string]
  [:dataIsSubtotal {:optional true} :boolean]],
 "DataSourceFormula"
 [:map
  {:closed false}
  [:dataSourceId {:optional true} :string]
  [:dataExecutionStatus
   {:optional true}
   [:ref "DataExecutionStatus"]]],
 "BigQueryQuerySpec"
 [:map {:closed false} [:rawQuery {:optional true} :string]],
 "UpdateBordersRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "GridRange"]]
  [:top {:optional true} [:ref "Border"]]
  [:bottom {:optional true} [:ref "Border"]]
  [:left {:optional true} [:ref "Border"]]
  [:right {:optional true} [:ref "Border"]]
  [:innerHorizontal {:optional true} [:ref "Border"]]
  [:innerVertical {:optional true} [:ref "Border"]]],
 "CandlestickData"
 [:map
  {:closed false}
  [:lowSeries {:optional true} [:ref "CandlestickSeries"]]
  [:openSeries {:optional true} [:ref "CandlestickSeries"]]
  [:closeSeries {:optional true} [:ref "CandlestickSeries"]]
  [:highSeries {:optional true} [:ref "CandlestickSeries"]]]})
