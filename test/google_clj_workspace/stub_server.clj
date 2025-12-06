(ns google-clj-workspace.stub-server
  (:require [clojure.test :refer :all]))

(defn stub-server-handler
  "A stub handler that simulates a server.
   It validates inputs if needed or just echoes back success.
   For Jules, we might want to check if specific fields are present."
  [request]
  (let [body (:body request)
        params (:params request)
        uri (:uri request)]
    ;; Logic to handle different endpoints
    (cond
      (clojure.string/includes? uri "jules")
      (if (and body (:prompt body))
        {:status 200 :body {:response "Jules response"}}
        {:status 400 :body {:error "Missing prompt"}})

      :else
      {:status 200 :body {:message "Stub response"}})))
