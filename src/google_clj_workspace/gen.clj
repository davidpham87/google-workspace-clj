(ns google-clj-workspace.gen
  (:require [google-clj-workspace.discovery :as discovery]
            [clojure.string :as str]
            [camel-snake-kebab.core :as csk]
            [clojure.pprint :refer [pprint]]
            [babashka.fs :as fs]
            [cheshire.core :as json]))

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

(defn get-resource-name [method-id]
  (let [parts (str/split method-id #"\.")
        parts (rest parts) ;; Remove service
        resource-parts (butlast parts) ;; Remove method
        name (last resource-parts)]
    (if name
      (csk/->kebab-case name)
      "root"))) ;; Fallback if no resource

(defn get-op-name [method-id]
  (let [parts (str/split method-id #"\.")
        method (last parts)]
    (keyword (csk/->kebab-case method))))

(defn generate-method-body [method-def base-url]
  (let [path (:path method-def)
        http-method (:httpMethod method-def)]
    (list 'google-clj-workspace.client/invoke-endpoint http-method path 'params 'opts base-url)))

(defn generate-resource-fn [resource-name methods base-url]
  (let [ops (group-by #(get-op-name (:id %)) methods)
        op-keys (keys ops)
        docstring (str "Available operations: " (str/join ", " (sort op-keys)))
        clauses (mapcat (fn [[op ms]]
                          ;; ms is a list of methods mapping to this op
                          ;; We just take the first one for now to avoid duplicates.
                          [op (generate-method-body (first ms) base-url)])
                        ops)]
    (list 'defn (symbol resource-name)
          docstring
          ['params '& ['opts]]
          (concat (list 'case (list :op 'opts))
                  clauses
                  [(list 'throw (list 'ex-info "Unknown op" {:op (list :op 'opts)}))]))))

(defn generate-service [service-config]
  (let [{:keys [name url examples]} service-config
        discovery-data (discovery/parse-discovery-schema url)

        base-url (:baseUrl discovery-data)
        resources (:resources discovery-data)
        methods (collect-methods resources)

        grouped-methods (group-by #(get-resource-name (:id %)) methods)

        defs (map (fn [[res-name ms]]
                    (generate-resource-fn res-name ms base-url))
                  grouped-methods)

        ns-decl (list 'ns (symbol (str "google-clj-workspace." name))
                      '(:require [google-clj-workspace.client :as client]
                                 [clojure.test :refer [deftest is testing]]))

        ;; Combine all
        code (concat [ns-decl] defs examples)]

    (with-out-str
      (doseq [form code]
        (pprint form)
        (println)))))

(def services
  [{:name "keep"
    :url "https://keep.googleapis.com/$discovery/rest?version=v1"
    :examples '((deftest test-example-notes-list
                  (testing "Example: notes list mock with kebab-case params"
                   (with-redefs [client/make-request (fn [_ _ params _ _]
                                                       ;; Check if params converted to camelCase (e.g. page-size -> pageSize)
                                                       (if (= 10 (:pageSize params))
                                                         {:status 200 :body "{}"}
                                                         {:status 400 :error "Params not converted"}))]
                     ;; Pass {:page-size 10}
                     (is (= 200 (:status (notes {:page-size 10} {:op :list}))))))))}
   {:name "forms"
    :url "https://forms.googleapis.com/$discovery/rest?version=v1"
    :examples '((deftest test-example-forms-create
                  (testing "Example: forms create mock"
                   (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200 :body "{}"})]
                     (is (= 200 (:status (forms {} {:op :create}))))))))}
   {:name "docs"
    :url "https://docs.googleapis.com/$discovery/rest?version=v1"
    :examples '((deftest test-example-documents-get
                  (testing "Example: documents get mock"
                    (with-redefs [client/make-request (fn [_ _ _ _ _] {:status 200 :body "{}"})]
                      (is (= 200 (:status (documents {} {:op :get}))))))))}
   {:name "sheets"
    :url "https://sheets.googleapis.com/$discovery/rest?version=v4"
    :examples '()}
   {:name "gemini"
    :url "https://generativelanguage.googleapis.com/$discovery/rest?version=v1beta"
    :examples '()}
   {:name "jules"
    :url "https://jules.googleapis.com/$discovery/rest"
    :examples '()}])

(defn generate-all []
  (doseq [service services]
    (let [code (generate-service service)
          file (str "src/google_clj_workspace/" (:name service) ".clj")]
      (spit file code)
      (println "Generated" file))))

(defn -main []
  (generate-all))
