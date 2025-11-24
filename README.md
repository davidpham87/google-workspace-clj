# google-workspace-clj [WIP]

Calling Google Workspace from Clojure. Because apparently, the official Java client wasn't painful enough for you.

This library is "vibe coded" (read: I hacked it together in a fugue state). It dynamically generates client functions from Google's Discovery documents, so you can enjoy the thrill of runtime API definition. Now with static generation because you complained about macros.

## Prerequisites

- Babashka (because starting a JVM takes longer than the heat death of the universe).
- A Google Cloud Project with APIs enabled (good luck navigating that console).
- An OAuth token (I'm not helping you get this; figuring out OAuth is a rite of passage).

## Usage

First, require the namespace. We have `keep` and `forms`.

```clojure
(require '[google-clj-workspace.keep :as keep]
         '[google-clj-workspace.forms :as forms])
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

## How it works

We run `bb update-api` to download discovery schemas to `schema/` (because checking in 1MB JSON files is a hobby of mine now), and generate static Clojure files.

## Testing

Run tests with `bb test.clj`. They mock the calls, so they always pass. Great confidence booster.
