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
        for (name in listOfNotNull(firstName, middleName, lastName)) {
            print(" $name")
        }
        println("!")
    }

    val totalLength: Int
        get() {
            return listOfNotNull(firstName, middleName, lastName).sumBy { it.length }
        }

    val hasMiddleName
        get() = middleName.notnull()

    val hasAge by lazy {
        this._age.notnull()
    }

    constructor(nameString: String) : this(nameString.split(" ").component1(),
                                            nameString.split(" ").component2()) {
        println("You've been initialized from a string")
    }

    override fun toString() =  listOfNotNull(firstName, middleName, lastName)
                                .joinToString(separator=" ")

}

abstract class RelationShip(
    val personA: Person,
    val personB: Person,
    var daysActive: Int? = null
) {
    abstract val name: String
    var isActive = true

    fun printRelationshipStatus() {
        if (isActive) {
            if (daysActive.notnull()) {
                println("$personA and $personB have been $name for $daysActive days")
            }
            else {
                println("$personA and $personB are $name")
            }
        }
        else {
            print("$personA and $personB are no longer $name.")
            if (daysActive.notnull()) {
                println(" The relationship lasted $daysActive days")
            }
            else {
                println(" ")
            }
        }
    }

    fun newDay() {
        if (isActive) {
            daysActive = daysActive?.let { it + 1 }
        }
        else {
            println("The relationship you're trying to change is over")
        }
    }
}


class FriendShip(
    personA: Person,
    personB: Person,
    daysActive: Int? = null
): RelationShip(personA, personB, daysActive) {
    init {
        println("FriendShip established!")

    }

    override val name = "friends"
}


class Love(
    personA: Person,
    personB: Person,
    daysActive: Int? = null,
    val previousFriendShip: FriendShip? = null
): RelationShip(personA, personB, daysActive) {
    init {
        println("Love is in the air!")
        if (previousFriendShip.notnull()) {
            previousFriendShip?.isActive = false
        }
    }

    override val name = "in love"
}

// this is obviously just a demonstration
val Love.extenstionProperty
    get() = "I'm an extension"

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

    val f1 = FriendShip(p1, p2)
    f1.printRelationshipStatus()
    val f2 = FriendShip(p2, p3, 42)
    f2.printRelationshipStatus()

    val l1 = Love(p2, p3, 73, f2)
    l1.printRelationshipStatus()

    l1.newDay()
    l1.printRelationshipStatus()
    l1.previousFriendShip?.printRelationshipStatus()
    f2.printRelationshipStatus()

    l1.previousFriendShip?.newDay()

    println(l1.extenstionProperty)
}
