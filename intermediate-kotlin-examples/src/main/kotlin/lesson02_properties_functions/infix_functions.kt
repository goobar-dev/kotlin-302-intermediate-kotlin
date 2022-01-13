package lesson02_properties_functions

// Infix Functions
// https://kotlinlang.org/docs/functions.html#infix-notation

// Functions declared with the `infix` modifier can be called using infix notation.
// Infix notation is a way of invoking a member function with a single parameter
// without having to use any parentheses or . notation

// The to function is a good example
// Both these lines are invoking the same to() function
val aPair = 5.to("") // invoke to() as regular member function
val alsoAPair = 5 to "" // invoke to() using infix notation

// Because of the special syntax, infix functions require several things
// must be defined as member functions or extension functions. A top-level function not associated with a type won't work
// must have a single parameter.  With more than one, the syntax breaks down
// the parameter cannot have a default value, and cannot use vararg

// We can define our own infix functions by applying the infix modifier to a function matching the above conditions

infix fun <T> T.isEqualTo(other: T) = this == other

val result = 5 isEqualTo 10
val result2 = "Kotlin".isEqualTo("Java")
val result3 = "Javascript" isEqualTo "Java"

// The function receiver and parameter do not have to share a type

data class Task(val title: String)
data class TaskContainer(private val tasks: MutableList<Task>) {
    infix fun addToContainer(task: Task) {
        tasks += task
    }
}

fun main() {
    val container = TaskContainer(mutableListOf())
    container addToContainer Task("Walk the dog")
    container addToContainer Task("Do the laundry")

    println(container.toString())
}