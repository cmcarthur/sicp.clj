(ns sicp-clj.section1-1)

;; # Exercise 1.1

10 ; => 10

(+ 5 3 4) ; => 12

(- 9 1) ; => 8

(/ 6 2) ; => 3

(+ (* 2 4) (- 4 6)) ; => 6

(def a 3) ; => #'sicp-clj.section1-1/a
a ; => 3

(def b (+ a 1)) ; => #'sicp-clj.section1-1/b
b ; => 4

(+ a b (* a b)) ; => 19

(= a b) ; => false

(if (and (> b a) (< b (* a b)))
    b
    a) ; => 4

(cond (= a 4) 6
      (= b 4) (+ 6 7 a)
      :else 25) ; => 16

(+ 2 (if (> b a) b a)) ; => 6

(* (cond (> a b) a
         (< a b) b
         :else -1)
   (+ a 1)) ; => 16


;; # Exercise 1.2

(/ (+ 4 5 
      (- 2 
         (- 3 
            (+ 6
               (/ 4 5)))))
   (* 3 (- 6 2) (- 2 7))) ; => -37/150


;; # Exercise 1.3

(defn square [x]
  (* x x))

(square 2) ; => 4

(defn sum-squares [a b]
  (+ (square a) (square b)))

(sum-squares 2 3) ; => 13

(defn sum-larger-squares [a b c]
  (cond (and (> a c) (> b c)) (sum-squares a b)
        (and (> a b) (> c b)) (sum-squares a c)
        :else (sum-squares b c)))

(sum-larger-squares 1 2 3) ; => 13
(sum-larger-squares 2 3 1) ; => 13
(sum-larger-squares 3 1 2) ; => 13


;; # Exercise 1.4

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

;; If b is greater than 0, we will use the + operator on a and b:

(a-plus-abs-b 2 3) ; => 5

;; If b is less than or equal to 0, we'll subtract b from a (ordering is important)

(a-plus-abs-b 2 -3) ; => 5

;; As the function name suggests, this adds a to the absolute value of b.


;; # Exercise 1.5

(defn p [] p)

(defn test [x y]
  (if (= x 0)
      0
      y))

(test 0 (p))

;; Taken from: http://community.schemewiki.org/?sicp-ex-1.5

;; applicative order (substitution): (p) infinitely expands to itself, leading to infinite recursion

(test 0 (p))
...
(test 0 (p))
...
(test 0 (p))

;; normal order: exaluates step-by-step to 0

(test 0 (p))
...
(if (= 0 0) 0 (p))
...
(if true 0 (p))
...
0

