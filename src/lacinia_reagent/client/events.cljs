(ns lacinia-reagent.client.events
  (:require [re-frame.core :as re-frame]
            [lacinia-reagent.client.db :as db]
            [reagent.format :refer [format]]
            [lacinia-reagent.client.api :refer [request]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::fetch-board-games
 (fn  [db [_ attrs]]
   (request {:venia/queries [[:games (vec attrs)]]}
            {:handler #(re-frame/dispatch [::process-games-response %])})
   (assoc db :attrs attrs)))

(re-frame/reg-event-db
 ::process-games-response
 (fn [db [_ response]]
   (let [games (get-in response [:data :games])]
     (assoc db :games games))))
