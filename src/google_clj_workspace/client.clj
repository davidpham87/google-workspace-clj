(ns google-clj-workspace.client
  (:require [babashka.curl :as curl]
            [clojure.string :as str]
            [cheshire.core :as json]
            [camel-snake-kebab.core :as csk]))

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

(defn- transform-keys [t coll]
  (cond
    (map? coll) (reduce-kv (fn [m k v] (assoc m (t k) (transform-keys t v))) {} coll)
    (vector? coll) (mapv #(transform-keys t %) coll)
    :else coll))

(defn invoke-endpoint [method path-template params opts base-url]
  (let [params (transform-keys csk/->camelCaseKeyword params)
        opts (if (:body opts)
               (update opts :body #(transform-keys csk/->camelCaseKeyword %))
               opts)

        [path-str used-keys] (interpolate-path path-template params)
        query-params (apply dissoc params used-keys)
        full-url (str base-url path-str)]
    (make-request method full-url query-params (:body opts) opts)))
