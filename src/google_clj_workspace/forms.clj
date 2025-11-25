(ns google-clj-workspace.forms
  (:require
   [google-clj-workspace.core :as core]
   [google-clj-workspace.imp.forms]))

(defn forms
  "Manages forms.
  - op: :create, :get, :batch-update, :set-publish-settings"
  [params & [opts]]
  (core/dispatch [:forms :forms (:op opts)] params opts))

(defn responses
  "Manages responses.
  - op: :get, :list"
  [params & [opts]]
  (core/dispatch [:forms :responses (:op opts)] params opts))

(defn watches
  "Manages watches.
  - op: :create, :list, :renew, :delete"
  [params & [opts]]
  (core/dispatch [:forms :watches (:op opts)] params opts))
