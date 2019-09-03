import java.util.function.Function;

public class P01PureFunction {

    private Integer impure1(Integer x) {
        System.out.println("x=" + x);
        return x;
    }

    private Double impure2(Integer x) {
        return x * Math.random();
    }

    private Integer impure3(Integer x) {
        x = 4;
        return x;
    }

    private void impure4(Integer x) {
        System.out.println(x + 3);
    }

    private Integer y = 1;

    private Integer impure5() {
        return y + 1;
    }

    private Integer impure6(Integer x) {
        var y = x + 1;
        return y;
    }

    private static Integer x = 4;

    private static Function<Integer, Integer> add1 = y -> y + x;

    private static Integer add1f(Integer y) {
        return y + x;
    }

    private static Function<Integer, Integer> add2 = ((Function<Integer, Function<Integer, Integer>>) y -> x -> y + x).apply(3);

    private static Integer add2f(Integer y) {
        return ((Function<Integer, Integer>) x -> y + x).apply(3);
    }

    private static Integer add2ff(Integer y) {
        return y + 3;
    }

    private static Function<Integer, Integer> add3 =
            w -> ((Function<Integer, Integer>) y -> ((Function<Integer, Integer>) x -> y + x).apply(1)).apply(2);

    private static Integer add3f(Integer w) {
        return ((Function<Integer, Integer>) y -> (((Function<Integer, Integer>) x -> y + x).apply(1))).apply(2);
    }

    private static Integer add3ff(Integer w) {
        return ((Function<Integer, Integer>) x -> 2 + x).apply(1);
    }

    private static Integer add3fff(Integer w) {
        return 3;
    }

    private static Integer add3ffff() {
        return 3;
    }

    public static void main(String[] args) {
        assert 5 == add1.apply(1);
        assert 5 == add1f(1);

        assert 4 == add2.apply(1);
        assert 4 == add2f(1);
        assert 4 == add2ff(1);

        assert 3 == add3.apply(1);
        assert 3 == add3f(1);
        assert 3 == add3ff(1);
        assert 3 == add3fff(1);
        assert 3 == add3ffff();

        assert 3 == add3.apply(2);
        assert 3 == add3f(2);
    }
}