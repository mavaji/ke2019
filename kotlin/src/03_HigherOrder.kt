object HigherOrderTest {
    @JvmStatic
    fun main(args: Array<String>) {
        println(h(f, ::g, 4))
    }

    val f: (Int) -> Int = { x -> x + 1 }

    fun g(x: Int): Int = x - 3

    fun h(f: (Int) -> Int, g: (Int) -> Int, x: Int) = f(g(x))
}