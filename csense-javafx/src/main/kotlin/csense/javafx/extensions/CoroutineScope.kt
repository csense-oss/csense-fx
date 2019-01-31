package csense.javafx.extensions

import kotlinx.coroutines.*

fun <T> CoroutineScope.asyncIO(
    action: suspend CoroutineScope.() -> T
): Deferred<T> = async(Dispatchers.IO, block = action)

fun CoroutineScope.launchIO(
    action: suspend CoroutineScope.() -> Unit
): Job = launch(Dispatchers.IO, block = action)

