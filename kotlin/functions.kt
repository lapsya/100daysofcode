fun not(f: (Int) -> Boolean): (Int) -> Boolean {
    return {n -> !f(n)}
}

fun main(args: Array<String>) {

    val num = args[0].toInt()

    val isOdd = { n: Int -> n % 2 == 1 }
    println("Is it odd? ${isOdd(num)}")

    val isEven = not(isOdd)
    println("Is it even? ${isEven(num)}")
}
