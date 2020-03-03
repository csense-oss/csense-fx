@file:Suppress("unused")

package csense.javafx.styling

import csense.javafx.extensions.scene.visibleProperty
import csense.kotlin.annotations.threading.*
import javafx.scene.*

/**
 * Hides the view
 * @receiver Node
 */
@InUi
fun Node.hide(): Unit = setVisibility(false)

/**
 * Shows / present the view
 * @receiver Node
 */
@InUi
fun Node.show(): Unit = setVisibility(true)

/**
 * Tells if this view is visible / shown
 */

inline val Node.isShown: Boolean
    @InUi
    inline get() = isVisible

/**
 * Tells if this view is invisible / not shown
 */

inline val Node.isNotShown: Boolean
    @InUi
    inline get() = !isShown

/**
 * Changes the visibility of this view
 * @receiver Node
 * @param visible Boolean if true it will be shown, false it will be hidden.
 */
@InUi
inline fun Node.setVisibility(visible: Boolean): Unit = visibleProperty.set(visible)
