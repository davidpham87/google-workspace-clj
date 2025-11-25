(ns google-clj-workspace.core)

(defmulti dispatch (fn [v & _] v))
