package csense.javafx.extensions.geometry

import javafx.geometry.Bounds
import kotlin.math.max

/**
 * Chooses the biggest dimension of width and height
 * @receiver Bounds
 * @return Double
 */
fun Bounds.biggestWidthHeight(): Double = max(width, height)