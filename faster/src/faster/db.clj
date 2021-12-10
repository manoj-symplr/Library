(ns faster.db
  (:require [monger.core :as mg]
            [monger.collection :as mgc]
            [monger.operators]
            )
  (:import (org.bson.types ObjectId))
  (:gen-class)
  ;(:refer-clojure :exclude [find remove count drop distinct empty? any? update])
  )

(def db-connection-uri "mongodb://localhost:27017/Library")

(def db (-> db-connection-uri
            mg/connect-via-uri
            :db))
(def books-coll "books")

(defn create-book [na sts prc]
  ;  (try
  ;(mc/insert db books-coll book)
  (mgc/insert db books-coll
             {
              ;:book_id (rand-int 1000)
              :name   na
              :status sts
              :price  prc})
  ;  )
  ;(catch Exception err (str "Excception occur :" (.getMessage err)))
  )

(defn get-books []
  (mgc/find-maps db books-coll))


(defn get-book-by-id [book-id]
  (mgc/find-map-by-id db books-coll (ObjectId. book-id)))
;
(defn update-book [book-id name status price]
  (mgc/update-by-id db books-coll (ObjectId. book-id)
                    {:name name
                     :status status
                     :price price}))
;
(defn delete-book-by-id [book-id]
  (mgc/remove-by-id db books-coll (ObjectId. book-id)))

