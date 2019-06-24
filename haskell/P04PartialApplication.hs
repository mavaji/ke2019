import Data.Typeable
import Test.HUnit.Base

main :: IO ()
main = do
  show (typeOf partial1) @?= "Int -> Int"
  partial1_3 @?= 4
  f 4 5 @?= 9

partial1 = f 1

partial1_3 = partial1 3

f :: Int -> Int -> Int
f x y = x + y

g x y = x + y