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
        assertEquals(Optional.of(2), applyResult.flatMap { x -> x })

        val bindResult = OptionMonad.bind(some2, fmapResult.get())
        assertEquals(Optional.of(2), bindResult)
    }

    val f: (Int) -> ((Int) -> Optional<Int>) = { x ->
        { y -> if (y == 0) Optional.empty() else Optional.of(x / y) }
    }
}

interface Monad : Applicative {
    fun <A, B> bind(ma: Optional<A>, f: (A) -> Optional<B>): Optional<B>
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

    override fun <A, B> bind(ma: Optional<A>, f: (A) -> Optional<B>): Optional<B> {
        return when {
            !ma.isPresent -> Optional.empty()
            else -> f(ma.get())
        }
    }
}