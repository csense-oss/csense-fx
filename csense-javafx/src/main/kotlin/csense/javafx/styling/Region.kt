@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.styling

import csense.javafx.viewdsl.*
import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import javafx.geometry.*
import javafx.scene.layout.*

/**
 *
 * @receiver Region
 * @param padding Number
 */
@InUi
fun Region.setPadding(padding: Number) = setPadding(padding.toDouble())

/**
 *
 * @receiver Region
 * @param padding Double
 */
@InUi
fun Region.setPadding(padding: Double) {
    this.padding = Insets(padding)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUi
fun Region.setPaddingRight(value: Number) {
    this.padding = this.padding.withRight(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUi
fun Region.setPaddingLeft(value: Number) {
    this.padding = this.padding.withLeft(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUi
fun Region.setPaddingTop(value: Number) {
    this.padding = this.padding.withTop(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUi
fun Region.setPaddingBottom(value: Number) {
    this.padding = this.padding.withBottom(value)
}

data class RegionPadding(
        var left: Number = 0,
        var top: Number = 0,
        var right: Number = 0,
        var bottom: Number = 0
)

/**
 * Sets all sides with the given number
 */
inline var RegionPadding.all: Number
    @Deprecated("Should never use this", level = DeprecationLevel.HIDDEN)
    get() = left
    set(newValue) {
        left = newValue
        right = newValue
        top = newValue
        bottom = newValue
    }

/**
 * Sets left and right to the given number
 */
inline var RegionPadding.leftRight: Number
    @Deprecated("Should never use this", level = DeprecationLevel.HIDDEN)
    get() = left
    set(newValue) {
        left = newValue
        right = newValue
    }

/**
 * Sets top and bottom to the given number
 */
inline var RegionPadding.topBottom: Number
    @Deprecated("Should never use this", level = DeprecationLevel.HIDDEN)
    get() = left
    set(newValue) {
        top = newValue
        bottom = newValue
    }

/**
 * Converts this padding to an insets
 * @receiver RegionPadding
 * @return Insets
 */
inline fun RegionPadding.toInsets(): Insets = Insets(
        top.toDouble(),
        right.toDouble(),
        bottom.toDouble(),
        left.toDouble()
)

@InUi
inline fun Region.padding(configure: @InViewDsl ReceiverFunctionUnit<RegionPadding>) {
    padding = RegionPadding().apply(configure).toInsets()
}
