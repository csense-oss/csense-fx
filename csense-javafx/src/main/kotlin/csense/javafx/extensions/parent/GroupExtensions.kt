@file:Suppress("NOTHING_TO_INLINE", "unused")


package csense.javafx.extensions.parent

import csense.kotlin.extensions.toUnit
import javafx.scene.Group
import javafx.scene.Node


//region Operators

operator fun Group.plusAssign(node: Node) = children.add(node).toUnit()

operator fun Group.minusAssign(node: Node) = children.remove(node).toUnit()

//endregion

inline fun <T : Group> T.addFluent(child: Node): T = this.apply {
    children.add(child)
}

fun Group.addToBack(node: Node) = children.add(0, node).toUnit()
