(ns google-clj-workspace.util
  (:require [clojure.string :as str]))

(defn interpolate-path
  "Replaces placeholders like `{+foo}` or `{foo}` in a path string with
  values from the `params` map.
  Returns a vector containing the interpolated path string and a set of
  keywords representing the parameters that were used for interpolation."
  [path params]
  (reduce-kv
   (fn [[p used-keys] k v]
     (let [pattern-str (str "\\{\\+?" (name k) "\\}")
           pattern (re-pattern pattern-str)]
       (if (re-find pattern p)
         [(str/replace p pattern (str v)) (conj used-keys k)]
         [p used-keys])))
   [path #{}]
   params))

(def ^:private service-docs-config
  {:docs   {:group "document"}
   :sheets {:group "spreadsheet"}
   :forms  {:group "forms"}
   :keep   {:group "notes"}
   :gemini {:version "v1beta"
            :url-template "https://ai.google.dev/api/rest/%s/%s/%s"}})

(defn help-url
  "Constructs a URL to the relevant Google API documentation page."
  [{:keys [service resource op]}]
  (let [{:keys [group version url-template]
         :or {version "v1"
              group (name service)}} (get service-docs-config service)
        default-url-template "https://developers.google.com/docs/api/reference/rest/%s/%s.%s/%s"
        final-url-template (or url-template default-url-template)
        resource-name (name resource)
        op-name (name op)]
    (if (= service :gemini)
      (format final-url-template version resource-name op-name)
      (format final-url-template version group resource-name op-name))))
