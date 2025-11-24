(ns test
  (:require [clojure.test :refer [run-tests]]
            [google-clj-workspace.keep]
            [google-clj-workspace.forms]))

(defn -main []
  (run-tests 'google-clj-workspace.keep 'google-clj-workspace.forms))

(when (= *file* (System/getProperty "babashka.file"))
  (-main))
