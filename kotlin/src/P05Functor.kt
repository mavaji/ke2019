import java.util.*
import kotlin.test.assertEquals

object P05Functor {
    @JvmStatic
    fun main(args: Array<String>) {
        val some2 = Optional.of(2)
        assertEquals(Optional.of(4), some2.map { x -> f(x) })

        val none = Optional.empty<Int>()
        assertEquals(Optional.empty(), none.map { x -> f(x) })

        assertEquals(Optional.of(4), OptionFunctor.fmap(f, some2))
        assertEquals(Optional.empty(), OptionFunctor.fmap(f, none))
    }

    val f: (Int) -> Int = { x -> x + 2 }
}

interface Functor {
    fun <A, B> fmap(f: (A) -> B, fa: Optional<A>): Optional<B>
}

object OptionFunctor : Functor {
    override fun <A, B> fmap(f: (A) -> B, fa: Optional<A>): Optional<B> {
        return when {
            !fa.isPresent -> Optional.empty()
            else -> Optional.of(f(fa.get()))
        }
    }
}