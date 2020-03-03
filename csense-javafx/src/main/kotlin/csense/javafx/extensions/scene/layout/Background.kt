package csense.javafx.extensions.scene.layout

import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.paint.Paint

/**
 *
 * @receiver Background
 * @param fillPaint Paint
 * @return Background
 */
fun Background.withFillOf(fillPaint: Paint): Background {
    val mapped = fills.map { BackgroundFill(fillPaint, it.radii, it.insets) }
    return Background(
            mapped, emptyList()
    )
}
