import kotlin.properties.Delegates

class Dog {
    var nameChangedCount = 0
        private set

    var name: String by Delegates.vetoable("Fido") { property, oldValue, newValue ->
        if (newValue == "Cat") false else {
            nameChangedCount++
            true
        }
    }

    val isConfused: Boolean get() = nameChangedCount >= 3
}

fun main() {
    val dog = Dog()
    println("Dog name should be Fido, is ${dog.name}")
    println("Name changed count should be 0, is ${dog.nameChangedCount}")
    println("Is confused should be false, is ${dog.isConfused}\n")

    dog.name = "Ben"
    println("Dog name should be Ben, is ${dog.name}")
    println("Name changed count should be 1, is ${dog.nameChangedCount}")
    println("Is confused should be false, is ${dog.isConfused}\n")

    dog.name = "Rex"
    println("Dog name should be Rex, is ${dog.name}")
    println("Name changed count should be 2, is ${dog.nameChangedCount}")
    println("Is confused should be false, is ${dog.isConfused}\n")

    dog.name = "Fifi"
    println("Dog name should be Fifi, is ${dog.name}")
    println("Name changed count should be 3, is ${dog.nameChangedCount}")
    println("Is confused should be true, is ${dog.isConfused}\n")

    dog.name = "Cat"
    println("Dog name should be Fifi, is ${dog.name}")
    println("Name changed count should be 3, is ${dog.nameChangedCount}")
    println("Is confused should be true, is ${dog.isConfused}\n")
}
