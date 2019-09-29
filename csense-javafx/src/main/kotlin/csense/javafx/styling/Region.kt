package csense.javafx.styling

import csense.javafx.annotations.*
import javafx.geometry.*
import javafx.scene.layout.*

/**
 *
 * @receiver Region
 * @param padding Number
 */
@InUI
fun Region.setPadding(padding: Number) = setPadding(padding.toDouble())

/**
 *
 * @receiver Region
 * @param padding Double
 */
@InUI
fun Region.setPadding(padding: Double) {
    this.padding = Insets(padding)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUI
fun Region.setPaddingRight(value: Number) {
    this.padding = this.padding.withRight(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUI
fun Region.setPaddingLeft(value: Number) {
    this.padding = this.padding.withLeft(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUI
fun Region.setPaddingTop(value: Number) {
    this.padding = this.padding.withTop(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
@InUI
fun Region.setPaddingBottom(value: Number) {
    this.padding = this.padding.withBottom(value)
}



