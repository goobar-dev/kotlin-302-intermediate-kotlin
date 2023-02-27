/**
 * Review the Description Tool Window for more
 * exercise details
 */

sealed class Result<T> {
    data class Success<T>(val value: T): Result<T>()
    data class Error<T>(val error: Throwable): Result<T>()
}

private fun <T> performAction(action: () -> T): Result<T> {
    return try {
        Result.Success(action())
    } catch (error: Throwable) {
        Result.Error(error)
    }
}

fun main() {
    performAction { loadData() }
}
