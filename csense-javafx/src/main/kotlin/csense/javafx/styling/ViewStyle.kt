package csense.javafx.styling

import javafx.geometry.*
import javafx.scene.layout.*

fun Region.setPadding(padding: Int) = setPadding(padding.toDouble())

fun Region.setPadding(padding: Double) {
    this.padding = Insets(padding)
}

