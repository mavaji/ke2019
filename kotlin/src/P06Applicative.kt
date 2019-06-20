import java.util.*
import kotlin.test.assertEquals

object P06Applicative {
    @JvmStatic
    fun main(args: Array<String>) {
        val some2 = Optional.of(2)
        val some3 = Optional.of(3)

        val fmapResult = OptionFunctor.fmap(f, some2)
        println(fmapResult)

        val applyResult = OptionApplicative.apply(fmapResult, some3)
        assertEquals(Optional.of(5), applyResult)
    }

    val f: (Int) -> ((Int) -> Int) = { x -> { y -> x + y } }
}

interface Applicative : Functor {
    fun <A, B> apply(f: Optional<(A) -> B>, fa: Optional<A>): Optional<B>
}

object OptionApplicative : Applicative {
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
}