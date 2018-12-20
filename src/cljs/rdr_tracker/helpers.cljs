(ns rdr-tracker.helpers)

(defn format-price [cents]
  (str " $" (/ cents 100)))