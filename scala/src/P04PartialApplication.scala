object P04PartialApplication {
  def main(args: Array[String]): Unit = {

    val partial1 = f(1)
    println(partial1)

    val partial1_3 = partial1(3)
    println(partial1_3)

    println(f(4)(5))
  }

  val f: Int => Int => Int = x => y => x + y

  def g(x: Int, y: Int) = x + y
}