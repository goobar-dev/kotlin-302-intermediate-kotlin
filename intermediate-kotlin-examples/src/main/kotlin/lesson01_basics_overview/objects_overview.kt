package lesson01_basics_overview

// Object Declarations

// If we use the object keyword by itself, we create a Singleton

// This object must be named.
// Can't be local (nested in a function)
// Isn't an expression: Can't do `val foo = object UpdateManager { ... }`

// Object classes are initialized lazily; when first accessed

object UpdateManager {
    const val version: String = "4.2.0"

    fun checkForUpdates() = Unit // Unit is also an object
}

// Can access them by using the object name
fun main1() {
    println(UpdateManager.version)

    UpdateManager.checkForUpdates()
}

// Object classes can extend other types and may implement interfaces

interface Dispenser {
    fun dispense()
}

open class Beverage(val calories: Int)

// Objects can have super types and implement interfaces
object WaterCooler : Beverage(calories = 0), Dispenser {
    override fun dispense() = println("Blub")
}

// Tools -> Kotlin -> Show bytecode -> Decompile to see what's going on

// Object Expressions


fun main() {
    // may declare an unamed object with an object expression
    val dispenser = object : Dispenser {
        override fun dispense() {
            TODO("Not yet implemented")
        }

    }

    // We can also declare an object with no super type
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }

    // Object expressions are executed/initialized where they are declared
    print(adHoc.x + adHoc.y)
}