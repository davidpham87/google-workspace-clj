(ns google-clj-workspace.impl.docs
  (:require
   [google-clj-workspace.client :as client]
   [google-clj-workspace.core :as core]
   [google-clj-workspace.util :as util]))

(def base-url "https://docs.googleapis.com/")


(def registry
{"SectionBreak"
 [:map
  {:closed true}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:sectionStyle {:optional true} [:ref "SectionStyle"]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "TabsCriteria"
 [:map {:closed true} [:tabIds {:optional true} [:vector :string]]],
 "DeleteNamedRangeRequest"
 [:map
  {:closed true}
  [:namedRangeId {:optional true} :string]
  [:name {:optional true} :string]
  [:tabsCriteria {:optional true} [:ref "TabsCriteria"]]],
 "TableCellStyle"
 [:map
  {:closed true}
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
  {:closed true}
  [:positioningSuggestionState
   {:optional true}
   [:ref "PositionedObjectPositioningSuggestionState"]]
  [:embeddedObjectSuggestionState
   {:optional true}
   [:ref "EmbeddedObjectSuggestionState"]]],
 "TableRowStyle"
 [:map
  {:closed true}
  [:preventOverflow {:optional true} :boolean]
  [:tableHeader {:optional true} :boolean]
  [:minRowHeight {:optional true} [:ref "Dimension"]]],
 "MergeTableCellsRequest"
 [:map
  {:closed true}
  [:tableRange {:optional true} [:ref "TableRange"]]],
 "LinkedContentReference"
 [:map
  {:closed true}
  [:sheetsChartReference
   {:optional true}
   [:ref "SheetsChartReference"]]],
 "BackgroundSuggestionState"
 [:map
  {:closed true}
  [:backgroundColorSuggested {:optional true} :boolean]],
 "InsertTextRequest"
 [:map
  {:closed true}
  [:location {:optional true} [:ref "Location"]]
  [:text {:optional true} :string]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "EmbeddedDrawingProperties" [:map {:closed true}],
 "Request"
 [:map
  {:closed true}
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
  [:insertDate {:optional true} [:ref "InsertDateRequest"]]
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
  {:closed true}
  [:tabId {:optional true} :string]
  [:heading {:optional true} [:ref "HeadingLink"]]
  [:url {:optional true} :string]
  [:bookmark {:optional true} [:ref "BookmarkLink"]]
  [:bookmarkId {:optional true} :string]
  [:headingId {:optional true} :string]],
 "BatchUpdateDocumentResponse"
 [:map
  {:closed true}
  [:writeControl {:optional true} [:ref "WriteControl"]]
  [:replies {:optional true} [:vector [:ref "Response"]]]
  [:documentId {:optional true} :string]],
 "Equation"
 [:map
  {:closed true}
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]],
 "ImagePropertiesSuggestionState"
 [:map
  {:closed true}
  [:contrastSuggested {:optional true} :boolean]
  [:angleSuggested {:optional true} :boolean]
  [:cropPropertiesSuggestionState
   {:optional true}
   [:ref "CropPropertiesSuggestionState"]]
  [:contentUriSuggested {:optional true} :boolean]
  [:sourceUriSuggested {:optional true} :boolean]
  [:transparencySuggested {:optional true} :boolean]
  [:brightnessSuggested {:optional true} :boolean]],
 "EmbeddedObject"
 [:map
  {:closed true}
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
  {:closed true}
  [:embeddedObjectSuggestionState
   {:optional true}
   [:ref "EmbeddedObjectSuggestionState"]]],
 "CreateParagraphBulletsRequest"
 [:map
  {:closed true}
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
    "NUMBERED_ZERODECIMAL_ALPHA_ROMAN"]]
  [:range {:optional true} [:ref "Range"]]],
 "SuggestedListProperties"
 [:map
  {:closed true}
  [:listProperties {:optional true} [:ref "ListProperties"]]
  [:listPropertiesSuggestionState
   {:optional true}
   [:ref "ListPropertiesSuggestionState"]]],
 "TableCellStyleSuggestionState"
 [:map
  {:closed true}
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
  {:closed true}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "TableOfContents"
 [:map
  {:closed true}
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "DeleteContentRangeRequest"
 [:map {:closed true} [:range {:optional true} [:ref "Range"]]],
 "ReplaceNamedRangeContentRequest"
 [:map
  {:closed true}
  [:tabsCriteria {:optional true} [:ref "TabsCriteria"]]
  [:namedRangeId {:optional true} :string]
  [:namedRangeName {:optional true} :string]
  [:text {:optional true} :string]],
 "Header"
 [:map
  {:closed true}
  [:headerId {:optional true} :string]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "Body"
 [:map
  {:closed true}
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "NamedRange"
 [:map
  {:closed true}
  [:name {:optional true} :string]
  [:namedRangeId {:optional true} :string]
  [:ranges {:optional true} [:vector [:ref "Range"]]]],
 "ReplaceAllTextResponse"
 [:map {:closed true} [:occurrencesChanged {:optional true} :int]],
 "ParagraphStyleSuggestionState"
 [:map
  {:closed true}
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
  {:closed true}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]],
 "LinkedContentReferenceSuggestionState"
 [:map
  {:closed true}
  [:sheetsChartReferenceSuggestionState
   {:optional true}
   [:ref "SheetsChartReferenceSuggestionState"]]],
 "TableColumnProperties"
 [:map
  {:closed true}
  [:width {:optional true} [:ref "Dimension"]]
  [:widthType
   {:optional true}
   [:enum
    "WIDTH_TYPE_UNSPECIFIED"
    "EVENLY_DISTRIBUTED"
    "FIXED_WIDTH"]]],
 "ParagraphElement"
 [:map
  {:closed true}
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
  {:closed true}
  [:name {:optional true} :string]
  [:range {:optional true} [:ref "Range"]]],
 "CreateNamedRangeResponse"
 [:map {:closed true} [:namedRangeId {:optional true} :string]],
 "PositionedObjectPositioning"
 [:map
  {:closed true}
  [:leftOffset {:optional true} [:ref "Dimension"]]
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
  [:topOffset {:optional true} [:ref "Dimension"]]],
 "ColumnBreak"
 [:map
  {:closed true}
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]],
 "TableCellLocation"
 [:map
  {:closed true}
  [:rowIndex {:optional true} :int]
  [:columnIndex {:optional true} :int]
  [:tableStartLocation {:optional true} [:ref "Location"]]],
 "DeleteHeaderRequest"
 [:map
  {:closed true}
  [:headerId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "Dimension"
 [:map
  {:closed true}
  [:unit {:optional true} [:enum "UNIT_UNSPECIFIED" "PT"]]
  [:magnitude {:optional true} :double]],
 "SectionStyle"
 [:map
  {:closed true}
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
  {:closed true}
  [:index {:optional true} :int]
  [:tabId {:optional true} :string]
  [:segmentId {:optional true} :string]],
 "ParagraphStyle"
 [:map
  {:closed true}
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
  {:closed true}
  [:offsetRightSuggested {:optional true} :boolean]
  [:offsetTopSuggested {:optional true} :boolean]
  [:offsetBottomSuggested {:optional true} :boolean]
  [:angleSuggested {:optional true} :boolean]
  [:offsetLeftSuggested {:optional true} :boolean]],
 "InsertTableRequest"
 [:map
  {:closed true}
  [:rows {:optional true} :int]
  [:columns {:optional true} :int]
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "InsertSectionBreakRequest"
 [:map
  {:closed true}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:sectionType
   {:optional true}
   [:enum "SECTION_TYPE_UNSPECIFIED" "CONTINUOUS" "NEXT_PAGE"]]],
 "SuggestedInlineObjectProperties"
 [:map
  {:closed true}
  [:inlineObjectProperties
   {:optional true}
   [:ref "InlineObjectProperties"]]
  [:inlineObjectPropertiesSuggestionState
   {:optional true}
   [:ref "InlineObjectPropertiesSuggestionState"]]],
 "Tab"
 [:map
  {:closed true}
  [:documentTab {:optional true} [:ref "DocumentTab"]]
  [:childTabs {:optional true} [:vector [:ref "Tab"]]]
  [:tabProperties {:optional true} [:ref "TabProperties"]]],
 "ParagraphBorder"
 [:map
  {:closed true}
  [:dashStyle
   {:optional true}
   [:enum "DASH_STYLE_UNSPECIFIED" "SOLID" "DOT" "DASH"]]
  [:color {:optional true} [:ref "OptionalColor"]]
  [:width {:optional true} [:ref "Dimension"]]
  [:padding {:optional true} [:ref "Dimension"]]],
 "DeletePositionedObjectRequest"
 [:map
  {:closed true}
  [:objectId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "SizeSuggestionState"
 [:map
  {:closed true}
  [:heightSuggested {:optional true} :boolean]
  [:widthSuggested {:optional true} :boolean]],
 "SubstringMatchCriteria"
 [:map
  {:closed true}
  [:text {:optional true} :string]
  [:searchByRegex {:optional true} :boolean]
  [:matchCase {:optional true} :boolean]],
 "SuggestedTableCellStyle"
 [:map
  {:closed true}
  [:tableCellStyleSuggestionState
   {:optional true}
   [:ref "TableCellStyleSuggestionState"]]
  [:tableCellStyle {:optional true} [:ref "TableCellStyle"]]],
 "Person"
 [:map
  {:closed true}
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:personId {:optional true} :string]
  [:personProperties {:optional true} [:ref "PersonProperties"]]],
 "EmbeddedObjectBorderSuggestionState"
 [:map
  {:closed true}
  [:dashStyleSuggested {:optional true} :boolean]
  [:widthSuggested {:optional true} :boolean]
  [:colorSuggested {:optional true} :boolean]
  [:propertyStateSuggested {:optional true} :boolean]],
 "BookmarkLink"
 [:map
  {:closed true}
  [:tabId {:optional true} :string]
  [:id {:optional true} :string]],
 "Bullet"
 [:map
  {:closed true}
  [:listId {:optional true} :string]
  [:nestingLevel {:optional true} :int]
  [:textStyle {:optional true} [:ref "TextStyle"]]],
 "CropProperties"
 [:map
  {:closed true}
  [:offsetTop {:optional true} :double]
  [:offsetBottom {:optional true} :double]
  [:offsetRight {:optional true} :double]
  [:offsetLeft {:optional true} :double]
  [:angle {:optional true} :double]],
 "StructuralElement"
 [:map
  {:closed true}
  [:table {:optional true} [:ref "Table"]]
  [:paragraph {:optional true} [:ref "Paragraph"]]
  [:endIndex {:optional true} :int]
  [:tableOfContents {:optional true} [:ref "TableOfContents"]]
  [:sectionBreak {:optional true} [:ref "SectionBreak"]]
  [:startIndex {:optional true} :int]],
 "TabStop"
 [:map
  {:closed true}
  [:offset {:optional true} [:ref "Dimension"]]
  [:alignment
   {:optional true}
   [:enum "TAB_STOP_ALIGNMENT_UNSPECIFIED" "START" "CENTER" "END"]]],
 "CreateFooterRequest"
 [:map
  {:closed true}
  [:type
   {:optional true}
   [:enum "HEADER_FOOTER_TYPE_UNSPECIFIED" "DEFAULT"]]
  [:sectionBreakLocation {:optional true} [:ref "Location"]]],
 "BulletSuggestionState"
 [:map
  {:closed true}
  [:listIdSuggested {:optional true} :boolean]
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]
  [:nestingLevelSuggested {:optional true} :boolean]],
 "InsertInlineImageResponse"
 [:map {:closed true} [:objectId {:optional true} :string]],
 "Response"
 [:map
  {:closed true}
  [:createFootnote {:optional true} [:ref "CreateFootnoteResponse"]]
  [:createNamedRange
   {:optional true}
   [:ref "CreateNamedRangeResponse"]]
  [:replaceAllText {:optional true} [:ref "ReplaceAllTextResponse"]]
  [:insertInlineSheetsChart
   {:optional true}
   [:ref "InsertInlineSheetsChartResponse"]]
  [:createFooter {:optional true} [:ref "CreateFooterResponse"]]
  [:createHeader {:optional true} [:ref "CreateHeaderResponse"]]
  [:insertInlineImage
   {:optional true}
   [:ref "InsertInlineImageResponse"]]],
 "Range"
 [:map
  {:closed true}
  [:endIndex {:optional true} :int]
  [:tabId {:optional true} :string]
  [:segmentId {:optional true} :string]
  [:startIndex {:optional true} :int]],
 "InlineObjectProperties"
 [:map
  {:closed true}
  [:embeddedObject {:optional true} [:ref "EmbeddedObject"]]],
 "DocumentStyleSuggestionState"
 [:map
  {:closed true}
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
  {:closed true}
  [:insertRight {:optional true} :boolean]
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]],
 "ReplaceImageRequest"
 [:map
  {:closed true}
  [:imageReplaceMethod
   {:optional true}
   [:enum "IMAGE_REPLACE_METHOD_UNSPECIFIED" "CENTER_CROP"]]
  [:tabId {:optional true} :string]
  [:uri {:optional true} :string]
  [:imageObjectId {:optional true} :string]],
 "PinTableHeaderRowsRequest"
 [:map
  {:closed true}
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:pinnedHeaderRowsCount {:optional true} :int]],
 "PageBreak"
 [:map
  {:closed true}
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "UpdateTableColumnPropertiesRequest"
 [:map
  {:closed true}
  [:fields {:optional true} :string]
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:tableColumnProperties
   {:optional true}
   [:ref "TableColumnProperties"]]
  [:columnIndices {:optional true} [:vector :int]]],
 "PersonProperties"
 [:map
  {:closed true}
  [:name {:optional true} :string]
  [:email {:optional true} :string]],
 "TabProperties"
 [:map
  {:closed true}
  [:parentTabId {:optional true} :string]
  [:title {:optional true} :string]
  [:nestingLevel {:optional true} :int]
  [:index {:optional true} :int]
  [:iconEmoji {:optional true} :string]
  [:tabId {:optional true} :string]],
 "SuggestedBullet"
 [:map
  {:closed true}
  [:bullet {:optional true} [:ref "Bullet"]]
  [:bulletSuggestionState
   {:optional true}
   [:ref "BulletSuggestionState"]]],
 "Shading"
 [:map
  {:closed true}
  [:backgroundColor {:optional true} [:ref "OptionalColor"]]],
 "NamedRanges"
 [:map
  {:closed true}
  [:namedRanges {:optional true} [:vector [:ref "NamedRange"]]]
  [:name {:optional true} :string]],
 "TableRowStyleSuggestionState"
 [:map
  {:closed true}
  [:minRowHeightSuggested {:optional true} :boolean]],
 "SuggestedTableRowStyle"
 [:map
  {:closed true}
  [:tableRowStyle {:optional true} [:ref "TableRowStyle"]]
  [:tableRowStyleSuggestionState
   {:optional true}
   [:ref "TableRowStyleSuggestionState"]]],
 "NamedStyle"
 [:map
  {:closed true}
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
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]],
 "InlineObjectElement"
 [:map
  {:closed true}
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:inlineObjectId {:optional true} :string]],
 "InsertInlineImageRequest"
 [:map
  {:closed true}
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:objectSize {:optional true} [:ref "Size"]]
  [:uri {:optional true} :string]
  [:location {:optional true} [:ref "Location"]]],
 "RichLinkProperties"
 [:map
  {:closed true}
  [:mimeType {:optional true} :string]
  [:uri {:optional true} :string]
  [:title {:optional true} :string]],
 "InsertTableRowRequest"
 [:map
  {:closed true}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]
  [:insertBelow {:optional true} :boolean]],
 "UpdateTextStyleRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "Range"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:fields {:optional true} :string]],
 "CreateFootnoteResponse"
 [:map {:closed true} [:footnoteId {:optional true} :string]],
 "NestingLevelSuggestionState"
 [:map
  {:closed true}
  [:glyphFormatSuggested {:optional true} :boolean]
  [:bulletAlignmentSuggested {:optional true} :boolean]
  [:indentFirstLineSuggested {:optional true} :boolean]
  [:glyphTypeSuggested {:optional true} :boolean]
  [:indentStartSuggested {:optional true} :boolean]
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]
  [:startNumberSuggested {:optional true} :boolean]
  [:glyphSymbolSuggested {:optional true} :boolean]],
 "InlineObject"
 [:map
  {:closed true}
  [:suggestedInlineObjectPropertiesChanges
   {:optional true}
   [:map-of :string :any]]
  [:suggestedInsertionId {:optional true} :string]
  [:inlineObjectProperties
   {:optional true}
   [:ref "InlineObjectProperties"]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:objectId {:optional true} :string]],
 "ShadingSuggestionState"
 [:map
  {:closed true}
  [:backgroundColorSuggested {:optional true} :boolean]],
 "InsertInlineSheetsChartResponse"
 [:map {:closed true} [:objectId {:optional true} :string]],
 "InsertPersonRequest"
 [:map
  {:closed true}
  [:personProperties {:optional true} [:ref "PersonProperties"]]
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "ListProperties"
 [:map
  {:closed true}
  [:nestingLevels {:optional true} [:vector [:ref "NestingLevel"]]]],
 "FootnoteReference"
 [:map
  {:closed true}
  [:footnoteId {:optional true} :string]
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:footnoteNumber {:optional true} :string]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "CreateHeaderResponse"
 [:map {:closed true} [:headerId {:optional true} :string]],
 "SuggestedPositionedObjectProperties"
 [:map
  {:closed true}
  [:positionedObjectProperties
   {:optional true}
   [:ref "PositionedObjectProperties"]]
  [:positionedObjectPropertiesSuggestionState
   {:optional true}
   [:ref "PositionedObjectPropertiesSuggestionState"]]],
 "UpdateSectionStyleRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "Range"]]
  [:fields {:optional true} :string]
  [:sectionStyle {:optional true} [:ref "SectionStyle"]]],
 "SuggestedNamedStyles"
 [:map
  {:closed true}
  [:namedStyles {:optional true} [:ref "NamedStyles"]]
  [:namedStylesSuggestionState
   {:optional true}
   [:ref "NamedStylesSuggestionState"]]],
 "WriteControl"
 [:map
  {:closed true}
  [:targetRevisionId {:optional true} :string]
  [:requiredRevisionId {:optional true} :string]],
 "UpdateTableRowStyleRequest"
 [:map
  {:closed true}
  [:tableRowStyle {:optional true} [:ref "TableRowStyle"]]
  [:rowIndices {:optional true} [:vector :int]]
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:fields {:optional true} :string]],
 "EmbeddedObjectSuggestionState"
 [:map
  {:closed true}
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
  {:closed true}
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
  {:closed true}
  [:styles {:optional true} [:vector [:ref "NamedStyle"]]]],
 "Size"
 [:map
  {:closed true}
  [:height {:optional true} [:ref "Dimension"]]
  [:width {:optional true} [:ref "Dimension"]]],
 "TextStyle"
 [:map
  {:closed true}
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
  {:closed true}
  [:spreadsheetId {:optional true} :string]
  [:chartId {:optional true} :int]],
 "BatchUpdateDocumentRequest"
 [:map
  {:closed true}
  [:writeControl {:optional true} [:ref "WriteControl"]]
  [:requests {:optional true} [:vector [:ref "Request"]]]],
 "SuggestedParagraphStyle"
 [:map
  {:closed true}
  [:paragraphStyleSuggestionState
   {:optional true}
   [:ref "ParagraphStyleSuggestionState"]]
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]],
 "CreateFooterResponse"
 [:map {:closed true} [:footerId {:optional true} :string]],
 "EmbeddedDrawingPropertiesSuggestionState" [:map {:closed true}],
 "Footer"
 [:map
  {:closed true}
  [:footerId {:optional true} :string]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "DocumentFormat"
 [:map
  {:closed true}
  [:documentMode
   {:optional true}
   [:enum "DOCUMENT_MODE_UNSPECIFIED" "PAGES" "PAGELESS"]]],
 "UpdateTableCellStyleRequest"
 [:map
  {:closed true}
  [:tableRange {:optional true} [:ref "TableRange"]]
  [:fields {:optional true} :string]
  [:tableStartLocation {:optional true} [:ref "Location"]]
  [:tableCellStyle {:optional true} [:ref "TableCellStyle"]]],
 "TableRow"
 [:map
  {:closed true}
  [:startIndex {:optional true} :int]
  [:tableRowStyle {:optional true} [:ref "TableRowStyle"]]
  [:tableCells {:optional true} [:vector [:ref "TableCell"]]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedTableRowStyleChanges
   {:optional true}
   [:map-of :string :any]]
  [:endIndex {:optional true} :int]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "Footnote"
 [:map
  {:closed true}
  [:footnoteId {:optional true} :string]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]],
 "DocumentTab"
 [:map
  {:closed true}
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
  {:closed true}
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:content {:optional true} :string]
  [:suggestedInsertionIds {:optional true} [:vector :string]]],
 "List"
 [:map
  {:closed true}
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:listProperties {:optional true} [:ref "ListProperties"]]
  [:suggestedInsertionId {:optional true} :string]
  [:suggestedListPropertiesChanges
   {:optional true}
   [:map-of :string :any]]],
 "UpdateDocumentStyleRequest"
 [:map
  {:closed true}
  [:tabId {:optional true} :string]
  [:documentStyle {:optional true} [:ref "DocumentStyle"]]
  [:fields {:optional true} :string]],
 "EmbeddedObjectBorder"
 [:map
  {:closed true}
  [:dashStyle
   {:optional true}
   [:enum "DASH_STYLE_UNSPECIFIED" "SOLID" "DOT" "DASH"]]
  [:width {:optional true} [:ref "Dimension"]]
  [:color {:optional true} [:ref "OptionalColor"]]
  [:propertyState {:optional true} [:enum "RENDERED" "NOT_RENDERED"]]],
 "SuggestedTextStyle"
 [:map
  {:closed true}
  [:textStyleSuggestionState
   {:optional true}
   [:ref "TextStyleSuggestionState"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]],
 "NamedStylesSuggestionState"
 [:map
  {:closed true}
  [:stylesSuggestionStates
   {:optional true}
   [:vector [:ref "NamedStyleSuggestionState"]]]],
 "TableStyle"
 [:map
  {:closed true}
  [:tableColumnProperties
   {:optional true}
   [:vector [:ref "TableColumnProperties"]]]],
 "EndOfSegmentLocation"
 [:map
  {:closed true}
  [:segmentId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "TableCellBorder"
 [:map
  {:closed true}
  [:dashStyle
   {:optional true}
   [:enum "DASH_STYLE_UNSPECIFIED" "SOLID" "DOT" "DASH"]]
  [:color {:optional true} [:ref "OptionalColor"]]
  [:width {:optional true} [:ref "Dimension"]]],
 "TableCell"
 [:map
  {:closed true}
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:content {:optional true} [:vector [:ref "StructuralElement"]]]
  [:tableCellStyle {:optional true} [:ref "TableCellStyle"]]
  [:suggestedTableCellStyleChanges
   {:optional true}
   [:map-of :string :any]]
  [:endIndex {:optional true} :int]
  [:startIndex {:optional true} :int]],
 "InsertDateRequest"
 [:map
  {:closed true}
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]
  [:dateElementProperties
   {:optional true}
   [:ref "DateElementProperties"]]
  [:location {:optional true} [:ref "Location"]]],
 "TextStyleSuggestionState"
 [:map
  {:closed true}
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
  {:closed true}
  [:green {:optional true} :double]
  [:red {:optional true} :double]
  [:blue {:optional true} :double]],
 "Paragraph"
 [:map
  {:closed true}
  [:suggestedPositionedObjectIds
   {:optional true}
   [:map-of :string :any]]
  [:elements {:optional true} [:vector [:ref "ParagraphElement"]]]
  [:bullet {:optional true} [:ref "Bullet"]]
  [:suggestedParagraphStyleChanges
   {:optional true}
   [:map-of :string :any]]
  [:suggestedBulletChanges {:optional true} [:map-of :string :any]]
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]
  [:positionedObjectIds {:optional true} [:vector :string]]],
 "DeleteTableRowRequest"
 [:map
  {:closed true}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]],
 "UnmergeTableCellsRequest"
 [:map
  {:closed true}
  [:tableRange {:optional true} [:ref "TableRange"]]],
 "SheetsChartReferenceSuggestionState"
 [:map
  {:closed true}
  [:chartIdSuggested {:optional true} :boolean]
  [:spreadsheetIdSuggested {:optional true} :boolean]],
 "Document"
 [:map
  {:closed true}
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
  {:closed true}
  [:embeddedObject {:optional true} [:ref "EmbeddedObject"]]
  [:positioning
   {:optional true}
   [:ref "PositionedObjectPositioning"]]],
 "OptionalColor"
 [:map {:closed true} [:color {:optional true} [:ref "Color"]]],
 "WeightedFontFamily"
 [:map
  {:closed true}
  [:fontFamily {:optional true} :string]
  [:weight {:optional true} :int]],
 "Table"
 [:map
  {:closed true}
  [:tableStyle {:optional true} [:ref "TableStyle"]]
  [:columns {:optional true} :int]
  [:rows {:optional true} :int]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:tableRows {:optional true} [:vector [:ref "TableRow"]]]],
 "Background"
 [:map
  {:closed true}
  [:color {:optional true} [:ref "OptionalColor"]]],
 "Color"
 [:map {:closed true} [:rgbColor {:optional true} [:ref "RgbColor"]]],
 "DocumentStyle"
 [:map
  {:closed true}
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
  {:closed true}
  [:leftOffsetSuggested {:optional true} :boolean]
  [:topOffsetSuggested {:optional true} :boolean]
  [:layoutSuggested {:optional true} :boolean]],
 "ImageProperties"
 [:map
  {:closed true}
  [:transparency {:optional true} :double]
  [:brightness {:optional true} :double]
  [:contrast {:optional true} :double]
  [:contentUri {:optional true} :string]
  [:angle {:optional true} :double]
  [:sourceUri {:optional true} :string]
  [:cropProperties {:optional true} [:ref "CropProperties"]]],
 "TableRange"
 [:map
  {:closed true}
  [:tableCellLocation {:optional true} [:ref "TableCellLocation"]]
  [:rowSpan {:optional true} :int]
  [:columnSpan {:optional true} :int]],
 "ObjectReferences"
 [:map {:closed true} [:objectIds {:optional true} [:vector :string]]],
 "CreateHeaderRequest"
 [:map
  {:closed true}
  [:sectionBreakLocation {:optional true} [:ref "Location"]]
  [:type
   {:optional true}
   [:enum "HEADER_FOOTER_TYPE_UNSPECIFIED" "DEFAULT"]]],
 "ListPropertiesSuggestionState"
 [:map
  {:closed true}
  [:nestingLevelsSuggestionStates
   {:optional true}
   [:vector [:ref "NestingLevelSuggestionState"]]]],
 "CreateFootnoteRequest"
 [:map
  {:closed true}
  [:location {:optional true} [:ref "Location"]]
  [:endOfSegmentLocation
   {:optional true}
   [:ref "EndOfSegmentLocation"]]],
 "HeadingLink"
 [:map
  {:closed true}
  [:tabId {:optional true} :string]
  [:id {:optional true} :string]],
 "SectionColumnProperties"
 [:map
  {:closed true}
  [:paddingEnd {:optional true} [:ref "Dimension"]]
  [:width {:optional true} [:ref "Dimension"]]],
 "NestingLevel"
 [:map
  {:closed true}
  [:startNumber {:optional true} :int]
  [:glyphSymbol {:optional true} :string]
  [:glyphFormat {:optional true} :string]
  [:indentFirstLine {:optional true} [:ref "Dimension"]]
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
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:indentStart {:optional true} [:ref "Dimension"]]
  [:bulletAlignment
   {:optional true}
   [:enum "BULLET_ALIGNMENT_UNSPECIFIED" "START" "CENTER" "END"]]],
 "ReplaceAllTextRequest"
 [:map
  {:closed true}
  [:replaceText {:optional true} :string]
  [:containsText {:optional true} [:ref "SubstringMatchCriteria"]]
  [:tabsCriteria {:optional true} [:ref "TabsCriteria"]]],
 "DateElementProperties"
 [:map
  {:closed true}
  [:timestamp {:optional true} :string]
  [:dateFormat
   {:optional true}
   [:enum
    "DATE_FORMAT_UNSPECIFIED"
    "DATE_FORMAT_CUSTOM"
    "DATE_FORMAT_MONTH_DAY_ABBREVIATED"
    "DATE_FORMAT_MONTH_DAY_FULL"
    "DATE_FORMAT_MONTH_DAY_YEAR_ABBREVIATED"
    "DATE_FORMAT_ISO8601"]]
  [:locale {:optional true} :string]
  [:timeZoneId {:optional true} :string]
  [:timeFormat
   {:optional true}
   [:enum
    "TIME_FORMAT_UNSPECIFIED"
    "TIME_FORMAT_DISABLED"
    "TIME_FORMAT_HOUR_MINUTE"
    "TIME_FORMAT_HOUR_MINUTE_TIMEZONE"]]
  [:displayText {:optional true} :string]],
 "AutoText"
 [:map
  {:closed true}
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:type
   {:optional true}
   [:enum "TYPE_UNSPECIFIED" "PAGE_NUMBER" "PAGE_COUNT"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "DeleteFooterRequest"
 [:map
  {:closed true}
  [:footerId {:optional true} :string]
  [:tabId {:optional true} :string]],
 "RichLink"
 [:map
  {:closed true}
  [:richLinkId {:optional true} :string]
  [:richLinkProperties {:optional true} [:ref "RichLinkProperties"]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]],
 "DeleteParagraphBulletsRequest"
 [:map {:closed true} [:range {:optional true} [:ref "Range"]]],
 "PositionedObject"
 [:map
  {:closed true}
  [:suggestedPositionedObjectPropertiesChanges
   {:optional true}
   [:map-of :string :any]]
  [:suggestedInsertionId {:optional true} :string]
  [:suggestedDeletionIds {:optional true} [:vector :string]]
  [:positionedObjectProperties
   {:optional true}
   [:ref "PositionedObjectProperties"]]
  [:objectId {:optional true} :string]],
 "SuggestedDocumentStyle"
 [:map
  {:closed true}
  [:documentStyleSuggestionState
   {:optional true}
   [:ref "DocumentStyleSuggestionState"]]
  [:documentStyle {:optional true} [:ref "DocumentStyle"]]],
 "HorizontalRule"
 [:map
  {:closed true}
  [:suggestedTextStyleChanges {:optional true} [:map-of :string :any]]
  [:textStyle {:optional true} [:ref "TextStyle"]]
  [:suggestedInsertionIds {:optional true} [:vector :string]]
  [:suggestedDeletionIds {:optional true} [:vector :string]]],
 "UpdateParagraphStyleRequest"
 [:map
  {:closed true}
  [:range {:optional true} [:ref "Range"]]
  [:paragraphStyle {:optional true} [:ref "ParagraphStyle"]]
  [:fields {:optional true} :string]]}
)

(def ops
{[:documents :get] {:method "GET", :path "v1/documents/{documentId}"},
 [:documents :create]
 {:method "POST", :path "v1/documents", :schema [:ref "Document"]},
 [:documents :batch-update]
 {:method "POST",
  :path "v1/documents/{documentId}:batchUpdate",
  :schema [:ref "BatchUpdateDocumentRequest"]}}
)

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
