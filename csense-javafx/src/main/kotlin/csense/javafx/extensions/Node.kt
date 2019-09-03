package csense.javafx.extensions

import javafx.geometry.*
import javafx.scene.*
import javafx.scene.layout.*


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

fun Node.fillVBox() = VBox.setVgrow(this, Priority.ALWAYS)

fun Node.fillHBox() = HBox.setHgrow(this, Priority.ALWAYS)

fun Node.fillAnchorPane() {
    AnchorPane.setBottomAnchor(this, 0.0)
    AnchorPane.setTopAnchor(this, 0.0)
    AnchorPane.setLeftAnchor(this, 0.0)
    AnchorPane.setRightAnchor(this, 0.0)
}

fun Node.fillBorderPane() {
    BorderPane.setAlignment(this, Pos.CENTER)
    BorderPane.setMargin(this, Insets.EMPTY)
}

fun Node.fillGridPane() {
    GridPane.setFillHeight(this, true)
    GridPane.setFillWidth(this, true)
}

fun Node.fillFlowPane() {
    FlowPane.setMargin(this, Insets.EMPTY)
}

fun Node.fillStackPane() {
    StackPane.setAlignment(this, Pos.CENTER)
    StackPane.setMargin(this, Insets.EMPTY)
}

fun Node.fillTilePane() {
    TilePane.setAlignment(this, Pos.CENTER)
    TilePane.setMargin(this, Insets.EMPTY)
}

