object Greeter {
    fun hello() {
        println("Hello, world!")
    }
}

fun main(args: Array<String>) {
    try {
        val mapData = mapOf("a" to 1, "b" to 2)
        for ((key, value) in mapData) {
            println("$key -> $value")
        }


        val powerSequence = generateSequence(1, { it * 2 })
        val x = powerSequence.take(10).toList()
        println(x)

        val z = x.map { it + 1 }
                  .filter { it <= 32 }
                  .groupBy { it <= 8 }
                  .mapKeys { if (it.key) "small" else "big" }
        println(z)


        data class ScreenSize(var width_res: Int, var height_res: Int, val diag: Double)
        fun print_screen_size(screen: ScreenSize) {
            println("Screen resolution is ${screen.width_res} x ${screen.height_res}, with ${screen.diag}'' diagonal")
        }

        val my_monitor = ScreenSize(1920, 1080, 12.5)
        print_screen_size(my_monitor)
        with (my_monitor) {
            width_res  = 1600
            height_res = 900
        }
        print_screen_size(my_monitor)

        Greeter.hello()
    }
    catch (err: Throwable) {
        println("Error!")
        println(err.message)
    }
}
