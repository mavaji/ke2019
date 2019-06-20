object P02Recursion {
  def main(args: Array[String]): Unit = {
    val xs = List(5, 3, 90, -1, 2)

    assert(max(xs) == 90)
    assert(max(List()) == 0)
  }

  def max(xs: List[Int]): Int = {
    xs match {
      case List() => 0
      case List(a) => a
      case a :: as =>
        val maxTail = max(as)
        if (a > maxTail) a else maxTail
    }
  }
}