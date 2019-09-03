package csense.javafx.viewdsl

import csense.javafx.extensions.parent.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*


inline fun Pane.label(
        title: String,
        crossinline action: ScopedViewDsl<Label> = {}
) {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    addToFront(Label(title).apply(action))
}