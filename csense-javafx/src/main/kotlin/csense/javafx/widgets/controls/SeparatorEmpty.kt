package csense.javafx.widgets.controls

import csense.javafx.extensions.parent.*
import csense.javafx.viewdsl.*
import csense.kotlin.annotations.threading.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlin.contracts.*

class SeparatorEmpty: Separator() {
    init {
        styleClass.clear()
    }
}
@InUi
inline fun Pane.separatorEmpty(
        @InUi crossinline action: ScopedViewDsl<SeparatorEmpty>
): SeparatorEmpty {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            SeparatorEmpty()).apply(action)
}