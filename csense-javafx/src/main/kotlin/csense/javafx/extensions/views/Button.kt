@file:Suppress("NOTHING_TO_INLINE", "unused", "EXPERIMENTAL_API_USAGE")

package csense.javafx.extensions.views

import csense.kotlin.annotations.threading.*
import csense.kotlin.*
import csense.kotlin.extensions.coroutines.*
import javafx.event.*
import javafx.scene.*
import javafx.scene.input.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
//TODO coroutines in UI is generally a bad idea (unless somehow you can solve the real problem of "is the UI still there" after any suspending)
@InUi
inline fun Node.setOnClickAsync(noinline action: AsyncFunctionUnit<MouseEvent>) {

    val eventActor = GlobalScope.actor<MouseEvent>(Dispatchers.Main, capacity = Channel.CONFLATED) {
        channel.forEach { action(it) }
    }

    onMouseClicked = EventHandler { event ->
        eventActor.offer(event)
    }
    //TODO touch ??? hm,mm
//    onTouchReleased = EventHandler {
//        eventActor.offer(it.)
//    }
}

@InUi
inline fun Node.setOnClickAsyncEmpty(noinline action: EmptyFunction) {
    @Suppress("RedundantLambdaArrow") //due to overload resolution.
    setOnClickAsync { _: MouseEvent -> action() }
}
