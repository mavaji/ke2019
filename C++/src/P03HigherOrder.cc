// g++ P03HigherOrder.cc && ./a.out
#include <functional>
#include <cassert>

// We can't define inner method in standard C++ compilers
// (only GCC's C compiler is able to and not in C++ mode)
int g(int x) { return x - 3; }

int main(int argc, char** argv) {
    auto f = [](int x) -> int { return x + 1; };
    
    auto h = [](std::function<int (int)> f, std::function<int (int)> g, auto x) {
        return f(g(x));
    };

    assert(2 == h(f, g, 4));

    return 0;
}
