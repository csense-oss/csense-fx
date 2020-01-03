package csense.javafx.extensions.listener

import csense.kotlin.EmptyFunction
import csense.kotlin.Function0
import javafx.beans.value.ChangeListener


fun <T> ChangeListenerNewValue(action: Function0<T>): ChangeListener<T> = ChangeListener { _, _, newValue ->
    action(newValue)
}

fun <T> ChangeListenerEmpty(action: EmptyFunction): ChangeListener<T> = ChangeListener { _, _, _ ->
    action()
}