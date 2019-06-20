object P01PureFunction {

  def impure1(x: Int): Int = {
    print(s"x=${x}")
    x
  }

  def impure2(x: Int): Double = {
    x * Math.random()
  }

  def impure3(x: Int): Int = {
    //        x = 4
    x
  }

  def impure4(x: Int) = {
    print(x + 3)
  }

  def impure5(): Int = {
    x + 1
  }

  def impure6(x: Int): Int = {
    var y = x + 1
    y
  }

  val x = 4

  val add1: Int => Int = y => y + x

  def add1f(y: Int): Int = y + x

  val add2: Int => Int = ((y: Int) => (x: Int) => y + x) (3)

  def add2f(y: Int): Int = ((x: Int) => y + x) (3)

  def add2ff(y: Int): Int = y + 3

  val add3: Int => Int = (w: Int) => ((y: Int) => ((x: Int) => y + x) (1)) (2)

  def add3f(w: Int): Int = ((y: Int) => ((x: Int) => y + x) (1)) (2)

  def add3ff(w: Int) = ((x: Int) => 2 + x) (1)

  def add3fff(w: Int) = 3

  def add3ffff() = 3

  def main(args: Array[String]): Unit = {
    assert(add1(1) == 5)
    assert(add1f(1) == 5)

    assert(add2(1) == 4)
    assert(add2f(1) == 4)
    assert(add2ff(1) == 4)

    assert(add3(1) == 3)
    assert(add3f(1) == 3)
    assert(add3ff(1) == 3)
    assert(add3fff(1) == 3)
    assert(add3ffff() == 3)

    assert(add3(2) == 3)
    assert(add3f(2) == 3)
  }
}