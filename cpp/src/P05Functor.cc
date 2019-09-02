// g++ P05Functor.cc -std=c++17 && ./a.out
#include <optional>
#include <functional>
#include <cassert>

template <typename A, typename B>
auto fmap(std::function<A (B)> f, std::optional<A> fa) -> std::optional<B> {
    return fa.has_value()
        ? std::optional<B>(f(fa.value()))
        : std::optional<B>();
}

int main(int argc, char** argv) {
    std::optional<int> some2(2);
    assert(2 == some2.value());
    // http://www.open-std.org/jtc1/sc22/wg21/docs/papers/2017/p0798r0.html
    // map isn't implemented in std::optional yet
    
    std::optional<int> none{};
    assert(false == none.has_value());

    auto f = [](int x) -> int { return x + 2; };
    { 
        int result = fmap<int, int>(f, some2).value();
        assert(4 == result);
    }
    {
        bool has_value = fmap<int, int>(f, none).has_value();
        assert(!has_value);
    }

    return 0;
}
