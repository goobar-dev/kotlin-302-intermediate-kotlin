package lesson07_testing

import junit.framework.*
import org.amshove.kluent.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.random.Random
import kotlin.test.*
import kotlin.test.Test as KotlinTest  // import as KotlinTest to avoid name conflict

// Testing

// Testing with Kotlin is an interesting story
// If you're using Kotlin to build a JVM application, you're free to use whichever Java/Kotlin testing
// libraries you're used to.

// For JVM applications, JUnit is the primary testing framework
// To create a basic JUnit test
// - we need to ensure JUnit dependencies are added to the project
// - we then need to create a test class
// - finally we can write methods, and annotate them with @Test

class SimpleJUnitTestClass {

    @Test
    fun test() {
        assert(true)
    }

}


// Because Kotlin support multiplatform development, the kotlin.test package provides
// annotations and test utilities for writing test code in a platform-target-independent way.
class KotlinTest {
    @KotlinTest
    fun test() {
        assert(true)
    }
}

// Take a look at build.gradle.kts
//tasks.test {
//    useJUnit()
//}
// The project is configured to use JUnit under the hood
// Even if using the kotlin.test utilities, JUnit will drive the tests behind the scenes

// If this was a multiplatform project, we could provide other test engines where appropriate for other targets


// To run our tests, we have a couple of options
// 1. run any of the verification Gradle tasks (test, check)  ex. ./gradlew check
// 2. click the run button next to a single test
// 3. click the run button next to a test class to run all tests on that class

// Within the test source set, Kotlin allows us to use a special naming syntax with backticks
// If we wrap our test function name in backticks, we can use spaces in the name
// This breaks typical naming conventions, but helps write human-readable test names
class BacktickNamingExamples() {
    @Test
    fun `random is digit`() {
        val random = getRandomDigit()
        assert(random in 0..9)
    }
}

// kotlin.test provides a number of assertion functions
class AssertionExamples {
    @Test
    fun `basic assertion`() {
        assert(true) // assert that a boolean expression evaluates to true
    }

    @Test
    fun `explicit assertions`() {
        // assertTrue or assertFalse using lambda for the boolean condition
        assertFalse { 5 == 3 }

        // most kotlin.test assertions support an optional message
        // that message can help give more detailed error messages when looking through test reports
        assertTrue(
            actual = 5 == 3,
            message = "5 does not equal 3"
        )
    }

    @Test
    fun `assertions on collections`() {
        // use assertContains to check if a Range/Collection/Sequence contains a specific value
        val languages = listOf("Java", "Kotlin", "C++")
        assertContains(languages, "Kotlin")

        assertContains(0..10, 5)

        // use assertContentEquals to compare the content of 2 Range/Collection/Sequence
        assertContentEquals(listOf(1,2,3), listOf(1,2,3))

        val alphabet = mapOf("a" to 1, "b" to 2, "c" to 3)
        assertContains(alphabet, "d", "alphabet map didn't contain d")
    }
}


// For mocking in JVM projects, the go-to tool is Mockito
// If you're coming from a Java and/or Android background, you're likely already familiar with Mockito

interface StringProvider {
    fun getString(id: Int): String
}

class MockitoExamples {
    @Test
    fun `StringProvider can be mocked with Mockito`() {
        val mockStringProvider = Mockito.mock(StringProvider::class.java)
        Mockito.`when`(mockStringProvider.getString(5)).thenReturn("Hello World!")
        Mockito.`when`(mockStringProvider.getString(3)).thenReturn("Hello Kotlin!")

        assertTrue {
            mockStringProvider.getString(3) == "Hello Kotlin!"
        }

        assertEquals("Hello World", mockStringProvider.getString(5))
    }
}

// Setting up mocks with Mockito can be verbose and complicated at times
// To improve this, we can use mockito-kotlin which makes use of Kotlin's type-safe builders to
// provide a more Kotlin-idiomatic syntax for mocking

class MockitoKotlinExamples {
    @Test
    fun `StringProvider can be mocked with Mockito`() {
        val mockStringProvider = mock<StringProvider> {
            on { getString(5) } doReturn "Hello World!"
            on { getString(3) } doReturn "Hello Kotlin!"
        }

        assertTrue {
            mockStringProvider.getString(3) == "Hello Kotlin!"
        }

        assertEquals("Hello World", mockStringProvider.getString(5))
    }

    @Test
    fun `StringProvider is called when action is taken`() {
        val mockStringProvider = mock<StringProvider> {
            on { getString(anyInt()) } doReturn "a mocked string"
        }
        val viewModel = ViewModel(mockStringProvider)

        viewModel.doSomething()

        verify(mockStringProvider).getString(anyInt())
    }
}


// There are other testing utility libraries available to help take advantage of Kotlin features
// One such library is kluent which provides human-readable, fluent assertion functions

class KluentExamples {
    @Test
    fun `kluent assertions`() {
        "Kotlin".shouldBeEqualTo("Kotlin")
        "Kotlin".shouldBeEqualToIgnoringCase("kotlin")
        "Java" shouldBeEqualTo "Java"
        "Kotlin" shouldNotBeEqualTo "Java"

        val androidLanguages = listOf("Kotlin", "Java", "C++")
        "Java" shouldBeIn androidLanguages
        "c++" shouldNotBeIn androidLanguages

        val foo = 3
        5 shouldBeGreaterOrEqualTo foo
    }
}


class ViewModel(private val stringProvider: StringProvider) {
    fun doSomething() {
        stringProvider.getString(2)
    }
}
private fun getRandomDigit() = Random.nextInt(10)