import java.util.Optional;
import java.util.function.Function;

public class P07Monad {
    public static void main(String[] args) {
        var some4 = Optional.of(4);
        var some2 = Optional.of(2);

        var fmapResult = OPTION_FUNCTOR.fmap(f, some4);
        System.out.println(fmapResult);

        var applyResult = OPTION_APPLICATIVE.apply(fmapResult, some2);
        assert Optional.of(Optional.of(2)).equals(applyResult);
        assert Optional.of(2).equals(applyResult.flatMap(x -> x));

        var bindResult = OPTION_MONAD.bind(some2, fmapResult.get());
        assert Optional.of(2).equals(bindResult);
    }

    private final static Function<Integer, Function<Integer, Optional<Integer>>> f = x ->
            y -> {
                if (y == 0) return Optional.empty();
                else return Optional.of(x / y);
            };

    private final static OptionFunctor OPTION_FUNCTOR = new OptionFunctor();
    private final static OptionApplicative OPTION_APPLICATIVE = new OptionApplicative();
    private final static OptionMonad OPTION_MONAD = new OptionMonad();
}

interface Monad extends Applicative {
    <A, B> Optional<B> bind(Optional<A> ma, Function<A, Optional<B>> f);
}

class OptionMonad implements Monad {
    @Override
    public <A, B> Optional<B> bind(Optional<A> ma, Function<A, Optional<B>> f) {
        if (!ma.isPresent()) return Optional.empty();
        else return f.apply(ma.get());
    }

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