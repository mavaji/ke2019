import java.util.*

object MonadTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val some4 = Optional.of(4)
        val some2 = Optional.of(2)

        val fmapResult = OptionFunctor.fmap(f, some4)
        println(fmapResult)

        val applyResult = OptionApplicative.apply(fmapResult, some2)
        println(applyResult)

        val bindResult = OptionMonad.bind(fmapResult.get(), some2)
        println(bindResult)
    }

    val f: (Int) -> ((Int) -> Optional<Int>) = { x ->
        { y -> if (y == 0) Optional.empty() else Optional.of(x / y) }
    }
}

object OptionMonad {
    fun <A, B> bind(f: (A) -> Optional<B>, fa: Optional<A>): Optional<B> {
        return if (!fa.isPresent) Optional.empty() else
            f(fa.get())
    }
}