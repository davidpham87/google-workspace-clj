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
  (let [clauses (mapcat (fn [m]
                          [(get-op-name (:id m))
                           (generate-method-body m base-url)])
                        methods)]
    (list 'defn (symbol resource-name)
          ['params '& ['opts]]
          (concat (list 'case (list :op 'opts))
                  clauses
                  [(list 'throw (list 'ex-info "Unknown op" {:op (list :op 'opts)}))]))))

(defn generate-service [service-config]
  (let [{:keys [name url examples]} service-config
        discovery-data (discovery/parse-discovery-schema url)

        ;; Save schema
        schema-file (str "schema/" name "-discovery.json")
        _ (fs/create-dirs "schema")
        _ (spit schema-file (json/generate-string discovery-data {:pretty true}))

        ;; Save registry
        schemas (:schemas discovery-data)
        registry (discovery/json-schemas->malli-registry schemas)
        registry-file (str "schema/" name "-registry.edn")
        _ (spit registry-file (with-out-str (pprint registry)))

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
                     (is (= 200 (:status (forms {} {:op :create}))))))))}])

(defn generate-all []
  (doseq [service services]
    (let [code (generate-service service)
          file (str "src/google_clj_workspace/" (:name service) ".clj")]
      (spit file code)
      (println "Generated" file))))

(defn -main []
  (generate-all))
