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

(deftest approximately==-function
  (testing "Pass if equals exactly"
    (is (approximately== 1 1))
  (testing "Pass if difference is less than 1%"
    (is (approximately== 101 102)))))
