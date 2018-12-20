(ns rdr-tracker.components.orders
  (:require [rdr-tracker.state :as state]
            [rdr-tracker.helpers :refer [format-price]]))

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
  (let [remove-from-order #(swap! state/orders dissoc %)
        remove-all-orders #(reset! state/orders {})]
    [:aside
     (if (empty? @state/orders)
       [:div.empty
        [:div.title "You don't have any orders"]
        [:div.subtitle "Click on a + to add an order"]]
       [:div.order
        [:div.gig__body (for [[id quant] @state/orders]
                          [:dev.item {:key id}
                           [:div.img
                            [:img {:src (get-in @state/gigs [id :img])
                                   :alt (get-in @state/gigs [id :title])}]]
                           [:div.content
                            [:p.title (str (get-in @state/gigs [id :title]) " \u00D7 " quant)]]
                           [:div.action
                            [:div.price (format-price (* (get-in @state/gigs [id :price]) quant))]
                            [:button.btn.btn--link.tooltip
                             {:data-tooltip "Remove"
                              :on-click #(remove-from-order id)}
                             [:i.icon.icon--cross]]]])
        [:div.total
         [:hr]
         [:div.item
          [:div.content "Total"]
          [:div.action
           [:div.price (format-price (total))]]
          [:button.btn.btn--link.tooltip
           {:data-tooltip "Remove all"
            :on-click #(remove-all-orders)}
           [:i.icon.icon--delete]]]]]])]))