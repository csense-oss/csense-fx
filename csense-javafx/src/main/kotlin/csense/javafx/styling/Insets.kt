@file:Suppress("unused")

package csense.javafx.styling

import csense.kotlin.annotations.threading.*
import javafx.geometry.*


/**
 *
 * @receiver Insets
 * @param top Number
 * @return Insets
 */
fun Insets.withTop(top: Number): Insets = copy(top = top.toDouble())

/**
 *
 * @receiver Insets
 * @param right Number
 * @return Insets
 */
fun Insets.withRight(right: Number): Insets = copy(right = right.toDouble())

/**
 *
 * @receiver Insets
 * @param left Number
 * @return Insets
 */
fun Insets.withLeft(left: Number): Insets = copy(left = left.toDouble())

/**
 *
 * @receiver Insets
 * @param bottom Number
 * @return Insets
 */
fun Insets.withBottom(bottom: Number): Insets = copy(bottom = bottom.toDouble())

/**
 *
 * @receiver Insets
 * @param top Number
 * @param right Number
 * @param left Number
 * @param bottom Number
 * @return Insets
 */
fun Insets.copy(
        top: Number = this.top,
        right: Number = this.right,
        left: Number = this.left,
        bottom: Number = this.bottom
): Insets = copy(top.toDouble(), right.toDouble(), left.toDouble(), bottom.toDouble())

/**
 *
 * @receiver Insets
 * @param top Double
 * @param right Double
 * @param left Double
 * @param bottom Double
 * @return Insets
 */
fun Insets.copy(
        top: Double = this.top,
        right: Double = this.right,
        left: Double = this.left,
        bottom: Double = this.bottom
): Insets {
    return Insets(top, right, bottom, left)
}