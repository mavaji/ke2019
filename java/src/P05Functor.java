import java.util.Optional;
import java.util.function.Function;

public class P05Functor {
    public static void main(String[] args) {
        var some2 = Optional.of(2);
        assert Optional.of(4).equals(some2.map(x -> f.apply(x)));

        var none = Optional.<Integer>empty();
        assert Optional.empty().equals(none.map(x -> f.apply(x)));

        assert Optional.of(4).equals(OPTION_FUNCTOR.fmap(f, some2));
        assert Optional.empty().equals(OPTION_FUNCTOR.fmap(f, none));
    }

    private final static Function<Integer, Integer> f = x -> x + 2;
    private final static OptionFunctor OPTION_FUNCTOR = new OptionFunctor();
}

interface Functor {
    <A, B> Optional<B> fmap(Function<A, B> f, Optional<A> fa);
}

class OptionFunctor implements Functor {
    @Override
    public <A, B> Optional<B> fmap(Function<A, B> f, Optional<A> fa) {
        if (!fa.isPresent()) return Optional.empty();
        else return Optional.of(f.apply(fa.get()));
    }
}