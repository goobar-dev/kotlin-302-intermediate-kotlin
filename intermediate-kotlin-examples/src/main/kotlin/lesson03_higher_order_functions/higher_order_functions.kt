package lesson03_higher_order_functions

// Higher-Order Functions

// In Kotlin, functions are first class citizens. This means they can be
// stored in variables and data structures, can be passed as in as parameters
// to functions, and can be returned from functions.

// Basically you can operate with functions in any way that's possible for
// other non-function values.

// For example, the type of onClick here is a function that takes a String parameter and returns unit
// The curly braces here represent a lambda - which is essential
val onClick: (String) -> Unit = { clickedString ->
    // handle click
}

// We can also use a typealias to reference a functional type
typealias ClickHandler = () -> Unit
val clickHandler: ClickHandler = {
    // handle click
}

// Functional variables and properties can be used as if we were invoking the function directly
val add = { x: Int, y: Int -> x + y }
val subtract = fun(x: Int, y: Int) = x - y

val additionResult = add(3, 4)
val subtractionResult = subtract(10, 5)



// Because we can represent functional types, we can also then pass these functions to other functions
// Functions that take functions as parameters are known as higher-order functions.

// We see these commonly in the Kotlin Standard Library
// Both filter() and map() are higher order functions
val items = listOf("Kotlin", "Java", "C++").filter { it.length > 3 }.map { it.length }


// To define a higher-order function, we define a function as we normally would
// We then declare a function parameter to have a functional type
fun filterListItems(list: List<String>, filterFunc: (String) -> Boolean): List<String> {
    return list.filter(filterFunc)
}

val languages = listOf("Kotlin", "Java", "C++")

// To use the function, we then pass a lambda as our functional parameter
val filteredLanguages = filterListItems(languages, { it -> it.length > 3 })

// Notice the use if `it` in the previous example
// It's common for lambdas to have a single parameter
// In these cases, that parameter is implicitly made available with the name `it`
// In cases with more than one parameter, or if you would prefer to be more explicit, you can rename the parameter

// To make the code easier to read, we can use "trailing lambda syntax"
// If the last argument to a function is a function, we can pass the lambda outside the parentheses
val filterWithTrailingLambda = filterListItems(languages) { language -> language.length > 4}

// Lambda expression don't allow for an explicit return type
// Most of the time the return type can be inferred
// However, if we need greater control over the return, we can use an anonymous function
val filterWithAnonymousFunction = filterListItems(languages, fun(language): Boolean = language.length > 3)
val filterWithAnonymousFunction2 = filterListItems(
    list = languages,
    filterFunc = fun(language: String): Boolean { return language.length > 3 }
)


// Function literals with a receiver are a special form of higher-order function
// In this format, the higher order function is called on an instance of a type
// That instance is then made available to the higher-order function as a receiver

class HTML {
    fun body() {}
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the instance of HTML to the lambda as the receiver
    return html
}

val doc = html {       // calls the html function
    body()   // calling a method on the receiver object - in this case, and instance of HTML
}


// When higher-order functions are compiled to bytecode, they're represented by Function objects
// This objects must also capture any variables that are within scope of the lambda
// These captures and function calls introduce runtime overhead

// In many situations however, that runtime overhead can be avoided by inlining the function
// This means, that rather than calling a passed function, the compiler will inline the implementation
// of that function, thereby avoiding the runtime overhead.

fun safeCall(action: () -> Unit ) {
    try {
        action()
    } catch (error: Throwable) {
        // log
    }
}

inline fun inlineSafeCall(action: () -> Unit ) {
    try {
        action()
    } catch (error: Throwable) {
        // log
    }
}

fun inlineExample() {
    // will result in an instance of Function0
    // and a method call to safeCall
    safeCall {
        val scores = listOf(10, 8, 9, 4, 3)
        scores.forEach { println(it) }
    }

    // the lambda body will be inlined into the body of
    // inlineExample() thereby avoiding some of the runtime overhead
    inlineSafeCall {
        val scores = listOf(10, 8, 9, 4, 3)
        scores.forEach { println(it) }
    }
}

