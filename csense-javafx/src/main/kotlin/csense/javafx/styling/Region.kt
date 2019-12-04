package csense.javafx.styling

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



