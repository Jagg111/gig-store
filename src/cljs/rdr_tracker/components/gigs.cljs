(ns rdr-tracker.components.gigs
  (:require [rdr-tracker.state :as gigstate]))

(defn gigs []
  [:main
   [:div.gigs
    (for [{:keys [id img title price desc]} (vals @gigstate/gigs)]
      [:div.gig {:key id}
       [:img.gig__artwork {:src img :alt title}]
       [:div.gig__body
        [:div.gig__title
         [:div.btn.btn--primary.float--right.tooltip {:data-tooltip "Blah"}
          [:i.icon.icon--plus]] title]
        [:p.gig__price price]
        [:p.gig__desc desc]]])]])