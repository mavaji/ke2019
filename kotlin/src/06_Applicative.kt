import java.util.*

object ApplicativeTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val some2 = Optional.of(2)
        val some3 = Optional.of(3)

        val fmapResult = OptionFunctor.fmap(f, some2)
        println(fmapResult)

        val applyResult = OptionApplicative.apply(fmapResult, some3)
        println(applyResult)
    }

    val f: (Int) -> ((Int) -> Int) = { x -> { y -> x + y } }
}

object OptionApplicative {
    fun <A, B> apply(f: Optional<(A) -> B>, fa: Optional<A>): Optional<B> {
        return when {
            !fa.isPresent -> Optional.empty()
            else -> when {
                !f.isPresent -> Optional.empty()
                else -> Optional.of(f.get()(fa.get()))
            }
        }
    }
}