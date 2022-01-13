import kotlin.random.Random

private val random: Int by lazy {
    println("Generating value")
    Random.nextInt(100)
}

fun main() {
    // Write your solution here
    println(random)
    println(random)
}
