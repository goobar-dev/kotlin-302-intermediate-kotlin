package lesson08_standard_library

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

// Kotlin Standard Library


// Collections
fun main1() {
    val list = listOf("Java", "Kotlin", "C++", "Javascript", "Go", "Rust")
    val set = setOf("Java", "Kotlin", "C++", "Javascript", "Go", "Rust", "Java", null)
    val map = mutableMapOf("dog" to 10, "cat" to 5)

    set
        .filterNotNull()
        .filter { language -> language.length > 3 }
        .sortedBy { it.length }
        .map { it.length }
        .takeLast(3)

    list.toMutableList().apply {
        add(3, "C#")
    }

    list.toSet()

    println(list.joinToString(" "))

    list.forEachIndexed { index, language -> println("$index: $language") }

    val emptyList: List<Int> = emptyList()
    emptyList.getOrElse(0) {
        -1
    }
    emptyList.getOrNull(0)

    map.getOrDefault("snake", "")
}


// Strings
fun main2() {
    val builder = buildString {
        append("Hooray")
        append(" ")
        append(4)
        append(" Kotlin!")
    }
    println(builder.toString())

    val formattedString = """
        We can do a multi-line string
        With formatting
    """.trimIndent()
    println(formattedString)

    val regEx = "[1-9]+".toRegex()
    println(regEx.matches("999"))
    println(regEx.matches("0"))

    val someString = "  I'm a sample string "
    println("trimStart(): ${someString.trimStart()}")
    println("trimEnd(): ${someString.trimEnd()}")
    println("trim(): ${someString.trim()}")
    println("reversed(): ${someString.reversed()}")
    println("lowercase(): ${someString.lowercase()}")
    println("uppercase(): ${someString.uppercase()}")
    someString.split(" ").forEach { println(it) }
}


// Utilities

fun main3() {
    // measure how long some operation takes
    val durationNano = measureNanoTime {
        // do something
    }
    val durationMillis = measureTimeMillis {  }

    // get access to the current version of kotlin
    println(KotlinVersion.CURRENT)

    // Pair and Triple for structured values
    val pair = Pair<String, String>("key", "value")
    val pair2 = "key" to "value"
    val triple = Triple(10, 11, 12)

    // check if a lateinit variable is initialized before using it
    class View {
        lateinit var clickHandler: () -> Unit

        fun onAction() {
            if (::clickHandler.isInitialized) { clickHandler() }
        }
    }

    // state assertions
    fun updateTestScore(studentId: String?, score: Double) {
        checkNotNull(studentId)
        check(studentId.isNotEmpty())
        check(score >= 0) {
            "A test score must be >= 0"
        }
    }
}