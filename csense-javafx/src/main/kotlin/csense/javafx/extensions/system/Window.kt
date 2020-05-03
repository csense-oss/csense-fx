package csense.javafx.extensions.system

import csense.javafx.extensions.listener.*
import csense.javafx.extensions.stage.*
import csense.kotlin.*
import javafx.beans.value.*
import javafx.stage.*

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