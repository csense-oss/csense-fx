package csense.javafx.extensions.scene.control

import csense.javafx.extensions.listener.ChangeListenerEmpty
import csense.javafx.extensions.listener.ChangeListenerNewValue
import csense.kotlin.EmptyFunction
import csense.kotlin.annotations.threading.InAny
import javafx.beans.value.ChangeListener
import javafx.scene.control.TextInputControl
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor


fun TextInputControl.onTextChangedNew(action: (newText: String) -> Unit): ChangeListener<String> =
        ChangeListenerNewValue(action).also { textProperty.addListener(it) }

fun TextInputControl.onTextChanged(action: EmptyFunction): ChangeListener<String> =
        ChangeListenerEmpty<String>(action).also { textProperty.addListener(it) }


@OptIn(ObsoleteCoroutinesApi::class)
fun TextInputControl.onTextChangedConflated(@InAny action: suspend () -> Unit): SendChannel<Unit> {
    //TODO globalscope !?? not a good idea perhaps.
    val eventActor = GlobalScope.actor<Unit>(Dispatchers.Main, capacity = Channel.CONFLATED) {
        for (event in channel) action() // pass event to action
    }
    onTextChanged {
        eventActor.offer(Unit)
    }
    return eventActor
}