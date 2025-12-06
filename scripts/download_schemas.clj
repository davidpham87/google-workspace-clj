(ns scripts.download-schemas
  (:require [babashka.curl :as curl]
            [cheshire.core :as json]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [babashka.fs :as fs]))

(defn download-schema [service url]
  (println "Downloading schema for" service "from" url)
  (try
    (let [resp (curl/get url)
          body (:body resp)
          ;; Validate it's JSON
          _ (json/parse-string body)
          filename (str "resources/schema/discovery/" (name service) ".json")]
      (spit filename body)
      (println "Saved to" filename))
    (catch Exception e
      (println "Error downloading" service ":" (.getMessage e)))))

(defn -main []
  (let [schemas (edn/read-string (slurp "resources/schema.edn"))]
    (fs/create-dirs "resources/schema/discovery")
    (doseq [[service url] schemas]
      (download-schema service url))))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
