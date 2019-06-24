import Test.HUnit.Base

main :: IO ()
main = do
  fmap' f some2 @?= Just 4
  fmap' f none @?= Nothing

f :: Int -> Int
f x = x + 2

some2 = Just 2

none = Nothing

class Functor' f where
  fmap' :: (a -> b) -> f a -> f b

instance Functor' Maybe where
  fmap' _ Nothing = Nothing
  fmap' f (Just a) = Just (f a)
