package csense.javafx.css

import javafx.geometry.*
import javafx.scene.layout.*
import javafx.scene.paint.*


fun SingleBackgroundColor(
        color: Color,
        radii: CornerRadii? = null,
        insets: Insets? = null
): Background = Background(BackgroundFill(
        color,
        radii ?: CornerRadii.EMPTY,
        insets ?: Insets.EMPTY))

fun SingleBackgroundColor(
        color: Color,
        radiiAll: Double?,
        insetsAll: Double? = null
): Background = SingleBackgroundColor(
        color,
        radiiAll?.let { CornerRadii(it) },
        insetsAll?.let { Insets(it) }
)
