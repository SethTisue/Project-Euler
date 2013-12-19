import Control.Exception (assert)
import Control.Monad (guard)
import Control.Applicative ((<$>), (<*>))
import Data.Char (digitToInt)
import Data.List (tails, find, maximumBy)
import Data.Maybe (fromJust, maybeToList)
import Data.Ord (comparing)
import Data.Ix (inRange)
import Data.Time.Clock (getCurrentTime, diffUTCTime)

-- integer square root code from Hellige
isqrt :: Integral a => a -> Maybe a
isqrt x = let y = round . sqrt . fromIntegral $ x
          in if y * y == x then Just y else Nothing

-- prime sieve code from:
-- http://en.literateprograms.org/Sieve_of_Eratosthenes_(Haskell)
merge :: (Ord a) => [a] -> [a] -> [a]
merge xs@(x:xt) ys@(y:yt) =
  case compare x y of
    LT -> x : (merge xt ys)
    EQ -> x : (merge xt yt)
    GT -> y : (merge xs yt)

diff :: (Ord a) => [a] -> [a] -> [a]
diff xs@(x:xt) ys@(y:yt) =
  case compare x y of
    LT -> x : (diff xt ys)
    EQ -> diff xt yt
    GT -> diff xs yt

primes, nonprimes :: [Integer]
primes    = [2, 3, 5] ++ (diff [7, 9 ..] nonprimes)
nonprimes = foldr1 f . map g . tail $ primes
  where
    f (x:xt) ys = x : (merge xt ys)
    g p         = [ n * p | n <- [p, p + 2 ..]]

-- this is mine (and should be speeded up)
primeFactors 1 = []
primeFactors n = fac : primeFactors (n `quot` fac)
  where fac = head $ dropWhile notDivides primes
        notDivides p = n `mod` p /= 0

-- I cheated on some of these by looking at
-- http://www.haskell.org/haskellwiki/Euler_problems
-- (though not until after I had already solved the problem in Scala)
-- on others, I got it working first and then used the wiki code to
-- clean up my solution

problem1 = sum $ filter ok [1..999]
    where ok = (||) <$> divides 3 <*> divides 5
          divides a b = b `mod` a == 0

problem2 = sum $ takeWhile (< 1000000) $ filter even fibs
    where fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

problem3 = last $ primeFactors 600851475143

problem4 = maximum $ filter isPalindrome [i * j | i <- [100..999], j <- [100..i]]
    where isPalindrome n = show n == (reverse $ show n)

problem5 = foldl1 lcm [2..20]

problem6 = sum nums ^ 2 - sum (map (^2) nums)
    where nums = [1..100]

problem7 = primes !! 10000

problem8 = maximum $ map (product . map digitToInt . take 5) $ tails input
    where input = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843" ++
                  "8586156078911294949545950173795833195285320880551112540698747158523863050715693290963295227443043557" ++
                  "6689664895044524452316173185640309871112172238311362229893423380308135336276614282806444486645238749" ++
                  "3035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776" ++
                  "6572733300105336788122023542180975125454059475224352584907711670556013604839586446706324415722155397" ++
                  "5369781797784617406495514929086256932197846862248283972241375657056057490261407972968652414535100474" ++
                  "8216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586" ++
                  "1786645835912456652947654568284891288314260769004224219022671055626321111109370544217506941658960408" ++
                  "0719840385096245544436298123098787992724428490918884580156166097919133875499200524063689912560717606" ++
                  "05886116467109405077541002256983155200055935729725"

problem9 = head [a * b * c | a <- [1..], b <- [1..a],
                             let sum = a * a + b * b,
                             c <- maybeToList (isqrt sum),
                             a + b + c == 1000]

problem10 = sum $ takeWhile (<2000000) primes

problem11 =
  let a = [[ 8, 2,22,97,38,15, 0,40, 0,75, 4, 5, 7,78,52,12,50,77,91, 8],
           [49,49,99,40,17,81,18,57,60,87,17,40,98,43,69,48, 4,56,62, 0],
           [81,49,31,73,55,79,14,29,93,71,40,67,53,88,30, 3,49,13,36,65],
           [52,70,95,23, 4,60,11,42,69,24,68,56, 1,32,56,71,37, 2,36,91],
           [22,31,16,71,51,67,63,89,41,92,36,54,22,40,40,28,66,33,13,80],
           [24,47,32,60,99, 3,45, 2,44,75,33,53,78,36,84,20,35,17,12,50],
           [32,98,81,28,64,23,67,10,26,38,40,67,59,54,70,66,18,38,64,70],
           [67,26,20,68, 2,62,12,20,95,63,94,39,63, 8,40,91,66,49,94,21],
           [24,55,58, 5,66,73,99,26,97,17,78,78,96,83,14,88,34,89,63,72],
           [21,36,23, 9,75, 0,76,44,20,45,35,14, 0,61,33,97,34,31,33,95],
           [78,17,53,28,22,75,31,67,15,94, 3,80, 4,62,16,14, 9,53,56,92],
           [16,39, 5,42,96,35,31,47,55,58,88,24, 0,17,54,24,36,29,85,57],
           [86,56, 0,48,35,71,89, 7, 5,44,44,37,44,60,21,58,51,54,17,58],
           [19,80,81,68, 5,94,47,69,28,73,92,13,86,52,17,77, 4,89,55,40],
           [ 4,52, 8,83,97,35,99,16, 7,97,57,32,16,26,26,79,33,27,98,66],
           [88,36,68,87,57,62,20,72, 3,46,33,67,46,55,12,32,63,93,53,69],
           [ 4,42,16,73,38,25,39,11,24,94,72,18, 8,46,29,32,40,62,76,36],
           [20,69,36,41,72,30,23,88,34,62,99,69,82,67,59,85,74, 4,36,16],
           [20,73,35,29,78,31,90, 1,74,31,49,71,48,86,81,16,23,57, 5,54],
           [ 1,70,54,71,83,51,54,69,16,92,33,48,61,43,52, 1,89,19,67,48]]
      inBounds = inRange (0,length a - 1)
      bothInBounds (i,j) = inBounds i && inBounds j
      lookup (i,j) = a !! i !! j
  in maximum (do i <- [0..length a - 1]; j <- [0..length a - 1]
                 (xdir,ydir) <- [(1,0),(1,1),(1,-1),(0,1)]
                 let coords = flip map [0..3] (\dist -> (i + dist * xdir, j + dist * ydir))
                 guard (all bothInBounds coords)
                 return $ foldl1 (*) (map lookup coords))

problem12 = fromJust $ find isSolution $ tail $ map triangle [1..]
  where triangle n = n * (n + 1) `div` 2
        isSolution = (>500) . product . map (1+) . factorCounts
        factorCounts n =
          let factor = fromJust $ find ((== 0) . (mod n)) primes
          in if factor == n then [1]
             else let recurse = factorCounts (n `div` factor)
                  in if (n `div` factor) `mod` factor /= 0 then 1 : recurse
                     else (head recurse + 1) : (tail recurse)

-- problem 13 has a data file. once I figure out how to read files, I should
-- go back and do problems 8 and 11 that way too (8.txt and 11.txt exist now)

-- I tried using "maximumBy (comparing chainLength)" instead of defining
-- maximize myself, but it ran a lot slower and chewed a ton of stack :-(

problem14 = maximize chainLength [3,5..1000000]
  where next n = if(even n) then n `div` 2
                            else 3 * n +1
        chainLength = length . (takeWhile (/= 1)) . iterate next
        maximize f = snd . maximum . map (\x -> (f x, x))

-- testing
test n actual expected =
  do startTime <- getCurrentTime
     putStr $ show n
     putChar ' '
     putStr $ show $ actual == expected
     putChar ' '
     putStr $ show actual
     putStr " ("
     endTime <- getCurrentTime
     putStr $ show $ diffUTCTime endTime startTime
     putStrLn ")"

main =
  do test 1 problem1 233168
     test 2 problem2 1089154
     test 3 problem3 6857
     test 4 problem4 906609
     test 5 problem5 232792560
     test 6 problem6 25164150
     test 7 problem7 104743
     test 8 problem8 40824
     test 9 problem9 31875000
     test 10 problem10 142913828922
     test 11 problem11 70600674
     test 12 problem12 76576500
     test 14 problem14 837799
