@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.javafx.extensions.parent

import csense.kotlin.annotations.threading.InUi
import csense.kotlin.extensions.collections.list.*
import csense.kotlin.extensions.toUnit
import javafx.scene.Node
import javafx.scene.layout.Pane

/**
 * Adds a given node to the "back" (first rendered, lowest z order) to this pane
 * @receiver Pane
 * @param node Node
 */
@InUi
inline fun Pane.addToBack(node: Node): Unit = children.add(0, node).toUnit()

/**
 * Adds a given node to the "top" (the latest view) order
 * @receiver Pane
 * @param node Node
 */
@InUi
inline fun Pane.addToFront(node: Node): Unit = children.add(node).toUnit()

/**
 * replaces all the children with the given nodes
 * @receiver Pane
 * @param nodes Collection<Node>
 * @return Unit
 */
@InUi
inline fun Pane.setChildren(nodes: Collection<Node>): Unit = children.setAll(nodes).toUnit()
@InUi
inline fun Pane.addAllToFront(nodes: Iterable<Node>) = children.addAll(nodes).toUnit()

@InUi
inline fun Pane.addAllToBack(nodes: Iterable<Node>) = children.addAll(0, nodes).toUnit()

@InUi
inline fun <T : Node> Pane.addToFrontF(view: T): T {
    addToFront(view)
    return view
}

/**
 * Adds the given child to this pane while returning it as well.
 * @receiver T
 * @param child Node
 * @return T
 */
@InUi
inline fun <T : Pane> T.addFluent(child: Node): T = this.apply {
    children.add(child)
}

/**
 * Removes a node
 * @receiver Pane
 * @param node Node
 */
@InUi
operator fun Pane.minusAssign(node: Node) = children.remove(node).toUnit()

/**
 * Addes a node
 * @receiver Pane
 * @param node Node
 */
@InUi
operator fun Pane.plusAssign(node: Node) = children.add(node).toUnit()

/**
 * Removes a given node
 * @receiver Pane
 * @param node Node
 * @return Boolean
 */
@InUi
fun Pane.remove(node: Node) = children.remove(node)

/**
 * Replaces the given pane with the second argument
 * @receiver Pane
 * @param toReplace Node
 * @param with Node
 */
@InUi
fun Pane.replace(toReplace: Node, with: Node) {
    children.replace(toReplace, with)
}

