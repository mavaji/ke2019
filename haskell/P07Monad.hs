import Data.Typeable

main :: IO ()
main = do
  print $ show $ typeOf $ fmapResult
  print $ applyResult
  print $ bindResult

some4 = Just 4
some2 = Just 2

fmapResult = fmap' f some4

applyResult = apply' fmapResult some2

unwrapped :: Maybe(Int -> Maybe Int) -> (Int -> Maybe Int)
unwrapped Nothing = error "nothing to unwrap"
unwrapped (Just a) = a

bindResult = bind' (unwrapped fmapResult) some2

f :: Int -> Int -> Maybe Int
f x y = if y == 0 then Nothing else Just (x `div` y)

class Functor' f where
  fmap' :: (a -> b) -> f a -> f b

class Functor' f => Applicative' f where
  apply' :: f (a -> b) -> f a -> f b

class Applicative' m => Monad' m where
   bind' :: (a -> m b) -> m a -> m b

instance Functor' Maybe where
  fmap' _ Nothing = Nothing
  fmap' f (Just a) = Just (f a)

instance Applicative' Maybe where
  apply' _ Nothing = Nothing
  apply' Nothing _ = Nothing
  apply' (Just g) (Just a) = Just (g a)

instance Monad' Maybe where
  bind' _ Nothing = Nothing
  bind' f (Just a) = f a