@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.javafx.extensions.parent

import csense.javafx.*
import csense.kotlin.extensions.collections.list.*
import csense.kotlin.extensions.collections.list.replace
import csense.kotlin.extensions.toUnit
import javafx.scene.Node
import javafx.scene.layout.Pane

/**
 * Adds a given node to the "back" (first rendered, lowest z order) to this pane
 * @receiver Pane
 * @param node Node
 */
inline fun Pane.addToBack(node: Node): Unit = children.add(0, node).toUnit()

/**
 * Adds a given node to the "top" (the latest view) order
 * @receiver Pane
 * @param node Node
 */
inline fun Pane.addToFront(node: Node): Unit = children.add(node).toUnit()

/**
 * Adds the given child to this pane while returning it as well.
 * @receiver T
 * @param child Node
 * @return T
 */
inline fun <T : Pane> T.addFluent(child: Node): T = this.apply {
    children.add(child)
}

/**
 * Removes a node
 * @receiver Pane
 * @param node Node
 */
operator fun Pane.minusAssign(node: Node) = children.remove(node).toUnit()

/**
 * Addes a node
 * @receiver Pane
 * @param node Node
 */
operator fun Pane.plusAssign(node: Node) = children.add(node).toUnit()

/**
 * Removes a given node
 * @receiver Pane
 * @param node Node
 * @return Boolean
 */
fun Pane.remove(node: Node) = children.remove(node)

/**
 * Replaces the given pane with the second argument
 * @receiver Pane
 * @param toReplace Node
 * @param with Node
 */
fun Pane.replace(toReplace: Node, with: Node) {
    children.replace(toReplace, with)
}

