@file:Suppress("unused")

package csense.javafx.styling

import csense.kotlin.annotations.threading.*
import javafx.scene.*

/**
 * Hides the view
 * @receiver Node
 */
@InUi
fun Node.hide() {
    setVisibility(false)
}

/**
 * Shows / present the view
 * @receiver Node
 */
@InUi
fun Node.show() {
    setVisibility(true)
}

/**
 * Tells if this view is visible / shown
 */

val Node.isShown: Boolean
    @InUi
    get() = visibleProperty().value

/**
 * Tells if this view is invisible / not shown
 */

val Node.isNotShown: Boolean
    @InUi
    get() = !isShown

/**
 * Changes the visibility of this view
 * @receiver Node
 * @param visible Boolean if true it will be shown, false it will be hidden.
 */
@InUi
fun Node.setVisibility(visible: Boolean): Unit = visibleProperty().set(visible)