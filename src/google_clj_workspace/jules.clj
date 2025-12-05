(ns google-clj-workspace.jules
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.impl.jules]))

(defn sources
  "Manages sources.
  - op: :get, :list

  Examples:
  ;; list sources
  (sources {} {:op :list})

  ;; get a source
  (sources {:name \"sources/123\"} {:op :get})"
  [params & [opts]]
  (core/dispatch [:jules :sources (:op opts)] params opts))

(defn sessions
  "Manages sessions.
  - op: :list, :send-message, :get, :approve-plan, :create, :delete

  Examples:
  ;; create a session
  (sessions {} {:op :create})

  ;; send a message
  (sessions {:session \"sessions/123\"} {:op :send-message :body {:message \"Hello\"}})"
  [params & [opts]]
  (core/dispatch [:jules :sessions (:op opts)] params opts))

(defn activities
  "Manages activities.
  - op: :get, :list

  Examples:
  ;; list activities
  (activities {:parent \"sessions/123\"} {:op :list})"
  [params & [opts]]
  (core/dispatch [:jules :activities (:op opts)] params opts))
