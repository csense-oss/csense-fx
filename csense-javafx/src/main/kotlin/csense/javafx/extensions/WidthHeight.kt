package csense.javafx.extensions

import javafx.geometry.Bounds
import javafx.scene.layout.Region

/**
 * Simple pair of width and height.
 * @property width Double
 * @property height Double
 * @constructor
 */
class WidthHeight(val width: Double, val height: Double) {
    companion object {
        val Zero = WidthHeight(0.0, 0.0)
        val UseComputed = WidthHeight(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE)
    }
}

fun Bounds.toWidthHeight(): WidthHeight = WidthHeight(width, height)