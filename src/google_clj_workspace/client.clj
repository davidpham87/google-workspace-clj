(ns google-clj-workspace.client
  (:require [google-clj-workspace.util :as util]
            [camel-snake-kebab.core :as csk]
            [cheshire.core :as json]
            [babashka.curl :as curl]
            [clojure.string :as str]))

(defn- ->camel-case-keys [m]
  (let [f (fn [[k v]] [(csk/->camelCase k) v])]
    (into {} (map f m))))

(defn invoke-endpoint
  "Makes an HTTP request to a Google API endpoint, handling path interpolation,
  parameter casing, and authentication."
  [http-method path params opts base-url]
  (let [token (:token opts)
        [interpolated-path used-path-keys] (util/interpolate-path path params)
        query-params (apply dissoc params used-path-keys)
        final-query-params (->camel-case-keys query-params)
        body (when (:body opts)
               (-> (:body opts)
                   (->camel-case-keys)
                   (json/generate-string)))
        url (str base-url interpolated-path)
        req {:method (-> http-method str/lower-case keyword)
             :url url
             :throw false
             :headers (when token {"Authorization" (str "Bearer " token)})
             :query-params final-query-params
             :body body}
        ;; for GET requests, body must be nil
        req (if (= http-method :get) (dissoc req :body) req)
        response (curl/request req)]
    (if-let [body-str (:body response)]
      (try
        (assoc response :body (json/parse-string body-str true))
        (catch Exception _
          ;; Not json, return as is
          response))
      response)))
