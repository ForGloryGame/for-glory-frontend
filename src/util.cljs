(ns util)

(defn keyname [key] (str (namespace key) "/" (name key)))
