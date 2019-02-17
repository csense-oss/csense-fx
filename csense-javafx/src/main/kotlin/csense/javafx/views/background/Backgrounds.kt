@file:Suppress("MemberVisibilityCanBePrivate")

package csense.javafx.views.background

import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color

/**
 * TODO write me
 */
object Backgrounds {

    /**
     * Flat no corner, no insets transparent background
     */
    val transparentBackgroundFill: BackgroundFill by lazy {
        BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)
    }

    /**
     * Wraps the transparent background fill in a background object.
     */
    val transparentBackground: Background by lazy {
        transparentBackgroundFill.background()
    }
}

/**
 * Wraps the given fill in a background.
 * This should be cached as it is immutable and thus can be reused.
 *
 */
fun BackgroundFill.background(): Background = Background(this)
