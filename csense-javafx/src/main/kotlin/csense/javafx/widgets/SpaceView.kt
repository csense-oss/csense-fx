package csense.javafx.widgets

import javafx.geometry.*
import javafx.scene.layout.*

/**
 * A simple dummy view used for spacing.
 * @constructor
 */
class SpaceView(spacing: Double) : Region() {
    init {
        padding = Insets(spacing)
    }
}