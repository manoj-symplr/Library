(ns faster.core
  (:require [faster.db :as db]
            [clojure.string :as str]))

(def book (atom {:nm "name" :sta "available":prc "233"}))
(defn base []
  (println "Welcome to Book Shop !!")
  (println "--------------------")
  (println "a. To add new Book")
  (println "b. To Load all Books")
  (println "c. To Get Book")
  (println "d. To remove book")
  (println "--------------------")
  (println "Enter the Option :"))

(defn add-book []
  (println "Enter Name of Book")
  (swap! book assoc :nm (read-line))
  (println "Enter Status of Book")
  (swap! book assoc :sta (read-line))
  (println "Enter price of Book")
  (swap! book assoc :prc (read-line))
  ;(println (get @book :nm))
  (println (db/create-book (get @book :nm) (get @book :sta) (get @book :prc)))

  )
(defn get-books []
  (let [bookarray  (db/get-books)]
    (doseq [b bookarray]
      (println "Book Id :" (get b :_id))
      (println "Book Name :" (get b :name))
      (println "Book Status :" (get b :status))
      (println "Book Price :" (get b :price))))
  )

(defn get-book []
  (println "Enter Id of Book")
  (let  [bk (db/get-book-by-id (read-line))]
    (println "Book Id :" (get bk :_id))
    (println "Book Name :" (get bk :name))
    (println "Book Status :" (get bk :status))
    (println "Book Price :" (get bk :price))))

(defn remove-books []
   (println "Enter Id of Book")
  (println (db/delete-book-by-id (read-line) )))

(defn -main [& args]
  (base)
  (let [opt (read-line)]
    (cond
      (= opt "a") (add-book)
      (= opt "b") (get-books)
      (= opt "c") (get-book)
      (= opt "d") (remove-books)
      :else "Please enter valid option")))