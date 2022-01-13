package lesson02_properties_functions

// Top-Level Properties & Functions

// Kotlin supports top-level properties and functions
// What does that mean?
// It means we can define properties and functions that aren't scoped to a specific class
// This is in contrast with Java where everything must exist within a class

// This is significant because it opens up new possibilities for how we organize our code

// Defining a top-level function simply means writing a function in any .kt file - outside a class
fun topLevelFunction() = println("I don't have an enclosing class")

// Defining a top-level property/variable works much the same
val topLevelVariable = 5


// Being able to define properties and functions within any .kt file is very flexible
// It also has the potential to pollute the global namespace
// Standard visibility modifier rules apply to top-level elements just as they would with class elements

private fun privateTopLevelFunction() = println("I'm only available within this file")
private var privateTopLevelVariable = "I'm only available within this file"


// Top-level functions can serve as a replacement for "helper classes" prominent in the Java world
// Rather than having to create a class such as StringHelpers,
// we can define a file string_helpers.kt and define top-level functions