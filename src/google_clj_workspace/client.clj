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

(defn- generate-fn-name [resource-path method-id]
  (let [parts (str/split method-id #"\.")
        parts (rest parts)] ;; Remove service name
    (->> parts
         (map csk/->kebab-case)
         (str/join "-")
         symbol)))

(defn- collect-methods [resources]
  (let [methods (atom [])]
    (letfn [(walk [res]
              (when (:methods res)
                (doseq [[_ m] (:methods res)]
                  (swap! methods conj m)))
              (when (:resources res)
                (doseq [[_ r] (:resources res)]
                  (walk r))))]
      (doseq [[_ r] resources]
        (walk r)))
    @methods))

(defmacro def-api [discovery-data-expr]
  (let [discovery-data (if (map? discovery-data-expr)
                         discovery-data-expr
                         (eval discovery-data-expr))
        base-url (:baseUrl discovery-data)
        resources (:resources discovery-data)
        methods (collect-methods resources)
        defs (map (fn [method-def]
                    (let [id (:id method-def)
                          path (:path method-def)
                          http-method (:httpMethod method-def)
                          fn-name (generate-fn-name nil id)]
                      `(defn ~fn-name [params# & [opts#]]
                         (let [[path-str# used-keys#] (google-clj-workspace.client/interpolate-path ~path params#)
                               query-params# (apply dissoc params# used-keys#)
                               full-url# (str ~base-url path-str#)]
                           (google-clj-workspace.client/make-request ~http-method full-url# query-params# (:body opts#) opts#)))))
                  methods)]
    `(do ~@defs)))
