package csense.javafx.viewdsl

import csense.javafx.extensions.parent.addToFrontF
import csense.kotlin.annotations.threading.*
import csense.kotlin.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*


@InUi
inline fun Pane.button(
        text: String,
        crossinline onAction: EmptyFunction,
        crossinline action: ScopedViewDsl<Button> = {}
): Button {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(Button(text))
            .apply {
                setOnAction { onAction() }
                action(this)
            }
}

