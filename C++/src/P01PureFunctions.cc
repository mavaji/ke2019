// g++ P01PureFunctions.cc && ./a.out
// (or clang++)
#include <iostream>
#include <functional>
#include <cstdlib>
#include <ctime>
#include <cassert>

auto impure1(int x) -> int {
    std::cout << "x=" << x;
    return x;
}

auto impure2(int x) -> int {
    srand(time(NULL));
    return x * rand();
}

// Even the fact reassignment itself is considered out of
// functional programming style, we tried to port this to
// something to show what really and unnoticeably, can go
// wrong with it.
auto impure3(int &x) -> int {
    x = 4;
    return x;
}
// Or:
// auto impure3(int *x) -> int * {
//     *x = 4;
//     return x;
// }

auto impure4(int x) {
    std::cout << x + 3;
}

int y = 1;

auto impure5() -> int {
    return y + 1;
}

// Same as impure3
auto impure6(int x) -> int* {
    int *y = new int; 
    *y = x + 1;
    return y;
}

constexpr int x = 4;

auto add1() -> std::function<int (int)> {
    return [](int y) { return y + x; };
}
auto add1f(int y) -> int { return y + x; }

// Roughly as we may can't return std::function of std::function
// easily as the special way C++ lambdas work, but one may could
// achieve it using pure C's function pointers easier.
auto add2() -> std::function<int (int)> {
    auto f = [](int y) { return [y](int x) { return y + x; }; };
    return f(3);
}
auto add2f(int y) -> std::function<int ()> {
    return [y]() { return y + 3; };
}
auto add2ff(int y) -> int { return y + 3; };

auto add3(int w) -> int {
    auto f = [](int y) {
        auto f = [y](int x) { return y + x; };
        return f(1);
    };
    return f(2);
}
auto add3f(int w) -> int {
    int y = 2;
    auto f = [y](int x) { return y + x; };
    return f(1);
}
auto add3ff(int w) -> int {
    int y = 2;
    return y + 1;
}
auto add3fff(int w) -> int { return 3; }
auto add3ffff() -> int { return 3; }

int main(int argc, char** argv) {
    int x = 4;

    assert(5 == add1()(1));
    assert(5 == add1f(1));

    assert(4 == add2()(1));
    assert(4 == add2f(1)());
    assert(4 == add2ff(1));

    assert(3 == add3(1));
    assert(3 == add3f(1));
    assert(3 == add3ff(1));
    assert(3 == add3fff(1));
    assert(3 == add3ffff());

    assert(3 == add3(2));
    assert(3 == add3f(2));

    return 0;
}
