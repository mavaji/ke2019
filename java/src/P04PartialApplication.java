import java.util.function.Function;

public class P04PartialApplication {
    public static void main(String[] args) {
        var partial1 = f.apply(1);
        System.out.println(partial1.getClass());
        System.out.println(partial1);

        var partial1_3 = partial1.apply(3);
        assert 4 == partial1_3;

        assert 9 == f.apply(4).apply(5);
    }

    private final static Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;

    private static Integer g(Integer x, Integer y) {
        return x + y;
    }
}