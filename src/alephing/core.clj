(ns alephing.core
  (:require [clojure.java.io :as io]
            [lamina.core :refer :all]
            [aleph.http :refer :all]
            [alephing.view :refer [show! pong]]))

(def ^:private config-filename "alephing.config.clj")

(defn server-uri
  []
  (let [rsrc (io/resource config-filename)
        c (if (nil? rsrc)
            (throw (RuntimeException. (str "Configuration file not found: " config-filename)))
            (read-string (slurp rsrc)))]
    (:server-uri c)))

(defn- handler [msg]
  (pong (read-string msg)))

(defn connect []
  (let [c (websocket-client {:url (server-uri)})
        ch (wait-for-result c)]
    (receive-all ch handler)
    {:client c
     :channel ch}))

(defn ping [c data]
  (enqueue (:channel c) (pr-str data)))

(defn -main []
  (let [c (connect)]
    (show! #(ping c %))))
