(ns lacinia-reagent.client
  (:require [lacinia-reagent.client.core :as core]
            [goog.events :as events]))

(events/listen js/window "load" #(when-not @core/system (core/start)))
