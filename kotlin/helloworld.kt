fun main(args: Array<String>) {
    println("Hello, World!")

    try {
        val values = readLine()!!.split(' ')
        println("Looking for ${values[0]} in range [${values[1]}, ${values[2]}]")
        val range = values[1].toInt()..values[2].toInt()
        val lookup = values[0].toInt()

        var found: Boolean = false
        label@ for(j in range) {
            if (j == lookup) {
                println("Found it!")
                found = true
                break@label
            } else {
                continue@label
            }
        }

        if (found == false) {
            println("Not found")
        }

    } catch(e : Exception) {
        println("Exception $e")
    }

}
