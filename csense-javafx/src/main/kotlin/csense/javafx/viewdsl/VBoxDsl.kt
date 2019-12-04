package csense.javafx.viewdsl

import csense.kotlin.annotations.threading.*
import javafx.geometry.*
import javafx.scene.*
import javafx.scene.layout.*

@InUi
fun Node.setMarginVBox(margin: Int) = setMarginVBox(margin.toDouble())

@InUi
fun Node.setMarginVBox(margin: Double) = setMarginVBox(Insets(margin))

@InUi
fun Node.setMarginVBox(margin: Insets) {
    VBox.setMargin(this, margin)
}

