package lesson02_properties_functions

// Operator Overloading
// https://kotlinlang.org/docs/operator-overloading.html#in-operator

// Operator overloading allows us to define the behavior of common operators such as +, -, *, etc
// We can define these behaviors for arbitrary types we control

enum class MenuItem {
    PIZZA, SALAD, BREADSTICKS, SODA, WATER, MARINARA
}

data class Order(val items: List<MenuItem>)

// We can overload an operator using both extension functions, or a member function

// Here, we'll define an extension to enable us to add 2 orders together to create a single order
operator fun Order.plus(order: Order): Order {
    return Order(this.items + order.items)
}

// We have access to many different operators including comparison, arithmetic, and equality opeartors
data class TestResult(val testId: String, val studentId: String, val score: Double) {
    operator fun compareTo(other: TestResult): Int {
        return this.score.compareTo(other.score)
    }
}

fun main() {
    val order1 = Order(listOf(MenuItem.PIZZA, MenuItem.PIZZA, MenuItem.SODA))
    val order2 = Order(listOf(MenuItem.PIZZA, MenuItem.SALAD, MenuItem.BREADSTICKS))

    val combinedOrder = order1 + order2
    println(combinedOrder.toString())

    val test1 = TestResult("123", "abc", 98.7)
    val test2 = TestResult("123", "def", 87.4)

    println(test1 > test2)
}