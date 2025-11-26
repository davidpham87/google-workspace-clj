# google-workspace-clj [WIP]

Calling Google Workspace from Clojure. Because apparently, the official Java client wasn't painful enough for you.

This library is "vibe coded" (read: I hacked it together in a fugue state). It dynamically generates client functions from Google's Discovery documents, so you can enjoy the thrill of runtime API definition. Now with static generation because you complained about macros.

## Prerequisites

- Babashka (because starting a JVM takes longer than the heat death of the universe).
- A Google Cloud Project with APIs enabled (good luck navigating that console).
- An OAuth token (I'm not helping you get this; figuring out OAuth is a rite of passage).

## Usage

First, require the namespaces. Pick a service, any service.

```clojure
(require '[google-clj-workspace.keep :as keep]
         '[google-clj-workspace.forms :as forms]
         '[google-clj-workspace.docs :as docs]
         '[google-clj-workspace.sheets :as sheets]
         '[google-clj-workspace.gemini :as gemini])
```

### Authentication

Pass your hard-earned token in the `opts` map. We simply stick it in the `Authorization: Bearer` header.

```clojure
(def opts {:token "ya29.a0..."})
```

### Google Keep

Want to read your chaotic notes programmatically? Sure. Use the `notes` function and pray you get the op right.

```clojure
;; List your disorganized thoughts
(keep/notes {} (merge opts {:op :list}))

;; Read a specific note
(keep/notes {:name "notes/1543..."} (merge opts {:op :get}))

;; Create a note to remind yourself to fix this code later
(keep/notes {} (merge opts {:op :create :body {:title "TODO" :text "Refactor everything"}}))
```

### Google Forms

Automate the bureaucracy!

```clojure
;; Create a form to ask people why they are the way they are
(forms/forms {} (merge opts {:op :create :body {:info {:title "Existential Survey"}}}))

;; Get the form you just created
(forms/forms {:formId "1FAIpQL..."} (merge opts {:op :get}))

;; List responses so you can ignore them efficiently
(forms/responses {:formId "1FAIpQL..."} (merge opts {:op :list}))
```

### Google Docs

Need to programmatically create a document you'll never read again? We got you.

```clojure
;; Create a document
(docs/documents {} (merge opts {:op :create :body {:title "My Doc"}}))

;; Get a document
(docs/documents {:documentId "..."} (merge opts {:op :get}))
```

### Google Sheets

Manipulate spreadsheets to maintain the illusion of productivity.

```clojure
;; Create a spreadsheet
(sheets/spreadsheets {} (merge opts {:op :create :body {:properties {:title "Data"}}))

;; Read a range
(sheets/values {:spreadsheetId "..." :range "Sheet1!A1:B2"} (merge opts {:op :get}))

;; Append a row
(sheets/values {:spreadsheetId "..." :range "Sheet1!A1"}
               (merge opts
                      {:op :append
                       :query-params {:valueInputOption "USER_ENTERED"}
                       :body {:values [["I" "am" "busy"]]}}))
```

### Google Gemini

Talk to the ghost in the machine. It's probably fine.

```clojure
;; Generate text from a prompt
(gemini/models {:model "gemini-pro"}
               (merge opts
                      {:op :generate-content
                       :body {:contents [{:parts [{:text "Tell me a joke"}]}]}}))
```

## How it works

We run `bb update-api` to download discovery schemas, and generate static Clojure files. No more checking in giant JSON blobs for you to complain about.

## Testing

Run tests with `bb test.clj`. They mock the calls, so they always pass. Great confidence booster.
