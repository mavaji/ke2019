import Test.HUnit.Base

main :: IO ()
main = do
    h f g 4 @?= 2

f :: Int -> Int
f x = x + 1

g :: Int -> Int
g x = x - 3

h :: (Int -> Int) -> (Int -> Int) -> Int -> Int
h f g x = f(g x)