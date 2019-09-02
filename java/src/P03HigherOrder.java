import java.util.function.Function;

public class P03HigherOrder {
    public static void main(String[] args) {
        assert 2 == h(f, P03HigherOrder::g, 4);
    }

    private static final Function<Integer, Integer> f = x -> x + 1;

    private static Integer g(Integer x) {
        return x - 3;
    }

    private static Integer h(Function<Integer, Integer> f, Function<Integer, Integer> g, Integer x) {
        return f.apply(g.apply(x));
    }
}