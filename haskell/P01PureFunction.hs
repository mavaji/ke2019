import Test.HUnit.Base

main :: IO ()
main = do
  add1 1 @?= 5
  add1' 1 @?= 5

  add2 1 @?= 4
  add2' 1 @?= 4
  add2'' 1 @?= 4

  add3 1 @?= 3
  add3' 1 @?= 3
  add3'' 1 @?= 3
  add3''' 1 @?= 3
  add3'''' @?= 3

  add3 2 @?= 3

x = 4

add1 :: Int -> Int
add1 = \y -> y + x

add1' :: Int -> Int
add1' y = y + x

add2 :: Int -> Int
add2 = (\y -> \x -> y + x) 3

add2' :: Int -> Int
add2' y = (\x -> y + x) 3

add2'' :: Int -> Int
add2'' y = y + 3

add3 :: Int -> Int
add3 = \w -> (\y -> (\x -> y + x) 1) 2

add3' :: Int -> Int
add3' y = (\y -> (\x -> y + x) 1) 2

add3'' :: Int -> Int
add3'' y = (\x -> 2 + x) 1

add3''' :: Int -> Int
add3''' w = 3

add3'''' :: Int
add3'''' = 3