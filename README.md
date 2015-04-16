# clj-async-test
clojure.test assertions for testing asynchronous code

## Installation

Add the following to your `project.clj` `:dependencies`:

```clojure
[clj-async-test "0.0.1"]
```

## Usage

Following test pass immediately if predicate '(= actual expected) is true.
Otherwise test runner tries it multiple times and fails if it does not pass in one second.

```clojure

(require '[clj-async-test :refer :all])

(is (eventually (= actual expected)))
```

## License

Copyright (C) 2015 Markus Hjort

Distributed under the Eclipse Public License, the same as Clojure.
