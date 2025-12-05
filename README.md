# google-workspace-clj [WIP]

Calling Google Workspace from Clojure. Because apparently, the official Java client wasn't painful enough for you.

This library is "vibe coded" (read: I hacked it together in a fugue state). It dynamically generates client functions from Google's Discovery documents, so you can enjoy the thrill of runtime API definition. Now with static generation because you complained about macros.

## Prerequisites

- Babashka (because starting a JVM takes longer than the heat death of the universe).
- A Google Cloud Project with APIs enabled (good luck navigating that console).
- An OAuth token (I'm not helping you get this; figuring out OAuth is a rite of passage).

## Usage

First, require the namespace. We have `keep`, `forms`, `docs`, `sheets`, and `gemini`.

```clojure
(require '[google-clj-workspace.keep :as keep]
         '[google-clj-workspace.forms :as forms]
         '[google-clj-workspace.docs :as docs]
         '[google-clj-workspace.sheets :as sheets]
         '[google-clj-workspace.gemini :as gemini]
         '[google-clj-workspace.jules :as jules])
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

Modify documents without opening a browser tab.

```clojure
;; Create a new document
(docs/documents {} (merge opts {:op :create :body {:title "Manifesto"}}))

;; Get a document
(docs/documents {:documentId "123..."} (merge opts {:op :get}))
```

### Google Sheets

Crunch numbers, or just store string data inappropriately.

```clojure
;; Create a spreadsheet
(sheets/spreadsheets {} (merge opts {:op :create :body {:properties {:title "Q4 Projections"}}}))

;; Get values from a range
(sheets/values {:spreadsheetId "123..." :range "A1:B10"} (merge opts {:op :get}))

;; Append values (because databases are too hard)
(sheets/values {:spreadsheetId "123..." :range "A1"}
               (merge opts {:op :append
                            :query-params {:valueInputOption "USER_ENTERED"}
                            :body {:values [["Date" "Amount"] ["2023-01-01" "100"]]}}))
```

### Google Gemini

Talk to the AI. Maybe it knows why this code works.

```clojure
;; Generate content
(gemini/models {:model "models/gemini-pro"}
               (merge opts {:op :generate-content
                            :body {:contents [{:parts [{:text "Write a haiku about Clojure"}]}]}}))
```

### Jules

Chat with an AI agent that might actually do what you ask (no guarantees).

```clojure
;; Create a session to start your therapy
(jules/sessions {} (merge opts {:op :create}))

;; Send a message to the void (or the agent)
(jules/sessions {:session "sessions/123..."}
                (merge opts {:op :send-message :body {:message "Write me a symphony"}}))

;; Approve the plan because you trust the machine implicitly
(jules/sessions {:session "sessions/123..."} (merge opts {:op :approve-plan}))

;; Check activities to see if it actually did anything
(jules/activities {:parent "sessions/123..."} (merge opts {:op :list}))
```

## How it works

We run `bb update-api` to fetch discovery schemas dynamically (no, we don't save them to disk, we aren't barbarians), and generate static Clojure files.

## Testing

Run tests with `bb test.clj`. They mock the calls, so they always pass. Great confidence booster.
