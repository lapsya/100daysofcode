fun Any?.notnull(): Boolean {
    return this != null
}

class Person(
    var firstName: String,
    var lastName: String,
    var middleName: String? = null,
    private var _age: Int? = null
) {
    init {
        print("Hello,")
        for (name in listOfNotNull(this.firstName, this.middleName, this.lastName)) {
            print(" $name")
        }
        println("!")
    }

    val totalLength: Int
        get() {
            return listOfNotNull(this.firstName, this.middleName, this.lastName).sumBy { it.length }
        }

    val hasMiddleName
        get() = this.middleName.notnull()

    val hasAge by lazy {
        this._age.notnull()
    }

    constructor(nameString: String) : this(nameString.split(" ").component1(),
                                            nameString.split(" ").component2()) {
        println("You've been initialized from a string")
    }

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
