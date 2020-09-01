package csense.javafx.widgets.input

import csense.javafx.extensions.parent.*
import csense.javafx.viewdsl.*
import csense.kotlin.annotations.threading.*
import javafx.scene.layout.*
import kotlin.contracts.*

/**
 * An unstyled textfield
 */
open class TextFieldEmpty : TextFieldStyleable() {
    init {
        styleClass.clear()
    }
}

@InUi
inline fun Pane.textFieldEmpty(
        crossinline action: ScopedViewDsl<TextFieldEmpty>
): TextFieldEmpty {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TextFieldEmpty()).apply(action)
}
