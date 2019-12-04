@file:Suppress("NOTHING_TO_INLINE", "unused")


package csense.javafx.extensions.parent

import csense.kotlin.annotations.threading.*
import csense.kotlin.extensions.toUnit
import javafx.scene.Group
import javafx.scene.Node


//region Operators
@InUi
operator fun Group.plusAssign(node: Node) = children.add(node).toUnit()
@InUi
operator fun Group.minusAssign(node: Node) = children.remove(node).toUnit()

//endregion
@InUi
inline fun <T : Group> T.addFluent(child: Node): T = this.apply {
    children.add(child)
}
@InUi
fun Group.addToBack(node: Node) = children.add(0, node).toUnit()
