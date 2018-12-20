(ns rdr-tracker.components.gigs
  (:require [rdr-tracker.state :as state]
            [rdr-tracker.helpers :refer [format-price]]))

(defn gigs []
  (let [add-to-order #(swap! state/orders update % inc)]
    [:main
     [:div.gigs
      (for [{:keys [id img title price desc]} (vals @state/gigs)]
        [:div.gig {:key id}
         [:img.gig__artwork {:src img :alt title}]
         [:div.gig__body
          [:div.gig__title
           [:div.btn.btn--primary.float--right.tooltip
            {:data-tooltip "Add to cart"
             ;; This is still confusing to me. I get that without a function wrapper
             ;; each gig gets set to 1 on render, but I don't get why the onclick
             ;; doesn't work or change the state of the atom.
             ;; Maybe it's because react detects a state change and re-renders
             ;; therefore setting it back to one?
             :on-click #(add-to-order id)}
            [:i.icon.icon--plus]] title]
          [:p.gig__price (format-price price)]
          [:p.gig__desc desc]]])]]))