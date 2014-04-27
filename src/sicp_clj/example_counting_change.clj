(ns sicp-clj.example-counting-change)

(defn get-coins []
  [25 10 5 1])

(defn get-combinations
  ([cents]
     (get-combinations cents (get-coins)))
  ([cents coins]
     (cond (= cents 0) 1
           (< cents 0) 0
           (= (count coins) 0) 0
           :else (+ (get-combinations cents (rest coins))
                    (get-combinations (- cents (first coins)) coins)))))

(get-combinations 100) ; => 242

;; with half-dollars

(get-combinations 100 [50 25 10 5 1]) ; => 292
