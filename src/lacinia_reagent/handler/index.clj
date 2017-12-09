(ns lacinia-reagent.handler.index
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [integrant.core :as ig]
            [hiccup.page :refer [html5 include-css include-js]]))

(defn- index-page [{:keys [graphql-endpoint]}]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "graphql-endpoint" :content graphql-endpoint}]
    [:meta {:name "viewport" :content"width=device-width, initial-scale=1.0"}]
    (include-css "/assets/normalize.css/normalize.css")
    (include-css "https://unpkg.com/purecss@1.0.0/build/pure-min.css")
    (include-css "/css/site.css")
    [:title "Lacinia Reagent"]]
   [:body
    [:div#app]
    (include-js "/js/main.js")]))

(defmethod ig/init-key :lacinia-reagent.handler/index [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (index-page options)]))
