package csense.javafx.styling

import javafx.geometry.*
import javafx.scene.layout.*

/**
 *
 * @receiver Region
 * @param padding Number
 */
fun Region.setPadding(padding: Number) = setPadding(padding.toDouble())

/**
 *
 * @receiver Region
 * @param padding Double
 */
fun Region.setPadding(padding: Double) {
    this.padding = Insets(padding)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
fun Region.setPaddingRight(value: Number) {
    this.padding = this.padding.withRight(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
fun Region.setPaddingLeft(value: Number) {
    this.padding = this.padding.withLeft(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
fun Region.setPaddingTop(value: Number) {
    this.padding = this.padding.withTop(value)
}

/**
 *
 * @receiver Region
 * @param value Number
 */
fun Region.setPaddingBottom(value: Number) {
    this.padding = this.padding.withBottom(value)
}



