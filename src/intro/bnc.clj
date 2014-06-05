(ns intro.bnc
  (:use overtone.core))

(connect-external-server "10.190.9.19" 57110)

; (boot-internal-server)

;Boot n Cat

(def boot (sample "C:/music/boot.wav"))

(def cat (sample "cat.wav"))

(def bees (sample "bees.wav"))

(def kneehigh (sample "kneehigh.wav"))


(def metro (metronome 50))

(defn player [beat]
  (at (metro beat) (boot))
  (at (metro (+ 0.5 beat)) (cat))
  (apply-at (metro (inc beat)) #'player (inc beat) []))

(def soundJob (player (metro)))


;Boots n Cats


(def boots (sample "boots.wav"))

(boots)

(defn loud-boots [] (boots :amp 2))

(loud-boots)

(def cats (sample "cats.wav"))

(cats)

(defn loud-cats [] (cats :amp 2))

(loud-cats)


(metro :bpm 60)

(defn player [beat]
  (at (metro beat) (loud-boots))
  (at (metro (+ 0.5 beat)) (loud-cats))
  (apply-at (metro (inc beat)) #'player (inc beat) []))

(player (metro))

;Kneehigh boots

(def kneehigh (sample "kneehigh.wav"))


(defn bring-the-knees [beat]
  (stop)
  (kneehigh)
  (apply-at (metro beat) #'player beat []))

(bring-the-knees (metro))

(def leather-boots (sample "leatherboots.wav"))

(leather-boots)

(defn player [beat]
  (at (metro beat) (leather-boots))
  (apply-at (metro (+ 0.5 beat)) #'player (+ 0.5 beat) []))

(defn bring-the-leather [beat]
  (stop)
  (at (metro beat) (leather-boots))
  (at (metro (+ 0.5 beat)) (leather-boots))
  (at (metro (+ 1.0 beat)) (leather-boots))
  (at (metro (+ 1.5 beat)) (leather-boots))
  (apply-at (metro (+ 2.0 beat)) #'player (+ 2.0 beat) []))

(bring-the-leather (metro))

(def bees (sample "bees.wav"))

(defn beeeeeeeeeeees [beat]
  (stop)
  (bees))

(beeeeeeeeeeees (metro))
