
data class Student(
    val firstName: String,
    val lastName: String
)

val Student.displayName
    get() = "$firstName $lastName".toLowerCase()

fun main() {
    val student = Student("Tony", "Stark")
    println(student.displayName)
}
