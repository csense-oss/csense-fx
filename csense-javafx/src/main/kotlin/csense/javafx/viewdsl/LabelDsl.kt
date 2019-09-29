package csense.javafx.viewdsl

import csense.javafx.annotations.*
import csense.javafx.extensions.parent.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*

/**
 *
 * @receiver Pane
 * @param title String
 * @param action [@kotlin.ExtensionFunctionType] Function1<[@csense.javafx.viewdsl.InViewDsl] Label, Unit>
 * @return Label
 */
@InUI
inline fun Pane.label(
        title: String,
        crossinline action: ScopedViewDsl<Label> = {}
): Label {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Label(title).apply(action).also {
        addToFront(it)
    }
}