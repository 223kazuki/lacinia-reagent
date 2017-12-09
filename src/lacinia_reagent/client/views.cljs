(ns lacinia-reagent.client.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [lacinia-reagent.client.subs :as subs]
            [lacinia-reagent.client.events :as events]))

(defn attr-checkbox
  [k]
  (let [attrs (re-frame/subscribe [::subs/attrs])]
    [:label
     [:input {:type "checkbox" :id k
              :checked (if (k @attrs) "checked" "")
              :on-change #(re-frame/dispatch [::events/fetch-board-games
                                              (if (k @attrs)
                                                (set (remove #{k} @attrs))
                                                (conj @attrs k))])}]
     (name k)]))

(defn main-panel []
  (let [attrs (re-frame/subscribe [::subs/attrs])
        games (re-frame/subscribe [::subs/games])]
    (reagent/create-class
     {:component-will-mount
      #(re-frame/dispatch [::events/fetch-board-games @attrs])
      :reagent-render
      (fn []
        [:div
         [:h1 "Games"]
         [:div
          (attr-checkbox :name)
          (attr-checkbox :summary)
          (attr-checkbox :description)
          (attr-checkbox :min_players)
          (attr-checkbox :max_players)
          (attr-checkbox :play_time)]
         [:br]
         [:table.pure-table
          [:thead
           [:tr
            (doall
             (for [attr @attrs]
               [:th {:key attr} (name attr)]))]]
          [:tbody
           (doall
            (for [g @games]
              [:tr {:key (:id g)}
               (for [attr @attrs]
                 [:td {:key attr} (attr g)])]))]]])})))
