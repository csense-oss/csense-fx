package csense.javafx

import kotlinx.coroutines.CoroutineScope

typealias AsyncScopedEmptyFunctionResult<T> = suspend CoroutineScope.() -> T

typealias AsyncScopedFunctionResult<T, U> = suspend CoroutineScope.(T) -> U

typealias AsyncScopedFunctionUnit<T> = suspend CoroutineScope.(T) -> Unit

typealias AsyncScopedEmptyFunctionUnit = suspend CoroutineScope.() -> Unit
