package csense.javafx.extensions

import kotlinx.coroutines.*


fun <T> CoroutineScope.asyncDefault(
    action: suspend CoroutineScope.() -> T
): Deferred<T> = async(Dispatchers.Default, block = action)

fun <T> CoroutineScope.asyncIO(
    action: suspend CoroutineScope.() -> T
): Deferred<T> = async(Dispatchers.IO, block = action)

fun <T> CoroutineScope.asyncMain(
    action: suspend CoroutineScope.() -> T
): Deferred<T> = async(Dispatchers.Main, block = action)


fun CoroutineScope.launchDefault(
    action: suspend CoroutineScope.() -> Unit
): Job = launch(Dispatchers.Default, block = action)

fun CoroutineScope.launchIO(
    action: suspend CoroutineScope.() -> Unit
): Job = launch(Dispatchers.IO, block = action)

fun CoroutineScope.launchMain(
    action: suspend CoroutineScope.() -> Unit
): Job = launch(Dispatchers.Main, block = action)


suspend fun <T> CoroutineScope.withContextDefault(action: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.Default, action)

suspend fun <T> CoroutineScope.withContextMain(action: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.Default, action)

suspend fun <T> CoroutineScope.withContextBackground(action: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.Default, action)
