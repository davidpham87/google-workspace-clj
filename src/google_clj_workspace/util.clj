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
