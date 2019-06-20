import kotlin.test.assertEquals

object P04PartialApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        val partial1 = f(1)
        println(partial1.javaClass)
        println(partial1)

        val partial1_3 = partial1(3)
        assertEquals(4, partial1_3)

        assertEquals(9, f(4)(5))
    }

    val f: (Int) -> ((Int) -> Int) = { x -> { y -> x + y } }
    fun g(x: Int, y: Int) = x + y
}