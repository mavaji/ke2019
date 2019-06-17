object P05Functor {
  val f: Int => Int = { x => x + 2 }

  def main(args: Array[String]): Unit = {
    val some = Option(2)
    println(some map { x => f(x) })

    val none = None
    println(none)
    println(none map { x => f(x) })

    println("**************************************************")

    println(OptionFunctor fmap(f, some))
    println(OptionFunctor fmap(f, none))
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
