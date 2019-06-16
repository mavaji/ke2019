import java.util.*

object RecursionTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val xs = listOf(5, 3, 90, -1, 2)
        println(xs)

        println(max(xs))
        println(max(emptyList()))
    }

    fun max(xs: List<Int>): Int {
        return when {
            xs.isEmpty() -> 0
            xs.size == 1 -> xs.first()
            else -> {
                val head = xs.first()
                val tail = xs.drop(1)
                val maxTail = max(tail)
                if (head > maxTail) head else maxTail
            }
        }
    }
}