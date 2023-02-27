/**
 * Review the Description Tool Window for more
 * exercise details
 */

data class AnalyticsEvent(
    val name: String,
    val properties: MutableMap<String, Any> = mutableMapOf()
)

// Create an extension function on the AnalyticsEvent class
// The function should be named track
// The function should take a single function parameter named init
// that has AnalyticsEvent as a receiver and returns Unit
fun AnalyticsEvent.track(init: AnalyticsEvent.() -> Unit) {
    this.init()
}

fun main() {
    val event = AnalyticsEvent("App Opened")
    event.track {
        properties["id"] = "123"
        properties["timestamp"] = System.currentTimeMillis()
    }
}
