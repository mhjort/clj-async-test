(ns clj-async-test.core-test
  (:require [clojure.test :refer :all]
            [clj-async-test.core :refer :all]))

(deftest eventually-function
  (testing "Pass immediately"
    (is (eventually (= 1 1)))
  (testing "Pass after few retries"
    (let [value (atom 0)]
      (future (Thread/sleep 200) (reset! value 1))
      (is (eventually (= @value 1)))))))
