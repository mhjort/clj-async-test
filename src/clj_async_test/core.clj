(ns clj-async-test.core
  (:require [clojure.test :refer [do-report assert-expr]])
  (:import [java.lang Math]))

(defmethod assert-expr 'approximately== [_ form]
  (let [[_ actual expected & {:keys [accuracy]
                              :or {accuracy 1}}] form
        diff (gensym 'diff)]
    `(let [~diff (Math/abs (- ~expected ~actual))]
       (do-report {:type (if (< (/ ~diff ~expected) (/ ~accuracy 100))
                           :pass
                           :fail)
                   :expected ~expected
                   :actual ~actual
                   :message (str "Not approximately equal (max " accuracy "% difference)")}))))

(defmethod assert-expr 'eventually [_ form-with-keyword]
  "Asserts that given predicate is eventually true.
   It will try predicate multiple times with 50 ms delay between retries.
   After one second it will return failure in same way normal clojure.test assertion does."
 (let [form (second form-with-keyword)
       args (rest form)
       pred (first form)]
   `(let [[success?# last-values#]
      (loop [time-elapsed# 0]
        (let [values# (list ~@args)
              result# (apply ~pred values#)]
          (if result#
            [true values#]
            (if (< time-elapsed# 1000)
              (do (Thread/sleep 50) (recur (+ 50 time-elapsed#)))
              [false values#]))))]
      (if success?#
        (do-report {:type :pass
                    :message "Predicate passed"
                    :expected '~form
                    :actual (cons ~pred last-values#)})
        (do-report {:type :fail
                    :message "Predicate failed after trying multiple times within 1000 ms."
                    :expected '~form,
                    :actual (list '~'not (cons '~pred last-values#))})))))
