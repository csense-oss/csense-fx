@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.javafx.extensions.parent

import csense.kotlin.extensions.toUnit
import javafx.scene.Node
import javafx.scene.layout.Pane

/**
 * Adds a given node to the "back" (first rendered, lowest z order) to this pane
 * @receiver Pane
 * @param node Node
 */
fun Pane.addToBack(node: Node) = children.add(0, node).toUnit()

inline fun <T : Pane> T.addChildFluent(child: Node): T = this.apply {
    children.add(child)
}


operator fun Pane.minusAssign(node: Node) = children.remove(node).toUnit()

operator fun Pane.plusAssign(node: Node) = children.add(node).toUnit()
