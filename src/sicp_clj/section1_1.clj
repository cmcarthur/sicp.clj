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


;; # Exercise 1.6

(defn abs [x]
  (if (>= x 0)
      x
      (- x)))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn new-if [pred then else]
  (print pred "\n")
  (cond pred then
        :else else))

(defn sqrt-iter [guess x ct]
  (print ct "\n")
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x (inc ct))))

(new-if (= 2 3) 0 5)

(sqrt-iter 1.0 4 1)

;; new-if evaluates (sqrt-iter ...) ahead of time -- leading to infinite recursion. like we
;; saw in Exercise 1.5, applicative order evaluation leads to infinite recursion.

;; if, on the other hand, has to use normal order evaluation -- it's a special form so it doesn't
;; evaluate the recursive (sqrt-iter ...) calls until after it branches.


;; # Exercise 1.7

;; good-enough won't be very good for small numbers, for example....

;; sqrt(0.0004) === 0.02

(sqrt-iter 1.0 0.0004 1) ; => 0.6548...

;; or for very large numbers, for example...

;; sqrt(1E10) === 1E5

;; goes through 22 iterations

(sqrt-iter 1.0 (square 100000) 1)

;; a better definition might be:

(defn percent-diff [initial observed]
  (if (= 0 initial)
      1
      (/ (- observed initial) initial)))

(defn good-enough-improved? [guess last-guess x]
  (< (abs (percent-diff last-guess guess)) 0.1))

(defn sqrt-iter-improved [guess last-guess x ct]
  (print ct ":" guess "\n")
  (if (good-enough-improved? guess last-guess x)
      guess
      (sqrt-iter-improved (improve guess x) guess x (inc ct))))

(sqrt-iter-improved 1.0 0 0.0004 1) ; => 0.2000... after 10 iterations

(sqrt-iter-improved 1.0 0 (square 100000) 1) ; => 100005... after 20 iterations

;; works better for small numbers, fewer iterations for large ones

;; from http://community.schemewiki.org/?sicp-ex-1.7:
;; there is a similar, but slightly more clever way to get the same effect without the coupling.

(defn good-enough-best? [guess x]
  (< (abs (percent-diff guess (improve guess x))) 0.1))

;; this checks that the percent difference is < 10% (tunable) but doesn't require the
;; extra last-guess argument.


;; # Exercise 1.8

(defn cube [x]
  (* x x x))

(defn improve-cube [guess x]
  (/ (+ (* 2 guess) (/ x (square guess))) 3))

(defn good-enough-cube? [guess x]
  (< (abs (percent-diff guess (improve-cube guess x))) 0.01))

(defn cbrt-iter [guess x]
  (if (good-enough-cube? guess x)
      guess
      (cbrt-iter (improve-cube guess x) x)))

(cbrt-iter 1.0 27) ;; => 3.00...
