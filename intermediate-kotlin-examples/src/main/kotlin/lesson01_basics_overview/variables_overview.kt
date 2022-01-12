package lesson01_basics_overview

fun main() {
    // Kotlin has two types of variables

    // mutable variables are defined using the var keyword
    var aMutableVariable = 5
    aMutableVariable = 10 // we can reassign the value

    // read-only variables are defined using the val keyword
    val aReadOnlyVariable = 5
    // aReadOnlyVariable = 6 // not allowed

    // variable initialization can be separate from definition
    // as long as the variable is not accessed before it's initialized
    val id: String
    id = "123abc" // this is okay

    val id2: String
    // println(id2) // not okay. variable not initialized
    id2 = "123abc"

    // the lateinit modifier allows us to defer initialization of the variable
    // until a later time.  The compiler won't give a compile-time warning
    // if a lateinit variable has not been initialized.
    // In this example, this would throw a runtime exception due to the variable
    // not being initialized
    lateinit var id3: String
    println(id3)

    // variable types may be inferred by the compiler
    val name = "Kotlin" // inferred type of String

    // or variable types by be explicitly defined
    val count: Int = 5 // explicit type of Int
}