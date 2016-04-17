# clj-async-test
clojure.test assertions for testing asynchronous code

## Installation

Add the following to your `project.clj` `:dependencies`:

```clojure
[clj-async-test "0.0.4"]
```

## Usage

Following test pass immediately if predicate '(= actual expected) is true.
Otherwise test runner tries it multiple times and fails if it does not pass in one second.

```clojure

(require '[clj-async-test :refer :all])

(is (eventually (= actual expected)))
```

Sometimes when testing asynchronous code it is hard to test with exact numbers.
It's enough when something is approximately right. Following test pass if difference
between actual and expected is less than 1%.

```clojure

(require '[clj-async-test :refer :all])

(is (approximately== actual expected))
```

You can also set the desired accuracy.

```clojure
(is (approximately== actual expected :accuracy 5))
```

## License

Copyright (C) 2015-2016 Markus Hjort

Distributed under the Eclipse Public License, the same as Clojure.
