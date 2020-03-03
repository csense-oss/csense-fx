package csense.javafx.extensions.system

import csense.javafx.extensions.listener.ChangeListenerNewValue
import csense.javafx.extensions.stage.focusedProperty
import csense.kotlin.EmptyFunction
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.stage.Window

fun Window.onWindowFocused(action: EmptyFunction): ChangeListener<Boolean> {
    return ChangeListenerNewValue { newValue: Boolean ->
        if (newValue) {
            action()
        }
    }.also {
        focusedProperty.addListener(it)
    }

}

fun Window.onWindowUnFocused(action: EmptyFunction): ChangeListener<Boolean> {
    return ChangeListenerNewValue { newValue: Boolean ->
        if (!newValue) {
            action()
        }
    }.also {
        focusedProperty.addListener(it)
    }
}