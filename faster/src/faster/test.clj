(ns faster.test)

(defn x []
  (println "no args"))
(defn a [a]
  (println a))
(defn b [a b ]
  (println a b))
(defn c [a b c]
  (println a b c))

(defn test
  ([]
   (println "No arguments"))
  ([x]
   (println "One args arguments -" x))
  ([x y]
   (println "two arguments - " x y))
  ([x y z]
   (println "three arguments -" x y z)
   ))
(defn test2 [& args]
  (condp = (count args)
    0 (println "No arguments")
    1 (println "One argument -")
    2 (println "two arguments ")
    3 (println "three arguments")))