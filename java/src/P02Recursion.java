import java.util.List;

public class P02Recursion {
    public static void main(String[] args) {
        var xs = List.of(5, 3, 90, -1, 2);

        assert 90 == max(xs);
        assert 0 == max(List.of());
    }

    private static Integer max(List<Integer> xs) {
        if (xs.isEmpty()) return 0;
        else if (xs.size() == 1) return xs.get(0);
        else {
            var head = xs.get(0);
            var tail = xs.subList(1, xs.size());
            var maxTail = max(tail);
            if (head > maxTail) return head;
            else return maxTail;
        }
    }
}