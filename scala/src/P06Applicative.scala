object P06Applicative {
  def main(args: Array[String]): Unit = {
    val some2 = Some(2)
    val some3 = Some(3)

    val fmapResult = OptionFunctor fmap(f, some2)
    println(fmapResult)

    val applyResult = OptionApplicative apply(fmapResult, some3)
    assert(applyResult == Some(5))
  }

  val f: Int => Int => Int = x => y => x + y
}

trait Applicative[F[_]] extends Functor[F] {
  def apply[A, B](f: F[A => B], fa: F[A]): F[B]
}

object OptionApplicative extends Applicative[Option] {
  override def fmap[A, B](f: A => B, fa: Option[A]): Option[B] = {
    fa match {
      case None => None
      case Some(a) => Some(f(a))
    }
  }

  override def apply[A, B](f: Option[A => B], fa: Option[A]): Option[B] = {
    fa match {
      case None => None
      case Some(a) => f match {
        case None => None
        case Some(g) => Some(g(a))
      }
    }
  }
}