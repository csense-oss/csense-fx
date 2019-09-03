package csense.javafx.css

import javafx.geometry.*
import javafx.scene.layout.*
import javafx.scene.paint.Color


fun SingleBackgroundColor(
        color: Color,
        radii: CornerRadii? = null,
        insets: Insets? = null
) = Background(BackgroundFill(
        color,
        radii ?: CornerRadii.EMPTY,
        insets ?: Insets.EMPTY))