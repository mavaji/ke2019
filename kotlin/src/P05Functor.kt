import java.util.*

object P05Functor {
    @JvmStatic
    fun main(args: Array<String>) {
        val some = Optional.of(2)
        println(some.map { x -> f(x) })

        val none = Optional.empty<Int>()
        println(none)
        println(none.map { x -> f(x) })

        println("**************************************************")

        println(OptionFunctor.fmap(f, some))
        println(OptionFunctor.fmap(f, none))
    }

    val f: (Int) -> Int = { x -> x + 2 }
}

object OptionFunctor {
    fun <A, B> fmap(f: (A) -> B, fa: Optional<A>): Optional<B> {
        return when {
            !fa.isPresent -> Optional.empty()
            else -> Optional.of(f(fa.get()))
        }
    }
}