package csense.javafx.extensions.scene

import csense.kotlin.annotations.threading.*
import csense.kotlin.logger.*
import javafx.geometry.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*

@InUi
fun Node.fillParent() {
    if (parent == null) {
        L.debug("Node.fillParent", "Null parent")
        return
    }
    when (parent) {
        is VBox -> fillVBox()
        is HBox -> fillHBox()
        is AnchorPane -> fillAnchorPane()
        is BorderPane -> fillBorderPane()
        is GridPane -> fillGridPane()
        is FlowPane -> fillFlowPane()
        is StackPane -> fillStackPane()
        is TilePane -> fillTilePane()
        else -> L.debug("Node.fillParent", "Failed to fill parent, unknown type; type is = " + parent.javaClass.simpleName)
    }
}

@InUi
fun Node.fillSplitPane() = SplitPane.setResizableWithParent(this, true)


@InUi
fun Node.fillVBox() = VBox.setVgrow(this, Priority.ALWAYS)

@InUi
fun Node.fillHBox() = HBox.setHgrow(this, Priority.ALWAYS)

@InUi
fun Node.fillAnchorPane() {
    AnchorPane.setBottomAnchor(this, 0.0)
    AnchorPane.setTopAnchor(this, 0.0)
    AnchorPane.setLeftAnchor(this, 0.0)
    AnchorPane.setRightAnchor(this, 0.0)
}

@InUi
fun Node.fillBorderPane() {
    BorderPane.setAlignment(this, Pos.CENTER)
    BorderPane.setMargin(this, Insets.EMPTY)
}

@InUi
fun Node.fillGridPane() {
    GridPane.setFillHeight(this, true)
    GridPane.setFillWidth(this, true)
}

@InUi
fun Node.fillFlowPane() {
    FlowPane.setMargin(this, Insets.EMPTY)
}

@InUi
fun Node.fillStackPane() {
    StackPane.setAlignment(this, Pos.CENTER)
    StackPane.setMargin(this, Insets.EMPTY)
}

@InUi
fun Node.fillTilePane() {
    TilePane.setAlignment(this, Pos.CENTER)
    TilePane.setMargin(this, Insets.EMPTY)
}


////region Cursor extensions
//fun Node.cursorHand() {
//    cursor = Cursor.HAND
//}
////endregion


val Node.isNotPressed: Boolean get() = !isPressed


var Node.isEnable: Boolean
    get() = !isDisable
    set(newValue) {
        isDisable = !newValue
    }