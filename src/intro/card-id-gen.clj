(ns card-id-gen.core)

(use 'clojure.stacktrace)

(import 'java.security.NoSuchAlgorithmException)

(import 'java.security.SecureRandom)

(import 'java.lang.Math)

(import 'java.lang.Integer)

(import 'java.lang.Character)

(def CHARS "0123456789BCDFGHJKLMNPQRSTVWXZ")

(def ALPHA "BCDFGHJKLMNPQRSTVWXZ")

(def NUMBERS "0123456789")

(def VOWELS "AEIOUY")

(def secureRandom (try

                    (SecureRandom/getInstance "SHA1PRNG")

                    (catch NoSuchAlgorithmException e
                      (throw (RuntimeException. "error initializing" e)))))

(defn getNextRandomString [length maxAdjacentAlphas]

  (let [builder (StringBuilder.)]

    (try
      ;Generate a String of the desired length by randomly selecting characters from CHAR
      ;and inserting them at random indexes after starting with a random char.
      (let [num (Math/abs (.nextInt secureRandom))
            retValue (atom (Integer/toString num 36))]

        (while (< (.length @retValue) length)

          (let [pos (.nextInt secureRandom (.length @retValue))
                character (.charAt CHARS
                                   (.nextInt secureRandom
                                             (.length CHARS)))
                pre (.substring @retValue 0 pos)
                post (.substring @retValue pos)]

            (swap! retValue (constantly (str pre character post)))))

        ;Builds a new String out of the existing characters while ensuring that there
        ;are no more successive alphabetic characters than specified in the params.
        (let [charsSinceLastDigit (atom 0)]

          (doseq [i (range 0 (.length @retValue))]

            (let [curr (atom (.charAt @retValue i))]

              (println "curr" @curr)

              (if (Character/isDigit @curr)

                (swap! charsSinceLastDigit (constantly 0))

                (do
                  (swap! charsSinceLastDigit inc)

                  (cond
                   (> @charsSinceLastDigit maxAdjacentAlphas)
                   (swap! curr
                          (constantly (.charAt NUMBERS (.nextInt secureRandom 9))))

                   (< (. VOWELS (int @curr)) 0)
                   (swap! curr
                          (constantly (.charAt ALPHA (.nextInt secureRandom (.length ALPHA))))))))

              (.append builder @curr)))))
      (catch RuntimeException e (print-stack-trace e)))

    (.toUpperCase (.toString builder))))



(getNextRandomString 100 5)

;A better implementation

;Numbers and letters sans \O and \I
(def allowedChars "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ")

(defn randomChar [] (get allowedChars (rand-int 34)))

(defn randomDigit [] (get allowedChars (rand-int 10)))

(defn addNumber
  "Returns the given sequence with the first letter replaced with a random digit."
  [s]
  (cons (randomDigit) (rest s)))

(defn breakSequentialLetters
  "Takes a list assumed to be full of letters
  and breaks it up into lists of the size requested by params.
  It then ensures each list of letters contains a digit."
  [maxAdjacentAlphas c]
  (if (> (count c) maxAdjacentAlphas)
    (mapcat addNumber (partition-all (inc maxAdjacentAlphas) c))
    c))

(defn removeAdjacentAlphas
  "Ensures the given seq does not contain more adjacent alphas than the param requests.
  Returns a lazy seq."
  [maxAdjacentAlphas c]
  (->>
   (partition-by #(Character/isLetter %) c)
   (mapcat #(if (Character/isLetter (first %))
              (breakSequentialLetters maxAdjacentAlphas %)
              %))))

(defn randomChars
  "Returns a lazy seq pf random chars"
  [] (repeatedly randomChar))

(defn randomCharsWithMaxAdjacentAlphas [n]
  (removeAdjacentAlphas n (randomString)))

(apply str (take 8 (randomStringWithMaxAdjacentAlphas 3)))

