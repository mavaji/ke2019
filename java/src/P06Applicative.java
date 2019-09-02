import java.util.Optional;
import java.util.function.Function;

public class P06Applicative {
    public static void main(String[] args) {
        var some2 = Optional.of(2);
        var some3 = Optional.of(3);

        var fmapResult = OPTION_FUNCTOR.fmap(f, some2);
        System.out.println(fmapResult);

        var applyResult = OPTION_APPLICATIVE.apply(fmapResult, some3);
        assert Optional.of(5).equals(applyResult);
    }

    private final static Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;
    private final static OptionFunctor OPTION_FUNCTOR = new OptionFunctor();
    private static final OptionApplicative OPTION_APPLICATIVE = new OptionApplicative();
}

interface Applicative extends Functor {
    <A, B> Optional<B> apply(Optional<Function<A, B>> f, Optional<A> fa);
}

class OptionApplicative implements Applicative {
    @Override
    public <A, B> Optional<B> apply(Optional<Function<A, B>> f, Optional<A> fa) {
        if (!fa.isPresent()) return Optional.empty();
        else {
            if (!f.isPresent()) return Optional.empty();
            else return Optional.of(f.get().apply(fa.get()));
        }
    }

    @Override
    public <A, B> Optional<B> fmap(Function<A, B> f, Optional<A> fa) {
        if (!fa.isPresent()) return Optional.empty();
        else return Optional.of(f.apply(fa.get()));
    }
}