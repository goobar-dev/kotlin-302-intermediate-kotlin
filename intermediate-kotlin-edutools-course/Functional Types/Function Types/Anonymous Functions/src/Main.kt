
private val languages = listOf(
    "Kotlin",
    "Java",
    "C++",
    "Javascript",
    "Rust",
    "Go",
    "Python"
)

fun main() {
    // Filter the list so only languages whose name is less than 6 characters long are printed.
    val shortNamedLanguages = languages.filter(fun(language: String) = language.length < 6)
    shortNamedLanguages.forEach { println(it) }
}
