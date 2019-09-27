package csense.javafx

import csense.kotlin.extensions.collections.list.*
import kotlinx.coroutines.*

fun <T> MutableList<T>.replace(toReplace: T, withItem: T) =
        replace(withItem, indexOf(toReplace))


//TODO csense ?
suspend fun Array<out Job>.joinAll() {
    forEach { it.join() }
}
