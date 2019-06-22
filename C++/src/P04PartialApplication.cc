// g++ P04PartialApplication.cc && ./a.out
#include <functional>
#include <cassert>

int main(int argc, char** argv) {
    auto f = [](int x) -> std::function<int (int)> {
        return [x](int y) -> int { return x + y; };
    };

    auto partial1 = f(1);
    assert(4 == partial1(3));

    assert(9 == f(4)(5));

    return 0;
}
