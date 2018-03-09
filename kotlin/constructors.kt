class Person(val firstName: String, var lastName: String, var middleName: String?) {
    var totalLength: Int = 0

    init {
        print("Hello,")
        for (name in listOf(firstName, middleName, lastName)) {
            totalLength += name?.length ?: 0
            if (name != null) {
                print(" $name")
            }
        }
        println("!")
    }

    var age: Int = 0
    constructor(firstName: String, lastName: String, middleName: String?, age: Int): this(firstName, lastName, middleName) {
        this.age = age
        println(age)
    }

}

fun main(args: Array<String>) {
    val p = Person("Maria", "Summer", null)
    val p2 = Person("Maria", "Winter", "January", 21)

    //println(p2.firstName)
    println(p.firstName)
    println(p.totalLength)

    println(p2.age)
}
