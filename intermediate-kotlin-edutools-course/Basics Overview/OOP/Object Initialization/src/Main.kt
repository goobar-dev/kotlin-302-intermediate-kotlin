/**
 * Review the Description Tool Window for more
 * exercise details
 */

object Declared {
    val id = 0
}

class TopLevel {
    val foo = ""

    private val anon = object {
        val x: Int = 42
    }

    fun bar() {
        println("Declared.id = ${Declared.id}, anon.x ${anon.x}")

    }

    companion object {
        val importantThing = "passport"
    }
}

fun main() {
    TopLevel().bar()
}
