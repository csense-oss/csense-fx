package csense.javafx.viewdsl

import csense.javafx.annotations.*
import javafx.geometry.*
import javafx.scene.*
import javafx.scene.layout.*

@InUI
fun Node.setMarginVBox(margin: Int) = setMarginVBox(margin.toDouble())

@InUI
fun Node.setMarginVBox(margin: Double) = setMarginVBox(Insets(margin))

@InUI
fun Node.setMarginVBox(margin: Insets) {
    VBox.setMargin(this, margin)
}

