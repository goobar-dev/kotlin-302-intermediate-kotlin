/**
 * Review the Description Tool Window for more
 * exercise details
 */

fun greetThings(
    greeting: String = "Hello",
    vararg thingsToGreet: String
) {
    for (thing in thingsToGreet) {
        println("$greeting $thing")
    }
}

fun main() {
    greetThings(
        greeting = "Hey",
        "Java",
        "Kotlin",
        "Coding"
    )

    val thingsToGreet = arrayOf("Dogs", "Cats", "Birds")
    greetThings(
        thingsToGreet = thingsToGreet,
        greeting = "I like",
    )
}
