object P03HigherOrder {
  def main(args: Array[String]): Unit = {
    println(h(f, g, 4))
  }

  val f: Int => Int = x => x + 1

  def g(x: Int): Int = x - 3

  def h(f: Int => Int, g: Int => Int, x: Int) = f(g(x))
}