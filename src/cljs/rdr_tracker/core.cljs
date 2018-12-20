(ns rdr-tracker.core
  (:require [reagent.core :as reagent :refer [atom]]
    ;;[reagent.core :as r]
            [rdr-tracker.components.header :refer [header]]
            [rdr-tracker.components.gigs :refer [gigs]]
            [rdr-tracker.components.orders :refer [orders]]
            [rdr-tracker.components.footer :refer [footer]]))

(enable-console-print!)

(defn app []
  [:div.container
   [header]
   [gigs]
   [orders]
   [footer]])


(defn render []
  (reagent/render [app] (js/document.getElementById "app")))
