object P07Monad {
  def main(args: Array[String]): Unit = {


    val some4 = Some(4)
    val some2 = Some(2)

    val fmapResult = OptionFunctor fmap(f, some4)
    println(fmapResult)

    val applyResult = OptionApplicative apply(fmapResult, some2)
    assert(applyResult == Some(Some(2)))

    val bindResult = OptionMonad bind(fmapResult.get, some2)
    assert(bindResult == Some(2))
  }

  val f: Int => Int => Option[Int] = x =>
    y => if (y == 0) None else Some(x / y)
}

trait Monad[M[_]] extends Applicative[M] {
  def bind[A, B](f: A => M[B], fa: M[A]): M[B]
}

object OptionMonad extends Monad[Option] {
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

  override def bind[A, B](f: A => Option[B], fa: Option[A]): Option[B] = {
    fa match {
      case None => None
      case Some(a) => f(a)
    }
  }
}