package lesson01_basics_overview

// Sealed Classes


// Sealed classes are used for representing restricted class hierarchies.

// A value can have a type from a limited set, similar to enums.
// Major difference is that with enums, you can only have a single instance of
// each type. Sealed classes allow you to have multiple instances of a type
// that can contain state.

// Direct children of sealed class must be in the same file.

sealed class ViewState {
    object Loading: ViewState()
    object Error: ViewState()
    data class Success(val items: List<Int>): ViewState()
}

fun render(state: ViewState) {
    when (state) {
        ViewState.Error -> {
            // render error state
        }
        ViewState.Loading -> {
            // render loading state
        }
        is ViewState.Success -> {
            // render loaded state
        }
    }
}

sealed class Result {
    data class Fail(val e: Exception): Result()
    data class Success(val i: Int): Result()
}