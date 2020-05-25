package csense.javafx.viewdsl

import csense.javafx.extensions.parent.*
import csense.kotlin.annotations.threading.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*


@InUi
inline fun Pane.scrollPaneVBox(
        @InUi crossinline action: ScopedViewDsl<VBox>
): ScrollPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(ScrollPane()).apply {
        val vbox = vBox { }
        content = vbox
        vbox.apply(action)
    }
}

@InUi
inline fun scrollPaneVBox(
        @InUi crossinline action: ScopedViewDsl<VBox>
): ScrollPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ScrollPane().apply {
        isFitToHeight = true
        isFitToWidth = true
        val vbox = vBox { }
        content = vbox
        vbox.apply(action)
    }
}