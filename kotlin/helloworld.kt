fun main(args: Array<String>) {
    println("Hello, World!")

    try {
        val values = readLine()!!.split(' ')
        val range = values[1].toInt()..values[2].toInt()
        val lookup = values[0].toInt()

        println("Looking for $lookup in range $range")
        var found = false
        label@ for(j in range) {
            if (j == lookup) {
                println("Found it!")
                found = true
                break@label
            } else {
                continue@label
            }
        }

        if (!found) {
            println("Not found")
        }

    } catch(e: Exception) {
        println("Exception $e")
    }

}
