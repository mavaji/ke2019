import java.util.*
import kotlin.test.assertEquals

object P07Monad {
    @JvmStatic
    fun main(args: Array<String>) {
        val some4 = Optional.of(4)
        val some2 = Optional.of(2)

        val fmapResult = OptionFunctor.fmap(f, some4)
        println(fmapResult)

        val applyResult = OptionApplicative.apply(fmapResult, some2)
        assertEquals(Optional.of(Optional.of(2)), applyResult)

        val bindResult = OptionMonad.bind(fmapResult.get(), some2)
        assertEquals(Optional.of(2), bindResult)
    }

    val f: (Int) -> ((Int) -> Optional<Int>) = { x ->
        { y -> if (y == 0) Optional.empty() else Optional.of(x / y) }
    }
}

interface Monad : Applicative {
    fun <A, B> bind(f: (A) -> Optional<B>, fa: Optional<A>): Optional<B>
}

object OptionMonad : Monad {
    override fun <A, B> fmap(f: (A) -> B, fa: Optional<A>): Optional<B> {
        return when {
            !fa.isPresent -> Optional.empty()
            else -> Optional.of(f(fa.get()))
        }
    }

    override fun <A, B> apply(f: Optional<(A) -> B>, fa: Optional<A>): Optional<B> {
        return when {
            !fa.isPresent -> Optional.empty()
            else -> when {
                !f.isPresent -> Optional.empty()
                else -> Optional.of(f.get()(fa.get()))
            }
        }
    }

    override fun <A, B> bind(f: (A) -> Optional<B>, fa: Optional<A>): Optional<B> {
        return when {
            !fa.isPresent -> Optional.empty()
            else -> f(fa.get())
        }
    }
}