(ns lacinia-reagent.client.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::attrs
 (fn [db]
   (:attrs db)))

(re-frame/reg-sub
 ::games
 (fn [db]
   (:games db)))
