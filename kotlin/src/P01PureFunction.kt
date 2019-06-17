object P01PureFunction {

    fun impure1(x: Int): Int {
        print("x=${x}")
        return x
    }

    fun impure2(x: Int): Double {
        return x * Math.random()
    }

    fun impure3(x: Int): Int {
//        x = 4
        return x
    }

    fun impure4(x: Int) {
        print(x + 3)
    }

    fun impure5(): Int {
        return x + 1
    }

    fun impure6(x: Int): Int {
        var y = x + 1
        return y
    }

    val x = 4

    val add1: (Int) -> Int = { y -> y + x }
    fun add1f(y: Int): Int = y + x

    val add2: (Int) -> Int = { y: Int -> { x: Int -> y + x } }(3)
    fun add2f(y: Int): Int = { x: Int -> y + x }(3)
    fun add2ff(y: Int): Int = y + 3

    val add3: (Int) -> Int = { w -> { y: Int -> { x: Int -> y + x }(1) }(2) }
    fun add3f(w: Int): Int = { y: Int -> { x: Int -> y + x }(1) }(2)
    fun add3ff(w: Int) = { x: Int -> 2 + x }(1)
    fun add3fff(w: Int) = 3
    fun add3ffff() = 3

    @JvmStatic
    fun main(args: Array<String>) {
        println("add1 1 = ${add1(1)}")
        println("add1f 1 = ${add1f(1)}")
        println("**************************************************************")

        println("add2 1 = ${add2(1)}")
        println("add2f 1 = ${add2f(1)}")
        println("add2ff 1 = ${add2ff(1)}")
        println("**************************************************************")

        println("add3 1 = ${add3(1)}")
        println("add3f 1 = ${add3f(1)}")
        println("add3ff 1 = ${add3ff(1)}")
        println("add3fff 1 = ${add3fff(1)}")
        println("add3ffff 1 = ${add3ffff()}")
        println("**************************************************************")

        println("add3 2 = ${add3(2)}")
        println("add3f 2 = ${add3f(2)}")
        println("**************************************************************")

    }
}