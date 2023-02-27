import kotlin.random.Random

/**
 * Review the Description Tool Window for more
 * exercise details
 */

private val random: Int by lazy {
    println("Generating value")
    Random.nextInt(100)
}

fun main() {
    // Write your solution here
    println(random)
    println(random)
}
