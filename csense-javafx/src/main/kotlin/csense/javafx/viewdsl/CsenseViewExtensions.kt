package csense.javafx.viewdsl

import csense.kotlin.EmptyFunction
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


@UseExperimental(ExperimentalContracts::class)
inline fun Pane.button(
    text: String,
    crossinline onAction: EmptyFunction,
    crossinline action: ScopedViewDsl<Button> = {}
): Button {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Button(text).apply {
            setOnAction { onAction() }
            action(this)
        }
    }
}
