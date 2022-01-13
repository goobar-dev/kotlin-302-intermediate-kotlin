package lesson05_dsls

// Domain Specific Languages
// https://kotlinlang.org/docs/type-safe-builders.html

// Example of a DSL for building HTML
//fun result() =
//    html {
//        head {
//            title {+"XML encoding with Kotlin"}
//        }
//        body {
//            h1 {+"XML encoding with Kotlin"}
//            p  {+"this format can be used as an alternative markup to XML"}
//
//            // an element with attributes and text content
//            a(href = "https://kotlinlang.org") {+"Kotlin"}
//        }
//    }

// By combining intuitively named top-level functions, function literals with receiver, infix functions,
// and operator overloading, we can build statically-typed, type-safe builders.
// These builders can be used to create intuitive DSLs


// When building a DSL, it's helpful to sketch out what the desired syntax looks like

// Let's imagine we want to build out a DSL for a pizza restaurant
// The final syntax could look something like this

//order {
//    customer {                   // configure the order customer
//        name = "Nate"
//        number = "123-456"
//    }
//
//    payment {                   // configure payment info
//        name = "Nate"
//        cardNumber = 123456789L
//    }
//
//    items {                     // configure the items in the order
//        +OrderItem.PIZZA
//        +OrderItem.BREADSTICKS
//    }
//}

// Next, we could consider what components we'll need to make this work
// An entry point function named order()
// Functions customer(), payment(), and items() on the order class
// Classes to represent Order, Customer, Payment, Order Items

data class Payment(var name: String = "", var cardNumber: Long = 0L)
data class Customer(var name: String = "", var number: String = "")
enum class OrderItem {
    PIZZA, SALAD, BREADSTICKS
}
class OrderItems {
    val items = mutableListOf<OrderItem>()

    operator fun OrderItem.unaryPlus() {
        items.add(this)
    }
}

// The Order class is our container for all other configurable objects
// It is also a secondary entry point for calling configuration functions
// Each property has a corresponding method that can be called to configure the data

class Order() {
    var customer: Customer = Customer()
    var payment: Payment = Payment()
    var items = OrderItems()

    fun customer(init: Customer.() -> Unit) {
        customer = Customer().apply { init() }
    }

    fun payment(init: Payment.() -> Unit) {
        payment = Payment().apply { init() }
    }

    fun items(init: OrderItems.() -> Unit) {
        items = OrderItems().apply { init() }
    }
}

// Order is our entry point function
// It creates an instance of Order, and then passes it as the receiver to the init function
// That init function is what the call-site code will use to configure the order

fun order(init: Order.() -> Unit): Order {
  val order = Order()
  order.init()
  return order
}

val order = order {
    customer {
        name = "Nate"
        number = "123-456"
    }

    payment {
        name = "Nate"
        cardNumber = 123456789L
    }

    items {
        +OrderItem.PIZZA
        +OrderItem.BREADSTICKS
    }
}

fun main() {
    println(order.customer.toString())
    println(order.payment.toString())
    println(order.items.items.toString())
}
