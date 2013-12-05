(ns alephing.view
  (:require [quil.core :refer :all]))

(def ^:private colors [[16 143 151]
                       [255 139 107]
                       [255 227 159]
                       [22 134 109]
                       [16 54 54]])

(defn- setup []
  (smooth)
  (frame-rate 30)
  (background 0))

(def pongs (ref []))

(defn- draw []
  (background 0)
  (doseq [p @pongs]
    (apply stroke (:color p))
    (stroke-weight 3)
    (no-fill)
    (ellipse (:x p) (:y p) (:size p) (:size p)))
  (dosync
   (alter pongs
          #(filter (fn [p]
                     (> 1000 (:size p)))
                   (map (fn [p]
                          (assoc p :size (+ 3 (:size p)))) %)))))

(defn- on-mouse-clicked [ping]
  (let [x (mouse-x)
        y (mouse-y)]
    (ping {:x x :y y})))

(defn show! [ping]
  (sketch :setup setup
          :draw draw
          :mouse-clicked #(on-mouse-clicked ping)
          :title "Alephing"
          :size [500 500]))

(defn pong [e]
  (println "pong with" e)
  (dosync
   (alter pongs conj (assoc e
                       :size 0
                       :color (nth colors (rand-int (count colors)))))))
