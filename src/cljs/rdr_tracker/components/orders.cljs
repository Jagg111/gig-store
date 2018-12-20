(ns rdr-tracker.components.orders
  (:require [rdr-tracker.state :as state]))

(defn total []
  ;; Use a thread last macro to calculate the total
  (->> @state/orders ;; Get the orders
       ;; Now use a map to iterate across the orders and figure out the price per order
       (map (fn [[id quant]] (* quant (get-in @state/gigs [id :price]))))
       ;; Now use reduce to sum up the results in the map
       (reduce +))
  ;; Another variation that is more difficult to read.
  ;;(reduce + (map (fn [[id quant]] (* quant (get-in @state/gigs [id :price]))) @state/orders))

  )

(defn orders []
  [:aside
   [:div.order
    [:div.gig__body (for [[id quant] @state/orders]
                      [:dev.item {:key id}
                       [:div.img
                        [:img {:src (get-in @state/gigs [id :img])
                               :alt (get-in @state/gigs [id :title])}]]
                       [:div.content
                        [:p.title (str (get-in @state/gigs [id :title]) " \u00D7 " quant)]]
                       [:div.action
                        [:div.price (* (get-in @state/gigs [id :price]) quant)]
                        [:button.btn.btn--link.tooltip
                         {:data-tooltip "Remove"
                          :on-click (fn [] (swap! state/orders dissoc id))}
                         [:i.icon.icon--cross]]]])]
    [:div.total
     [:hr]
     [:div.item
      [:div.content "Total"]
      [:div.action
       [:div.price (total)]]]]]])