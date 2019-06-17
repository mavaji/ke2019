object P07Monad {
  def main(args: Array[String]): Unit = {


    val some4 = Some(4)
    val some2 = Some(2)

    val fmapResult = OptionFunctor fmap(f, some4)
    println(fmapResult)

    val applyResult = OptionApplicative apply(fmapResult, some2)
    println(applyResult)

    val bindResult = OptionMonad bind(fmapResult.get, some2)
    println(bindResult)
  }

  val f: Int => Int => Option[Int] = x =>
    y => if (y == 0) None else Some(x / y)
}

trait Monad[M[_]] {
  def bind[A, B](f: A => M[B], fa: M[A]): M[B]
}

object OptionMonad extends Monad[Option] {
  override def bind[A, B](f: A => Option[B], fa: Option[A]): Option[B] = {
    fa match {
      case None => None
      case Some(a) => f(a)
    }
  }
}