package greet

class Greeter(val name: String) {

    fun greet() {
        println("Hello, ${name}!")
    }
}