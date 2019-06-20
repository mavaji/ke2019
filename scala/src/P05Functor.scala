object P05Functor {
  val f: Int => Int = { x => x + 2 }

  def main(args: Array[String]): Unit = {
    val some = Option(2)
    assert((some map { x => f(x) }) == Some(4))

    val none = None
    assert((none map { x => f(x) }) == None)

    assert((OptionFunctor fmap(f, some)) == Some(4))
    assert((OptionFunctor fmap(f, none)) == None)
  }
}

trait Functor[F[_]] {
  def fmap[A, B](f: A => B, fa: F[A]): F[B]
}


object OptionFunctor extends Functor[Option] {
  override def fmap[A, B](f: A => B, fa: Option[A]): Option[B] = {
    fa match {
      case None => None
      case Some(a) => Some(f(a))
    }
  }
}
