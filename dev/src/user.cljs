(ns cljs.user
  (:require [lacinia-reagent.client.core :as core]))

(enable-console-print!)
(println "dev mode")

(when @core/system
  (core/stop))
(core/start)
