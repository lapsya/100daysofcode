class Person(var firstName: String, var lastName: String, var middleName: String? = null, var age: Int? = null) {
    init {
        print("Hello,")
        for (name in listOf(this.firstName, this.middleName, this.lastName)) {
            if (name != null) {
                print(" $name")
            }
        }
        println("!")
    }

    constructor(nameString: String): this(nameString.split(" ").component1(),
                                            nameString.split(" ").component2()) {
        println("You've been initialized from a string")
    }

    val totalLength: Int
        get() {
            return listOfNotNull(this.firstName, this.middleName, this.lastName).sumBy { it.length }
        }

    val hasMiddleName: Boolean
        get() = this.middleName != null

    val hasAge
        get() = this.age != null
}

fun main(args: Array<String>) {
    val p1 = Person("Maria", "Summer")
    val p2 = Person("Maria", "Winter", "January", 21)
    val p3 = Person("Maria Spring")

    for (person in listOf(p1, p2, p3)) {
        println("Has middle name: ${person.hasMiddleName}")
        println("Total name length: ${person.totalLength}")
        println("Has age: ${person.hasAge}")
        println("---")
    }
}
