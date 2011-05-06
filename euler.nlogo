extensions [array]
globals [primes mem]

;;;

to problem1
  output-print sum filter [? mod 3 = 0 or ? mod 5 = 0] n-values 1000 [?]
end

to problem2
  let result 0
  let a 1
  let b 1
  while [b < 1000000] [
    if b mod 2 = 0 [ set result result + b ]
    let new a + b
    set a b
    set b new
  ]
  output-print result
end

to problem3
  let :primes []
  let n 2
  let target 600851475143
  loop [
    let :factors filter [n mod ? = 0] :primes
    if empty? :factors [
      set :primes fput n :primes
      while [target mod n = 0] [
        set target target / n
        if target = 1 [ output-print n  stop ]
      ]
    ]
    set n n + 1
  ]
end

;;;

to problem4
  let largest 0
  foreach range 100 999 [
    let a ?
    set largest max sentence (list largest)
                             filter [palindrome? ?]
                               map [a * ?] range a 999
  ]
  output-print largest
end

to-report range [a b]
  report n-values (b - a + 1) [a + ?]
end

to-report palindrome? [a]
  report (word a) = reverse (word a)
end

;;;

to problem5
  output-print reduce [lcm ?1 ?2] range 2 20
end

; http://en.wikipedia.org/wiki/Least_common_multiple#Calculating_the_least_common_multiple
to-report lcm [a b]
  report a * b / gcd a b
end

; http://en.wikipedia.org/wiki/Euclidean_algorithm
to-report gcd [a b]
  if b = 0 [ report a]
  report gcd b (a mod b)
end

;;;

to problem6
  output-print (sum range 1 100) ^ 2 - sum map [? ^ 2] range 1 100
end

;;;

to problem7
  let :primes []
  let n 2
  loop [
    let :factors filter [n mod ? = 0] :primes
    if empty? :factors [
      set :primes fput n :primes
      if length :primes = 10001 [ output-print first :primes stop ]
    ]
    set n n + 1
  ]
end

;;;

to problem8
  let input-string
    (word "731671765313306249192251196744265747423553491949349698352031277450632623957831801698480186947885184"
          "385861560789112949495459501737958331952853208805511125406987471585238630507156932909632952274430435"
          "576689664895044524452316173185640309871112172238311362229893423380308135336276614282806444486645238"
          "749303589072962904915604407723907138105158593079608667017242712188399879790879227492190169972088809"
          "377665727333001053367881220235421809751254540594752243525849077116705560136048395864467063244157221"
          "553975369781797784617406495514929086256932197846862248283972241375657056057490261407972968652414535"
          "100474821663704844031998900088952434506585412275886668811642717147992444292823086346567481391912316"
          "282458617866458359124566529476545682848912883142607690042242190226710556263211111093705442175069416"
          "589604080719840385096245544436298123098787992724428490918884580156166097919133875499200524063689912"
          "560717606058861164671094050775410022569831552000559357297257163626956188267042825248360082325753042"
          "0752963450")
  let input-list n-values length input-string [item ? input-string]
  let result 0
  foreach n-values (length input-string - 4) [?] [
    let index ?
    let product reduce [?1 * ?2] map [read-from-string ?] n-values 5 [item (index + ?) input-list]
    if product > result [ set result product ]
  ]
  output-print result
end

;;;

to problem9
  let a 1
  loop [
    let b 1
    while [b <= a] [
      let c sqrt (a ^ 2 + b ^ 2)
      if a + b + c = 1000 [ output-print a * b * c stop ]
      set b b + 1
    ]
    set a a + 1
    set b 1
  ]
end

;;;

;; http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

;; array version (faster):

;to problem10
;  let limit 2000000
;  let result array:from-list n-values limit [?]
;  array:set result 1 0  ;; 1 is not prime
;  let i 2
;  while [i <= sqrt limit] [
;    if array:item result i != 0 [
;      let j i * 2
;      while [j < limit] [
;        array:set result j 0
;        set j j + i
;      ]
;    ]
;    set i i + 1
;  ]
;  output-print sum array:to-list result
;end

;; list version (slower by about a factor of two):

to problem10
  let limit 2000000
  let result bf bf n-values limit [?]
  let total 0
  loop [
    let p item 0 result
    set total total + p
    set result filter [? mod p != 0] result
    if p >= sqrt limit [ output-print total + sum result stop ]
  ]
end

;;;

to problem11
  let nums [[08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08]
            [49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00]
            [81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65]
            [52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91]
            [22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80]
            [24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50]
            [32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70]
            [67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21]
            [24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72]
            [21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95]
            [78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92]
            [16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57]
            [86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58]
            [19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40]
            [04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66]
            [88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69]
            [04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36]
            [20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16]
            [20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54]
            [01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48]]
  let dim length nums
  let a array:from-list map [array:from-list ?] nums
  let result 0
  foreach n-values dim [?] [
    let i ?
    foreach n-values dim [?] [
      let j ?
      foreach [[1 0] [1 1] [1 -1] [0 1]] [
        let xdir item 0 ?
        let ydir item 1 ?
        let coords n-values 4 [(list (i + ? * xdir) (j + ? * ydir))]
        if length filter [in-bounds? ? dim] coords = 4 [
          set result max list result mult a coords
        ]
      ]
    ]
  ]
  output-print result
end

to-report in-bounds? [coord-pair dim]
  let i item 0 coord-pair
  let j item 1 coord-pair
  report i >= 0 and i < dim and j >= 0 and j < dim
end

to-report mult [a coords]
  report reduce [?1 * ?2] map [array:item (array:item a item 0 ?) item 1 ?] coords
end

;;;

; globals [primes]

to problem12
  set primes [2]
  let n 3
  loop [
    let triangle n * (n - 1) / 2
    if divisor-count triangle > 500 [ output-print triangle stop ]
    set n n + 1
  ]
end

to-report divisor-count [n]
  let result 1
  let :factors factors n
  while [not empty? :factors] [
    let f first :factors
    let :count length filter [f = ?] :factors
    set result result * (:count + 1)
    set :factors filter [f != ?] :factors
  ]
  report result
end

to-report factors [n]
  let f first-factor n primes
  while [f = -1] [
    generate-more-primes
    set f first-factor n primes
  ]
  ifelse n = f
    [ report (list n) ]
    [ report fput f factors (n / f) ]
end

to-report first-factor [n :primes]
  if empty? :primes [ report -1 ]
  if n mod first :primes = 0 [ report first :primes ]
  report first-factor n butfirst :primes
end

to generate-more-primes
  let target-length 2 * length primes
  let more-primes []
  let n 1 + max primes
  while [length more-primes < length primes] [
    if empty? filter [n mod ? = 0] primes [ set more-primes fput n more-primes ]
    set n n + 1
  ]
  set primes sentence primes reverse more-primes
end

;;;

to problem13
  let inputs ["37107287533902102798797998220837590246510135740250"
              "46376937677490009712648124896970078050417018260538"
              "74324986199524741059474233309513058123726617309629"
              "91942213363574161572522430563301811072406154908250"
              "23067588207539346171171980310421047513778063246676"
              "89261670696623633820136378418383684178734361726757"
              "28112879812849979408065481931592621691275889832738"
              "44274228917432520321923589422876796487670272189318"
              "47451445736001306439091167216856844588711603153276"
              "70386486105843025439939619828917593665686757934951"
              "62176457141856560629502157223196586755079324193331"
              "64906352462741904929101432445813822663347944758178"
              "92575867718337217661963751590579239728245598838407"
              "58203565325359399008402633568948830189458628227828"
              "80181199384826282014278194139940567587151170094390"
              "35398664372827112653829987240784473053190104293586"
              "86515506006295864861532075273371959191420517255829"
              "71693888707715466499115593487603532921714970056938"
              "54370070576826684624621495650076471787294438377604"
              "53282654108756828443191190634694037855217779295145"
              "36123272525000296071075082563815656710885258350721"
              "45876576172410976447339110607218265236877223636045"
              "17423706905851860660448207621209813287860733969412"
              "81142660418086830619328460811191061556940512689692"
              "51934325451728388641918047049293215058642563049483"
              "62467221648435076201727918039944693004732956340691"
              "15732444386908125794514089057706229429197107928209"
              "55037687525678773091862540744969844508330393682126"
              "18336384825330154686196124348767681297534375946515"
              "80386287592878490201521685554828717201219257766954"
              "78182833757993103614740356856449095527097864797581"
              "16726320100436897842553539920931837441497806860984"
              "48403098129077791799088218795327364475675590848030"
              "87086987551392711854517078544161852424320693150332"
              "59959406895756536782107074926966537676326235447210"
              "69793950679652694742597709739166693763042633987085"
              "41052684708299085211399427365734116182760315001271"
              "65378607361501080857009149939512557028198746004375"
              "35829035317434717326932123578154982629742552737307"
              "94953759765105305946966067683156574377167401875275"
              "88902802571733229619176668713819931811048770190271"
              "25267680276078003013678680992525463401061632866526"
              "36270218540497705585629946580636237993140746255962"
              "24074486908231174977792365466257246923322810917141"
              "91430288197103288597806669760892938638285025333403"
              "34413065578016127815921815005561868836468420090470"
              "23053081172816430487623791969842487255036638784583"
              "11487696932154902810424020138335124462181441773470"
              "63783299490636259666498587618221225225512486764533"
              "67720186971698544312419572409913959008952310058822"
              "95548255300263520781532296796249481641953868218774"
              "76085327132285723110424803456124867697064507995236"
              "37774242535411291684276865538926205024910326572967"
              "23701913275725675285653248258265463092207058596522"
              "29798860272258331913126375147341994889534765745501"
              "18495701454879288984856827726077713721403798879715"
              "38298203783031473527721580348144513491373226651381"
              "34829543829199918180278916522431027392251122869539"
              "40957953066405232632538044100059654939159879593635"
              "29746152185502371307642255121183693803580388584903"
              "41698116222072977186158236678424689157993532961922"
              "62467957194401269043877107275048102390895523597457"
              "23189706772547915061505504953922979530901129967519"
              "86188088225875314529584099251203829009407770775672"
              "11306739708304724483816533873502340845647058077308"
              "82959174767140363198008187129011875491310547126581"
              "97623331044818386269515456334926366572897563400500"
              "42846280183517070527831839425882145521227251250327"
              "55121603546981200581762165212827652751691296897789"
              "32238195734329339946437501907836945765883352399886"
              "75506164965184775180738168837861091527357929701337"
              "62177842752192623401942399639168044983993173312731"
              "32924185707147349566916674687634660915035914677504"
              "99518671430235219628894890102423325116913619626622"
              "73267460800591547471830798392868535206946944540724"
              "76841822524674417161514036427982273348055556214818"
              "97142617910342598647204516893989422179826088076852"
              "87783646182799346313767754307809363333018982642090"
              "10848802521674670883215120185883543223812876952786"
              "71329612474782464538636993009049310363619763878039"
              "62184073572399794223406235393808339651327408011116"
              "66627891981488087797941876876144230030984490851411"
              "60661826293682836764744779239180335110989069790714"
              "85786944089552990653640447425576083659976645795096"
              "66024396409905389607120198219976047599490197230297"
              "64913982680032973156037120041377903785566085089252"
              "16730939319872750275468906903707539413042652315011"
              "94809377245048795150954100921645863754710598436791"
              "78639167021187492431995700641917969777599028300699"
              "15368713711936614952811305876380278410754449733078"
              "40789923115535562561142322423255033685442488917353"
              "44889911501440648020369068063960672322193204149535"
              "41503128880339536053299340368006977710650566631954"
              "81234880673210146739058568557934581403627822703280"
              "82616570773948327592232845941706525094512325230608"
              "22918802058777319719839450180888072429661980811197"
              "77158542502016545090413245809786882778948721859617"
              "72107838435069186155435662884062257473692284509516"
              "20849603980134001723930671666823555245252804609722"
              "53503534226472524250874054075591789781264330331690"]
  set inputs map [word "00" ?] inputs
  let total reduce [big-add13 ?1 ?2] inputs
  output-print substring total 0 10
end

; assumes inputs have same # of digits
to-report big-add13 [n1 n2]
  if length n1 != length n2 [ error "length mismatch" ]
  let result ""
  let carry 0
  foreach reverse n-values (length n1) [?] [
    let digit-sum carry
        + read-from-string item ? n1
        + read-from-string item ? n2
    set result word (digit-sum mod 10) result
    set carry ifelse-value (digit-sum >= 10) [1] [0]
  ]
  if carry = 1 [ error "leftover carry" ]
  report result
end

;;;

to problem14
  let memory array:from-list n-values 1000000 [0]
  array:set memory 1 1
  let longest-chain 0
  let winner 0
  foreach n-values 499999 [? * 2 + 1] [
    let history (list ?)
    let n ?
    while [n >= 1000000 or array:item memory n = 0] [
      set history fput n history
      ifelse n mod 2 = 0
        [ set n n / 2]
        [ set n 3 * n + 1 ]
    ]
    let counter array:item memory n
    while [not empty? history] [
      if first history < 1000000 and array:item memory first history = 0 [
        array:set memory first history counter
        if counter > longest-chain [
          set winner first history
          set longest-chain counter
        ]
      ]
      set history butfirst history
      set counter counter + 1
    ]
  ]
  output-print (word winner " (" longest-chain ")")
end

;;;

; globals [mem]

; This can be solved more simply with combinatorics.

to problem15
  set mem array:from-list n-values (21 * 21) [0]
  output-print recur 20 20
end

to-report recur [x y]
  let answer array:item mem (x * 21 + y)
  if answer > 0 [ report answer ]
  ifelse x = 0 or y = 0
    [ set answer 1 ]
    [ set answer sum map [recur (x - 1) ?] n-values (y + 1) [?] ]
  array:set mem (x * 21 + y) answer
  report answer
end

;;;

; assumes inputs have same # of digits
to-report big-add16 [n1 n2]
  if length n1 != length n2 [ error "length mismatch" ]
  let result ""
  let carry 0
  foreach reverse n-values (length n1) [?] [
    let digit-sum carry
        + read-from-string item ? n1
        + read-from-string item ? n2
    set result word (digit-sum mod 10) result
    set carry ifelse-value (digit-sum >= 10) [1] [0]
  ]
  ifelse carry = 1
    [ report word 1 result ]
    [ report result ]
end

to problem16
  let answer "1"
  repeat 1000 [ set answer big-add16 answer answer ]
  output-print sum map [read-from-string item ? answer] n-values length answer [?]
end

;;;

to problem17
  output-print sum map [letter-length english ?] range 1 1000
end

to-report letter-length [s]
  report length remove " " remove "-" s
end

to-report english [n]
  if n = 1000 [ report "one thousand" ]
  if n mod 100 = 0 [ report word english (n / 100) " hundred" ]
  if n > 100 [ report (word english (100 * floor (n / 100)) " and " english (n mod 100)) ]
  if n < 20 [ report item (n - 1) ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine"
                                   "ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen"
                                   "seventeen" "eighteen" "nineteen"] ]
  if n mod 10 = 0 [ report item (n / 10 - 2) ["twenty" "thirty" "forty" "fifty" "sixty"
                                              "seventy" "eighty" "ninety"] ]
  report (word english (10 * floor (n / 10)) "-" english (n mod 10))
end

;;;

; Brute force solution.  The problem has a hint that says something cleverer is possible
; (and Problem 67 is the same but with a triangle too big to solve by brute force).
; I guess I'll cross that bridge when I come to it...

to problem18
  let triangle [[75]
                [95 64]
                [17 47 82]
                [18 35 87 10]
                [20 04 82 47 65]
                [19 01 23 75 03 34]
                [88 02 77 73 07 63 67]
                [99 65 04 28 06 16 70 92]
                [41 41 26 56 83 40 80 70 33]
                [41 48 72 33 47 32 37 16 94 29]
                [53 71 44 65 25 43 91 52 97 51 14]
                [70 11 33 28 77 73 17 78 39 68 17 57]
                [91 71 52 38 17 14 91 43 58 50 27 29 48]
                [63 66 04 68 89 53 67 30 73 16 69 87 40 31]
                [04 62 98 27 23 09 70 98 73 93 38 53 60 04 23]]
  output-print recurse18 first first triangle butfirst triangle
end

to-report recurse18 [total triangle]
  if empty? triangle [ report total ]
  report max list recurse18 (total + first first triangle)
                           map [butlast ?] butfirst triangle
                  recurse18 (total + item 1 first triangle)
                            map [butfirst ?] butfirst triangle
end

;;;

to problem20
  let result bigfact "100"
  output-print sum map [read-from-string item ? result] n-values length result [?]
end

to-report bigfact [n]
  if n = "1" [ report "1" ]
  report bigmult n bigfact bigdec n
end

to-report bigmult [a b]
  if a = "1" [ report b ]
  report big-add20 b bigmult bigdec a b
end

to-report big-add20 [n1 n2]
  if length n1 > length n2 [ set n2 pad n2 length n1 ]
  if length n2 > length n1 [ set n1 pad n1 length n2 ]
  let result ""
  let carry 0
  foreach reverse n-values (length n1) [?] [
    let digit-sum carry
      + read-from-string item ? n1
      + read-from-string item ? n2
    set result word (digit-sum mod 10) result
    set carry ifelse-value (digit-sum >= 10) [1] [0]
  ]
  ifelse carry = 1
    [ report word 1 result ]
    [ report result ]
end

to-report pad [n len]
  report reduce [word ?1 ?2] lput n n-values (len - length n) ["0"]
end

to-report bigdec [n]
  let digitpos length n - 1
  while [item digitpos n = "0"] [
    set n replace-item digitpos n "9"
    set digitpos digitpos - 1
  ]
  set n replace-item digitpos n (word (read-from-string item digitpos n - 1))
  while [item 0 n = "0"] [ set n replace-item 0 n "" ]
  report n
end

;;;

to problem25
  let f0 "1"
  let f1 "1"
  let counter 2
  while [length f1 < 1000] [
    let f2 big-add20 f0 f1
    set f0 f1
    set f1 f2
    set counter counter + 1
  ]
  output-print counter
end
@#$#@#$#@
GRAPHICS-WINDOW
315
10
630
46
30
0
5.0
1
10
1
1
1
0
1
1
1
-30
30
0
0
0
0
1
ticks
30

BUTTON
14
10
109
43
NIL
problem1
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
45
109
78
NIL
problem2
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
80
109
113
NIL
problem3
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
115
109
148
NIL
problem4
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
150
109
183
NIL
problem5
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
185
109
218
NIL
problem6
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
220
109
253
NIL
problem7
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
255
109
288
NIL
problem8
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
290
109
323
NIL
problem9
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
325
109
358
NIL
problem10
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
14
360
109
393
NIL
problem11
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
10
220
43
NIL
problem12
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
45
220
78
NIL
problem13
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
80
220
113
NIL
problem14
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
115
220
148
NIL
problem15
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
150
220
183
NIL
problem16
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
185
220
218
NIL
problem17
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
220
220
253
NIL
problem18
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
121
254
221
287
NIL
problem20
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
120
290
220
323
NIL
problem25
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

OUTPUT
279
63
616
392
12

@#$#@#$#@
## WHAT IS IT?

solutions problems at http://projecteuler.net

outputs are right, but maybe the code isn't optimal. can you improve it?

## NETLOGO FEATURES

these were written before NetLogo had tasks. maybe they would be useful here?
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270

@#$#@#$#@
NetLogo 5.0beta1
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180

@#$#@#$#@
0
@#$#@#$#@
