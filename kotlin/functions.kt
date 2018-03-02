
fun not(f: (Int) -> Boolean): (Int) -> Boolean {
    return {n -> !f.invoke(n)}
}

fun main(args: Array<String>) {

    val num = args[0].toInt()

    fun is_odd(n: Int): Boolean = n % 2 == 1
    println("Is it odd? ${is_odd(num)}")

    val is_even = not(::is_odd)
    println("Is it even? ${is_even(num)}")
}
