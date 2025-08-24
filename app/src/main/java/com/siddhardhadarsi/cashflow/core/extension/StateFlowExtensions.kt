package com.siddhardhadarsi.cashflow.core.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Combine multiple StateFlows
fun <T1, T2, R> StateFlow<T1>.combineWith(
    other: StateFlow<T2>,
    transform: (T1, T2) -> R
): StateFlow<R> = combine(this, other, transform).stateIn(
    scope = CoroutineScope(kotlinx.coroutines.Dispatchers.Main),
    started = SharingStarted.Eagerly,
    initialValue = transform(this.value, other.value)
)

// Map StateFlow with initial value
fun <T, R> StateFlow<T>.mapState(
    scope: CoroutineScope,
    transform: (T) -> R
): StateFlow<R> = map(transform).stateIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    initialValue = transform(value)
)

// Filter StateFlow with initial value check
fun <T> StateFlow<T>.filterState(
    scope: CoroutineScope,
    predicate: (T) -> Boolean
): StateFlow<T?> = filter(predicate).stateIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    initialValue = if (predicate(value)) value else null
)

// Debounce StateFlow updates
@OptIn(FlowPreview::class)
fun <T> StateFlow<T>.debounceState(
    scope: CoroutineScope,
    timeoutMillis: Long
): StateFlow<T> = debounce(timeoutMillis).stateIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    initialValue = value
)

// Throttle StateFlow updates (first wins)
fun <T> StateFlow<T>.throttleFirst(
    scope: CoroutineScope,
    periodMillis: Long
): StateFlow<T> {
    return flow {
        var lastEmissionTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmissionTime >= periodMillis) {
                lastEmissionTime = currentTime
                emit(value)
            }
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = value
    )
}

// Safe collect with error handling
fun <T> StateFlow<T>.safeCollect(
    scope: CoroutineScope,
    onError: (Throwable) -> Unit = {},
    onValue: (T) -> Unit
) {
    scope.launch {
        fun FlowCollector<T>.(exception: Throwable) {
            onError(exception)
            emptyFlow<T>()
        }
        this@safeCollect.collect(onValue)
    }
}