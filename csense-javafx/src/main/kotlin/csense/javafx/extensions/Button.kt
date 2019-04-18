@file:Suppress("NOTHING_TO_INLINE", "unused", "EXPERIMENTAL_API_USAGE")

package csense.javafx.extensions

import csense.kotlin.*
import csense.kotlin.extensions.coroutines.*
import javafx.event.*
import javafx.scene.*
import javafx.scene.input.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import sun.invoke.empty.Empty

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

inline fun Node.setOnClickAsyncEmpty(noinline action: EmptyFunction) {
    @Suppress("RedundantLambdaArrow") //due to overload resolution.
    setOnClickAsync { _: MouseEvent -> action() }
}
