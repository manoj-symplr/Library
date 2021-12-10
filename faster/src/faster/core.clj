(ns faster.core
  (:require [faster.db :as db]
            [clojure.string :as str]))

(def book (atom {:id "" :nm "name" :sta "available":prc "233"}))

(defn base []
  (println "Welcome to Book Shop !!")
  (println "--------------------")
  (println "a. To add new Book")
  (println "b. To Load all Books")
  (println "c. To Get Book")
  (println "d. To remove book")
  (println "e. To update book")
  (println "exit. To exit")
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
    #_(println bookarray)
    (doseq [b bookarray]
      (println "Book Id :" (get b :_id))
      (println "Book Name :" (get b :name))
      (println "Book Status :" (get b :status))
      (println "Book Price :" (get b :price)))))

(defn get-book []
  (println "Enter Id of Book")
  (let  [bk (db/get-book-by-id (read-line))]
    (println "Book Id :" (get bk :_id))
    (println "Book Name :" (get bk :name))
    (println "Book Status :" (get bk :status))
    (println "Book Price :" (get bk :price))))

(defn remove-books []
   (println "Enter Id of Book")

   (let [bid (read-line)]
     (do
       (db/delete-book-by-id bid )
       (println "book id " bid "removed"))))
(defn update-book []
  (println "Enter Name of BookId")
  (swap! book assoc :id (read-line))
  (println "Enter Name of Book")
  (swap! book assoc :nm (read-line))
  (println "Enter Status of Book")
  (swap! book assoc :sta (read-line))
  (println "Enter price of Book")
  (swap! book assoc :prc (read-line))
  ;(println (get @book :nm))
  (println (db/update-book (get @book :id)(get @book :nm) (get @book :sta) (get @book :prc))))

(def my-atom (atom 0))

(defn -main [& args]

  (while (= @my-atom 0)
    (base)
    (let [opt (read-line)]
      (cond
      (= opt "a") (add-book)
      (= opt "b") (get-books)
      (= opt "c") (get-book)
      (= opt "d") (remove-books)
      (= opt "e") (update-book)
      (= opt "exit") (swap! my-atom inc)
      :else (println "Please enter valid option"))))
  )







;(base)
;(let [opt (read-line)]
;  (cond
;    (= opt "a") (add-book)
;    (= opt "b") (get-books)
;    (= opt "c") (get-book)
;    (= opt "d") (remove-books)
;    (= opt "e") (update-book)
;    :else "Please enter valid option"))