main :: IO ()
main = do
  print $ xs
  print $ max' $ xs
  print $ max' []

xs = [5, 3, 90, -1, 2]

max' :: [Int] -> Int
max' [] = 0
max' [a] = a
max' (a:as) = if a > maxTail then a else maxTail
              where maxTail = max' as