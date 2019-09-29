package csense.javafx.extensions

import csense.javafx.annotations.*
import javafx.geometry.*
import javafx.scene.*
import javafx.scene.layout.*

@InUI
fun Node.fillParent() {
    when (parent) {
        is VBox -> fillVBox()
        is HBox -> fillHBox()
        is AnchorPane -> fillAnchorPane()
        is BorderPane -> fillBorderPane()
        is GridPane -> fillGridPane()
        is FlowPane -> fillFlowPane()
        is StackPane -> fillStackPane()
        is TilePane -> fillTilePane()
    }
}

@InUI
fun Node.fillVBox() = VBox.setVgrow(this, Priority.ALWAYS)

@InUI
fun Node.fillHBox() = HBox.setHgrow(this, Priority.ALWAYS)

@InUI
fun Node.fillAnchorPane() {
    AnchorPane.setBottomAnchor(this, 0.0)
    AnchorPane.setTopAnchor(this, 0.0)
    AnchorPane.setLeftAnchor(this, 0.0)
    AnchorPane.setRightAnchor(this, 0.0)
}

@InUI
fun Node.fillBorderPane() {
    BorderPane.setAlignment(this, Pos.CENTER)
    BorderPane.setMargin(this, Insets.EMPTY)
}

@InUI
fun Node.fillGridPane() {
    GridPane.setFillHeight(this, true)
    GridPane.setFillWidth(this, true)
}

@InUI
fun Node.fillFlowPane() {
    FlowPane.setMargin(this, Insets.EMPTY)
}

@InUI
fun Node.fillStackPane() {
    StackPane.setAlignment(this, Pos.CENTER)
    StackPane.setMargin(this, Insets.EMPTY)
}

@InUI
fun Node.fillTilePane() {
    TilePane.setAlignment(this, Pos.CENTER)
    TilePane.setMargin(this, Insets.EMPTY)
}

