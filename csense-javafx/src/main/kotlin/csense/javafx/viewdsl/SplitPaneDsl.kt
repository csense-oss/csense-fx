@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.javafx.viewdsl

import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.toUnit
import javafx.scene.Node
import javafx.scene.control.SplitPane
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@InUi
inline fun SplitPane.anchorPane(
        crossinline action: @InViewDsl ScopedViewDsl<AnchorPane>
): AnchorPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addViewF(AnchorPane()).apply(action)
}

@InUi
inline fun SplitPane.vBox(
        crossinline action: @InViewDsl ScopedViewDsl<VBox>
): VBox{
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addViewF(VBox()).apply(action)
}


inline fun SplitPane.addView(node: Node): Unit =
        items.add(node).toUnit()

inline fun <T : Node> SplitPane.addViewF(node: T): T {
    items.add(node)
    return node
}