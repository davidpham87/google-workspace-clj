(ns google-clj-workspace.client
  (:require [babashka.curl :as curl]
            [clojure.string :as str]
            [cheshire.core :as json]))

(defn make-request [method url params body opts]
  (let [token (:token opts)
        headers (cond-> (merge {"Content-Type" "application/json"}
                               (:headers opts))
                  token (assoc "Authorization" (str "Bearer " token)))
        curl-opts (merge {:method (keyword (str/lower-case method))
                          :url url
                          :query-params params
                          :body (when body (json/generate-string body))
                          :headers headers}
                         (select-keys opts [:throw]))]
    (curl/request curl-opts)))

(defn interpolate-path [path params]
  (let [used-keys (atom #{})
        interpolated (reduce (fn [p [k v]]
                               (let [k-str (name k)
                                     ;; Matches {name} or {+name}
                                     pattern (re-pattern (str "\\{\\+?" k-str "\\}"))]
                                 (if (re-find pattern p)
                                   (do (swap! used-keys conj k)
                                       (str/replace p pattern (str v)))
                                   p)))
                             path
                             params)]
    [interpolated @used-keys]))
