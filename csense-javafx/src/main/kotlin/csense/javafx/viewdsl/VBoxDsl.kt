package csense.javafx.viewdsl

import javafx.geometry.*
import javafx.scene.*
import javafx.scene.layout.*

fun Node.setMarginVBox(margin: Int) = setMarginVBox(margin.toDouble())

fun Node.setMarginVBox(margin: Double) = setMarginVBox(Insets(margin))

fun Node.setMarginVBox(margin: Insets) {
    VBox.setMargin(this, margin)
}

