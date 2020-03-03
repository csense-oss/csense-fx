@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.javafx.extensions.scene.paint

import csense.kotlin.annotations.numbers.DoubleLimit
import csense.kotlin.extensions.primitives.equalsWithin
import javafx.scene.paint.Color

/**
 * Returns a new color with the given opacity; unless the given color is of that opacity, then its just returned
 * @receiver Color
 * @param opacity Double
 * @return Color
 */
inline fun Color.withOpacity(@DoubleLimit(from = 0.0, to = 1.0) opacity: Double): Color {
    if (this.opacity.equalsWithin(opacity, 0.001)) { //within 0.1% ? then its the "same".
        return this
    }
    return Color.color(red, green, blue, opacity)
}