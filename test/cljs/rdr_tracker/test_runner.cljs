(ns rdr-tracker.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [rdr-tracker.core-test]
   [rdr-tracker.common-test]))

(enable-console-print!)

(doo-tests 'rdr-tracker.core-test
           'rdr-tracker.common-test)
