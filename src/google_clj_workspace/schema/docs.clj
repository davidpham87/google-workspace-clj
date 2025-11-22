(ns google-clj-workspace.schema.docs)

(def registry
  {"SectionBreak"
 [:map
  {:closed false}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:sectionStyle {:optional true} [:ref "SectionStyle"]]],
 "TabsCriteria"
 [:map {:closed false} [:tabIds {:optional true} [:vector :string]]],
 "DeleteNamedRangeRequest"
 [:map
  {:closed false}
  [:namedRangeId {:optional true} :string]
  [:name {:optional true} :string]
  [:tabsCriteria {:optional true} [:ref "TabsCriteria"]]],
 "TableCellStyle"
 [:map
  {:closed false}
  [:rowSpan {:optional true} :int]
  [:columnSpan {:optional true} :int]
  [:paddingBottom {:optional true} [:ref "Dimension"]]
  [:paddingTop {:optional true} [:ref "Dimension"]]
  [:paddingRight {:optional true} [:ref "Dimension"]]
  [:borderTop {:optional true} [:ref "TableCellBorder"]]
  [:paddingLeft {:optional true} [:ref "Dimension"]]
  [:borderBottom {:optional true} [:ref "TableCellBorder"]]
  [:contentAlignment
   {:optional true}
   [:enum
    "CONTENT_ALIGNMENT_UNSPECIFIED"
    "CONTENT_ALIGNMENT_UNSUPPORTED"
    "TOP"
    "MIDDLE"
    "BOTTOM"]]
  [:borderRight {:optional true} [:ref "TableCellBorder"]]
  [:borderLeft {:optional true} [:ref "TableCellBorder"]]
  [:backgroundColor {:optional true} [:ref "OptionalColor"]]],
 "PositionedObjectPropertiesSuggestionState"
 [:map
  {:closed false}
  [:positioningSuggestionState
   {:optional true}
   [:ref "PositionedObjectPositioningSuggestionState"]]
  [:embeddedObjectSuggestionState
   {:optional true}
   [:ref "EmbeddedObjectSuggestionState"]]],
 "TableRowStyle"
 [:map
  {:closed false}
  [:minRowHeight {:optional true} [:ref "Dimension"]]
  [:tableHeader {:optional true} :boolean]
  [:preventOverflow {:optional true} :boolean]],
 "MergeTableCellsRequest"
 [:map
  {:closed false}
  [:tableRange {:optional true} [:ref "TableRange"]]],
 "LinkedContentReference"
 [:map
  {:closed false}
  [:sheetsChartReference
   {:optional true}
   [:ref "SheetsChartReference"]]],
 "BackgroundSuggestionState"
 [:map
  {:closed false}
  [:backgroundColorSuggested {:optional true} :boolean]],
 "InsertTextRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:text {:optional true} :string]],
 "EmbeddedDrawingProperties" [:map {:closed false}],
 "Request"
 [:map
  {:closed false}
  [:updateSectionStyle
   {:optional true}
   [:ref "UpdateSectionStyleRequest"]]
  [:deletePositionedObject
   {:optional true}
   [:ref "DeletePositionedObjectRequest"]]
  [:createNamedRange {:optional true} [:ref "CreateNamedRangeRequest"]]
  [:replaceNamedRangeContent
   {:optional true}
   [:ref "ReplaceNamedRangeContentRequest"]]
  [:deleteContentRange
   {:optional true}
   [:ref "DeleteContentRangeRequest"]]
  [:createFooter {:optional true} [:ref "CreateFooterRequest"]]
  [:insertTable {:optional true} [:ref "InsertTableRequest"]]
  [:deleteTableRow {:optional true} [:ref "DeleteTableRowRequest"]]
  [:insertTableColumn
   {:optional true}
   [:ref "InsertTableColumnRequest"]]
  [:updateTextStyle {:optional true} [:ref "UpdateTextStyleRequest"]]
  [:insertPerson {:optional true} [:ref "InsertPersonRequest"]]
  [:replaceAllText {:optional true} [:ref "ReplaceAllTextRequest"]]
  [:updateTableCellStyle
   {:optional true}
   [:ref "UpdateTableCellStyleRequest"]]
  [:insertInlineImage
   {:optional true}
   [:ref "InsertInlineImageRequest"]]
  [:updateDocumentStyle
   {:optional true}
   [:ref "UpdateDocumentStyleRequest"]]
  [:deleteHeader {:optional true} [:ref "DeleteHeaderRequest"]]
  [:mergeTableCells {:optional true} [:ref "MergeTableCellsRequest"]]
  [:updateTableColumnProperties
   {:optional true}
   [:ref "UpdateTableColumnPropertiesRequest"]]
  [:createHeader {:optional true} [:ref "CreateHeaderRequest"]]
  [:updateTableRowStyle
   {:optional true}
   [:ref "UpdateTableRowStyleRequest"]]
  [:updateParagraphStyle
   {:optional true}
   [:ref "UpdateParagraphStyleRequest"]]
  [:replaceImage {:optional true} [:ref "ReplaceImageRequest"]]
  [:deleteFooter {:optional true} [:ref "DeleteFooterRequest"]]
  [:insertPageBreak {:optional true} [:ref "InsertPageBreakRequest"]]
  [:pinTableHeaderRows
   {:optional true}
   [:ref "PinTableHeaderRowsRequest"]]
  [:insertText {:optional true} [:ref "InsertTextRequest"]]
  [:deleteParagraphBullets
   {:optional true}
   [:ref "DeleteParagraphBulletsRequest"]]
  [:deleteNamedRange {:optional true} [:ref "DeleteNamedRangeRequest"]]
  [:createParagraphBullets
   {:optional true}
   [:ref "CreateParagraphBulletsRequest"]]
  [:deleteTableColumn
   {:optional true}
   [:ref "DeleteTableColumnRequest"]]
  [:unmergeTableCells
   {:optional true}
   [:ref "UnmergeTableCellsRequest"]]
  [:createFootnote {:optional true} [:ref "CreateFootnoteRequest"]]
  [:insertTableRow {:optional true} [:ref "InsertTableRowRequest"]]
  [:insertSectionBreak
   {:optional true}
   [:ref "InsertSectionBreakRequest"]]],
 "Link"
 [:map
  {:closed false}
  [:url {:optional true} :string]
  [:tabId {:optional true} :string]
  [:bookmark {:optional true} [:ref "BookmarkLink"]]
  [:heading {:optional true} [:ref "HeadingLink"]]
  [:bookmarkId {:optional true} :string]
  [:headingId {:optional true} :string]],
 "BatchUpdateDocumentResponse"
 [:map
  {:closed false}
  [:documentId {:optional true} :string]
  [:replies {:optional true} [:vector [:ref "Response"]]]
  [:writeControl {:optional true} [:ref "WriteControl"]]],
 "Equation"
 [:map
  {:closed false}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "ImagePropertiesSuggestionState"
 [:map
  {:closed false}
  [:contentUriSuggested {:optional true} :boolean]
  [:sourceUriSuggested {:optional true} :boolean]
  [:brightnessSuggested {:optional true} :boolean]
  [:contrastSuggested {:optional true} :boolean]
  [:transparencySuggested {:optional true} :boolean]
  [:cropPropertiesSuggestionState
   {:optional true}
   [:ref "CropPropertiesSuggestionState"]]
  [:angleSuggested {:optional true} :boolean]],
 "EmbeddedObject"
 [:map
  {:closed false}
  [:description {:optional true} :string]
  [:marginLeft {:optional true} [:ref "Dimension"]]
  [:embeddedDrawingProperties
   {:optional true}
   [:ref "EmbeddedDrawingProperties"]]
  [:marginTop {:optional true} [:ref "Dimension"]]
  [:linkedContentReference
   {:optional true}
   [:ref "LinkedContentReference"]]
  [:size {:optional true} [:ref "Size"]]
  [:title {:optional true} :string]
  [:imageProperties {:optional true} [:ref "ImageProperties"]]
  [:marginBottom {:optional true} [:ref "Dimension"]]
  [:embeddedObjectBorder
   {:optional true}
   [:ref "EmbeddedObjectBorder"]]
  [:marginRight {:optional true} [:ref "Dimension"]]],
 "InlineObjectPropertiesSuggestionState"
 [:map
  {:closed false}
  [:embeddedObjectSuggestionState
   {:optional true}
   [:ref "EmbeddedObjectSuggestionState"]]],
 "CreateParagraphBulletsRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "Range"]]
  [:bulletPreset
   {:optional true}
   [:enum
    "BULLET_GLYPH_PRESET_UNSPECIFIED"
    "BULLET_DISC_CIRCLE_SQUARE"
    "BULLET_DIAMONDX_ARROW3D_SQUARE"
    "BULLET_CHECKBOX"
    "BULLET_ARROW_DIAMOND_DISC"
    "BULLET_STAR_CIRCLE_SQUARE"
    "BULLET_ARROW3D_CIRCLE_SQUARE"
    "BULLET_LEFTTRIANGLE_DIAMOND_DISC"
    "BULLET_DIAMONDX_HOLLOWDIAMOND_SQUARE"
    "BULLET_DIAMOND_CIRCLE_SQUARE"
    "NUMBERED_DECIMAL_ALPHA_ROMAN"
    "NUMBERED_DECIMAL_ALPHA_ROMAN_PARENS"
    "NUMBERED_DECIMAL_NESTED"
    "NUMBERED_UPPERALPHA_ALPHA_ROMAN"
    "NUMBERED_UPPERROMAN_UPPERALPHA_DECIMAL"
    "NUMBERED_ZERODECIMAL_ALPHA_ROMAN"]]],
 "SuggestedListProperties"
 [:map
  {:closed false}
  [:listProperties {:optional true} [:ref "ListProperties"]]
  [:listPropertiesSuggestionState
   {:optional true}
   [:ref "ListPropertiesSuggestionState"]]],
 "TableCellStyleSuggestionState"
 [:map
  {:closed false}
  [:rowSpanSuggested {:optional true} :boolean]
  [:paddingRightSuggested {:optional true} :boolean]
  [:columnSpanSuggested {:optional true} :boolean]
  [:borderTopSuggested {:optional true} :boolean]
  [:borderBottomSuggested {:optional true} :boolean]
  [:backgroundColorSuggested {:optional true} :boolean]
  [:contentAlignmentSuggested {:optional true} :boolean]
  [:borderLeftSuggested {:optional true} :boolean]
  [:paddingLeftSuggested {:optional true} :boolean]
  [:borderRightSuggested {:optional true} :boolean]
  [:paddingBottomSuggested {:optional true} :boolean]
  [:paddingTopSuggested {:optional true} :boolean]],
 "InsertPageBreakRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "TableOfContents"
 [:map
  {:closed false}
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "DeleteContentRangeRequest"
 [:map {:closed false} [:range {:optional true} [:ref "Range"]]],
 "ReplaceNamedRangeContentRequest"
 [:map
  {:closed false}
  [:text {:optional true} :string]
  [:namedRangeId {:optional true} :string]
  [:namedRangeName {:optional true} :string]
  [:tabsCriteria {:optional true} [:ref "TabsCriteria"]]],
 "Header"
 [:map
  {:closed false}
  [:headerId {:optional true} :string]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "Body"
 [:map
  {:closed false}
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "NamedRange"
 [:map
  {:closed false}
  [:namedRangeId {:optional true} :string]
  [:name {:optional true} :string]
  [:ranges {:optional true} [:vector [:ref "Range"]]]],
 "ReplaceAllTextResponse"
 [:map {:closed false} [:occurrencesChanged {:optional true} :int]],
 "ParagraphStyleSuggestionState"
 [:map
  {:closed false}
  [:spaceBelowSuggested {:optional true} :boolean]
  [:alignmentSuggested {:optional true} :boolean]
  [:pageBreakBeforeSuggested {:optional true} :boolean]
  [:indentEndSuggested {:optional true} :boolean]
  [:avoidWidowAndOrphanSuggested {:optional true} :boolean]
  [:spaceAboveSuggested {:optional true} :boolean]
  [:borderTopSuggested {:optional true} :boolean]
  [:indentFirstLineSuggested {:optional true} :boolean]
  [:lineSpacingSuggested {:optional true} :boolean]
  [:borderBottomSuggested {:optional true} :boolean]
  [:directionSuggested {:optional true} :boolean]
  [:keepLinesTogetherSuggested {:optional true} :boolean]
  [:spacingModeSuggested {:optional true} :boolean]
  [:namedStyleTypeSuggested {:optional true} :boolean]
  [:shadingSuggestionState
   {:optional true}
   [:ref "ShadingSuggestionState"]]
  [:borderLeftSuggested {:optional true} :boolean]
  [:keepWithNextSuggested {:optional true} :boolean]
  [:borderBetweenSuggested {:optional true} :boolean]
  [:borderRightSuggested {:optional true} :boolean]
  [:indentStartSuggested {:optional true} :boolean]
  [:headingIdSuggested {:optional true} :boolean]],
 "DeleteTableColumnRequest"
 [:map
  {:closed false}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]],
 "LinkedContentReferenceSuggestionState"
 [:map
  {:closed false}
  [:sheetsChartReferenceSuggestionState
   {:optional true}
   [:ref "SheetsChartReferenceSuggestionState"]]],
 "TableColumnProperties"
 [:map
  {:closed false}
  [:widthType
   {:optional true}
   [:enum "WIDTH_TYPE_UNSPECIFIED" "EVENLY_DISTRIBUTED" "FIXED_WIDTH"]]
  [:width {:optional true} [:ref "Dimension"]]],
 "ParagraphElement"
 [:map
  {:closed false}
  [:footnoteReference {:optional true} [:ref "FootnoteReference"]]
  [:person {:optional true} [:ref "Person"]]
  [:horizontalRule {:optional true} [:ref "HorizontalRule"]]
  [:richLink {:optional true} [:ref "RichLink"]]
  [:columnBreak {:optional true} [:ref "ColumnBreak"]]
  [:pageBreak {:optional true} [:ref "PageBreak"]]
  [:inlineObjectElement {:optional true} [:ref "InlineObjectElement"]]
  [:autoText {:optional true} [:ref "AutoText"]]
  [:textRun {:optional true} [:ref "TextRun"]]
  [:endIndex {:optional true} :int]
  [:startIndex {:optional true} :int]
  [:equation {:optional true} [:ref "Equation"]]],
 "CreateNamedRangeRequest"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "Range"]]],
 "CreateNamedRangeResponse"
 [:map {:closed false} [:namedRangeId {:optional true} :string]],
 "PositionedObjectPositioning"
 [:map
  {:closed false}
  [:layout
   {:optional true}
   [:enum
    "POSITIONED_OBJECT_LAYOUT_UNSPECIFIED"
    "WRAP_TEXT"
    "BREAK_LEFT"
    "BREAK_RIGHT"
    "BREAK_LEFT_RIGHT"
    "IN_FRONT_OF_TEXT"
    "BEHIND_TEXT"]]
  [:leftOffset {:optional true} [:ref "Dimension"]]
  [:topOffset {:optional true} [:ref "Dimension"]]],
 "ColumnBreak"
 [:map
  {:closed false}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "TableCellLocation"
 [:map
  {:closed false}
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:rowIndex {:optional true} :int]
  [:columnIndex {:optional true} :int]],
 "DeleteHeaderRequest"
 [:map
  {:closed false}
  [:headerId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "Dimension"
 [:map
  {:closed false}
  [:magnitude {:optional true} :double]
  [:unit {:optional true} [:enum "UNIT_UNSPECIFIED" "PT"]]],
 "SectionStyle"
 [:map
  {:closed false}
  [:defaultHeaderId {:optional true} :string]
  [:marginLeft {:optional true} [:ref "Dimension"]]
  [:flipPageOrientation {:optional true} :boolean]
  [:evenPageFooterId {:optional true} :string]
  [:useFirstPageHeaderFooter {:optional true} :boolean]
  [:contentDirection
   {:optional true}
   [:enum
    "CONTENT_DIRECTION_UNSPECIFIED"
    "LEFT_TO_RIGHT"
    "RIGHT_TO_LEFT"]]
  [:marginTop {:optional true} [:ref "Dimension"]]
  [:sectionType
   {:optional true}
   [:enum "SECTION_TYPE_UNSPECIFIED" "CONTINUOUS" "NEXT_PAGE"]]
  [:pageNumberStart {:optional true} :int]
  [:firstPageHeaderId {:optional true} :string]
  [:defaultFooterId {:optional true} :string]
  [:firstPageFooterId {:optional true} :string]
  [:evenPageHeaderId {:optional true} :string]
  [:marginBottom {:optional true} [:ref "Dimension"]]
  [:columnSeparatorStyle
   {:optional true}
   [:enum
    "COLUMN_SEPARATOR_STYLE_UNSPECIFIED"
    "NONE"
    "BETWEEN_EACH_COLUMN"]]
  [:marginHeader {:optional true} [:ref "Dimension"]]
  [:columnProperties
   {:optional true}
   [:vector [:ref "SectionColumnProperties"]]]
  [:marginFooter {:optional true} [:ref "Dimension"]]
  [:marginRight {:optional true} [:ref "Dimension"]]],
 "Location"
 [:map
  {:closed false}
  [:segmentId {:optional true} :string]
  [:index {:optional true} :int]
  [:tabId {:optional true} :string]],
 "ParagraphStyle"
 [:map
  {:closed false}
  [:keepWithNext {:optional true} :boolean]
  [:indentStart {:optional true} [:ref "Dimension"]]
  [:keepLinesTogether {:optional true} :boolean]
  [:avoidWidowAndOrphan {:optional true} :boolean]
  [:spacingMode
   {:optional true}
   [:enum
    "SPACING_MODE_UNSPECIFIED"
    "NEVER_COLLAPSE"
    "COLLAPSE_LISTS"]]
  [:tabStops {:optional true} [:vector [:ref "TabStop"]]]
  [:borderTop {:optional true} [:ref "ParagraphBorder"]]
  [:alignment
   {:optional true}
   [:enum "ALIGNMENT_UNSPECIFIED" "START" "CENTER" "END" "JUSTIFIED"]]
  [:borderBottom {:optional true} [:ref "ParagraphBorder"]]
  [:shading {:optional true} [:ref "Shading"]]
  [:borderBetween {:optional true} [:ref "ParagraphBorder"]]
  [:indentFirstLine {:optional true} [:ref "Dimension"]]
  [:borderRight {:optional true} [:ref "ParagraphBorder"]]
  [:borderLeft {:optional true} [:ref "ParagraphBorder"]]
  [:headingId {:optional true} :string]
  [:pageBreakBefore {:optional true} :boolean]
  [:indentEnd {:optional true} [:ref "Dimension"]]
  [:spaceAbove {:optional true} [:ref "Dimension"]]
  [:lineSpacing {:optional true} :double]
  [:spaceBelow {:optional true} [:ref "Dimension"]]
  [:namedStyleType
   {:optional true}
   [:enum
    "NAMED_STYLE_TYPE_UNSPECIFIED"
    "NORMAL_TEXT"
    "TITLE"
    "SUBTITLE"
    "HEADING_1"
    "HEADING_2"
    "HEADING_3"
    "HEADING_4"
    "HEADING_5"
    "HEADING_6"]]
  [:direction
   {:optional true}
   [:enum
    "CONTENT_DIRECTION_UNSPECIFIED"
    "LEFT_TO_RIGHT"
    "RIGHT_TO_LEFT"]]],
 "CropPropertiesSuggestionState"
 [:map
  {:closed false}
  [:offsetLeftSuggested {:optional true} :boolean]
  [:offsetRightSuggested {:optional true} :boolean]
  [:offsetTopSuggested {:optional true} :boolean]
  [:offsetBottomSuggested {:optional true} :boolean]
  [:angleSuggested {:optional true} :boolean]],
 "InsertTableRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:rows {:optional true} :int]
  [:columns {:optional true} :int]],
 "InsertSectionBreakRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:sectionType
   {:optional true}
   [:enum "SECTION_TYPE_UNSPECIFIED" "CONTINUOUS" "NEXT_PAGE"]]],
 "SuggestedInlineObjectProperties"
 [:map
  {:closed false}
  [:inlineObjectProperties
   {:optional true}
   [:ref "InlineObjectProperties"]]
  [:inlineObjectPropertiesSuggestionState
   {:optional true}
   [:ref "InlineObjectPropertiesSuggestionState"]]],
 "Tab"
 [:map
  {:closed false}
  [:tabProperties {:optional true} [:ref "TabProperties"]]
  [:childTabs {:optional true} [:vector [:ref "Tab"]]]
  [:documentTab {:optional true} [:ref "DocumentTab"]]],
 "ParagraphBorder"
 [:map
  {:closed false}
  [:color {:optional true} [:ref "OptionalColor"]]
  [:width {:optional true} [:ref "Dimension"]]
  [:padding {:optional true} [:ref "Dimension"]]
  [:dashStyle
   {:optional true}
   [:enum "DASH_STYLE_UNSPECIFIED" "SOLID" "DOT" "DASH"]]],
 "DeletePositionedObjectRequest"
 [:map
  {:closed false}
  [:objectId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "SizeSuggestionState"
 [:map
  {:closed false}
  [:heightSuggested {:optional true} :boolean]
  [:widthSuggested {:optional true} :boolean]],
 "SubstringMatchCriteria"
 [:map
  {:closed false}
  [:text {:optional true} :string]
  [:matchCase {:optional true} :boolean]
  [:searchByRegex {:optional true} :boolean]],
 "SuggestedTableCellStyle"
 [:map
  {:closed false}
  [:tableCellStyle {:optional true} [:ref "TableCellStyle"]]
  [:tableCellStyleSuggestionState
   {:optional true}
   [:ref "TableCellStyleSuggestionState"]]],
 "Person"
 [:map
  {:closed false}
  [:personId {:optional true} :string]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:personProperties {:optional true} [:ref "PersonProperties"]]],
 "EmbeddedObjectBorderSuggestionState"
 [:map
  {:closed false}
  [:colorSuggested {:optional true} :boolean]
  [:widthSuggested {:optional true} :boolean]
  [:dashStyleSuggested {:optional true} :boolean]
  [:propertyStateSuggested {:optional true} :boolean]],
 "BookmarkLink"
 [:map
  {:closed false}
  [:id {:optional true} :string]
  [:tabId {:optional true} :string]],
 "Bullet"
 [:map
  {:closed false}
  [:listId {:optional true} :string]
  [:nestingLevel {:optional true} :int]
  [:textStyle {:optional true} [:ref "TextStyle"]]],
 "CropProperties"
 [:map
  {:closed false}
  [:offsetLeft {:optional true} :double]
  [:offsetRight {:optional true} :double]
  [:offsetTop {:optional true} :double]
  [:offsetBottom {:optional true} :double]
  [:angle {:optional true} :double]],
 "StructuralElement"
 [:map
  {:closed false}
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:paragraph {:optional true} [:ref "Paragraph"]]
  [:sectionBreak {:optional true} [:ref "SectionBreak"]]
  [:table {:optional true} [:ref "Table"]]
  [:tableOfContents {:optional true} [:ref "TableOfContents"]]],
 "TabStop"
 [:map
  {:closed false}
  [:offset {:optional true} [:ref "Dimension"]]
  [:alignment
   {:optional true}
   [:enum "TAB_STOP_ALIGNMENT_UNSPECIFIED" "START" "CENTER" "END"]]],
 "CreateFooterRequest"
 [:map
  {:closed false}
  [:type
   {:optional true}
   [:enum "HEADER_FOOTER_TYPE_UNSPECIFIED" "DEFAULT"]]
  [:sectionBreakLocation {:optional true} [:ref "Location"]]],
 "BulletSuggestionState"
 [:map
  {:closed false}
  [:listIdSuggested {:optional true} :boolean]
  [:nestingLevelSuggested {:optional true} :boolean]
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]],
 "InsertInlineImageResponse"
 [:map {:closed false} [:objectId {:optional true} :string]],
 "Response"
 [:map
  {:closed false}
  [:replaceAllText {:optional true} [:ref "ReplaceAllTextResponse"]]
  [:createNamedRange
   {:optional true}
   [:ref "CreateNamedRangeResponse"]]
  [:insertInlineImage
   {:optional true}
   [:ref "InsertInlineImageResponse"]]
  [:insertInlineSheetsChart
   {:optional true}
   [:ref "InsertInlineSheetsChartResponse"]]
  [:createHeader {:optional true} [:ref "CreateHeaderResponse"]]
  [:createFooter {:optional true} [:ref "CreateFooterResponse"]]
  [:createFootnote {:optional true} [:ref "CreateFootnoteResponse"]]],
 "Range"
 [:map
  {:closed false}
  [:segmentId {:optional true} :string]
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:tabId {:optional true} :string]],
 "InlineObjectProperties"
 [:map
  {:closed false}
  [:embeddedObject {:optional true} [:ref "EmbeddedObject"]]],
 "DocumentStyleSuggestionState"
 [:map
  {:closed false}
  [:marginRightSuggested {:optional true} :boolean]
  [:pageSizeSuggestionState
   {:optional true}
   [:ref "SizeSuggestionState"]]
  [:defaultHeaderIdSuggested {:optional true} :boolean]
  [:flipPageOrientationSuggested {:optional true} :boolean]
  [:pageNumberStartSuggested {:optional true} :boolean]
  [:evenPageHeaderIdSuggested {:optional true} :boolean]
  [:marginBottomSuggested {:optional true} :boolean]
  [:defaultFooterIdSuggested {:optional true} :boolean]
  [:firstPageHeaderIdSuggested {:optional true} :boolean]
  [:firstPageFooterIdSuggested {:optional true} :boolean]
  [:marginHeaderSuggested {:optional true} :boolean]
  [:useFirstPageHeaderFooterSuggested {:optional true} :boolean]
  [:marginFooterSuggested {:optional true} :boolean]
  [:useCustomHeaderFooterMarginsSuggested {:optional true} :boolean]
  [:evenPageFooterIdSuggested {:optional true} :boolean]
  [:backgroundSuggestionState
   {:optional true}
   [:ref "BackgroundSuggestionState"]]
  [:marginLeftSuggested {:optional true} :boolean]
  [:marginTopSuggested {:optional true} :boolean]
  [:useEvenPageHeaderFooterSuggested {:optional true} :boolean]],
 "InsertTableColumnRequest"
 [:map
  {:closed false}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]
  [:insertRight {:optional true} :boolean]],
 "ReplaceImageRequest"
 [:map
  {:closed false}
  [:imageObjectId {:optional true} :string]
  [:uri {:optional true} :string]
  [:imageReplaceMethod
   {:optional true}
   [:enum "IMAGE_REPLACE_METHOD_UNSPECIFIED" "CENTER_CROP"]]
  [:tabId {:optional true} :string]],
 "PinTableHeaderRowsRequest"
 [:map
  {:closed false}
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:pinnedHeaderRowsCount {:optional true} :int]],
 "PageBreak"
 [:map
  {:closed false}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "UpdateTableColumnPropertiesRequest"
 [:map
  {:closed false}
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:columnIndices {:optional true} [:vector :int]]
  [:tableColumnProperties
   {:optional true}
   [:ref "TableColumnProperties"]]
  [:fields {:optional true} :string]],
 "PersonProperties"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:email {:optional true} :string]],
 "TabProperties"
 [:map
  {:closed false}
  [:tabId {:optional true} :string]
  [:title {:optional true} :string]
  [:parentTabId {:optional true} :string]
  [:index {:optional true} :int]
  [:nestingLevel {:optional true} :int]
  [:iconEmoji {:optional true} :string]],
 "SuggestedBullet"
 [:map
  {:closed false}
  [:bullet {:optional true} [:ref "Bullet"]]
  [:bulletSuggestionState
   {:optional true}
   [:ref "BulletSuggestionState"]]],
 "Shading"
 [:map
  {:closed false}
  [:backgroundColor {:optional true} [:ref "OptionalColor"]]],
 "NamedRanges"
 [:map
  {:closed false}
  [:name {:optional true} :string]
  [:namedRanges {:optional true} [:vector [:ref "NamedRange"]]]],
 "TableRowStyleSuggestionState"
 [:map
  {:closed false}
  [:minRowHeightSuggested {:optional true} :boolean]],
 "SuggestedTableRowStyle"
 [:map
  {:closed false}
  [:tableRowStyle {:optional true} [:ref "TableRowStyle"]]
  [:tableRowStyleSuggestionState
   {:optional true}
   [:ref "TableRowStyleSuggestionState"]]],
 "NamedStyle"
 [:map
  {:closed false}
  [:namedStyleType
   {:optional true}
   [:enum
    "NAMED_STYLE_TYPE_UNSPECIFIED"
    "NORMAL_TEXT"
    "TITLE"
    "SUBTITLE"
    "HEADING_1"
    "HEADING_2"
    "HEADING_3"
    "HEADING_4"
    "HEADING_5"
    "HEADING_6"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]],
 "InlineObjectElement"
 [:map
  {:closed false}
  [:inlineObjectId {:optional true} :string]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "InsertInlineImageRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:uri {:optional true} :string]
  [:objectSize {:optional true} [:ref "Size"]]],
 "RichLinkProperties"
 [:map
  {:closed false}
  [:title {:optional true} :string]
  [:uri {:optional true} :string]
  [:mimeType {:optional true} :string]],
 "InsertTableRowRequest"
 [:map
  {:closed false}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]
  [:insertBelow {:optional true} :boolean]],
 "UpdateTextStyleRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "Range"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:fields {:optional true} :string]],
 "CreateFootnoteResponse"
 [:map {:closed false} [:footnoteId {:optional true} :string]],
 "NestingLevelSuggestionState"
 [:map
  {:closed false}
  [:bulletAlignmentSuggested {:optional true} :boolean]
  [:glyphTypeSuggested {:optional true} :boolean]
  [:glyphFormatSuggested {:optional true} :boolean]
  [:glyphSymbolSuggested {:optional true} :boolean]
  [:indentFirstLineSuggested {:optional true} :boolean]
  [:indentStartSuggested {:optional true} :boolean]
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]
  [:startNumberSuggested {:optional true} :boolean]],
 "InlineObject"
 [:map
  {:closed false}
  [:objectId {:optional true} :string]
  [:inlineObjectProperties
   {:optional true}
   [:ref "InlineObjectProperties"]]
  [:suggestedInlineObjectPropertiesChanges
   {:optional true}
   [:map-of :string :any]]
  [:suggestedInsertionId {:optional true} :string]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "ShadingSuggestionState"
 [:map
  {:closed false}
  [:backgroundColorSuggested {:optional true} :boolean]],
 "InsertInlineSheetsChartResponse"
 [:map {:closed false} [:objectId {:optional true} :string]],
 "InsertPersonRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:personProperties {:optional true} [:ref "PersonProperties"]]],
 "ListProperties"
 [:map
  {:closed false}
  [:nestingLevels {:optional true} [:vector [:ref "NestingLevel"]]]],
 "FootnoteReference"
 [:map
  {:closed false}
  [:footnoteId {:optional true} :string]
  [:footnoteNumber {:optional true} :string]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "CreateHeaderResponse"
 [:map {:closed false} [:headerId {:optional true} :string]],
 "SuggestedPositionedObjectProperties"
 [:map
  {:closed false}
  [:positionedObjectProperties
   {:optional true}
   [:ref "PositionedObjectProperties"]]
  [:positionedObjectPropertiesSuggestionState
   {:optional true}
   [:ref "PositionedObjectPropertiesSuggestionState"]]],
 "UpdateSectionStyleRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "Range"]]
  [:sectionStyle {:optional true} [:ref "SectionStyle"]]
  [:fields {:optional true} :string]],
 "SuggestedNamedStyles"
 [:map
  {:closed false}
  [:namedStyles {:optional true} [:ref "NamedStyles"]]
  [:namedStylesSuggestionState
   {:optional true}
   [:ref "NamedStylesSuggestionState"]]],
 "WriteControl"
 [:map
  {:closed false}
  [:requiredRevisionId {:optional true} :string]
  [:targetRevisionId {:optional true} :string]],
 "UpdateTableRowStyleRequest"
 [:map
  {:closed false}
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:rowIndices {:optional true} [:vector :int]]
  [:tableRowStyle {:optional true} [:ref "TableRowStyle"]]
  [:fields {:optional true} :string]],
 "EmbeddedObjectSuggestionState"
 [:map
  {:closed false}
  [:sizeSuggestionState {:optional true} [:ref "SizeSuggestionState"]]
  [:marginRightSuggested {:optional true} :boolean]
  [:embeddedDrawingPropertiesSuggestionState
   {:optional true}
   [:ref "EmbeddedDrawingPropertiesSuggestionState"]]
  [:descriptionSuggested {:optional true} :boolean]
  [:marginBottomSuggested {:optional true} :boolean]
  [:titleSuggested {:optional true} :boolean]
  [:linkedContentReferenceSuggestionState
   {:optional true}
   [:ref "LinkedContentReferenceSuggestionState"]]
  [:embeddedObjectBorderSuggestionState
   {:optional true}
   [:ref "EmbeddedObjectBorderSuggestionState"]]
  [:imagePropertiesSuggestionState
   {:optional true}
   [:ref "ImagePropertiesSuggestionState"]]
  [:marginLeftSuggested {:optional true} :boolean]
  [:marginTopSuggested {:optional true} :boolean]],
 "NamedStyleSuggestionState"
 [:map
  {:closed false}
  [:namedStyleType
   {:optional true}
   [:enum
    "NAMED_STYLE_TYPE_UNSPECIFIED"
    "NORMAL_TEXT"
    "TITLE"
    "SUBTITLE"
    "HEADING_1"
    "HEADING_2"
    "HEADING_3"
    "HEADING_4"
    "HEADING_5"
    "HEADING_6"]]
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]
  [:paragraphStyleSuggestionState
   {:optional true}
   [:ref "ParagraphStyleSuggestionState"]]],
 "NamedStyles"
 [:map
  {:closed false}
  [:styles {:optional true} [:vector [:ref "NamedStyle"]]]],
 "Size"
 [:map
  {:closed false}
  [:height {:optional true} [:ref "Dimension"]]
  [:width {:optional true} [:ref "Dimension"]]],
 "TextStyle"
 [:map
  {:closed false}
  [:bold {:optional true} :boolean]
  [:smallCaps {:optional true} :boolean]
  [:baselineOffset
   {:optional true}
   [:enum
    "BASELINE_OFFSET_UNSPECIFIED"
    "NONE"
    "SUPERSCRIPT"
    "SUBSCRIPT"]]
  [:underline {:optional true} :boolean]
  [:link {:optional true} [:ref "Link"]]
  [:strikethrough {:optional true} :boolean]
  [:fontSize {:optional true} [:ref "Dimension"]]
  [:backgroundColor {:optional true} [:ref "OptionalColor"]]
  [:italic {:optional true} :boolean]
  [:foregroundColor {:optional true} [:ref "OptionalColor"]]
  [:weightedFontFamily {:optional true} [:ref "WeightedFontFamily"]]],
 "SheetsChartReference"
 [:map
  {:closed false}
  [:spreadsheetId {:optional true} :string]
  [:chartId {:optional true} :int]],
 "BatchUpdateDocumentRequest"
 [:map
  {:closed false}
  [:requests {:optional true} [:vector [:ref "Request"]]]
  [:writeControl {:optional true} [:ref "WriteControl"]]],
 "SuggestedParagraphStyle"
 [:map
  {:closed false}
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]
  [:paragraphStyleSuggestionState
   {:optional true}
   [:ref "ParagraphStyleSuggestionState"]]],
 "CreateFooterResponse"
 [:map {:closed false} [:footerId {:optional true} :string]],
 "EmbeddedDrawingPropertiesSuggestionState" [:map {:closed false}],
 "Footer"
 [:map
  {:closed false}
  [:footerId {:optional true} :string]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "DocumentFormat"
 [:map
  {:closed false}
  [:documentMode
   {:optional true}
   [:enum "DOCUMENT_MODE_UNSPECIFIED" "PAGES" "PAGELESS"]]],
 "UpdateTableCellStyleRequest"
 [:map
  {:closed false}
  [:tableRange {:optional true} [:ref "TableRange"]]
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:tableCellStyle {:optional true} [:ref "TableCellStyle"]]
  [:fields {:optional true} :string]],
 "TableRow"
 [:map
  {:closed false}
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:tableCells {:optional true} [:vector [:ref "TableCell"]]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:tableRowStyle {:optional true} [:ref "TableRowStyle"]]
  [:suggestedTableRowStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "Footnote"
 [:map
  {:closed false}
  [:footnoteId {:optional true} :string]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "DocumentTab"
 [:map
  {:closed false}
  [:suggestedDocumentStyleChanges
   {:optional true}
   [:map-of :string :any]]
  [:footnotes {:optional true} [:map-of :string :any]]
  [:lists {:optional true} [:map-of :string :any]]
  [:positionedObjects {:optional true} [:map-of :string :any]]
  [:footers {:optional true} [:map-of :string :any]]
  [:inlineObjects {:optional true} [:map-of :string :any]]
  [:namedRanges {:optional true} [:map-of :string :any]]
  [:namedStyles {:optional true} [:ref "NamedStyles"]]
  [:suggestedNamedStylesChanges
   {:optional true}
   [:map-of :string :any]]
  [:documentStyle {:optional true} [:ref "DocumentStyle"]]
  [:headers {:optional true} [:map-of :string :any]]
  [:body {:optional true} [:ref "Body"]]],
 "TextRun"
 [:map
  {:closed false}
  [:content {:optional true} :string]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "List"
 [:map
  {:closed false}
  [:listProperties {:optional true} [:ref "ListProperties"]]
  [:suggestedListPropertiesChanges
   {:optional true}
   [:map-of :string :any]]
  [:suggestedInsertionId {:optional true} :string]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "UpdateDocumentStyleRequest"
 [:map
  {:closed false}
  [:documentStyle {:optional true} [:ref "DocumentStyle"]]
  [:fields {:optional true} :string]
  [:tabId {:optional true} :string]],
 "EmbeddedObjectBorder"
 [:map
  {:closed false}
  [:color {:optional true} [:ref "OptionalColor"]]
  [:width {:optional true} [:ref "Dimension"]]
  [:dashStyle
   {:optional true}
   [:enum "DASH_STYLE_UNSPECIFIED" "SOLID" "DOT" "DASH"]]
  [:propertyState {:optional true} [:enum "RENDERED" "NOT_RENDERED"]]],
 "SuggestedTextStyle"
 [:map
  {:closed false}
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]],
 "NamedStylesSuggestionState"
 [:map
  {:closed false}
  [:stylesSuggestionStates
   {:optional true}
   [:vector [:ref "NamedStyleSuggestionState"]]]],
 "TableStyle"
 [:map
  {:closed false}
  [:tableColumnProperties
   {:optional true}
   [:vector [:ref "TableColumnProperties"]]]],
 "EndOfSegmentLocation"
 [:map
  {:closed false}
  [:segmentId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "TableCellBorder"
 [:map
  {:closed false}
  [:color {:optional true} [:ref "OptionalColor"]]
  [:width {:optional true} [:ref "Dimension"]]
  [:dashStyle
   {:optional true}
   [:enum "DASH_STYLE_UNSPECIFIED" "SOLID" "DOT" "DASH"]]],
 "TableCell"
 [:map
  {:closed false}
  [:startIndex {:optional true} :int]
  [:endIndex {:optional true} :int]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]
  [:tableCellStyle {:optional true} [:ref "TableCellStyle"]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:suggestedTableCellStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "TextStyleSuggestionState"
 [:map
  {:closed false}
  [:foregroundColorSuggested {:optional true} :boolean]
  [:weightedFontFamilySuggested {:optional true} :boolean]
  [:italicSuggested {:optional true} :boolean]
  [:strikethroughSuggested {:optional true} :boolean]
  [:boldSuggested {:optional true} :boolean]
  [:smallCapsSuggested {:optional true} :boolean]
  [:backgroundColorSuggested {:optional true} :boolean]
  [:linkSuggested {:optional true} :boolean]
  [:underlineSuggested {:optional true} :boolean]
  [:baselineOffsetSuggested {:optional true} :boolean]
  [:fontSizeSuggested {:optional true} :boolean]],
 "RgbColor"
 [:map
  {:closed false}
  [:red {:optional true} :double]
  [:green {:optional true} :double]
  [:blue {:optional true} :double]],
 "Paragraph"
 [:map
  {:closed false}
  [:elements {:optional true} [:vector [:ref "ParagraphElement"]]]
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]
  [:suggestedParagraphStyleChanges
   {:optional true}
   [:map-of :string :any]]
  [:bullet {:optional true} [:ref "Bullet"]]
  [:suggestedBulletChanges {:optional true} [:map-of :string :any]]
  [:positionedObjectIds {:optional true} [:vector :string]]
  [:suggestedPositionedObjectIds
   {:optional true}
   [:map-of :string :any]]],
 "DeleteTableRowRequest"
 [:map
  {:closed false}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]],
 "UnmergeTableCellsRequest"
 [:map
  {:closed false}
  [:tableRange {:optional true} [:ref "TableRange"]]],
 "SheetsChartReferenceSuggestionState"
 [:map
  {:closed false}
  [:spreadsheetIdSuggested {:optional true} :boolean]
  [:chartIdSuggested {:optional true} :boolean]],
 "Document"
 [:map
  {:closed false}
  [:suggestedDocumentStyleChanges
   {:optional true}
   [:map-of :string :any]]
  [:footnotes {:optional true} [:map-of :string :any]]
  [:lists {:optional true} [:map-of :string :any]]
  [:positionedObjects {:optional true} [:map-of :string :any]]
  [:footers {:optional true} [:map-of :string :any]]
  [:tabs {:optional true} [:vector [:ref "Tab"]]]
  [:inlineObjects {:optional true} [:map-of :string :any]]
  [:namedRanges {:optional true} [:map-of :string :any]]
  [:revisionId {:optional true} :string]
  [:namedStyles {:optional true} [:ref "NamedStyles"]]
  [:suggestedNamedStylesChanges
   {:optional true}
   [:map-of :string :any]]
  [:documentStyle {:optional true} [:ref "DocumentStyle"]]
  [:title {:optional true} :string]
  [:suggestionsViewMode
   {:optional true}
   [:enum
    "DEFAULT_FOR_CURRENT_ACCESS"
    "SUGGESTIONS_INLINE"
    "PREVIEW_SUGGESTIONS_ACCEPTED"
    "PREVIEW_WITHOUT_SUGGESTIONS"]]
  [:headers {:optional true} [:map-of :string :any]]
  [:documentId {:optional true} :string]
  [:body {:optional true} [:ref "Body"]]],
 "PositionedObjectProperties"
 [:map
  {:closed false}
  [:positioning {:optional true} [:ref "PositionedObjectPositioning"]]
  [:embeddedObject {:optional true} [:ref "EmbeddedObject"]]],
 "OptionalColor"
 [:map {:closed false} [:color {:optional true} [:ref "Color"]]],
 "WeightedFontFamily"
 [:map
  {:closed false}
  [:fontFamily {:optional true} :string]
  [:weight {:optional true} :int]],
 "Table"
 [:map
  {:closed false}
  [:rows {:optional true} :int]
  [:columns {:optional true} :int]
  [:tableRows {:optional true} [:vector [:ref "TableRow"]]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:tableStyle {:optional true} [:ref "TableStyle"]]],
 "Background"
 [:map
  {:closed false}
  [:color {:optional true} [:ref "OptionalColor"]]],
 "Color"
 [:map {:closed false} [:rgbColor {:optional true} [:ref "RgbColor"]]],
 "DocumentStyle"
 [:map
  {:closed false}
  [:defaultHeaderId {:optional true} :string]
  [:marginLeft {:optional true} [:ref "Dimension"]]
  [:flipPageOrientation {:optional true} :boolean]
  [:evenPageFooterId {:optional true} :string]
  [:useFirstPageHeaderFooter {:optional true} :boolean]
  [:useCustomHeaderFooterMargins {:optional true} :boolean]
  [:background {:optional true} [:ref "Background"]]
  [:marginTop {:optional true} [:ref "Dimension"]]
  [:documentFormat {:optional true} [:ref "DocumentFormat"]]
  [:pageNumberStart {:optional true} :int]
  [:firstPageHeaderId {:optional true} :string]
  [:defaultFooterId {:optional true} :string]
  [:firstPageFooterId {:optional true} :string]
  [:pageSize {:optional true} [:ref "Size"]]
  [:evenPageHeaderId {:optional true} :string]
  [:marginBottom {:optional true} [:ref "Dimension"]]
  [:marginHeader {:optional true} [:ref "Dimension"]]
  [:useEvenPageHeaderFooter {:optional true} :boolean]
  [:marginFooter {:optional true} [:ref "Dimension"]]
  [:marginRight {:optional true} [:ref "Dimension"]]],
 "PositionedObjectPositioningSuggestionState"
 [:map
  {:closed false}
  [:layoutSuggested {:optional true} :boolean]
  [:leftOffsetSuggested {:optional true} :boolean]
  [:topOffsetSuggested {:optional true} :boolean]],
 "ImageProperties"
 [:map
  {:closed false}
  [:contentUri {:optional true} :string]
  [:sourceUri {:optional true} :string]
  [:brightness {:optional true} :double]
  [:contrast {:optional true} :double]
  [:transparency {:optional true} :double]
  [:cropProperties {:optional true} [:ref "CropProperties"]]
  [:angle {:optional true} :double]],
 "TableRange"
 [:map
  {:closed false}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]
  [:rowSpan {:optional true} :int]
  [:columnSpan {:optional true} :int]],
 "ObjectReferences"
 [:map
  {:closed false}
  [:objectIds {:optional true} [:vector :string]]],
 "CreateHeaderRequest"
 [:map
  {:closed false}
  [:type
   {:optional true}
   [:enum "HEADER_FOOTER_TYPE_UNSPECIFIED" "DEFAULT"]]
  [:sectionBreakLocation {:optional true} [:ref "Location"]]],
 "ListPropertiesSuggestionState"
 [:map
  {:closed false}
  [:nestingLevelsSuggestionStates
   {:optional true}
   [:vector [:ref "NestingLevelSuggestionState"]]]],
 "CreateFootnoteRequest"
 [:map
  {:closed false}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "HeadingLink"
 [:map
  {:closed false}
  [:id {:optional true} :string]
  [:tabId {:optional true} :string]],
 "SectionColumnProperties"
 [:map
  {:closed false}
  [:width {:optional true} [:ref "Dimension"]]
  [:paddingEnd {:optional true} [:ref "Dimension"]]],
 "NestingLevel"
 [:map
  {:closed false}
  [:bulletAlignment
   {:optional true}
   [:enum "BULLET_ALIGNMENT_UNSPECIFIED" "START" "CENTER" "END"]]
  [:glyphType
   {:optional true}
   [:enum
    "GLYPH_TYPE_UNSPECIFIED"
    "NONE"
    "DECIMAL"
    "ZERO_DECIMAL"
    "UPPER_ALPHA"
    "ALPHA"
    "UPPER_ROMAN"
    "ROMAN"]]
  [:glyphSymbol {:optional true} :string]
  [:glyphFormat {:optional true} :string]
  [:indentFirstLine {:optional true} [:ref "Dimension"]]
  [:indentStart {:optional true} [:ref "Dimension"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:startNumber {:optional true} :int]],
 "ReplaceAllTextRequest"
 [:map
  {:closed false}
  [:replaceText {:optional true} :string]
  [:containsText {:optional true} [:ref "SubstringMatchCriteria"]]
  [:tabsCriteria {:optional true} [:ref "TabsCriteria"]]],
 "AutoText"
 [:map
  {:closed false}
  [:type
   {:optional true}
   [:enum "TYPE_UNSPECIFIED" "PAGE_NUMBER" "PAGE_COUNT"]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "DeleteFooterRequest"
 [:map
  {:closed false}
  [:footerId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "RichLink"
 [:map
  {:closed false}
  [:richLinkId {:optional true} :string]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:richLinkProperties {:optional true} [:ref "RichLinkProperties"]]],
 "DeleteParagraphBulletsRequest"
 [:map {:closed false} [:range {:optional true} [:ref "Range"]]],
 "PositionedObject"
 [:map
  {:closed false}
  [:objectId {:optional true} :string]
  [:positionedObjectProperties
   {:optional true}
   [:ref "PositionedObjectProperties"]]
  [:suggestedPositionedObjectPropertiesChanges
   {:optional true}
   [:map-of :string :any]]
  [:suggestedInsertionId {:optional true} :string]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "SuggestedDocumentStyle"
 [:map
  {:closed false}
  [:documentStyle {:optional true} [:ref "DocumentStyle"]]
  [:documentStyleSuggestionState
   {:optional true}
   [:ref "DocumentStyleSuggestionState"]]],
 "HorizontalRule"
 [:map
  {:closed false}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges
   {:optional true}
   [:map-of :string :any]]],
 "UpdateParagraphStyleRequest"
 [:map
  {:closed false}
  [:range {:optional true} [:ref "Range"]]
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]
  [:fields {:optional true} :string]]})
