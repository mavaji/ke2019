import Data.Typeable

main :: IO ()
main = do
  print $ show $ typeOf $ partial1
  print $ partial1_3
  print $ f 4 5

partial1 = f 1

partial1_3 = partial1 3

f :: Int -> Int -> Int
f x y = x + y

g x y = x + y