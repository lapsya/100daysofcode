object Greeter {
    fun greet() {
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
        fun printScreenSize(screen: ScreenSize) {
            println("Screen resolution is ${screen.width_res} x ${screen.height_res}, with ${screen.diag}'' diagonal")
        }

        val myMonitor = ScreenSize(1920, 1080, 12.5)
        printScreenSize(myMonitor)
        with (myMonitor) {
            width_res  = 1600
            height_res = 900
        }
        printScreenSize(myMonitor)

        Greeter.greet()
    }
    catch (err: Throwable) {
        println("Error!")
        println(err.message)
    }
}
