(reduce +
  (filter #(or (= (mod %1 3) 0)
	       (= (mod %1 5) 0))
	  (range 1 1000)))

(defn add [x y] (+ x y))

(def add2 #(+ %1 %2))

(add2 7 8)