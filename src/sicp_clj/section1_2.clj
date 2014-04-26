(ns sicp-clj.section1-2)

;; # Exercise 1.9

(defn plus-recursive [a b]
  (if (= a 0)
      b
      (inc (plus-recursive (dec a) b))))

(defn plus-iterative [a b]
  (if (= a 0)
      b
      (plus-iterative (dec a) (inc b))))

;; plus-recursive is a recursive process:

(plus-recursive 3 3)
..
(inc (plus-recursive 2 3))
..
(inc (inc (plus-recursive 1 3))
..
(inc (inc (inc (plus-recursive 0 3))))
..
6

;; plus-iterative is an iterative proces:

(plus-iterative 3 3)
..
(plus-iterative 2 4)
..
(plus-iterative 1 5)
..
(plus-iterative 0 6)
..
6


;; # Exercise 1.10

(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1) (A x (- y 1)))))

(A 1 10) ; => 1024

(A 2 4) ; => 65536

(A 3 3) ; => 65536

(defn A-f [n]
  (A 0 n))

;; Based on my reading, I believe A-f will always return 2 * n.

(A-f 1) ; => 2

(A-f 2) ; => 4

(A-f 3) ; => 6

(defn A-g [n]
  (A 1 n))

;; A-g should return 2 ^ n

(A-g 1) ; => 2

(A-g 2) ; => 4

(A-g 3) ; => 8

(A-g 4) ; => 16

(defn A-h [n]
  (A 2 n))

;; for n=1 => 2, for n=2 => 2*2, for n>1, 2^(repeat n^a for a in n^2)

;; for example:

;; n = 1  => 2
;; n = 2  => 2*2
;; n = 3  => 2^(2^2^2^2)
;; n = 4  => 2^(2^2^2^2... (16 2s))
;; n = 5  => 2^(2^2^2^2... (256 2s))
;; n = 6  => 2^(2^2^2^2... (65536 2s))

(A-h 1) ; => 2

(A-h 2) ; => 4

(A-h 3) ; => 16

(A-h 4) ; => 65536

;; in general, it looks like Ackermann's formula returns:
;;
;; for y=1 => 2
;; for y=2 => 4
;; for y>2 => 2^(powers of two equal to the value of y^n)
