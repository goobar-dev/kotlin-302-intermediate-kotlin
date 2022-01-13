package lesson01_basics_overview

import kotlin.random.Random

fun main() {
    // Functions in Kotlin are very flexible

    // A basic function only requires a couple of things
    // the fun keyword
    // a name

    // this is a valid function - though it doesn't do anything
    fun basicFunction() {}

    // all functions return something
    // however, if no meaningful value is returned, the implicit return
    // type is Unit, and it can be omitted

    // these functions are equivalent
    // Kotlin allows us to skip the extra verbosity and use the basic version above
    fun basicFunctionWithReturn(): Unit {}
    fun basicFunctionWithExplicitReturn(): Unit {
        return Unit
    }

    // if a function body can be defined in a single expression
    // we can define our functions as single-expression functions
    fun getGreeting(): String = "Hello"
    fun greet() = println("${getGreeting()} World")

    // to explicitly return a meaningful value, we use the return keyword
    fun getRandom(): Int {
        return Random.nextInt(100)
    }

    // if we need to pass values to a function, we can define parameters
    // parameters are defined with name first, colon, then type
    fun basicGreeting(thingToGreet: String) {
        println("Hello $thingToGreet")
    }
    basicGreeting("Kotlin")

    // we can define multiple parameters
    fun betterGreeting(greeting: String, thingToGreet: String) {
        println("$greeting $thingToGreet")
    }
    betterGreeting("Hello there", "Kotlin")

    // we can also use vararg to allow passing a variable number of arguments
    // of a given type
    fun varargGreeting(greeting: String, vararg thingsToGreet: String) {
        for (thing in thingsToGreet) {
            println("$greeting $thing")
        }
    }
    varargGreeting("Hello there", "Kotlin", "World", "Coders")

    // if a parameter may sometimes be omitted, or has a sensible default
    // we can provider a default value for that parameter
    // if a parameter has a default value, then we do not have to pass that argument
    // when invoking the function

    fun defaultGreeting(greeting: String, thingToGreet: String = "Kotlin") {
        println("$greeting $thingToGreet")
    }
    defaultGreeting("Hey!") // will print "Hey! Kotlin"
    defaultGreeting("Hello there") // will print "Hello there Kotlin"

    // if we have multiple parameters of the same type
    // some with default values, and some with not
    // the compiler can't always infer correctly which parameters should use defaults

    // we can address this by using named arguments
    // named arguments allow us to explicitly indicate which parameter a value
    // should be associated with

    fun varargGreetingWithDefaults(greeting: String = "Hello", vararg thingsToGreet: String) {
        for (thing in thingsToGreet) {
            println("$greeting $thing")
        }
    }
    varargGreetingWithDefaults(
        greeting = "Hey",
        "kotlin",
        "java"
    )
    val thingsToGreet = arrayOf("Kotlin", "Java", "C++")
    varargGreetingWithDefaults(thingsToGreet = thingsToGreet, greeting = "Hello")


}