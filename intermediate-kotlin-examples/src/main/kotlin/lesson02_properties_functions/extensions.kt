package lesson02_properties_functions

// Extension Functions & Properties

// Extension functions & properties allow us to extend types - even ones we don't define ourselves


// Extension Functions
// Extension functions can be defined as top-level functions, or as a class method

// Let's say we want to create a function to map domain objects between layers of our application
data class StudentEntity(val id: String, val firstName: String,  val lastName: String)
data class StudentViewItem(val displayName: String)

// we could write a stand-alone conversion function
fun studentEntityToStudentViewItem(entity: StudentEntity): StudentViewItem {
    return StudentViewItem("${entity.firstName} ${entity.lastName}")
}

// this conversion function has some potential drawbacks however
// discoverability can be difficult
// when made public, it can pollute the global namespace

// to help with these concerns, we could rewrite this as an extension function
fun StudentEntity.toViewItem() = StudentViewItem("$firstName $lastName")

// this makes the function easier to discover since most IDEs will suggest it if after adding a . when
// working with an instance of StudentEntity
// this also avoids as much cluttering of the global namespace since the function is only available on
// an instance of StudentEntity

// We can extend types we don't own, and we can apply our usual visibility modifiers
internal fun Int.doubleIt() = this * 2


// Extension Properties
// Extension properties work similarly to extension functions
// They allow us to extend a type by adding properties that can be accessed on instances of that type
// The actual extended properties aren't defined in the type declaration, but rather as external declarations

private val StudentViewItem.headerDisplayName: String
    get() = displayName.uppercase()

// when defining an extension property, we can use both var and val
// because extension properties are not actually inserted into the class, they cannot support initializers
// this means we must define explicit getters/setters when defining our extension
// within the extension declaration, the instance of the type we're extending is available as a receiver
// this gives us implicit access to the public properties/methods of that instance


fun main() {
    val entity = StudentEntity("123", "Peter", "Parker")
    val viewItem = studentEntityToStudentViewItem(entity)
    println(viewItem.displayName)

    val viewItem2 = entity.toViewItem()
    println(viewItem2.displayName)

    768.doubleIt()

    println("headerDisplayName = ${viewItem.headerDisplayName}")
}