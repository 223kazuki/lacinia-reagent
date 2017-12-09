(ns lacinia-reagent.client.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [integrant.core :as ig]
            [lacinia-reagent.client.events :as events]
            [lacinia-reagent.client.views :as views]))

(defonce system (atom nil))

(defmethod ig/init-key ::db
  [_ _]
  (re-frame/dispatch-sync [::events/initialize-db]))

(defmethod ig/init-key ::app
  [_ _]
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(def system-conf
  {::db nil
   ::app {:db (ig/ref ::db)}})

(defn start []
  (reset! system (ig/init system-conf)))

(defn stop []
  (ig/halt! @system)
  (reset! system nil))
