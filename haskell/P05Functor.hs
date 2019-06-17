main :: IO ()
main = do
  print $ fmap' f some
  print $ fmap' f none

f :: Int -> Int
f x = x + 2

some = Just 2

none = Nothing

class Functor' f where
  fmap' :: (a -> b) -> f a -> f b

instance Functor' Maybe where
  fmap' _ Nothing = Nothing
  fmap' f (Just a) = Just (f a)
