;This is a valid clojure file. Semicolons = //
(ns intro.core)

; _____  _       _                                  _   _                 _____
;/  __ \| |     (_)                             _  | | | |               |_   _|
;| /  \/| | ___  _ _   _ _ __ ___     ___  _ __(_) | |_| | _____      __   | |
;| |   || |/ _ \| | | | | '__/ _ \   / _ \| '__|   |  _  |/ _ \ \ /\ / /   | |
;| \__/\| | (_) | | |_| | | |  __/  | (_) | |   _  | | | | (_) \ V  V /   _| |_
; \____/|_|\___/| |\__,_|_|  \___|   \___/|_|  (_) \_| |_/\___/ \_/\_/    \___/
;             _/ |
;            |__/
; _                                    _   _          _____ _                _    _                       _
;| |                                  | | | |        /  ___| |              | |  | |                     (_)
;| |     ___  __ _ _ __ _ __   ___  __| | | |_ ___   \ `--.| |_ ___  _ __   | |  | | ___  _ __ _ __ _   _ _ _ __   __ _
;| |    / _ \/ _` | '__| '_ \ / _ \/ _` | | __/ _ \   `--. \ __/ _ \| '_ \  | |/\| |/ _ \| '__| '__| | | | | '_ \ / _` |
;| |___|  __/ (_| | |  | | | |  __/ (_| | | || (_) | /\__/ / || (_) | |_) | \  /\  / (_) | |  | |  | |_| | | | | | (_| |
;\_____/\___|\__,_|_|  |_| |_|\___|\__,_|  \__\___/  \____/ \__\___/| .__/   \/  \/ \___/|_|  |_|   \__, |_|_| |_|\__, |
;                                                                   | |                              __/ |         __/ |
;                                                                   |_|                             |___/         |___/
;                 _   _                      _   _           ______
;                | | | |                    | | | |          | ___ \
;  __ _ _ __   __| | | |     _____   _____  | |_| |__   ___  | |_/ /_ _ _ __ ___ _ __  ___
; / _` | '_ \ / _` | | |    / _ \ \ / / _ \ | __| '_ \ / _ \ |  __/ _` | '__/ _ \ '_ \/ __|
;| (_| | | | | (_| | | |___| (_) \ V /  __/ | |_| | | |  __/ | | | (_| | | |  __/ | | \__ \
; \__,_|_| |_|\__,_| \_____/\___/ \_/ \___|  \__|_| |_|\___| \_|  \__,_|_|  \___|_| |_|___/

;Clojure!
;It's a Lisp.

;Its a functional language!
;That you can program imperatively.

;Its immutable!
;Except when its not.

;It demphasises state!
;Unless you want to use state.

;Its emphasises pure functions with no side effects!
;While providing very easy constructs specifically for generating side effects.

;Its a scripting language that can be run in a REPL!
;That can be fully compiled to JVM byte code.


;Well, now that we have defined the notable language attributes let's move on.


;The two things Lisp developers don't want you to know!

;The first tip requires you to learn Data Structures! ='(

;List
'(1, 2, 3)

;Also a list. Lists can be delimeted with commas or whitespace.
'(1 2 3)

;Not a list. Meow.
;      \    /\
;       )  ( ')
;      (  /  )
;       \(__)|

;Another list, that contains a function, a String, and another String
'(println "Hello" "World!")

;Wait! Thats not all!

;Tip Number Two!
;What do you mean you didnt get tip one? Are you sure you're in the right place?

;When a list is evaluated by the evaluator without a preceeding quote it attempts to evaluate the first argument as a function(or a macro/special form)
(println "Hello" "World!") ;Believe me this prints Hello World. Do I look like the kind of guy who would lie?

;Lisp is HOMOICONIC(This does not mean it is a gay unicorn. You dummy. Why would anyone think that?)
;It means Lisp is written in Lisp. To be exact "In a homoiconic language the primary representation of programs are also a data structure in a primitive type of the language itself" (Wikipedia)

;So, lets review
;1. Lisp code is written in Lists
;2. The first argument is evaluated as a function.

;Congratulations! You now know Lisp!!
;You may now hold your nose in disgust as the plebians pass you by.

;For anyone who didnt leave we can cover some more details!

;Prefix notation!
(println "Hello" "World!")

;Mostly equivalent to the following java code:
; System.out.println("Hello" + " " + "World!");

;Make sure you note the number of parenthesis was identical.

(+ 1 2 3 4 5) ;1 + 2 + 3 + 4 + 5

(< 1 2 3 4 5) ;Checks if the elements are in order from least to greatest.

;It takes getting used to, but deal with it, cause its awesome.
;Why is it awesome? Special forms are always consistent in the system
(if true "true" "false")

(try
  (throw (RuntimeException. "Boom!"))
  (catch RuntimeException e (println e)))

;anonymous function
(fn [x] (println x))

;foreach
(doseq [x (range 1 10)] (println x))


;Since everything is consistent its trivial to add new Special Forms via Macros
;Its very common for libraries to provide them to simplify interacting with the lib.
;Consider how awesome java would be if the libraries could add new syntax.


;Boooring. Lets do something fun.

;Well maybe not just yet. Just because you can parse lisp doesnt mean you are familiar with clojure.

;The three big workhorses of Lisp.
;Map, Reduce, Filter

;Oh, I forgot to explain anonymous function syntax.
;All the following are equivelent
(fn [x y] (+ x y))

#(+ %1 %2)

#(+ % %2) ;This is the form usually preferred.

;Map.
;Map is used to apply transformations to collections by applying the given function to each element in the collection.
(map inc [1 2 3 4]) ;(2 3 4 5)

;Lets compare this to a java equivalent
;
;int[] nums = {1, 2, 3, 4};
;for(int x=0; x < nums.length; x++)
;  nums[x]++;

;Its often used with anonymous functions, although this is unecessary in simple transformations.
(map #(inc (* % 2)) [1 2 3 4]);(3 5 7 9)

;Filter.
;Filters a collection to contain records that are accepted by the provided predicate
(filter even? [1 2 3 4 5 6]) ;(2 4 6)

;Filter list to contain multiples of 3
(filter #(zero? (mod % 3)) '(1 2 3 4 5 6 7 8 9));(3 6 9)

;Reduce.
;Reduce is different than the other functions. Its purpose is to "reduce" all the values of a collection to a single value.

;Adds all the values in the list together.
(reduce + '(1 2 3 4 5));15

;Reduce takes a function that must accept two params and return a single value.
;The first param is the result of the previous application of the function while the second value is the next value from the list.
(reduce #(+ %1 %2) '(1 2 3 4 5))

;It doesnt always aggregate things it can be used to select a single value from the list.
;Although it should only be used in cases where it does not want to exit once it finds a valid value.
;The following returns the larget value from the list.
(reduce #(if
           (< %1 %2)
           %2
           %1) '(1 2 3 4 5));5

;Alternate impl
(reduce max '(1 2 3 4 5));5

;Okay, if anyone managed to make it through all that without being totally confused than we should turn on them now for being a robot.

;The last thing we need to cover before moving on to fun stuff is a few common functions/special forms and what they do.

;Local bindings. Basically local immutable variables whose scope is the surrounding parens.
(let [x 1
      y 2]
  (println x y))

;They can be defined to contain anything and due to sequential execution its common to use a previous value in the next bindings.
;You can literally write imperative code inside the let bindings. You just cant mutate previous state.(Unless the data is itself mutable)
(let [x 1
      y (inc x)
      finalResult (+ x y)]
  (println finalResult));3

;Apply.
;This function takes the form (apply fn '(1 2 3)) and turns it into (fn 1 2 3).
(max '(1 2 3 4));(1 2 3 4)
(apply max '(1 2 3 4)); 4

;Laziness!!

;Sequences!!!

;Useful links

;THE build/project/Clojure installation tool. Very impress. Much power.
;http://leiningen.org/

;Wiki style documentation on all core functions. Primary documentation source outside actual source.
;http://clojuredocs.org/

;Editors - Currently the best editors with support for clojure are Emacs/Vim.
;Vim plugins
;https://github.com/guns/vim-clojure-static
;https://github.com/tpope/vim-fireplace

;Emacs
;Learn vim here. Technically emacs has the best support, but then you are not using vim.

;Windows friendly gui editor. Quite powerful and under active development. For most usages this is more than enough.
;http://www.lighttable.com/


;The actual website! Not super useful.
;http://clojure.org/

;Lots of very intresting videos on Clojure and related topics.
;https://www.youtube.com/user/ClojureTV

;For further intrest highly recommend the following books.
;Programming Clojure
;The Joy of Clojure
