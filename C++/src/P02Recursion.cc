// g++ P02Recursion.cc && ./a.out
#include <vector>
#include <cassert>

// Not best performance and stability wise
auto max(const std::vector<int> xs) -> int {
    switch (xs.size()) {
    case 0: return 0;
    case 1: return xs[0];
    default:
        int head = xs[0];
        std::vector<int> tail(&xs[1], &xs.back() + 1);
        int maxTail = max(tail);
        return head > maxTail ? head : maxTail;
    }
}

int main(int argc, char** argv) {
    constexpr int SIZE = 5;
    int data[SIZE] = { 5, 3, 90, -1, 2 };
    const std::vector<int> xs(data, data + SIZE);

    assert(90 == max(xs));
    assert(0 == max(std::vector<int>()));

    return 0;
}
