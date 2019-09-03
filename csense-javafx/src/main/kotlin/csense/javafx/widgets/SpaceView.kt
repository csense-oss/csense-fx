package csense.javafx.widgets

import javafx.geometry.*
import javafx.scene.layout.*

class SpaceView(spacing: Double) : Region() {
    init {
        padding = Insets(spacing)
    }
}