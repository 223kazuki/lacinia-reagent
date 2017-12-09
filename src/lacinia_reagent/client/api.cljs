(ns lacinia-reagent.client.api
  (:require [ajax.core :refer [POST]]
            [venia.core :as v]))

(def graphql-endpoint (.. js/document
                          (querySelector "meta[name=graphql-endpoint]")
                          (getAttribute "content")))

(defn request [query options]
  (POST graphql-endpoint (-> options
                             (select-keys [:handler :error-handler :params])
                             (assoc :headers {"Content-type" "application/graphql"}
                                    :response-format :json
                                    :keywords? true
                                    :body (str "query" (v/graphql-query query))))))
